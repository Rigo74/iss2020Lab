package it.unibo.sonarclientserver.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class RadarServer {
	
	private final int port;
	private final String name; 
	
	public RadarServer(final String name, final int port) {
		radarPojo.radarSupport.setUpRadarGui();
		this.name = name;
		this.port = port;
	}
	
	public void start() throws Exception {
		final ServerSocket welcomeSocket = new ServerSocket(port);
		while(true) {
			final Socket connectionSocket = welcomeSocket.accept();
			new Thread(() -> {
				System.out.println("connected");
				boolean work = true;
				try {
					final BufferedReader inFromClient = new BufferedReader(
							new InputStreamReader(connectionSocket.getInputStream())
					);
					while(work) {
						final String message = inFromClient.readLine();
						radarPojo.radarSupport.update(Double.parseDouble(message)+"", "0.0");
					}
				} catch (final Exception e) {
					System.err.println(e.getMessage());
					work = false;
				}
			}).start();
		}
	}
	
	public static void main(String argv[]) throws Exception {
		new RadarServer("radar", 6789).start();
	}
	
}
