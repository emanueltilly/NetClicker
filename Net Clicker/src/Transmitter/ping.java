package Transmitter;

import java.net.InetAddress;

public class ping {
	
	public static boolean sendPing(String sourceIP) {
		
		try {
		
			
			    InetAddress inet = InetAddress.getByName(sourceIP);
			    System.out.println("Sending Ping Request to " + sourceIP);
			    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");
			
		    
		    if (inet.isReachable(5000)) { return true; }
		    else { return false; }
			
		} catch(Exception e) {
			System.out.println("Error pinging reciver: " + e);
			return false;
		}
		
		

	}

}
