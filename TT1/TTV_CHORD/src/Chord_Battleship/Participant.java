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
	BigInteger spaceSz;
	boolean hasWraparound;
	BigInteger wrapInterval;
	
	private int[] ships; /* field values for...
							other participants: transactionID that sunk the ship,
												0 otherwise 
						 	my navy: 			1 if ship,
						 						transactionID if ship was sunk,
						 						0 otherwise */
	
	public Participant(ID id, int numSpaces){
		this.id = id;
		this.numSpaces = numSpaces;
		ships = new int[numSpaces];
		
		logger = Logger.getLogger(this.getClass().getName());
	}
		
	public void setPredecessor(ID pred) {
		this.pred = pred;
		if (BattleshipTools.increaseID(this.pred).toBigInteger()
							.compareTo(id.toBigInteger()) == 1) {
			hasWraparound = true;
			wrapInterval = MAX_ID.subtract(pred.toBigInteger());
		} else {
			hasWraparound = false;
		}
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
			id_bi = wraparoundNormalize(start, id_bi);
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
		ID posID;

		/*calculate offset*/
		BigInteger b = BigInteger.valueOf(position).multiply(spaceSz);
		
		if(hasWraparound) {
			if (b.compareTo(wrapInterval) == 1) {
				/* offset is bigger than the interval between predecessor
				 * and 0 => we have to be careful here */
				b = b.subtract(wrapInterval);
			}
		} else {
			/* add to start */
			b = b.add(BattleshipTools.increaseID(pred).toBigInteger());
		}
		
		/* make sure we shoot in the middle of the field */
		b = b.add(spaceSz.divide(BigInteger.valueOf(2)));
		posID = new ID(b.toByteArray());
		return posID;
	}
	
	/* Calculate the size of each space for a ship with respect to the 
	 * interval size
	 */
	public void calcInterval(){
		if (this.pred == null){
			logger.error("predeccessor mist be set first!");
		}

		BigInteger from = BattleshipTools.increaseID(this.pred).toBigInteger();
		BigInteger to = BattleshipTools.decreaseID(this.id).toBigInteger();

		BigInteger diff;

		if(hasWraparound) {
			diff = wraparoundNormalize(from, to);
		} else {
			diff = to.subtract(from);
		}
        
        /*TODO: what about the rest?*/
        spaceSz = diff.divide(BigInteger.valueOf(numSpaces));
	}
	
	/* When the predecessor of an ID is bigger than the ID
	 * (i.e. the interval between them crosses point 0 of the
	 * CHORD ring), "normalize" the interval between them
	 * by shifting it to start on point 0 and return the
	 * "new", normalized ID (pred is implicitly 0 then)
	 */
	private BigInteger wraparoundNormalize(BigInteger start, BigInteger id_bi) {
		BigInteger diff = MAX_ID.subtract(start);
		start = BigInteger.valueOf(0);
		return diff.add(id_bi);
	}
	
	@Override
	public String toString() {
		return("Ship ID: "+id+"\nShips at: "+Arrays.toString(ships)+"\n");
	}
}
