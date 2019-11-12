package it.unibo.sonarclientserver.personal.fireforget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SonarManager extends Thread {
	
	private final BufferedReader sonarInputStream;
	private final OutputStream radarOutputStream;
	private boolean hasToWork = true;
	
	public SonarManager(final InputStream sonarInputStream, final OutputStream radarOutputStream) {
		this.sonarInputStream = new BufferedReader(new InputStreamReader(sonarInputStream));
		this.radarOutputStream = radarOutputStream;
	}
	
	@Override
	public void run() {
		while(hasToWork) {
			final String data = receiveMessage();
			if (!data.isEmpty())
				sendMessage(data);
		}
	}
	
	private String receiveMessage() {
		try {
			return sonarInputStream.readLine();
		} catch (IOException e) {
			System.err.println("ERROR: Message cannot be received.");
			stopWork();
			return "";
		}
	}
	
	private void sendMessage(final String data) {
		try {
			radarOutputStream.write((data + "\n").getBytes());
		} catch (IOException e) {
			System.err.println("ERROR: Message cannot be sent.");
			stopWork();
		}
	}
	
	private void stopWork() {
		hasToWork = false;
		try {
			sonarInputStream.close();
			radarOutputStream.close();
		} catch (IOException e) {}
	}
}
