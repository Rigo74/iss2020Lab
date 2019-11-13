package it.unibo.sonarclientserver.personal.requestresponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Optional;

public class SonarManager extends Thread {
	
	private final BufferedReader sonarInputStream;
	private final OutputStream radarOutputStream;
	private final Runnable onEnd;
	private boolean hasToWork = true;
	private State state = State.WORK;
	private Optional<String> sonarDistance = Optional.empty();
	
	public SonarManager(final InputStream sonarInputStream, final OutputStream radarOutputStream, final Runnable onEnd) {
		this.sonarInputStream = new BufferedReader(new InputStreamReader(sonarInputStream));
		this.radarOutputStream = radarOutputStream;
		this.onEnd = onEnd;
	}
	
	@Override
	public void run() {
		while(hasToWork) {
			switch (state) {
				case WORK: work(); break;
				case SEND_DISTANCE: sendDistance(); break;
				case STOP: stopWork(); break;
				default: break;
			}
		}
	}
	
	private void work() {
		sonarDistance = receiveMessage();
		state = State.SEND_DISTANCE;
	}
	
	private void sendDistance() {
		sonarDistance.ifPresent(this::sendMessage);
		state = State.WORK;
	}
	
	private Optional<String> receiveMessage() {
		try {
			return Optional.ofNullable(sonarInputStream.readLine());
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
		WORK,SEND_DISTANCE,STOP
	}
}
