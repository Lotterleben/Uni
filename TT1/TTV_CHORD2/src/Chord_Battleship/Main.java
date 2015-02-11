package Chord_Battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.uniba.wiai.lspi.chord.service.PropertiesLoader;

public class Main {

	static String serverURL = "ocsocket://192.168.14.260:5555/";
	static String baseClientURL = "ocsocket://192.168.14.141:5000";
	static int numClients = 1;
	
	static List<Strategy> nodes = new ArrayList<Strategy>();
	
	public static void main(String[] args) {
		PropertiesLoader.loadPropertyFile();
		
		/*
		System.out.println("Starting server at" + serverURL);
		Strategy serverNode = new Strategy();
		serverNode.startServer(serverURL);
		nodes.add(serverNode);
		*/
		
		launchClients();
		
		System.out.println("\nAll clients launched. Press enter to start the battle:\n");
		Scanner in = new Scanner(System.in);
		in.nextLine();
		in.close();
		
		for(Strategy node : nodes) {
			node.initializeNode();
		}
		
		System.out.println("Begun, the battleship wars have.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Strategy node: nodes) {
			node.startBattle();
		}

	}
	
	public static void launchClients() {
		String clientURL;
		for (int i=1; i<= numClients; i++) {
			clientURL = baseClientURL+i+"/";
			System.out.println("Starting client at: "+clientURL);
			
			Strategy clientNode = new Strategy();
			clientNode.startClient(clientURL, serverURL);
			nodes.add(clientNode);
		}
	}

}
