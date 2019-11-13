package it.unibo.sonarclientserver.personal.fireforget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		new RadarEntryPoint(6789).start();
	}
	
}
