package Chord_Battleship;

import java.math.BigInteger;

import de.uniba.wiai.lspi.chord.data.ID;

public class BattleshipTools {

    public static ID increaseID(ID id){
    	return ID.valueOf(id.toBigInteger().add(BigInteger.ONE).mod(BigInteger.valueOf(2).pow(160)));
    }
}
