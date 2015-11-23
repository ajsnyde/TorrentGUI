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
			return null;
	}
	
	public static Peer getFromID(int ID){
		for(int i=0; i<peers.size(); ++i)
			if(peers.get(i).ID == ID)
				return peers.get(i);
		return null;
	}
	
	public static void tick(){

		for(Connection connection: connections){
			for(int i = 0; i < connection.traffic.size(); ++i){
				connection.traffic.get(i).sent += connection.totalCapacity/connection.traffic.size();
				if(connection.traffic.get(i).sent >= connection.traffic.get(i).size)
					connection.traffic.get(i).complete = true;
					
			}
		}
	}
	public Peer getRecipient(Peer in){
		for(Peer peer: peers){
			if(connectionPossible(peer, in) && sendSectionPossible(peer, in)!=-1)
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
	public static int sendSectionPossible(Peer in1, Peer in2){
		for(Torrent torrent: in1.torrents)
			if(in2.hasTorrent(torrent.ID)>=0){
				for(int i = 0; i<torrent.sections.size(); ++i){
					if(torrent.sections.get(i).complete == true && in2.torrents.get(in2.hasTorrent(torrent.ID)).sections.get(i).complete == false)
						return i;
				}
			}
		return -1;
	}
}