package Chord_Battleship;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

public class Main {
	// caution, these IPs are bullshit
	static String serverURL = "ocsocket://141.22.28.180:12345/";
	static String clientURL = "ocsocket://141.22.28.170:12345/";

	
	public static void main(String[] args) {
		// make logger STFU
		LogManager.getRootLogger().setLevel(Level.INFO);
		
		Chord_Battleship chord = new Chord_Battleship();
		//chord.startServer(serverURL);
		chord.startClient(clientURL, serverURL);
	}
}
