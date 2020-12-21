/*
 * This class handles sending keystrokes to the host system.
 */
package Receiver;

import java.awt.*;
import java.awt.event.*;

public class keystroke {
	
	public static void send(String request){
		//Create robot for sending global keystrokes
		Robot robot;
		try {
			robot = new Robot();

		//Send keystroke corresponding to request
		switch (request) {
			case "next":
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case "back":
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
				break;
			case "black":
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_B);
				break;
			case "start":
				robot.keyPress(KeyEvent.VK_F5);
				robot.keyRelease(KeyEvent.VK_F5);
				break;
			case "exit":
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				break;
			
			}

		} catch (AWTException e) { System.out.println("Error sending keystroke"); e.printStackTrace(); }
	}
}
