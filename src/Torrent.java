import java.util.ArrayList;

public class Torrent {
	static int keyCounter = -1;
	final int ID;
	String name;
	int totalSize;
	int sectionSize = 8;
	ArrayList<Boolean> sections;
	
	Torrent(){
		ID = ++keyCounter;
		sections = new ArrayList<Boolean>(32);
		name = "default";
	}
	
	Torrent(int size){
		ID = ++keyCounter;
		sections = new ArrayList<Boolean>(size);
		name = "torrent_"+ID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSectionSize() {
		return sectionSize;
	}

	public void setSectionSize(int sectionSize) {
		this.sectionSize = sectionSize;
	}

	public static int getKeyCounter() {
		return keyCounter;
	}

	Torrent(int totalSize, int sectionSize, String name){
		ID = ++keyCounter;
		this.totalSize = totalSize;
		this.sectionSize = sectionSize;
		this.name = name;
		if(sectionSize != 0)
			sections = new ArrayList<Boolean>((totalSize/sectionSize)+1);
		else
			sections = new ArrayList<Boolean>((totalSize/8)+1);
	}
	
	void reset(){
		for(int i = 0; i < sections.size(); ++i){
			sections.set(i, false);
		}
	}
	
	void setSection(int index, boolean in){
		sections.set(index, in);
	}
	
	int getID(){
		return ID;
	}
	
	void initializeFalse(){
		sections.clear();
		for(int i = 0; i < (totalSize/sectionSize)+1; ++i)
			sections.add(true);	
	}
	
	Boolean check(int index){
		try{
		if(sections.get(index) == true)
			return true;
		else
			return false;
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Out of bounds error! Returning false in check!");
			return false;
		}
	}
}
