package it.unibo.sonarclientserver.personal.commons;

import java.util.Random;

public class SonarSimulator {
	
	public static void main(String[] args) {
		final Random rand = new Random();
		while(true) {
			try {
				Thread.sleep(500);
				int distance = rand.nextInt(100);
				int theta = rand.nextInt(360);
				System.out.println(distance + "-" + theta);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}
}
