/*This class gets string messages such as next and back, checking them and telling the Network manager to send the packets.*/

package Transmitter;

import java.util.concurrent.Executors;

public class clicker {
	
	private static int sendRedundantMessages = 5;		//This is how many redundant UDP packets will be sent to each reciver
	private static String nextMessage = "next";
	private static String backMessage = "back";
	private static String blackMessage = "black";
	private static String startMessage = "start";
	private static String exitMessage = "exit";
	
	public static void sendClick(String clickType) {
		String sendMessage = "";
		switch(clickType) {
			case "next":
				sendMessage = nextMessage; break;
			case "back": 
				sendMessage = backMessage; break;
			case "black": 
				sendMessage = blackMessage; break;
			case "start": 
				sendMessage = startMessage; break;
			case "exit": 
				sendMessage = exitMessage;
		}
		String finalSendMessage = sendMessage;		//Fix for threading
		
		//Send messages to Reciver A & B in new thread
		Executors.newSingleThreadExecutor().submit(() -> {
			if (gui.getEnabledA() == true) { networkManager.sendUDP(gui.getReciverA()[0], gui.getReciverA()[1], finalSendMessage, sendRedundantMessages, false); }
			if (gui.getEnabledB() == true) { networkManager.sendUDP(gui.getReciverB()[0], gui.getReciverB()[1], finalSendMessage, sendRedundantMessages, false); }
		});
		

		
		//Send message to Companion in new thread
		if (clickType == "next" || clickType == "back")
		{
			Executors.newSingleThreadExecutor().submit(() -> {
				//Send message to Companion
				String companionMessage = ("BANK-PRESS " + gui.getCompanionButtons(clickType)[0] + " " + gui.getCompanionButtons(clickType)[1]);
				if (gui.getEnabledCompanion() == true) { networkManager.sendUDP(gui.getCompanionCon()[0], gui.getCompanionCon()[1], companionMessage, 1, true); }
			});
		}
		
	}
	
	

}
