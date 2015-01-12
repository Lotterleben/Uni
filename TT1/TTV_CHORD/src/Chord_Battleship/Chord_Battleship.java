package Chord_Battleship;

import java.net.MalformedURLException;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.NotifyCallback;
import de.uniba.wiai.lspi.chord.service.PropertiesLoader;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;
import de.uniba.wiai.lspi.util.logging.Logger;

public class Chord_Battleship implements NotifyCallback{

	private ChordImpl chord = null;
	private ID myID = null;
	private Logger logger;
	private boolean hit;
	
	public Chord_Battleship () {
		logger = Logger.getLogger(this.getClass().getName());
		chord = new ChordImpl();
		chord.setCallback(this);
		
		org.apache.log4j.Level level = org.apache.log4j.Level.WARN;
		org.apache.log4j.Logger.getRootLogger().setLevel(level);
		System.out.println("ooo LOG LEVEL: "+ org.apache.log4j.Logger.getRootLogger().getLevel());
	}
	
	public void startServer(String serverURL) {
		
		try {
			URL url = new URL(serverURL);
			chord.create(url);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myID = chord.getID();
	}
	
	public void startClient(String client, String server) {

		try {
			URL clientURL = new URL(client);
			URL bootstrapURL = new URL(server);
			chord.join(clientURL, bootstrapURL);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myID = chord.getID();
		sendTest();
	}

	// for testing purposes only TODO delete me
	private void sendTest(){
		chord.retrieve(myID);
	}

	/* This callback is called if I am the target of a retrieve, which attacks the "Sea" at target
	 * "coordinate". If a ship exists at target, it will be destroyed.
	 * NOTE: Everyone has to to figure out themselves which ID range belongs to which 
	 * participant, and attack accordingly!
	 */
	@Override
	public void retrieved(ID target) {
		logger.error("\n\t" + myID + "retrieved\n\t\t target: " + target);
		// TODO were we harmed?
		hit = !hit; // TODO actual code and stuff
		
		// inform all participants about the attack and its outcome.
		chord.broadcast(myID, hit);
	}
	
	/* This callback is called whenever a broadcast is sent by any node.
	 * Here, we can log what is going on, and adjust our strategy accordingly.
	 */
	@Override
	public void broadcast(ID source, ID target, Boolean hit) {
		logger.error("\n\t"+ myID + " received broadcast \n\t\t source: " + source +" target: " + target + "hit: " + hit);
		// TODO Auto-generated method stub
	}

}
