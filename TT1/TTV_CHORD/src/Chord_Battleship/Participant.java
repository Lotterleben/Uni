package Chord_Battleship;

import java.util.Arrays;

import de.uniba.wiai.lspi.chord.data.ID;


public class Participant {
	private ID id;
	private int intervalSz;
	private int numShips;
	private int[] ships; /* field values for...
							other participants: transactionID that sunk the ship,
												0 otherwise 
						 	my navy: 			1 if ship,
						 						transactionID if ship was sunk,
						 						0 otherwise */
	// TODO: range?!
	
	public Participant(ID id, int intervalSz, int numShips){
		this.id = id;
		this.numShips = numShips;
		this.intervalSz = intervalSz;
		ships = new int[intervalSz];
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
	
	@Override
	public String toString() {
		return("Ship ID: "+id+"\nShips at: "+Arrays.toString(ships));
	}
}
