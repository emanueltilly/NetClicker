package Transmitter;

import java.util.Random;

public class random {
	
	public static String getMessageID() {
		Random dice = new Random();
		int number;
		String returnMe = "";
		
		for(int counter=1; counter<=10;counter++) {
			number = dice.nextInt(10);
			returnMe = (returnMe + number);
		}
		System.out.println("Random number: " + returnMe);
		return returnMe;
		
	}
	
	public static String getSessionID() {
		Random dice = new Random();
		int number;
		String returnMe = "";
		
		for(int counter=1; counter<=4;counter++) {
			number = dice.nextInt(10);
			returnMe = (returnMe + number);
		}
		System.out.println("Random number: " + returnMe);
		return returnMe;
		
	}

}
