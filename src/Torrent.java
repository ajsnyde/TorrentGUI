import java.util.ArrayList;

public class Torrent {
	static int keyCounter = -1;
	final int ID;
	String name;
	ArrayList<Boolean> sections;
	
	Torrent(){
		ID = ++keyCounter;
		sections = new ArrayList<Boolean>(32);
	}
	
	Torrent(int size){
		ID = ++keyCounter;
		sections = new ArrayList<Boolean>(size);
	}
	
	void reset(){
		for(int i = 0; i < sections.size(); ++i){
			sections.set(i, false);
		}
	}
	
	void add(int index){
		sections.set(index, true);
	}
	
	int getID(){
		return ID;
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
