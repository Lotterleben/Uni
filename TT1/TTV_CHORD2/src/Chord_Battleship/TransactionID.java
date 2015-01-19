package Chord_Battleship;

import de.uniba.wiai.lspi.util.logging.Logger;

public class TransactionID {
	private Integer id;
	private Logger logger;
	
	private static TransactionID instance; 
	
	private TransactionID(){
		logger = Logger.getLogger(this.getClass().getName());
		id = 0;
	}
	
	public static synchronized TransactionID getInstance() {
		if (TransactionID.instance == null){
			TransactionID.instance = new TransactionID();
		}
		return TransactionID.instance;
	}
	
	public static synchronized Integer get() {
		getInstance().printChange();
		return getInstance().id;
	}
	
	public static synchronized void set(Integer transactionID) {
		getInstance().printChange();
		getInstance().id = transactionID;
	}
	
	public static synchronized void inc() {
		getInstance().printChange();
		getInstance().id++;
	}

	// only for debugging purposes
	private void printChange(){
		logger.error("[TransactionID]\n\t" + "ID: " + id);
	}
}