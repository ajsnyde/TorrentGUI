import java.util.ArrayList;

public class Node {
	static int keyCounter = -1;
	final int ID = ++keyCounter;
	ArrayList<Connection> connections;
	int maxIn;
	int maxOut;
	int trafficIn;
	int trafficOut;
	
	Node(){
		
	}
	
	public Node createCopy(Node in){
		Node out = new Node();
		out.maxIn = in.getMaxIn();
		out.maxOut = in.getMaxIn();
		return out;
	}
	
	public int getMaxIn() {
		return maxIn;
	}

	public void setMaxIn(int maxIn) {
		this.maxIn = maxIn;
	}

	public int getMaxOut() {
		return maxOut;
	}

	public void setMaxOut(int maxOut) {
		this.maxOut = maxOut;
	}

	public int getTrafficIn() {
		return trafficIn;
	}

	public void setTrafficIn(int trafficIn) {
		this.trafficIn = trafficIn;
	}

	public int getTrafficOut() {
		return trafficOut;
	}

	public void setTrafficOut(int trafficOut) {
		this.trafficOut = trafficOut;
	}
}
