package Chord_Battleship;

import java.math.BigInteger;

import de.uniba.wiai.lspi.chord.data.ID;



public class Util {
	public static boolean isIDinRange(ID start, ID end, ID probe) {
        if(start.compareTo(end) == 0 || probe.compareTo(start) == 0 || probe.compareTo(end) == 0) {
            return false;
        }

        // No wrap-around of the range hash
        if(end.compareTo(start) == 1) {
            return probe.compareTo(start) == 1 && probe.compareTo(end) == -1;
        }

        // Range has wraps around
        return probe.compareTo(start) == 1 ^ probe.compareTo(end) == -1;
    }
	
    public static ID incrementID(ID id){
    	return ID.valueOf(id.toBigInteger().add(BigInteger.ONE).mod(BigInteger.valueOf(2).pow(160)));
    }
    
    public static ID decrementID(ID id){
    	return ID.valueOf(id.toBigInteger().subtract(BigInteger.ONE).mod(BigInteger.valueOf(2).pow(160)));
    }
}
