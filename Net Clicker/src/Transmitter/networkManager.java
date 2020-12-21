package Transmitter;

import java.net.*;
import java.io.IOException;
import java.util.StringTokenizer;

public class networkManager {
	
	public static int protocolVersion = 1;
	
	public static void sendUDP(String reciverIP, String reciverPort, String userMessage, int resendRate, boolean sendClean){
				
		
		    	int userPort = Integer.parseInt(reciverPort);
				
				String messageRandom = random.getMessageID();
				
				String finalMessage = ("netclicker-v" + protocolVersion + "-" + gui.getSessionID() + "-" + messageRandom + "-" + userMessage);
				if (sendClean == true) {finalMessage = userMessage;}

		        byte[] buf = new byte[256];
		        DatagramSocket socket = null;
		        try {
		            socket = new DatagramSocket();
		        } catch (SocketException e) {
		            e.printStackTrace();
		            
		        }
		        InetAddress address = null;
		        try {
		            
		            byte[] convertedIP = convertToIP(reciverIP);
		              
		            address = InetAddress.getByAddress(convertedIP);
		            
		            System.out.println(address);
		        } catch (UnknownHostException e) {
		            e.printStackTrace();
		            applicationcontrol.crash();
		            
		        }

		        buf= finalMessage.getBytes();
		        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, userPort);
		        
		        //Send redundant packets
		        for (int i = 1; i <= resendRate; ++i) {
		        
			        try {
			            socket.send(packet);
			            System.out.println("Sending UDP Packet...");
			        } catch (IOException e) {
			            e.printStackTrace();
			            
			        }
		        }
		   
		
	
	
		
	}
	
	public static byte[] convertToIP(String addr) {
		// Convert the TCP/IP address string to an integer value
	      
	      int ipInt = parseNumericAddress(addr);
	      if ( ipInt == 0)
	        return null;
	      
	      // Convert to bytes
	      
	      byte[] ipByts = new byte[4];
	      
	      ipByts[3] = (byte) (ipInt & 0xFF);
	      ipByts[2] = (byte) ((ipInt >> 8) & 0xFF);
	      ipByts[1] = (byte) ((ipInt >> 16) & 0xFF);
	      ipByts[0] = (byte) ((ipInt >> 24) & 0xFF);
	      
	      // Return the TCP/IP bytes
	      
	      return ipByts;
	}
	
	public final static int parseNumericAddress(String ipaddr) {
		  
	    //  Check if the string is valid
	    
	    if ( ipaddr == null || ipaddr.length() < 7 || ipaddr.length() > 15)
	      return 0;
	      
	    //  Check the address string, should be n.n.n.n format
	    
	    StringTokenizer token = new StringTokenizer(ipaddr,".");
	    if ( token.countTokens() != 4)
	      return 0;

	    int ipInt = 0;
	    
	    while ( token.hasMoreTokens()) {
	      
	      //  Get the current token and convert to an integer value
	      
	      String ipNum = token.nextToken();
	      
	      try {
	        
	        //  Validate the current address part
	        
	        int ipVal = Integer.valueOf(ipNum).intValue();
	        if ( ipVal < 0 || ipVal > 255)
	          return 0;
	          
	        //  Add to the integer address
	        
	        ipInt = (ipInt << 8) + ipVal;
	      }
	      catch (NumberFormatException ex) {
	        return 0;
	      }
	    }
	    
	    //  Return the integer address
	    
	    return ipInt;
	  }

}


