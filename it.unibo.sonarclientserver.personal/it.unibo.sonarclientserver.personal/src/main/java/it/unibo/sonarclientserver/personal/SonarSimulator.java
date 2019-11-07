package it.unibo.sonarclientserver.personal;

import java.util.Random;

public class SonarSimulator {
	
	public static void main(String[] args) {
		while(true) {
			try {
				Thread.sleep(1000);
				System.out.println(new Random().nextInt() % 50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
