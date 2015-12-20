import java.util.ArrayList;
import java.util.logging.*;

public class Peer{
	
	static ArrayList<Peer> peers = new ArrayList<Peer>();
	static int keyCounter = -1;
	final static int MAX_CONNECTIONS = 4;
	
	static Logger logger = Logger.getLogger("peerLogger");
	ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	ArrayList<Connection> connections = new ArrayList<Connection>();
	boolean seed;
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
		seed = false;
		logger.info("Created peer " + name);
	}
	
	public String toString(){
		return name;
	}
	
	public void addConnection(Connection in){
		connections.add(in);
	}
	
	public void addTorrent(Torrent in){
		if(seed){
			System.out.println("Flooding torrent " + in.ID + " with TRUE");
			in.flood(true);
		}
		else{
			System.out.println("Flooding torrent " + in.ID + " with FALSE");
			in.flood(false);
		}
		System.out.println("IS COMPLETE: " + in.isComplete());
		this.torrents.add(in);
	}
	
	public Peer getRandomPeer(){	// should never run without peers availble to return
		return peers.get((int)(peers.size()*Math.random()));
	}
	
	public int hasTorrent(int torrentID){	// TODO: Should this return a bool? Is the index necessary?
		for(int i=0; i<torrents.size(); ++i){
			logger.info("Comparing: " + torrentID + " and " + torrents.get(i).ID);
			if(torrents.get(i).ID == torrentID)
				return i;
		}
		return -1;
	}
	
	public void showTorrents(){	// TODO: Should this return a bool? Is the index necessary?
		System.out.println(name + " has the following torrents:");
		for(Torrent torrent: this.torrents){
			System.out.println(torrent.name + " - " + torrent.ID + " - " + torrent.isComplete());
			for(int i = 0; i < torrent.numSections; ++i)
				System.out.println(torrent.sections[i]);
			
		}
	}
	
	public static Peer getFromID(int ID){	// TODO: add safety
		for(int i=0; i<peers.size(); ++i)
			if(peers.get(i).ID == ID)
				return peers.get(i);
		logger.warning("getFromID() returning NULL!");
		return null;
	}
	
	public Torrent getTorrent(int torrentID){ // TODO: add safety
		for(Torrent torrent: torrents)
			if(torrent.ID == torrentID)
				return torrent;
		logger.warning("getTorrent() returning NULL!");
		return null;
	}
	
	public void tick(){
		// tick existing Connections
		for(Connection connection: connections){
			connection.tick();
			if(connection.kill){
				logger.info(connection.ID + "is being destroyed.");
				trafficOut -= connection.speed;
				Peer.getFromID(connection.peer2).trafficIn -= connection.speed;
				connections.remove(connection);
				Connection.connections.remove(connection);
			}
		}
		for(Torrent torrent: torrents){
		logger.info("Running torrent loop, attempting to send section for each torrent in peer " + this.name);
		sendSection(torrent);
		}
	}

	public void sendSection(Torrent torrent){
		Peer peer = getRandomPeer();
		logger.info("Random peer selected: " + peer.name);
			for(int i = 0; i<5; peer = getRandomPeer()){
				logger.info("Random peer selected: " + peer.name);
				if(sendSectionPossible(peer, torrent)!=-1){		// This code never passes.. Problem area..
					logger.info("Random peer sendSection possible with: " + peer.name);
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
		if(connectionPossible(peer2)){
			//logger.info("ConnectionPossible passes: " + peer2.name);
			if(peer2.hasTorrent(torrent.ID)>=0){	// FAILS! False negative
				logger.info("hasTorrent passes: " + peer2.name);
				for(int i = 0; i<torrent.numSections; ++i){
					if(torrent.sections[i] == true && peer2.getTorrent(torrent.ID).sections[i] == false)
						return i;
				}
			}
		}
		logger.info("SendSectionPossible returns -1: " + peer2.name);
		return -1;
	}
	
	public boolean connectionPossible(Peer peer2){		// checks for connection
		if(trafficOut < maxOut && peer2.trafficIn < peer2.maxIn && ID!=peer2.ID)
			return true;
		else
			return false;
	}
}
