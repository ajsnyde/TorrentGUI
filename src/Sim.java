import java.awt.Color;
import java.util.ArrayList;

public class Sim {
	ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	static ArrayList<Peer> peers = new ArrayList<Peer>();
	ArrayList<Connection> connections = new ArrayList<Connection>();
	
	
	Sim(){
		addPeer(new Peer("Hi!", new Color(1,1,1)));
		addPeer(new Peer("Hi!", new Color(1,1,1)));
		addPeer(new Peer("Hi!", new Color(1,1,1)));
	}
	
	
	public static void addPeer(Peer in){
		peers.add(in);
	}
	public Peer getPeer(int i){
		if(i >= 0 && i < peers.size())
			return peers.get(i);
		else
			return new Peer("FAILED", new Color(0,0,0));
	}
}


