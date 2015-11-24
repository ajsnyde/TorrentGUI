import java.util.ArrayList;

public class Connection {
	
	static ArrayList<Connection> connections = new ArrayList<Connection>();
	static int keyCounter = -1;
	final int ID;
	Section payload;
	boolean kill = false;
	
	int peer1;	// SENDER
	int peer2;	// RECIEVER
	int speed;	// Units sent per tick
	
	Connection(int peer1, int peer2, Section payload, int speed){
		ID = ++keyCounter;
		this.payload = payload;
		this.peer1 = peer1;
		this.peer2 = peer2;
		this.speed = speed;
		connections.add(this);
	}
	
	public void tick(){
		payload.sent += speed;
		if(payload.isComplete()){
			Peer.getFromID(peer2).getTorrent(payload.torrentID).setSection(payload.sectionNum, true);
			kill = true;
		}
	}
}
