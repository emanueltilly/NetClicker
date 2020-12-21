/*
 * This class takes message recived from Tranmsmitter via UDP, splits message into String Array, error-checks and executes functions based on request.
 */


package Receiver;

import java.util.ArrayList;

public class packetManager {
	
	/*PROTOCOL VERSION 1
	 * 
	 * pos	type		desc						example
	 * 0	static		softwarename				netclicker
	 * 1	static		protocol version			v1
	 * 2	session		session id					8793
	 * 3	message		message id					4757823227
	 * 4	message		string message				heartbeat_A
	 */
	
	//Global variables
	private static ArrayList<String> messageHistory = new ArrayList<String>();

	
	public static void unpackMessage(String sourceMessage) {

		String okSoftwareHeader = "netclicker";
		String okProtocolHeader = "v1";
		
		//Split string into array
		String[] message = sourceMessage.split("-");
		
		//Check if valid & unread message
		boolean messageOK = false;
		try {
			if (okSoftwareHeader.equals(message[0]) && okProtocolHeader.equals(message[1]) && checkNewMessage(message[3])) { messageOK = true; }
		} catch(Exception e) { return; }
		
		//Execute message if OK
		if (messageOK == true)
		{
			System.out.println(sourceMessage);
			
			//Check if Heartbeat
			boolean isHeartbeat = sourceMessage.contains("heartbeat");
			if (isHeartbeat == true) {manageHeartbeat(message[4], message[2]); return;}
			
			//Send keystrokes
			switch(message[4]) {
				case "next":
					keystroke.send("next");
					break;
				case "back":
					keystroke.send("back");
					break;
				case "black":
					if (clientGUI.getEnabledBlack()) { keystroke.send("black"); }		//Checks user settings for special 
					break;
				case "start":
					if (clientGUI.getEnabledStart()) { keystroke.send("start"); }		
					break;
				case "exit":
					if (clientGUI.getEnabledExit()) { keystroke.send("exit"); }	
					break;
					
			}
			
		}
		

		
	}

	private static void manageHeartbeat(String message, String sID) {
		String lastChar = message.substring(message.length() - 1);
		clientGUI.setNewHeartbeat(lastChar, sID);
		
	}
	
	private static boolean checkNewMessage(String sourceMessage)
	{
		if (messageHistory.contains(sourceMessage))
		{
			return false;
			
		} else {
			messageHistory.add(sourceMessage);
			return true;
		}
	}

}
