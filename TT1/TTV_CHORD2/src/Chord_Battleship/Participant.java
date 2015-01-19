package Chord_Battleship;

import java.util.Arrays;
import java.util.Random;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.util.logging.Logger;

public class Participant {
	private Logger logger;
	private ID id;
	private ID predecessor;
	private int intervalSize;
	
	private int[] ships; /* field values for...
							other participants: transactionID that sunk the ship,
												0 otherwise 
						 	my navy: 			1 if ship,
						 						transactionID if ship was sunk,
						 						0 otherwise */

	public Participant(ID id, int intervalSize){
		this.id = id;
		this.intervalSize = intervalSize;
		ships = new int[intervalSize];
		
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public void setShip(int position, int value) {
		ships[position] = value;
	}
	
	public void setPredecessor(ID predecessor) {
		this.predecessor = predecessor;
	}
	
	/* Set ships according to our strategy. 
	 * This should ONLY be called for the Participant object
	 * which represents a node's own navy! */
	public void setShips(int intervalSize, int chopSize, int numShips) {
		Random rand = new Random();

		// chop beginning/end off to avoid easy "iterate through beginning/end" hits
		int min = intervalSize / chopSize;
		int max = intervalSize - min;
		int shipPosition;
		
	    for (int i=0; i<numShips; i++) {
	    	shipPosition = rand.nextInt((max - min) + 1) + min;
	    	setShip(shipPosition, 1);
	    }
	    logger.warn("[SET SHIPS]\n\tpositions:"+Arrays.toString(ships));
	}
	
	public void calcInterval() {
		// TODO
	}
}
