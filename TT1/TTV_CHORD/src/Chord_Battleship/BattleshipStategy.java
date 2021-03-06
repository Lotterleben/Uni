package Chord_Battleship;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import de.uniba.wiai.lspi.chord.com.CommunicationException;
import de.uniba.wiai.lspi.chord.com.Node;
import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.NotifyCallback;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;
import de.uniba.wiai.lspi.util.logging.Logger;

/* TODO 
 * - figure out which ranges our neighbors have
 * - attack (retrieve()) them according to SuperAwesomeUnicornStrategy
 */

public class BattleshipStategy implements NotifyCallback{

	private ChordImpl chord = null;
	private ID myID = null;
	private Logger logger;
	private boolean hit;
	private static final ID biggestKey = ID.valueOf(BigInteger.valueOf(2).pow(160).subtract(BigInteger.ONE));
	private Participant myNavy;
	private ArrayList<Participant> participants;
	private int intervalSz = 100;
	private int numShips = 30;
	private int chopSz = 5;
	
	public BattleshipStategy () {
		logger = Logger.getLogger(this.getClass().getName());
		chord = new ChordImpl();
		chord.setCallback(this);
		TransactionID.getInstance();
		org.apache.log4j.Level level = org.apache.log4j.Level.WARN;
		org.apache.log4j.Logger.getRootLogger().setLevel(level);
		System.out.println("ooo LOG LEVEL: "+ org.apache.log4j.Logger.getRootLogger().getLevel());
	}
	
	public void startServer(String serverURL) {
		
		try {
			URL url = new URL(serverURL);
			chord.create(url);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		myID = chord.getID();
		myNavy = new Participant(myID, intervalSz);
		setShips();
	}
	
	public void startClient(String client, String server) {
		try {
			URL clientURL = new URL(client);
			URL bootstrapURL = new URL(server);
			chord.join(clientURL, bootstrapURL);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		myID = chord.getID();
		myNavy = new Participant(myID, intervalSz);
		setShips();
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
			shoot(); // TODO. select proper target
		}
	}

	private void initParticipants() throws CommunicationException {
		logger.error(myID+" initializing participants...");
		participants = new ArrayList<Participant>();
		Participant participant;
		ID prevID;
		ID currID=myID;
		boolean hereBeDragons = true;
		
		Node currNode = chord.getFingerTable().get(0).findSuccessor(BattleshipTools.increaseID(myID));
		
		while (hereBeDragons){
			prevID = currID;
			currID = currNode.getNodeID();
			
			logger.error(myID+" found new participant:\n\t"+currID);
			
			if (currID != myID) {
				participant = new Participant(currID, intervalSz);
				participant.setPredecessor(prevID);
				participant.calcInterval();
				participants.add(participant);
				currNode = currNode.findSuccessor(BattleshipTools.increaseID(currID));
			} else {
				hereBeDragons = false;
				myNavy.setPredecessor(prevID);
				myNavy.calcInterval();
			}
		}
		System.out.println(participants);
	}
	
	/* Check if this node has the biggest ID and thus gets to shoot first */
	private boolean iGoFirst() {
		/* because isInterval doesn't include the start or end ID, we have to increment our ID */
		return biggestKey.isInInterval(chord.getPredecessorID(), BattleshipTools.increaseID(myID));
	}
	
	private void setShips() {
		Random rand = new Random();

		// chop beginning/end off to avoid easy "iterate through beginning/end" hits
		int min = intervalSz / chopSz;
		int max = intervalSz - min;
		int shipPosition;
		
	    for (int i=0; i<numShips; i++) {
	    	shipPosition = rand.nextInt((max - min) + 1) + min;
	    	myNavy.setShip(shipPosition, 1);
	    }	    
	}
	
	/* Select suitable node and shoot at their part of the "sea".
	 */
	private void shoot() {
		// wait a while until everybody is ready again
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// TODO select target to shoot
		ID target = BattleshipTools.increaseID(myID);
		
		chord.retrieve(target);
	}
	
	/* This callback is called if I am the target of a retrieve, which attacks the "Sea" at target
	 * "coordinate". If a ship exists at target, it will be destroyed.
	 * NOTE: Everyone has to to figure out themselves which ID range belongs to which 
	 * participant, and attack accordingly!
	 */
	@Override
	public void retrieved(ID target) {
		logger.error("\n\t" + myID + "retrieved\n\t\t target: " + target);
		
		if (myNavy.isShip(target)){
			logger.error(myID+"HIT AT position "+myNavy.idToPosition(target));
			hit = true;
			myNavy.setShip(myNavy.idToPosition(target),
						   TransactionID.getTransactionID());
		} else {
			hit = false;
		}
		
		// inform all participants about the attack and its outcome.
		chord.broadcast(myID, hit);
		
		// Now it's our turn to shoot
		shoot();
	}
	
	/* This callback is called whenever a broadcast is sent by any node.
	 * Here, we can log what is going on, and adjust our strategy accordingly.
	 */
	@Override
	public void broadcast(ID source, ID target, Boolean hit) {
		logger.error("\n\t"+ myID + " received broadcast \n\t\t source: " + source +" target: " + target + "hit: " + hit);
		// TODO log things and stuff
		
		//Participant t = participants.get(target)
	}
}
