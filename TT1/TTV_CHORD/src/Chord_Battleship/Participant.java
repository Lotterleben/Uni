package Chord_Battleship;

import java.math.BigInteger;
import java.util.Arrays;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.util.logging.Logger;


public class Participant {
	private static final BigInteger MAX_ID = BigInteger.valueOf(2).pow(160).subtract(BigInteger.ONE);
	private Logger logger;
	private ID id,pred;
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
		
	public void setPredecessor(ID pred) {
		this.pred = pred;
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
	
		/* wraparound */
		if(start.compareTo(id_bi) == 1) {
			BigInteger diff = MAX_ID.subtract(start);
			start = BigInteger.valueOf(0);
			id_bi = diff.add(id_bi);
		}

		/* position = (id-start)/spaceSz */
		BigInteger sub = id_bi.subtract(start);
		BigInteger position = (sub).divide(spaceSz);
		
		if (position.intValue() > numSpaces 
			|| position.intValue() < 0 ) {
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
		if (this.pred == null){
			logger.error("predeccessor mist be set first!");
		}

		BigInteger from = this.id.toBigInteger();
		BigInteger to = BattleshipTools.increaseID(this.pred).toBigInteger();

		BigInteger diff = to.subtract(from);

		/* account for ID value wraparound */
		// TODO i think this is BS
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
