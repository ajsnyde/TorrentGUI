import java.awt.Color;
import java.util.ArrayList;

public class Peer implements Algorithm{
	static int keyCounter = -1;
	final int ID;
	Color color;
	String name;
	ArrayList<Torrent> torrents;
	ArrayList<Connection> connections;
	
	Peer(String name, Color color){
		ID = ++keyCounter;
		this.name = name;
		this.color = color;
		torrents = new ArrayList<Torrent>();
		connections = new ArrayList<Connection>();
	}
	Peer(String name){
		ID = ++keyCounter;
		this.name = name;
		this.color = new Color(0,0,0);
		torrents = new ArrayList<Torrent>();
		connections = new ArrayList<Connection>();
	}
	
	// Obvious/simple getters/setters
	public int getkeyCounter(){
		return keyCounter;
	}
	public int getID(){
		return ID;
	}
	public void addConnection(Connection in){
		connections.add(in);
	}
	public void addTorrent(Torrent in){
		torrents.add(in);
	}
	@Override
	public void run() {
		
		
	}
	
	public int getRecipient(){
		for(int i = 0; i<connections.size(); ++i){
			if(connections.get(i).totalTraffic != connections.get(i).totalCapacity)
				return i;			
		}
		return ID;
	}
	
	public void giveSection(Section in){
		
		// TODO
		
	}
	
	@Override
	public int findSection() {
		
		return 0;
	}
	
}
