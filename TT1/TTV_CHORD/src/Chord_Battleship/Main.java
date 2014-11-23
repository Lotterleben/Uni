package Chord_Battleship;

public class Main {
	// caution, these IPs are bullshit
	static String serverURL = "ocsocket://141.22.56.21:12345/";
	static String clientURL = "ocsocket://141.22.56.21:12345/";

	
	public static void main(String[] args) {
		Chord_Battleship chord = new Chord_Battleship();
		chord.startServer(serverURL);
		//chord.startClient(clientURL, serverURL);
	}
}
