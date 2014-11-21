package Chord_Battleship;

import java.net.MalformedURLException;

import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.NotifyCallback;
import de.uniba.wiai.lspi.chord.service.PropertiesLoader;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;

public class Chord_Battleship implements NotifyCallback{

	private ChordImpl chord = null;
	
	public void startServer(String serverURL) {
		PropertiesLoader.loadPropertyFile();
		chord = new ChordImpl();
		chord.setCallback(this);
		try {
			URL url = new URL(serverURL);
			chord.create(url);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startClient(String client, String server) {
		PropertiesLoader.loadPropertyFile();
		chord = new ChordImpl();
		chord.setCallback(this);
		try {
			URL clientURL = new URL(client);
			URL bootstrapURL = new URL(server);
			chord.join(clientURL, bootstrapURL);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

}
