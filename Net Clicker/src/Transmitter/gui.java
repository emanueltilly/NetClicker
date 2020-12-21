package Transmitter;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;




public class gui {
	
	//Declare variables
	
	private static boolean statusA = false;
	private static boolean statusB = false;
	private static boolean statusCompanion = false;
	
	private static boolean enabledA;
	private static boolean enabledB;
	private static boolean enabledCompanion;
	
	private static String sessionID;
	private static String rAip;
	private static String rAport;
	private static String rBip;
	private static String rBport;
	
	private static String compIP;
	private static String compPort;
	private static String compNextPage;
	private static String compNextBank;
	private static String compBackPage;
	private static String compBackBank;
	
	
	public static void buildGUI() {
		//User Preferences
		Preferences userPreferences = Preferences.userRoot();
		
		enabledA = userPreferences.getBoolean("enabledA", false);
		enabledB = userPreferences.getBoolean("enabledB", false);
		enabledCompanion = userPreferences.getBoolean("enabledCompanion", false);
		
		sessionID = userPreferences.get("sessionID", random.getSessionID());
		rAip = userPreferences.get("rAip", "127.0.0.1");
		rAport = userPreferences.get("rAport", "50001");
		rBip = userPreferences.get("rBip", "127.0.0.1");
		rBport = userPreferences.get("rBport", "50002");
		
		compIP = userPreferences.get("compIP", "127.0.0.1");
		compPort = userPreferences.get("compPort", "51235");
		compNextPage = userPreferences.get("compNextPage", "1");
		compNextBank = userPreferences.get("compNextBank", "1");
		compBackPage = userPreferences.get("compBackPage", "1");
		compBackBank = userPreferences.get("compBackBank", "1");
		
		//Setup frame and panel
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(293, 560);
		frame.setFocusable(true);
		frame.setTitle("Net Clicker");
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		
		//HEADER LABELS
		
		JLabel logoLabel = new JLabel("Net Clicker Transmitter");
		logoLabel.setBounds(10, 15, 250, 25);
		logoLabel.setForeground(Color.LIGHT_GRAY);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
		panel.add(logoLabel);
		
		JLabel sesIdLabel = new JLabel("Session ID: " + sessionID);
		sesIdLabel.setBounds(10, 40, 250, 25);
		sesIdLabel.setForeground(Color.LIGHT_GRAY);
		sesIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sesIdLabel.setFont(new Font("Sans-Serif", Font.BOLD, 15));
		panel.add(sesIdLabel);
		
		
		//RECIVER A
		
		JButton enableButtonA = new JButton(enabledA?"Enabled":"Disabled");
		enableButtonA.setBounds(100, 80, 165, 25);
		enableButtonA.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enabledA == true) { enabledA = false; enableButtonA.setText("Disabled"); }
	             else { enabledA = true; enableButtonA.setText("Enabled"); }
	        }  
	    });  
		panel.add(enableButtonA);

		
		JLabel labelATop = new JLabel("Reciver A");
		labelATop.setBounds(10, 80, 100, 25);
		labelATop.setForeground(Color.LIGHT_GRAY);
		panel.add(labelATop);
		
		JLabel labelIP1 = new JLabel("IP:");
		labelIP1.setBounds(10, 110, 100, 25);
		labelIP1.setForeground(Color.LIGHT_GRAY);
		panel.add(labelIP1);
		
		JTextField userIP1 = new JTextField(rAip);
		userIP1.setBounds(100,110,165,25);
		panel.add(userIP1);
		
		JLabel labelPort1 = new JLabel("Port:");
		labelPort1.setBounds(10, 140, 100, 25);
		labelPort1.setForeground(Color.LIGHT_GRAY);
		panel.add(labelPort1);
		
		JTextField userIP1port = new JTextField(rAport);
		userIP1port.setBounds(100,140,165,25);
		panel.add(userIP1port);
		
		
		//RECIVER B
		
		JButton enableButtonB = new JButton(enabledB?"Enabled":"Disabled");
		enableButtonB.setBounds(100, 180, 165, 25);
		enableButtonB.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enabledB == true) { enabledB = false; enableButtonB.setText("Disabled"); }
	             else { enabledB = true; enableButtonB.setText("Enabled"); }
	        }  
	    });  
		panel.add(enableButtonB);
		
		JLabel labelBTop = new JLabel("Reciver B");
		labelBTop.setBounds(10, 180, 100, 25);
		labelBTop.setForeground(Color.LIGHT_GRAY);
		panel.add(labelBTop);
		
		JLabel labelIP2 = new JLabel("IP:");
		labelIP2.setBounds(10, 210, 100, 25);
		labelIP2.setForeground(Color.LIGHT_GRAY);
		panel.add(labelIP2);
		
		JTextField userIP2 = new JTextField(rBip);
		userIP2.setBounds(100, 210,165,25);
		panel.add(userIP2);
		
		JLabel labelPort2 = new JLabel("Port:");
		labelPort2.setBounds(10, 240, 100, 25);
		labelPort2.setForeground(Color.LIGHT_GRAY);
		panel.add(labelPort2);
		
		JTextField userIP2port = new JTextField(rBport);
		userIP2port.setBounds(100,240,165,25);
		panel.add(userIP2port);
		
		//COMPANION
		
		JButton enableButtonComp = new JButton(enabledCompanion?"Enabled":"Disabled");
		enableButtonComp.setBounds(100, 280, 165, 25);
		enableButtonComp.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	             if (enabledCompanion == true) { enabledCompanion = false; enableButtonComp.setText("Disabled"); }
	             else { enabledCompanion = true; enableButtonComp.setText("Enabled"); }
	        }  
	    });  
		panel.add(enableButtonComp);
		
		JLabel labelCompTop = new JLabel("Companion");
		labelCompTop.setBounds(10, 280, 100, 25);
		labelCompTop.setForeground(Color.LIGHT_GRAY);
		panel.add(labelCompTop);
		
		JLabel labelIPcomp = new JLabel("IP:");
		labelIPcomp.setBounds(10, 310, 100, 25);
		labelIPcomp.setForeground(Color.LIGHT_GRAY);
		panel.add(labelIPcomp);
		
		JTextField userIPcomp = new JTextField(compIP);
		userIPcomp.setBounds(100, 310,165,25);
		panel.add(userIPcomp);
		
		JLabel labelPortComp = new JLabel("UDP Port:");
		labelPortComp.setBounds(10, 340, 100, 25);
		labelPortComp.setForeground(Color.LIGHT_GRAY);
		panel.add(labelPortComp);
		
		JTextField userCompPort = new JTextField(compPort);
		userCompPort.setBounds(100,340,165,25);
		panel.add(userCompPort);
		
		JLabel labelCompHeaderPage = new JLabel("Page");
		labelCompHeaderPage.setBounds(100, 370, 100, 25);
		labelCompHeaderPage.setForeground(Color.LIGHT_GRAY);
		panel.add(labelCompHeaderPage);
		
		JLabel labelCompHeaderBank = new JLabel("Bank");
		labelCompHeaderBank.setBounds(200, 370, 100, 25);
		labelCompHeaderBank.setForeground(Color.LIGHT_GRAY);
		panel.add(labelCompHeaderBank);
		
		JLabel labelCompNext = new JLabel("NEXT");
		labelCompNext.setBounds(10, 400, 100, 25);
		labelCompNext.setForeground(Color.LIGHT_GRAY);
		panel.add(labelCompNext);
		
		JTextField userCompNextPage = new JTextField(compNextPage);
		userCompNextPage.setBounds(100,400,65,25);
		panel.add(userCompNextPage);
		
		
		JTextField userCompNextBank = new JTextField(compNextBank);
		userCompNextBank.setBounds(200,400,65,25);
		panel.add(userCompNextBank);
		
		
		
		JLabel labelCompBack = new JLabel("BACK");
		labelCompBack.setBounds(10, 430, 100, 25);
		labelCompBack.setForeground(Color.LIGHT_GRAY);
		panel.add(labelCompBack);
		
		JTextField userCompBackPage = new JTextField(compBackPage);
		userCompBackPage.setBounds(100,430,65,25);
		panel.add(userCompBackPage);
		
		JTextField userCompBackBank = new JTextField(compBackBank);
		userCompBackBank.setBounds(200,430,65,25);
		panel.add(userCompBackBank);
		
		
		
		JButton applyButton = new JButton("Apply settings");
		applyButton.setBounds(0, 470, 293, 50);
		applyButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				
				//Store GUI fields to variables
				rAip = userIP1.getText();
				rAport = userIP1port.getText();
				rBip = userIP2.getText();
				rBport = userIP2port.getText();
				
				compIP = userIPcomp.getText();
				compPort = userCompPort.getText();
				compNextPage = userCompNextPage.getText();
				compNextBank = userCompNextBank.getText();
				compBackPage = userCompBackPage.getText();
				compBackBank = userCompBackBank.getText();
				
				
				//Store to user preferences
				userPreferences.putBoolean("enabledA", enabledA);
				userPreferences.putBoolean("enabledB", enabledB);
				userPreferences.putBoolean("enabledCompanion", enabledCompanion);
				
				userPreferences.put("sessionID", sessionID);
				userPreferences.put("rAip", rAip);
				userPreferences.put("rAport", rAport);
				userPreferences.put("rBip", rBip);
				userPreferences.put("rBport", rBport);
				
				userPreferences.put("compIP", compIP);
				userPreferences.put("compPort", compPort);
				userPreferences.put("compNextPage", compNextPage);
				userPreferences.put("compNextBank", compNextBank);
				userPreferences.put("compBackPage", compBackPage);
				userPreferences.put("compBackBank", compBackBank);
					
	        }  
	    });  
		panel.add(applyButton);
	

		//Show frame
		frame.setVisible(true);
		
		//Loop to update STATUS in new thread
		Executors.newSingleThreadExecutor().submit(() -> {
	        while(true) {
	        	if (enabledA == true) {userIP1.setBackground(statusA?Color.GREEN:Color.RED);} else {userIP1.setBackground(Color.WHITE);}
	        	if (enabledB == true) {userIP2.setBackground(statusB?Color.GREEN:Color.RED);} else {userIP2.setBackground(Color.WHITE);}
	        	if (enabledCompanion == true) {userIPcomp.setBackground(statusCompanion?Color.GREEN:Color.RED);} else {userIPcomp.setBackground(Color.WHITE);}
	        	
	        	
	        	TimeUnit.SECONDS.sleep(2);
	        }
	    });
		
		//Loop to PING in new thread
		Executors.newSingleThreadExecutor().submit(() -> {
	        while(true) {
	        	if (enabledA == true) {statusA = ping.sendPing(rAip);}
	        	if (enabledB == true) {statusB = ping.sendPing(rBip);}
	        	if (enabledCompanion == true) {statusCompanion = ping.sendPing(compIP);}

	        	
	        	TimeUnit.SECONDS.sleep(10);
	        }
	    });
		
	}
	
	

	
	//GET
	
	public static String getSessionID() {
		return sessionID;
	}
	public static String[] getReciverA() {
		String[] returnArray = new String[2];
		returnArray[0] = rAip; 		returnArray[1] = rAport; 		return returnArray;
	}
	public static String[] getReciverB() {
		String[] returnArray = new String[2];
		returnArray[0] = rBip; 		returnArray[1] = rBport; 		return returnArray;
	}
	public static String[] getCompanionCon() {
		String[] returnArray = new String[2];
		returnArray[0] = compIP; 	returnArray[1] = compPort;		return returnArray;
	}
	public static String[] getCompanionButtons(String request)
	{
		String[] returnArray = new String[2];
		returnArray[0] = "1";		returnArray[1] = "1";		//Crash prevention
		switch (request)
		{
		case "next":
			returnArray[0] = compNextPage;		returnArray[1] = compNextBank;		return returnArray;
		case "back":
			returnArray[0] = compBackPage;		returnArray[1] = compBackBank;		return returnArray;
		}
		
		return returnArray;
		
	}
	public static boolean getEnabledA() {return enabledA;}
	public static boolean getEnabledB() {return enabledB;}
	public static boolean getEnabledCompanion() {return enabledCompanion;}
	
	//SET
	
}
