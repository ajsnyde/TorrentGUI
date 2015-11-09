
public class Section {
	static int keyCounter = -1;
	int ID;
	Boolean complete;
	int senderID;
	int receiverID;
	int size = 64;	//size kbits
	int sent = 0;
	
	Section(int senderID, int recieverID){
		ID = ++keyCounter;
		this.senderID = senderID;
		this.receiverID = recieverID;
	}
}
