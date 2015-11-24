import java.util.ArrayList;

public class Peer{
	
	static ArrayList<Peer> peers = new ArrayList<Peer>();
	static int keyCounter = -1;
	final static int MAX_CONNECTIONS = 4;
	
	ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	ArrayList<Connection> connections = new ArrayList<Connection>();
	boolean complete = false;
	final int ID;
	String name;
	int maxIn = 8192;
	int maxOut = 1024;
	int trafficIn = 0;
	int trafficOut = 0;
	int x;
	int y;
	
	Peer(String name){
		ID = ++keyCounter;
		this.name = name;
		peers.add(this);
	}
	
	public String toString(){
		return name;
	}
	
	public void addConnection(Connection in){
		connections.add(in);
	}
	
	public void addTorrent(Torrent in){
		if(complete)
			in.flood(true);
		torrents.add(in);
	}
	
	public Peer getRandomPeer(){
		return peers.get((int)(peers.size()*Math.random()));
	}
	
	public int hasTorrent(int torrentID){	// TODO: Should this return a bool? Is the index necessary?
		for(int i=0; i<torrents.size(); ++i)
			if(torrents.get(i).ID == torrentID)
				return i;
		return -1;
	}
	
	public static Peer getFromID(int ID){	// TODO: add safety
		for(int i=0; i<peers.size(); ++i)
			if(peers.get(i).ID == ID)
				return peers.get(i);
		return null;
	}
	
	public Torrent getTorrent(int torrentID){ // TODO: add safety
		for(Torrent torrent: torrents)
			if(torrent.ID == torrentID)
				return torrent;
		return null;
	}
	
	public void tick(){
		// tick existing Connections
		for(Connection connection: connections){
			connection.tick();
			if(connection.kill){
				trafficOut -= connection.speed;
				Peer.getFromID(connection.peer2).trafficIn -= connection.speed;
				connections.remove(connection);
				Connection.connections.remove(connection);
			}
		}
		for(Torrent torrent: torrents){
		sendSection(torrent);
		}
	}

	public void sendSection(Torrent torrent){
		Peer peer = getRandomPeer();
			for(int i = 0; i<5; peer = getRandomPeer()){
				if(sendSectionPossible(peer, torrent)!=-1){
					System.out.println("Creating new connection for uploader " + name + " and downloader " + peer.name);
					Connection created = createConnection(peer, torrent);
					connections.add(created);
					Connection.connections.add(created);
				}
				i++;
			}
	}
	
	public Connection createConnection(Peer in2, Torrent torrent){	// created Connection holding payload of FIRST possible section to transfer.
		int speed = Math.min(((maxOut - trafficOut)/torrents.size()), in2.maxIn-in2.trafficIn);
		Connection out = new Connection(this.ID, in2.ID, new Section(torrent.ID, sendSectionPossible(in2, torrent)), speed);
		trafficOut += speed;
		in2.trafficIn += speed;
		return out;
	}
	
	public int sendSectionPossible(Peer peer2, Torrent torrent){	// checks for any possible sending of sections between Peers..
		if(connectionPossible(peer2))
			if(peer2.hasTorrent(torrent.ID)>=0){
				for(int i = 0; i<torrent.numSections; ++i){
					if(torrent.sections[i] == true && peer2.getTorrent(torrent.ID).sections[i] == false)
						return i;
				}
			}
		return -1;
	}
	
	public boolean connectionPossible(Peer peer2){		// checks for connection
		if(trafficOut < maxOut && peer2.trafficIn < peer2.maxIn && ID!=peer2.ID)
			return true;
		else
			return false;
	}
}
