package it.unibo.sonarclientserver.personal.requestresponse;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SonarEntryPoint {

	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 6789;
	private static final String EXEC_CMD = "java -cp bin/main it.unibo.sonarclientserver.personal.simulator.SonarSimulator";
	
	public void start() throws UnknownHostException, IOException {
		final Socket radarServer = new Socket(SERVER_HOST, SERVER_PORT);
		final Process sonar = Runtime.getRuntime().exec(EXEC_CMD);
		new SonarManager(sonar.getInputStream(), radarServer.getOutputStream(), () -> this.onConnectionEnd(radarServer)).start();
	}
	
	private void onConnectionEnd(final Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		try {
			new SonarEntryPoint().start();
		} catch (Exception e) {
			System.err.println("Error initializing the program.");
			System.exit(1);
		}
	}

}
