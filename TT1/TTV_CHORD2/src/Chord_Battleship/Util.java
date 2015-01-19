package Chord_Battleship;

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
}
