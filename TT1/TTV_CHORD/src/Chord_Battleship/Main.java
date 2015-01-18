package Chord_Battleship;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import de.uniba.wiai.lspi.chord.service.PropertiesLoader;

public class Main {
	// NOTE: If join operations fail, make sure you changed these
	// IPs to yours! (No time to implement this in a way that works on windows,
	// Linux and Mac OS. sorry.)
	static String serverURL = "ocsocket://192.168.1.91:12350/";
	static String baseClientURL = "ocsocket://192.168.1.91:1235";
	static int numClients = 3;

	static ArrayList<Thread> clientThreads = new ArrayList<Thread>();
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
				
		PropertiesLoader.loadPropertyFile();
		lock.lock();
		if(args.length > 0) {
			switch(args[0]){
				case "-s":{
					System.out.println("Starting server at" + serverURL);
					BattleshipStategy chord = new BattleshipStategy();
					chord.startServer(serverURL);
					break;
				}
				case "-c":{
					launchClients();
					break;
				}
			}
		} else {
			System.out.println("Missing arguments. aborting.");
			return;
		}
		
		lock.unlock();
	
	}
	
	static void launchClients() {
		String clientURL;
		for (int i=1; i<= numClients; i++) {
			clientURL = baseClientURL+i+"/";
			System.out.println("Starting client at: "+clientURL);
			Runnable r = new ChordClient(clientURL);
			Thread t = new Thread(r); 
			clientThreads.add(t);
			t.start();
		}
		
		System.out.println("\nAll clients launched. Press enter to start the battle:\n");
		lock.lock();
		Scanner in = new Scanner(System.in);
		in.nextLine();
		in.close();
		System.out.println("Begun, the battleship wars have.");
		lock.unlock();
	}
	
	static class ChordClient implements Runnable {
		String clientURL_ = "";
		BattleshipStategy chord;
		
		public ChordClient(String clientURL) {
		    clientURL_ = clientURL;
			chord = new BattleshipStategy();
		}
	
		public void run() {
			chord.startClient(clientURL_, serverURL);
			lock.lock();
			lock.unlock();
			System.out.println("client "+clientURL_+" unlocked");
			chord.startBattle();
		}
	}
}
