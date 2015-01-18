package Chord_Battleship;

import java.math.BigInteger;
import java.util.Arrays;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.util.logging.Logger;


public class Participant {
	private static final BigInteger MAX_ID = BigInteger.valueOf(2).pow(160).subtract(BigInteger.ONE);
	private Logger logger;
	private ID id, succ, pred;
	private int numSpaces;
	private int numShips;
	BigInteger spaceSz;
	private int[] ships; /* field values for...
							other participants: transactionID that sunk the ship,
												0 otherwise 
						 	my navy: 			1 if ship,
						 						transactionID if ship was sunk,
						 						0 otherwise */
	
	public Participant(ID id, int numSpaces, int numShips){
		this.id = id;
		this.numShips = numShips;
		this.numSpaces = numSpaces;
		ships = new int[numSpaces];
		
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	//!!!! WORK WITH PREDECESSORS, NOT SUCCESSORS
	
	public void setSuccessor(ID successor) {
		this.succ = successor;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return this.id;
	}
	
	public void setShip(int position, int value) {
		ships[position] = value;
	}

	public int hitID(int position) {
		return ships[position];
	}
	
	public boolean isShip(ID id) {
		int position = idToPosition(id);
		return ships[position] > 0;
	}
	
	public int idToPosition(ID id) {
		BigInteger start = BattleshipTools.increaseID(pred).toBigInteger();
		BigInteger id_bi = id.toBigInteger();
		/* position = (id-start)/spaceSz */
		BigInteger position = (id_bi.subtract(start)).divide(spaceSz);
		
		if (position.intValue() > numSpaces) {
			logger.error("something went terribly wrong.");
		}
		
		return position.intValue();
	}
	
	public ID positionToID(int position){
		// TODO: return ID in the middle of space at position
		return null;
	}
	
	/* Calculate the size of each space for a ship with respect to the 
	 * interval size
	 */
	public void calcInterval(){
		if (this.succ == null){
			logger.error("successor mist be set first!");
		}

		BigInteger from = this.id.toBigInteger();
		BigInteger to = BattleshipTools.increaseID(this.succ).toBigInteger();

		BigInteger diff = to.subtract(from);

		/* account for ID value wraparound */
        if(diff.compareTo(BigInteger.ZERO) == -1){
            diff = MAX_ID.add(diff);
        }
        
        /*TODO: what about the rest?*/
        spaceSz = diff.divide(BigInteger.valueOf(numSpaces));
	}
	
	@Override
	public String toString() {
		return("Ship ID: "+id+"\nShips at: "+Arrays.toString(ships)+"\n");
	}
}
