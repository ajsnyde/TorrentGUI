public class Sim {
	Sim(){

	}
	
	public static void tick(){
		for(Peer peer: Peer.peers)
			peer.tick();
	}
}