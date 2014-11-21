package Chord_Battleship;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.service.PropertiesLoader;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;

public class Chord_Battleship implements de.uniba.wiai.lspi.chord.service.NotifyCallback{

	private ChordImpl chord = null;
	@Override
	public void retrieved(ID target) {
		System.out.println("retrieved " + target);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcast(ID source, ID target, Boolean hit) {
		System.out.println("broadcast ; source: " + source +" target: " + "hit: " + hit);
		// TODO Auto-generated method stub
		
	}
	
	public void start (){
		PropertiesLoader.loadPropertyFile();
		chord = new ChordImpl();
		chord.setCallback(this);
	}

}
