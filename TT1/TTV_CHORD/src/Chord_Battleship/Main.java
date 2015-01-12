package Chord_Battleship;

import java.util.Enumeration;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

import de.uniba.wiai.lspi.chord.service.PropertiesLoader;
import de.uniba.wiai.lspi.util.logging.Log4jLogger;

public class Main {
	// caution, these IPs are bullshit
	static String serverURL = "ocsocket://141.22.28.170:12340/";
	static String baseClientURL = "ocsocket://141.22.28.170:1234";
	static int numClients = 1;
	
	public static void main(String[] args) {
		
		PropertiesLoader.loadPropertyFile();
		
		if(args.length > 0) {
			switch(args[0]){
				case "-s":{
					System.out.println("Starting server at" + serverURL);
					Chord_Battleship chord = new Chord_Battleship();
					chord.startServer(serverURL);
					break;
				}
				case "-c":{
					String clientURL;
					for (int i=1; i<= numClients; i++) {
						clientURL = baseClientURL+i+"/";
						System.out.println("Starting client at: "+clientURL);
						Runnable r = new ChordClient(clientURL);
						new Thread(r).start();
					}
					break;
				}
			}
		} else {
			System.out.println("Missing arguments. aborting.");
			return;
		}
	}
	
	static class ChordClient implements Runnable {
		String clientURL_ = "";
		Chord_Battleship chord;
		
		public ChordClient(String clientURL) {
		    clientURL_ = clientURL;
			chord = new Chord_Battleship();
		}
	
		public void run() {
			chord.startClient(clientURL_, serverURL);
		}
	}
}
