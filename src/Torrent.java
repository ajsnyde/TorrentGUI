import java.util.ArrayList;

public class Torrent {
	static int keyCounter = -1;
	static ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	final int ID;
	String name;
	final int totalSize;
	final int sectionSize;
	final int numSections;
	Boolean[] sections;
	
	Torrent(String name, int totalSize, int sectionSize){
		ID = ++keyCounter;
		this.name = name;
		this.totalSize = totalSize;
		this.sectionSize = sectionSize;
		numSections = ((totalSize/sectionSize)+1);
		sections = new Boolean[numSections];
		flood(false);
		torrents.add(this);
	}
	
	public boolean isComplete(){	//FALSE NEGATIVE?
		for(int i = 0; i < numSections; ++i)
			if(!sections[i])
				return false;
		return true;
	}
	
	void flood(Boolean in){
		for(int i = 0; i < numSections; ++i)
			sections[i] = in;
	}	
	
	public String toString(){
		return name;
	}
	
	void setSection(int index, boolean in){
		sections[index] = in;
	}
	
	Boolean getSection(int index){
		if(index > numSections-1 || index < 0)
			return false;
		else
			return sections[index];
	}
	
	public static Torrent getFromID(int ID){
		for(int i=0; i<torrents.size(); ++i)
			if(torrents.get(i).ID == ID)
				return torrents.get(i);
		return null;
	}
}
