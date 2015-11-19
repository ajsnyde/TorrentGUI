import java.awt.Color;
import java.util.ArrayList;

public class Sim {
	static ArrayList<Torrent> torrents = new ArrayList<Torrent>();
	static ArrayList<Peer> peers = new ArrayList<Peer>();
	static ArrayList<Connection> connections = new ArrayList<Connection>();
	
	
	Sim(){
		
	}
	
	public static void addPeer(Peer in){
		peers.add(in);
	}
	public static void addTorrent(Torrent in){
		torrents.add(in);
	}
	
	public static Peer getPeer(int i){
		if(i >= 0 && i < peers.size())
			return peers.get(i);
		else
			return new Peer("FAILED", new Color(0,0,0));
	}
	
	public static Peer getFromID(int ID){
		for(int i=0; i<peers.size(); ++i)
			if(peers.get(i).ID == ID)
				return peers.get(i);
		return null;
	}
	
	public static void generateConnections(int Totalcapacity){
		for(Peer peer: peers)
			peer.generateConnections(Totalcapacity);
	}
	public static void tick(){
		for(Peer peer: peers){
			for(Peer peer2: peers)
				if(connectionPossible(peer, peer2) && sendSectionPossible(peer, peer2)){
					System.out.println("Creating new connection for uploader " + peer.name + " and downloader " + peer2.name);
					connections.add(new Connection(peer.ID, peer2.ID, Math.min(peer.node.maxOut - peer.node.trafficOut, peer2.node.maxIn - peer2.node.trafficIn)));
					peer.node.trafficOut = peer.node.maxOut;
					peer2.node.trafficIn = peer2.node.maxIn;
					break;
			}
		}
	}
	public Peer getRecipient(Peer in){
		for(Peer peer: peers){
			if(connectionPossible(peer, in) && sendSectionPossible(peer, in))
				return peer;
		}
		return null;
	}
	public static boolean connectionPossible(Peer in1, Peer in2){
		if(in1.node.getTrafficOut() < in1.node.getMaxOut() && in2.node.getTrafficIn() < in2.node.getMaxIn() && !in1.equals(in2))
			return true;
		else
			return false;
	}
	public static boolean sendSectionPossible(Peer in1, Peer in2){
		for(Torrent torrent: in1.torrents)
			if(in2.hasTorrent(torrent.ID)>=0){
				for(int i = 0; i<torrent.sections.size(); ++i){
					if(torrent.sections.get(i) == true && in2.torrents.get(in2.hasTorrent(torrent.ID)).sections.get(i))
						return true;
				}
			}
		return false;
	}
}


