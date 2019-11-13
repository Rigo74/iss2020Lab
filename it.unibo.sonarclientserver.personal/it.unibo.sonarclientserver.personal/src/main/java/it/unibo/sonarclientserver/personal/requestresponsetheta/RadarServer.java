package it.unibo.sonarclientserver.personal.requestresponsetheta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Optional;

import it.unibo.sonarclientserver.personal.commons.Message;

public class RadarServer extends Thread {
	
	private final BufferedReader sonarInputStream;
	private final OutputStream sonarOutputStream;
	private final Runnable onEnd;
	private boolean hasToWork = true;
	private Optional<String> sonarDistance = Optional.empty();
	private Optional<String> sonarTheta = Optional.empty();
	private State state = State.WORK;
	
	public RadarServer(final InputStream sonarInputStream, final OutputStream sonarOutputStream, final Runnable onEnd) {
		this.sonarInputStream = new BufferedReader(new InputStreamReader(sonarInputStream));
		this.sonarOutputStream = sonarOutputStream;
		this.onEnd = onEnd;
	}
	
	@Override
	public void run() {
		while(hasToWork)
			switch (state) {
				case WORK: work(); break;
				case REQUEST_THETA: requestTheta(); break;
				case UPDATE_POJO: updatePojo(); break;
				case REPLY: reply(); break;
				case STOP: stopWork(); break;
				default: break;
			}
	}
	
	private void work() {
		sonarDistance = receiveMessage();
		state = State.REQUEST_THETA;
	}
	
	private void requestTheta() {
		sendMessage(Message.REQUEST_THETA.getMessage());
		sonarTheta = receiveMessage();
		state = State.UPDATE_POJO;
	}
	
	private void updatePojo() {
		sonarDistance.ifPresent(distance -> sonarTheta.ifPresent(theta -> 
			radarPojo.radarSupport.update(
					String.valueOf(Double.parseDouble(distance)),
					String.valueOf(Double.parseDouble(theta)))));
		state = State.REPLY;
	}
	
	private void reply() {
		sendMessage(Message.ACK.getMessage());
		state = State.WORK;
	}
	
	private Optional<String> receiveMessage() {
		try {
			return Optional.ofNullable(sonarInputStream.readLine());
		} catch (IOException e) {
			state = State.STOP;
			return Optional.empty();
		}
	}
	
	private void sendMessage(final String message) {
		try {
			sonarOutputStream.write((message + "\n").getBytes());
		} catch (IOException e) {
			state = State.STOP;
		}
	}
	
	private void stopWork() {
		hasToWork = false;
		try {
			sonarInputStream.close();
			sonarOutputStream.close();
			onEnd.run();
		} catch (IOException e) {
			onEnd.run();
		}
	}
	
	private enum State {
		WORK,REQUEST_THETA,UPDATE_POJO,REPLY,STOP
	}

}

