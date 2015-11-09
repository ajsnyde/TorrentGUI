import java.awt.Color;
import java.util.ArrayList;

public class Sim {
	ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	ArrayList<Peer> peers = new ArrayList<Peer>();
	ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public void addPeer(Peer in){
		peers.add(in);
	}
	public Peer getPeer(int i){
		if(i >= 0 && i < peers.size())
			return peers.get(i);
		else
			return new Peer("FAILED", new Color(0,0,0));
	}
}


