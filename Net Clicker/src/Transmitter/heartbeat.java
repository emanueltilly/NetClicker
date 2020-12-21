package Transmitter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class heartbeat {
	
	private static boolean firstRequest = true;
	
	public static void heartbeatLoop() {
		if (firstRequest == true)
		{
			firstRequest = false;
			
			Executors.newSingleThreadExecutor().submit(() -> {
				while (true) {
					if (gui.getEnabledA() == true) { networkManager.sendUDP(gui.getReciverA()[0], gui.getReciverA()[1], "heartbeat_A", 1, false); }
					if (gui.getEnabledB() == true) { networkManager.sendUDP(gui.getReciverB()[0], gui.getReciverB()[1], "heartbeat_B", 1, false); }
					TimeUnit.SECONDS.sleep(5);
				}
			});
		}
		
	}
		

}
