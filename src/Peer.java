import java.awt.Color;
import java.util.ArrayList;

public class Peer {
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
	
}
