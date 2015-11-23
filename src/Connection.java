import java.awt.Color;
import java.util.ArrayList;

public class Connection {
	
	static final Color notInUse = new Color(0,0,0);
	static final Color inUse = new Color(255,0,0);
	
	static int keyCounter = -1;
	ArrayList<Section> traffic;
	final int ID;
	int node1;
	int node2;
	int totalTraffic = 0;
	int totalCapacity;
	Color color = new Color(0,0,0);
	
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
	public void updateColor(){
		recalcluateTraffic();
		double percentUtilized = totalTraffic/Math.max(Sim.getFromID(node1).node.maxOut, Sim.getFromID(node2).node.maxIn);
		int difference = Math.abs(notInUse.getRed()-inUse.getRed());
		int red = notInUse.getRed() + (int)(percentUtilized*difference);
		color = new Color(red,0,0);
	}
}
