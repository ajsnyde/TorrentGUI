import java.awt.Color;
import java.util.ArrayList;

public class Peer implements Algorithm{
	static int keyCounter = -1;
	final int ID;
	final int MAX_CONNECTIONS = 4;
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
	
	public String toString(){
		return name;
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
		while(connections.size() < MAX_CONNECTIONS)
			for(Torrent torrent: torrents)	// round-robin torrent selection, giving connections as possible
				if(torrent.isComplete()){
				{
					
				}
			}
	}
	
	public void findCandidate(Torrent torrent){
			Peer peer = null;
			for(int i = 0; i<5; peer = getRandomPeer())
				if(sendSectionPossible(this, peer, torrent)!=-1){
					
					
					
					
					
					
					
					System.out.println("Creating new connection for uploader " + name + " and downloader " + peer.name);
					connections.add(new Connection(ID, peer.ID, Math.min(node.maxOut - node.trafficOut, peer.node.maxIn - peer.node.trafficIn)));
					Sim.connections.add(new Connection(ID, peer.ID, Math.min(node.maxOut - node.trafficOut, peer.node.maxIn - peer.node.trafficIn)));
					node.trafficOut = node.maxOut;
					peer.node.trafficIn = peer.node.maxIn;
					connections.get(connections.size()-1).updateColor();
					break;
				}
	}
	
	public Connection createConnection(Peer in1, Peer in2, Torrent torrent){
		
		
	}
	
	
	
	public Peer getRandomPeer(){
		return Sim.peers.get((int)(Sim.peers.size()*Math.random()));
	}
	
	
	public static int sendSectionPossible(Peer in1, Peer in2, Torrent torrent){			// checks for any possible sending of sections between Peers..
		if(connectionPossible(in1, in2))
			if(in2.hasTorrent(torrent.ID)>=0){
				for(int i = 0; i<torrent.sections.size(); ++i){
					if(torrent.sections.get(i).complete == true && in2.torrents.get(in2.hasTorrent(torrent.ID)).sections.get(i).complete == false)
						return i;
				}
			}
		return -1;
	}
	
	public static boolean connectionPossible(Peer in1, Peer in2){		// checks for connection
		if(in1.node.getTrafficOut() < in1.node.getMaxOut() && in2.node.getTrafficIn() < in2.node.getMaxIn() && !in1.equals(in2))
			return true;
		else
			return false;
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
