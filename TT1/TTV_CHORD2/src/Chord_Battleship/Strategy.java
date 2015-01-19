package Chord_Battleship;

import java.math.BigInteger;
import java.net.MalformedURLException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.NotifyCallback;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;
import de.uniba.wiai.lspi.util.logging.Logger;

public class Strategy implements NotifyCallback {
	
	private ChordImpl chord = null;
	private Logger logger;
	private ID myID = null;
	
	private int intervalSize = 100;
	private int numShips = 30;
	private int chopSize = 5;
	
	public Strategy() {
		logger = Logger.getLogger(this.getClass().getName());
		chord = new ChordImpl();
		chord.setCallback(this);
	}
	
	public void startServer(String serverURL) {
		try {
			URL url = new URL(serverURL);
			chord.create(url);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void startClient(String client, String server) {
		try {
			URL clientURL = new URL(client);
			URL bootstrapURL = new URL(server);
			chord.join(clientURL, bootstrapURL);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void initializeNode() {
		myID = chord.getID();
		logger.error("[NODE INIT]\n\t" + "MyID: " + myID);
		//chord.retrieve(myID);
	}
	
	public void test() {
		chord.retrieve(ID.valueOf(BigInteger.valueOf(10)));
	}
	
	
	
	
	
	
	
	
	@Override
	public void retrieved(ID target) {
		logger.error("[RETRIEVED]\n\t" + "My ID: " + myID + "\n\tTarget:" + target);
		
		// TODO: Calculate if hit
		// inform all participants about the attack and its outcome.
		chord.broadcast(target, false);
		
	}

	@Override
	public void broadcast(ID source, ID target, Boolean hit) {
		logger.error("[BROADCAST RECEIVED]\n\t" + "My ID: " + myID + "\n\tSource: " + source + "\n\tTarget:" + target + "\n\tHit:" + hit);
	}

}
