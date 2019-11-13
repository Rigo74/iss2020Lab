package it.unibo.sonarclientserver.personal.requestresponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Optional;

public class RadarServer extends Thread {
	
	private static final String THETA = "0.0";
	private static final String ACK_MESSAGE = "ok";
	private final BufferedReader sonarInputStream;
	private final OutputStream sonarOutputStream;
	private final Runnable onEnd;
	private boolean hasToWork = true;
	private Optional<String> sonarDistance = Optional.empty();
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
				case UPDATE_POJO: updatePojo(); break;
				case REPLY: reply(); break;
				case STOP: stopWork(); break;
				default: break;
			}
	}
	
	private void work() {
		sonarDistance = receiveMessage();
		state = State.UPDATE_POJO;
	}
	
	private void updatePojo() {
		sonarDistance.ifPresent(distance -> 
			radarPojo.radarSupport.update(String.valueOf(Double.parseDouble(distance)), THETA));
		state = State.REPLY;
	}
	
	private void reply() {
		sendMessage(ACK_MESSAGE);
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
		WORK,UPDATE_POJO,REPLY,STOP
	}

}
