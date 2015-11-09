import java.util.ArrayList;

public class Connection {
	static int keyCounter = -1;
	ArrayList<Section> traffic;
	final int ID;
	int node1;
	int node2;
	int totalTraffic = 0;
	int totalCapacity;
	
	Connection(int node1, int node2, int capacity){
		ID = ++keyCounter;
		traffic = new ArrayList<Section>();
		this.node1 = node1;
		this.node2 = node2;
		totalCapacity = capacity;
	}
	public int recalcluateTraffic(){
		int total = 0;
		for(int i = 0; i<traffic.size(); ++i)
			total+=traffic.get(i).size;
		return total;
	}
	public void addSection(Section in){
		traffic.add(in);
		totalTraffic += in.size;
	}
	public void removeSection(int sectionID){	// Removes first match. subtracts from capacity
		for(int i = 0; i<traffic.size(); ++i){
			if(traffic.get(i).ID == sectionID){
				totalTraffic -= traffic.get(i).size;
				traffic.remove(i);
				return;
			}
		}
	}
	public boolean moveSection(){
		int timeSlice = (totalCapacity / traffic.size());
		for(int i = 0; i<traffic.size(); ++i){	//shouldn't ever divide by zero. Watch out..
			traffic.get(i).sent += timeSlice;	//has integer cutoff overhead; considered packet overhead for now...
			if(traffic.get(i).sent >= traffic.get(i).size){
				//Sim.peers.getFromID(traffic.get(i).receiverID).giveSection(traffic.get(i));
			}
		}
		
		return false;
	}
}
