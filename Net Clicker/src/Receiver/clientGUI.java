package Receiver;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class clientGUI {

	//Declare variables
	private static boolean enableListen;
	private static int port;
	private static boolean enableBlack;
	private static boolean enableStart;
	private static boolean enableExit;
	
	private static String sessionID = "Waiting";
	private static String sessionMember = "?";
	private static boolean heartbeatOK = false;
	
	private static boolean newHeartbeat = false;
	private static int heartbeatCounter = 0;
	
	
	
	public static void buildGUI() {
		
		//User Preferences
		Preferences userPreferences = Preferences.userRoot();
		enableListen = userPreferences.getBoolean("enableListen", false);
		port = userPreferences.getInt("port", 50001);
		enableBlack = userPreferences.getBoolean("enableBlack", true);
		enableStart = userPreferences.getBoolean("enableStart", true);
		enableExit = userPreferences.getBoolean("enableExit", true);
		
		
		//Setup frame and panel
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setTitle("Net Clicker Receiver");
		frame.setBackground(Color.DARK_GRAY);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		frame.add(panel);
		
		
		
		//HEARTBEAT LABEL
		
		JLabel heartbeatLabel = new JLabel("NOT CONNECTED TO TRANSMITTER");
		heartbeatLabel.setBounds(0, 0, 300, 25);
		heartbeatLabel.setFont(new Font("Sans-Serif", Font.BOLD, 10));
		heartbeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heartbeatLabel.setForeground(Color.WHITE);
		heartbeatLabel.setBackground(Color.red);
		heartbeatLabel.setOpaque(true);
		panel.add(heartbeatLabel);
				
				
		//ENGAGED LABEL
		
		JLabel listenLabel = new JLabel("NOT ENGAGED");
		listenLabel.setBounds(0, 25, 300, 100);
		listenLabel.setFont(new Font("Sans-Serif", Font.BOLD, 25));
		listenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listenLabel.setForeground(Color.gray);
		listenLabel.setBackground(Color.DARK_GRAY);
		listenLabel.setOpaque(true);
		panel.add(listenLabel);
		
		
		//PORT
		
		JLabel portLabel = new JLabel("PORT");
		portLabel.setBounds(0, 135, 300, 25);
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portLabel.setForeground(Color.LIGHT_GRAY);
		panel.add(portLabel);
		
		
		JTextField portTextfield = new JTextField(String.valueOf(port));
		portTextfield.setBounds(0,160,300,25);
		portTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(portTextfield);
		
		
		//ENABLE KEYSTROKES
		
		JLabel enableHeaderLabel = new JLabel("PERMISSIONS");
		enableHeaderLabel.setBounds(0, 190, 300, 25);
		enableHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enableHeaderLabel.setForeground(Color.LIGHT_GRAY);
		panel.add(enableHeaderLabel);
		
		//BLACKOUT
		JButton enableButtonBlack = new JButton(enableBlack?"Blackout ON":"Blackout OFF");
		enableButtonBlack.setBounds(0, 215, 300, 25);
		enableButtonBlack.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enableBlack == true) { enableBlack = false; enableButtonBlack.setText("Blackout OFF"); }
	             else { enableBlack = true; enableButtonBlack.setText("Blackout ON"); }
	        }
	    });  
		panel.add(enableButtonBlack);
		
		//START WITH F5
		JButton enableButtonStart = new JButton(enableStart?"Start with F5 ON":"Start with F5 OFF");
		enableButtonStart.setBounds(0, 240, 300, 25);
		enableButtonStart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enableStart == true) { enableStart = false; enableButtonStart.setText("Start with F5 OFF"); }
	             else { enableStart = true; enableButtonStart.setText("Start with F5 ON"); }
	        }
	    });  
		panel.add(enableButtonStart);
		
		//EXIT WITH ESC
		JButton enableButtonExit = new JButton(enableExit?"Exit with ESC ON":"Exit with ESC OFF");
		enableButtonExit.setBounds(0, 265, 300, 25);
		enableButtonExit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enableExit == true) { enableExit = false; enableButtonExit.setText("Exit with ESC OFF"); }
	             else { enableExit = true; enableButtonExit.setText("Exit with ESC ON"); }
	        }
	    });  
		panel.add(enableButtonExit);
		
		
		
		//ENGAGE BUTTON
		JButton engageButton = new JButton(enableListen?"QUIT":"START LISTENING");
		engageButton.setBounds(0, 310, 300, 40);
		engageButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enableListen == true) {
	            	 
	            	 //Quit application
	            	 System.exit(0);
	            	 
	             }
	             else {
	            	 
	            	 engageButton.setText("QUIT");
	            	 
	            	 portTextfield.setEnabled(false);
	            	 enableButtonBlack.setEnabled(false);
	            	 enableButtonStart.setEnabled(false);
	            	 enableButtonExit.setEnabled(false);
	            	 
	            	 //Store GUI to variables
	            	 port = Integer.parseInt(portTextfield.getText());
	            	 
	            	 //Store variables to user preferences
	            	 userPreferences.putInt("port", port);
	            	 userPreferences.putBoolean("enableBlack", enableBlack);
	            	 userPreferences.putBoolean("enableStart", enableStart);
	            	 userPreferences.putBoolean("enableExit", enableExit);
	            			 	
	            	 
	            	 //Start UDP Client
	            	 client.startListening(port);
	            	 //Flag for software to start listening
	            	 enableListen = true;
	            	 
	            	 
	            	//GUI UPDATE LOOP
	         		Executors.newSingleThreadExecutor().submit(() -> {
	         			while (enableListen == true)
	         			{
	         				try {Thread.sleep(1000);} catch (InterruptedException ie) {Thread.currentThread().interrupt();}		//Sleep
	         				
	         				//Heartbeat Label
	         				if (heartbeatOK == true) {heartbeatLabel.setBackground(Color.GREEN); heartbeatLabel.setForeground(Color.BLACK); heartbeatLabel.setText("Connected to session " + sessionID + " Reciver " + sessionMember);}
	         				else {heartbeatLabel.setBackground(Color.RED); heartbeatLabel.setForeground(Color.WHITE); heartbeatLabel.setText("CONNECTION LOST WITH SESSION " + sessionID);}
	         				
	         				//Engaged Label
	         				if (enableListen == false) {listenLabel.setText("NOT ENGAGED");}
	         				if (enableListen == true && heartbeatOK == true) {listenLabel.setText("ENGAGED"); listenLabel.setBackground(Color.DARK_GRAY); listenLabel.setForeground(Color.gray);}
	         				if (enableListen == true && heartbeatOK == false) {listenLabel.setText("CONNECTION LOST"); listenLabel.setForeground(Color.WHITE);}
	         				if (enableListen == true && heartbeatOK == false) {
	         					
	         					while (heartbeatOK == false)
	         					{
	         						listenLabel.setBackground(Color.LIGHT_GRAY);
	         						try {Thread.sleep(100);} catch (InterruptedException ie) {Thread.currentThread().interrupt();}
	         						listenLabel.setBackground(Color.DARK_GRAY);
	         						try {Thread.sleep(100);} catch (InterruptedException ie) {Thread.currentThread().interrupt();}
	         					}
	         					
	         					
	         				}
	         				
	         				
	         			}
	         		});
	         		
	         		//HEARTBEAT CHECKER
	         		/*packetManager sets the boolean "newHeartbeat" to TRUE when a heartbeat packet has arrived from the transmittor.
	         		 * This function uses this boolean as a trigger to reset the counter.
	         		 * The number "7" is used as a threshold since the transmittor is sending heartbeats every 5 sec + 2 sec padding.
	         		 */
	         		Executors.newSingleThreadExecutor().submit(() -> {
	         			while (enableListen == true)
	         			{
	         				if (newHeartbeat == true) {
	         					//Reset flags and counters
	         					newHeartbeat = false;
	         					heartbeatCounter = 0;
	         					//Set status to OK
	         					heartbeatOK = true;
	         				} else {
	         					
	         					//Check if Threshold-value reached
	         					if (heartbeatCounter > 7) {
	         						heartbeatOK = false;	//Flag that connection is lost
	         					} else {
	         						heartbeatCounter++;
	         					}
	         				}
	         				
	         				try {
	         				    Thread.sleep(1000);
	         				} catch (InterruptedException ie) {
	         				    Thread.currentThread().interrupt();
	         				}
	         				
	         				
	         			}
	         		});
	         		
	         		
            	 }
	        }
	    });  
		panel.add(engageButton);
		
		
		//Show frame
		frame.setVisible(true);
	
	}
	
	//SET
	public static void setNewHeartbeat(String sesMem, String sesID) { newHeartbeat = true; sessionMember = sesMem; sessionID = sesID;}
	
	//GET
	public static boolean getEnabledBlack() {return enableBlack;}
	public static boolean getEnabledStart() {return enableStart;}
	public static boolean getEnabledExit() {return enableExit;}

}
