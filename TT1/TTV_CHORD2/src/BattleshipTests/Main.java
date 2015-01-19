package BattleshipTests;

import java.math.BigInteger;

import Chord_Battleship.Participant;
import de.uniba.wiai.lspi.chord.data.ID;

public class Main {
	
	public static void main(String[] args) {

		testCalcInterval();		
	}

	public static void test(boolean x, String msg) {
		System.out.print(msg +": ");
		if (x) {
			System.out.println("PASSED :)");
		} else {
			System.out.println("FAILED :(");
		}
	}

	public static void testEquals(Object expected, Object result, String msg) {
		System.out.print(msg +": ");
		if (expected.equals(result)) {
			System.out.println("PASSED :)");
		} else {
			System.out.println("FAILED :( | expected:"+expected+" result:"+result);
		}
	}
	
	public static void testCalcInterval() {
		ID id = ID.valueOf(BigInteger.valueOf(50));
		ID pred = ID.valueOf(BigInteger.valueOf(2).pow(160).subtract(BigInteger.TEN));
		int intervalSize = 10;
		
		Participant p = new Participant(id, intervalSize);
		p.setPredecessor(pred);
		
		testEquals(BigInteger.valueOf(5), p.calcInterval(), "Calculate Interval correctly");
	}
}
