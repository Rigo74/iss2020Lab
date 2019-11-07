package it.unibo.sonarclientserver.personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SonarManager extends Thread {
	
	private final BufferedReader sonarInputStream;
	private final OutputStream radarOutputStream;
	private final boolean hasToWork = true;
	
	public SonarManager(final InputStream sonarInputStream, final OutputStream radarOutputStream) {
		this.sonarInputStream = new BufferedReader(new InputStreamReader(sonarInputStream));
		this.radarOutputStream = radarOutputStream;
	}
	
	@Override
	public void run() {
		while(hasToWork) {
			//try {
				sonarInputStream.lines().forEach(d -> {
					try {
						System.out.println(d);
						radarOutputStream.write((d + "\n").getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				/*final String data = sonarInputStream.readLine();
				System.out.println(data);
				radarOutputStream.write((data + "\n").getBytes());*/
			/*} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
	
}
