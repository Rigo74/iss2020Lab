package it.unibo.sonarclientserver.personal.requestresponse;

import java.util.Random;

public class SonarSimulator {
	
	public static void main(String[] args) {
		final Random rand = new Random();
		while(true) {
			try {
				Thread.sleep(500);
				int value = rand.nextInt() % 100;
				System.out.println(value < 0 ? -value : value);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}
}
