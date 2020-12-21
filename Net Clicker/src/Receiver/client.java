package Receiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class client {

	public static void main(String[] args) {
		System.out.println("NetClicker Client is starting...");
		
		//Setup GUI
		clientGUI.buildGUI();
	}
	
	//Creating and starting the UDP listener
	public static void startListening(int port) {
		System.out.println("UDP Client is listening on port " + port);
		udpClient client = new udpClient(port);
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.submit(client);
	}

}
