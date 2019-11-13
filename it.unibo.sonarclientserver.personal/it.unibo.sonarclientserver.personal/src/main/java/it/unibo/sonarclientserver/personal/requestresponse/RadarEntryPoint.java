package it.unibo.sonarclientserver.personal.requestresponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RadarEntryPoint {
	
	private final int port;
	
	public RadarEntryPoint(final int port) {
		radarPojo.radarSupport.setUpRadarGui();
		this.port = port;
	}
	
	public void start() throws Exception {
		final ServerSocket welcomeSocket = new ServerSocket(port);
		while(true) {
			final Socket connectionSocket = welcomeSocket.accept();
			new RadarServer(
				connectionSocket.getInputStream(), 
				connectionSocket.getOutputStream(), 
				() -> this.onConnectionEnd(connectionSocket)
			).start();
		}
	}
	
	private void onConnectionEnd(final Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {}
	}
	
	public static void main(String argv[]) throws Exception {
		new RadarEntryPoint(6789).start();
	}
	
}
