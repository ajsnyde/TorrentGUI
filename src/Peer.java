import java.awt.Color;
import java.util.ArrayList;

public class Peer implements Algorithm{
	static int keyCounter = -1;
	final int ID;
	Color color;
	String name;
	int x, y;
	ArrayList<Torrent> torrents;
	ArrayList<Connection> connections;
	Node node;
	
	Peer(String name, Color color){
		ID = ++keyCounter;
		this.name = name;
		this.color = color;
		node = new Node();
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
	
	public void generateConnections(int Totalcapacity){
		connections.clear();
		for(Peer peer: Sim.peers){
			if(peer.ID != this.ID){
				connections.add(new Connection(this.ID, peer.ID, Totalcapacity));
			}
		}
	}
	
	public int getRecipient(){
		for(int i = 0; i<connections.size(); ++i){
			if(connections.get(i).totalTraffic != connections.get(i).totalCapacity)
				return i;			
		}
		return ID;
	}
	
	public int hasTorrent(int torrentID){
		for(int i=0; i<torrents.size(); ++i)
			if(torrents.get(i).ID == torrentID)
				return i;
		return -1;
	}
	
	public void giveSection(Section in){
		
		// TODO
		
	}
	
	@Override
	public int findSection() {
		
		return 0;
	}
	
}
