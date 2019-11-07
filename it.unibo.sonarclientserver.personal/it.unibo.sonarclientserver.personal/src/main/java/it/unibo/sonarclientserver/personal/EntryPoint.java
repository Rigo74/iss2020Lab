package it.unibo.sonarclientserver.personal;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class EntryPoint {

	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 6789;
	private static final String EXEC_CMD = "java SonarSimulator";
	
	public static void main(String[] args) {
		System.out.println("starting client");
		try {
			final Socket radarSerever = new Socket(SERVER_HOST, SERVER_PORT);
			System.out.println("connection established");
			final Process sonar = Runtime.getRuntime().exec(EXEC_CMD);
			final SonarManager sonarManager = new SonarManager(sonar.getInputStream(), radarSerever.getOutputStream());
			sonarManager.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
