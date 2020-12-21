package Transmitter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class netclicker implements NativeKeyListener {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Net Clicker is starting...");
		
		//Setup native hook
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(new netclicker());
		
	
		
		//Launch GUI
		gui.buildGUI();
		
		//Start heartbeat
		heartbeat.heartbeatLoop();
		

	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
	System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			
			
			
	        /*if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
	            try {
	                GlobalScreen.unregisterNativeHook();
	            } catch (NativeHookException e1) {
	                
	                e1.printStackTrace();
	            }
	        }*/
			
			//CHECK WHAT KEY IS PRESSED
			if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT) { clicker.sendClick("next"); }
			if (e.getKeyCode() == NativeKeyEvent.VC_LEFT) { clicker.sendClick("back"); }
			if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {clicker.sendClick("next"); }
			if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) { clicker.sendClick("exit"); }
			if (e.getKeyCode() == NativeKeyEvent.VC_F5) { clicker.sendClick("start"); }
			if (e.getKeyCode() == NativeKeyEvent.VC_B) { clicker.sendClick("black"); }
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		//Not used
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		//Not used
	}
	


}
