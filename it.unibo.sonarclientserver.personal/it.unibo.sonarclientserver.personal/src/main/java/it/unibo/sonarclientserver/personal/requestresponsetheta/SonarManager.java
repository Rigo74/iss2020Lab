package it.unibo.sonarclientserver.personal.requestresponsetheta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Optional;

import it.unibo.sonarclientserver.personal.commons.Message;

public class SonarManager extends Thread {
	
	private final BufferedReader sonarInputStream;
	private final OutputStream radarOutputStream;
	private final BufferedReader radarInputStream;
	private final Runnable onEnd;
	private boolean hasToWork = true;
	private State state = State.WORK;
	private Optional<String> sonarDistance = Optional.empty();
	private Optional<String> sonarTheta = Optional.empty();
	
	public SonarManager(final InputStream sonarInputStream, 
			final OutputStream radarOutputStream,
			final InputStream radarInputStream, 
			final Runnable onEnd) {
		this.sonarInputStream = new BufferedReader(new InputStreamReader(sonarInputStream));
		this.radarOutputStream = radarOutputStream;
		this.radarInputStream = new BufferedReader(new InputStreamReader(radarInputStream));
		this.onEnd = onEnd;
	}
	
	@Override
	public void run() {
		while(hasToWork) {
			switch (state) {
				case WORK: work(); break;
				case SEND_DISTANCE: sendDistance(); break;
				case WAIT_REPLY: waitReply(); break;
				case SEND_THETA: sendTheta(); break;
				case WAIT_ACK: waitAck(); break;
				case STOP: stopWork(); break;
				default: break;
			}
		}
	}
	
	private void work() {
		receiveMessage(sonarInputStream).ifPresent(sonarData -> {
			final String[] datas = sonarData.split("-");
			if (datas.length == 2) {
				sonarDistance = Optional.ofNullable(datas[0]);
				sonarTheta = Optional.ofNullable(datas[1]);
				state = State.SEND_DISTANCE;
			}
		});
	}
	
	private void sendDistance() {
		sonarDistance.ifPresent(this::sendMessage);
		state = State.WAIT_REPLY;
	}
	
	private void waitReply() {
		receiveMessage(radarInputStream).ifPresent(reply -> {
			if (Message.REQUEST_THETA.getMessage().equals(reply))
				state = State.SEND_THETA;
			else if (Message.ACK.getMessage().equals(reply))
				state = State.WORK;
		});
	}
	
	private void sendTheta() {
		sonarTheta.ifPresent(this::sendMessage);
		state = State.WAIT_ACK;
	}
	
	private void waitAck() {
		receiveMessage(radarInputStream).ifPresent(reply -> {
			if (Message.ACK.getMessage().equals(reply))
				state = State.WORK;
		});
	}
	
	private Optional<String> receiveMessage(final BufferedReader in) {
		try {
			return Optional.ofNullable(in.readLine());
		} catch (IOException e) {
			System.err.println("ERROR: Message cannot be received.");
			state = State.STOP;
			return Optional.empty();
		}
	}
	
	private void sendMessage(final String data) {
		try {
			radarOutputStream.write((data + "\n").getBytes());
		} catch (IOException e) {
			System.err.println("ERROR: Message cannot be sent.");
			state = State.STOP;
		}
	}
	
	private void stopWork() {
		hasToWork = false;
		try {
			sonarInputStream.close();
			radarOutputStream.close();
			onEnd.run();
		} catch (IOException e) {
			onEnd.run();
		}
	}
	
	private enum State {
		WORK,SEND_DISTANCE,WAIT_REPLY,SEND_THETA,WAIT_ACK,STOP
	}
}
