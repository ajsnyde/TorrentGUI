public class Section {
	
	static int keyCounter = -1;
	final int ID;
	final int size;	//size kbits
	final int sectionNum;
	int sent = 0;
	int torrentID;
	
	Section(int torrentID, int sectionNum){	//TODO: add safety
		ID = ++keyCounter;
		this.torrentID = torrentID;
		this.sectionNum = sectionNum;
		size = Torrent.getFromID(torrentID).sectionSize;
	}
	public boolean isComplete(){
		return (sent >= size);
	}
}
