package Chord_Battleship;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.util.logging.Logger;

public class Participant {
	
	private static final BigInteger MAX_ID = BigInteger.valueOf(2).pow(160).subtract(BigInteger.ONE);
	private Logger logger;
	private ID id;
	private ID predecessor;
	private int intervalSize;
	BigInteger spaceSize;
	private boolean hasWraparound;
	BigInteger wrapInterval;
	
	private int[] ships; /* field values for...
							other participants: transactionID that sunk the ship,
												-1 if shot but no ship sunk,
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
		if (this.predecessor.compareTo(id) == 1) {
			hasWraparound = true;
			wrapInterval = MAX_ID.subtract(predecessor.toBigInteger());
		} else {
			hasWraparound = false;
		}
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
	
	public BigInteger calcInterval() {
		if (this.predecessor == null){
			logger.error("[PARTICIPANT] predeccessor must be set first!");
			throw new RuntimeException();
		}
		
		BigInteger from = Util.incrementID(predecessor).toBigInteger();
		BigInteger to = Util.decrementID(id).toBigInteger();
		
		BigInteger diff;
		
		if(hasWraparound) {
			diff = wraparoundNormalize(from, to);
		} else {
			diff = to.subtract(from);
		}
		
        /*TODO: what about the rest?*/
        spaceSize = diff.divide(BigInteger.valueOf(intervalSize));
        
        return spaceSize;
	}
	
	public boolean isMyTerritory(ID id) {
		return Util.isIDinRange(predecessor, this.id, id);
	}
	
	public int idToPosition(ID id) {
		BigInteger start = Util.incrementID(predecessor).toBigInteger();
		BigInteger end = id.toBigInteger();
		
		/* wraparound */
		if(start.compareTo(end) == 1) {
			end = wraparoundNormalize(start, end);
			start = BigInteger.valueOf(0);
		}

		/* position = (id-start)/spaceSz */
		BigInteger sub = end.subtract(start);
		BigInteger position = (sub).divide(spaceSize);
		
		if (position.intValue() > intervalSize) {
			// caution dirty hack
			position = BigInteger.valueOf(intervalSize-1);
		} else if (position.intValue() < 0 ) {
			logger.error("[ID TO POSITION] calculated position out of bounds: "+position+"expected: 0<=position<"+intervalSize);
			return -1;
		}
		
		return position.intValue();
	}
	
	/* When the predecessor of an ID is bigger than the ID
	 * (i.e. the interval between them crosses point 0 of the
	 * CHORD ring), "normalize" the interval between them
	 * by shifting it to start on point 0 and return the
	 * "new", normalized ID (pred is implicitly 0 then)
	 */
	private BigInteger wraparoundNormalize(BigInteger start, BigInteger end) {
		BigInteger diff = MAX_ID.subtract(start).add(BigInteger.ONE);
		return diff.add(end);
	}
	
}
