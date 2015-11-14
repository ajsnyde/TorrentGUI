import java.util.ArrayList;

public class Torrent {
	static int keyCounter = -1;
	final int ID;
	String name;
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
	
	Torrent(int size, String name){
		ID = ++keyCounter;
		sections = new ArrayList<Boolean>(size);
		this.name = name;
	}
	
	void reset(){
		for(int i = 0; i < sections.size(); ++i){
			sections.set(i, false);
		}
	}
	
	void set(int index, boolean in){
		sections.set(index, in);
	}
	
	int getID(){
		return ID;
	}
	int getkeyCounter(){
		return keyCounter;
	}
	
	void initializeFalse(int size){
		sections.clear();
		for(int i = 0; i < size; ++i)
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
