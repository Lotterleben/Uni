package Chord_Battleship;

public class TransactionID {
	private static Integer id;
	private static TransactionID transactionID; 
	
	private TransactionID(){
		id = 0;
	}
	
	public static synchronized TransactionID getInstance() {
		if (TransactionID.transactionID == null){
			TransactionID.transactionID = new TransactionID();
		}
		return TransactionID.transactionID;
	}
	
	public static Integer getTransactionID() {
		printChange();
		return id;
	}
	
	public static void setTransactionID(Integer transactionID) {
		printChange();
		id = transactionID;
	}
	
	public static void incTransactionID() {
		printChange();
		id++;
	}

	// only for debugging purposes
	private static void printChange(){
		System.out.println("new transaction ID :" +id);
	}
}
