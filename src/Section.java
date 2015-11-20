
public class Section {
	static int keyCounter = -1;
	int ID;
	boolean complete;
	int senderID;
	int receiverID;
	int size = 64;	//size kbits
	int sent = 0;
	int torrentID;
	
	Section(int torrentID, int senderID, int recieverID){
		ID = ++keyCounter;
		this.senderID = senderID;
		this.receiverID = recieverID;
		this.torrentID = torrentID;
	}
	Section(int torrentID, boolean complete){
		ID = ++keyCounter;
		this.complete = complete;
		this.torrentID = torrentID;
	}
	
}
