package Chord_Battleship;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
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
			
			// select random participant and shoot at their last field
			ID target = Util.decrementID(chord.getPredecessorID());
			shoot(target);
		}
	}
	
	private ID selectTarget() {
		// find the one with the fewest ships (yes, clumsy, could be done better)
		Participant targetParticipant = participants.get(0);
		int maxSunkShips = targetParticipant.numSunkShips();
		logger.error("[SELECTTARGET]");

		for (Participant p: participants) {
			if (p.numSunkShips() > maxSunkShips) {
				targetParticipant = p;
				maxSunkShips = p.numSunkShips();
			}
		}
		
		boolean foundPosition = false;
		int offset = 1;
		int position = targetParticipant.getLastSunkShip();
		while (!foundPosition){
			if (offset+position >= intervalSize) {
				// do nothing
			} else if (offset +position < 0) {
				// do nothing
			} else {
				if (targetParticipant.getShipStatusOn(position+offset) == 0) {
					logger.error("[SELECTTARGET] selected Target Participant "
								+"\n\tmy ID:"+myID
								+"\n\tparticipant:"+targetParticipant.getID()
								+"\n\ttarget: "+targetParticipant.positionToID(position+offset));
					return targetParticipant.positionToID(position+offset);
				}
			}
			
			if (offset>0){
				offset *= (-1);
			} else {
				offset *= (-1);
				offset += 1;
			}
		}		
		logger.error("[SELECTTARGET] Couldn't find suitable target. We're doomed.");
		
		// we're doomed.
		return null;
	}
	
	private void shoot(ID target) {
		// wait a while until everybody is ready again
		logger.error("[SHOOT] \n\tmy ID:"+myID+"\n\ttarget:"+target);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		chord.retrieve(target);
	}
	
	@Override
	public void retrieved(ID target) {
		new Thread(new Runnable(){
			public void run(){
				logger.error("[RETRIEVED]\n\t" + "My ID: " + myID + "\n\tTarget:" + target);
				boolean hit;
				
				int position = myNavy.idToPosition(target);
				//logger.error("Getting Ship status for position:"+position);
				int shipStatus = myNavy.getShipStatusOn(position);
				if (shipStatus == 0){
					hit = false;
				} else if (shipStatus == 1){
					hit = true;
				} else {
					logger.warn("[RETRIEVED] Shot at ship that aleady sunk.. Ha ha!");
					hit = true;
				}
				
				// inform all participants about the attack and its outcome.
				chord.broadcast(target, hit);
				if (myNavy.getSunkShips() == 100) {
					logger.error("[retrieved] OH NO! WE LOST!!! :((");
					throw new NotImplementedException();
				} else {
					ID newTarget = selectTarget();
					shoot(newTarget);	
				}
			}
		}).start();
	}

	@Override
	public void broadcast(ID source, ID target, Boolean hit) {
		logger.error("[BROADCAST RECEIVED]\n\t" + "My ID: " + myID + "\n\tSource: " + source + "\n\tTarget:" + target + "\n\tHit:" + hit);
		
		if (myNavy.isMyTerritory(target)) {
			logger.error("[BROADCAST RECEIVED] Target is me; ignoring broadcast");
			return;
		}
		
		Participant participant = null;
		
		for (Participant p: participants) {
			logger.warn("[BROADCAST RECEIVED] Checking "+p.getID());
			if (p.isMyTerritory(target)){
				participant = p;
				break;
			}
		}
		if (participant == null) {
			logger.error("[BROADCAST RECEIVED] [SOS] No matching participant found for target "+target+"\n\t My ID: "+myID);
			// TODO error handling
		} else {
			logger.error("[BROADCAST RECEIVED] Found matching participant for "+target+": "+participant.getID());
			if (hit) {
				participant.setShip(participant.idToPosition(target), TransactionID.get());
			} else {
				participant.setShip(participant.idToPosition(target), -1);
			}
			if (myNavy.getID().equals(source)) {
				if (participant.getSunkShips() == 100) {
					for (int i = 0; i< 6; i++ ) {
						logger.error("[BROADCAST RECEIVED] WE WON! WE WON! WE WON!");
					}
				}
			}
		}
	}

	private void initParticipants() throws CommunicationException {
		participants = new ArrayList<Participant>();
		
		myNavy.setPredecessor(chord.getPredecessorID());
		myNavy.calcInterval();

		Participant participant;
		Node currentNode = chord.findSuccessor(Util.incrementID(myID));
		ID predID = myID;
		
		while (!currentNode.getNodeID().equals(myID)) {
			participant = new Participant(currentNode.getNodeID(), intervalSize);
			participant.setPredecessor(predID);
			participant.calcInterval();
			participants.add(participant);
		
			predID = currentNode.getNodeID(); 
			currentNode = currentNode.findSuccessor(Util.incrementID(currentNode.getNodeID()));
		}
		logger.warn("[INIT PARTICIPANTS] ID: "+myID
					+"\n\tfound "+participants.size()+" other participants");
	}
	
	/* Check if this node has the biggest ID and thus gets to shoot first */
	private boolean iGoFirst() {
		/* because isInterval doesn't include the start or end ID, we have to increment our ID */
		return biggestKey.isInInterval(chord.getPredecessorID(), Util.incrementID(myID));
	}

}
