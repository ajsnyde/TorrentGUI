import java.util.ArrayList;

public class Connection {
	static int keyCounter = -1;
	ArrayList<Section> traffic;
	final int ID;
	int node1;
	int node2;
	
	Connection(int node1, int node2){
		ID = ++keyCounter;
		traffic = new ArrayList<Section>();
		this.node1 = node1;
		this.node2 = node2;
	}
}
