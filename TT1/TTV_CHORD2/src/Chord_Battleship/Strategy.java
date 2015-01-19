package Chord_Battleship;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.ArrayList;

import de.uniba.wiai.lspi.chord.com.CommunicationException;
import de.uniba.wiai.lspi.chord.com.Node;
import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.NotifyCallback;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;
import de.uniba.wiai.lspi.chord.service.impl.NodeImpl;
import de.uniba.wiai.lspi.util.logging.Logger;

public class Strategy implements NotifyCallback {
	
	private ChordImpl chord = null;
	private Logger logger;
	private ID myID = null;
	private static final ID biggestKey = ID.valueOf(BigInteger.valueOf(2).pow(160).subtract(BigInteger.ONE));

	private Participant myNavy;
	private ArrayList<Participant> participants;

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
		myNavy = new Participant(myID, intervalSize);
		myNavy.setShips(intervalSize, chopSize, numShips);
	}
	
	public void startBattle() {
		try {
			initParticipants();
		} catch (CommunicationException e) {
			e.printStackTrace();
			return;
		}
		
		if (iGoFirst()) {
			logger.error("I AM FIRST! WOHOO! My ID: "+myID);
			shoot();
		}
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
	
	private void shoot() {
		// TODO
	}

	private void initParticipants() throws CommunicationException {
		participants = new ArrayList<Participant>();
		
		myNavy.setPredecessor(chord.getPredecessorID());
		myNavy.calcInterval();

		Participant participant;
		Node currentNode = chord.findSuccessor(Util.increaseID(myID));
		ID predID = myID;
		
		while (!currentNode.getNodeID().equals(myID)) {
			participant = new Participant(currentNode.getNodeID(), intervalSize);
			participant.setPredecessor(predID);
			participant.calcInterval();
			participants.add(participant);
		
			predID = currentNode.getNodeID(); 
			currentNode = currentNode.findSuccessor(Util.increaseID(currentNode.getNodeID()));
		}
		logger.warn("[INIT PARTICIPANTS] ID: "+myID
					+"\n\tfound "+participants.size()+" other participants");
	}
	
	/* Check if this node has the biggest ID and thus gets to shoot first */
	private boolean iGoFirst() {
		/* because isInterval doesn't include the start or end ID, we have to increment our ID */
		return biggestKey.isInInterval(chord.getPredecessorID(), Util.increaseID(myID));
	}

}
