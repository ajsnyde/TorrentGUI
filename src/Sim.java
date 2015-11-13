import java.awt.Color;
import java.util.ArrayList;

public class Sim {
	static ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	static ArrayList<Peer> peers = new ArrayList<Peer>();
	static ArrayList<Connection> connections = new ArrayList<Connection>();
	
	
	Sim(){		// TESTING ALSO...
		addPeer(new Peer("Hi!", new Color(1,1,1)));
		addPeer(new Peer("Hi1!", new Color(1,1,1)));
		addPeer(new Peer("Hi2!", new Color(1,1,1)));
		addTorrent(new Torrent());
	}
	
	public static void addPeer(Peer in){
		peers.add(in);
	}
	public static void addTorrent(Torrent in){
		torrents.add(in);
	}
	
	public Peer getPeer(int i){
		if(i >= 0 && i < peers.size())
			return peers.get(i);
		else
			return new Peer("FAILED", new Color(0,0,0));
	}
	
	public Peer getFromID(int ID){
		for(int i=0; i<peers.size(); ++i)
			if(peers.get(i).ID == ID)
				return peers.get(i);
		return null;
	}
	
	
	public void tick(){
		
	}
}


