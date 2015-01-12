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
		
		org.apache.log4j.Level level = org.apache.log4j.Level.INFO;
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
		//sendTest();
	}

	// for testing purposes only TODO delete me
	private void sendTest(){
		logger.error("my ID: "+myID);
		chord.retrieve(myID);
		chord.broadcast(myID, hit);
	}

	@Override
	public void retrieved(ID target) {
		logger.error("retrieved " + target);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcast(ID source, ID target, Boolean hit) {
		logger.error("broadcast ; source: " + source +" target: " + "hit: " + hit);
		// TODO Auto-generated method stub
		
	}

}
