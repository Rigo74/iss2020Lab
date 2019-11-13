package it.unibo.sonarclientserver.personal.fireforget;

import java.net.Socket;

public class SonarEntryPoint {

	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 6789;
	private static final String EXEC_CMD = "java -cp bin/main it.unibo.sonarclientserver.personal.fireforget.SonarSimulator";
	
	public static void main(String[] args) {
		try {
			final Socket radarServer = new Socket(SERVER_HOST, SERVER_PORT);
			final Process sonar = Runtime.getRuntime().exec(EXEC_CMD);
			new SonarManager(sonar.getInputStream(), radarServer.getOutputStream()).start();
		} catch (Exception e) {
			System.err.println("Error initializing the program.");
			System.exit(1);
		}
	}

}
