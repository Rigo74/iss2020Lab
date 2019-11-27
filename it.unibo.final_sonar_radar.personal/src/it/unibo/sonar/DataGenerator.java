package it.unibo.sonar;

import java.util.Random;

public class DataGenerator {
	
	public static void main(String[] args) {
		try {
			while(true) {
				System.out.println(new Random().nextInt() % 50);
				Thread.sleep(1000);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
