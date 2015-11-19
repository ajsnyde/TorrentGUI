import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class VisSim {
	
	public static JPanel displayPanel = new Canvas();      // graphics are drawn here
	public static boolean run = true;
	static boolean particlesVisible = false;
	double windX = 0;
	static int DENSITY = 1;
	static ArrayList<Particle> particles = new ArrayList<Particle>();
	static int sleepTime;
	
	public static class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int w = getWidth();
			int h = getHeight();
			if(run){
				displayPanel.repaint();
				}
			update(g,w,h);
			if (particlesVisible)
				for(int i = 0; i < particles.size(); i++)
					particles.get(i).draw(g, w, h);
			int xMid = w/2;
			int yMid = h/2;
			int x = 0;
			int y = 0;
			for(int i = 0; i<Sim.peers.size(); ++i){
				if(Sim.peers.size() > 1){
				x = (int) ((Math.sin(Math.toRadians((360.0/Sim.peers.size()))*i))*(xMid/1.3))+xMid;
				y = (int) ((Math.cos(Math.toRadians((360.0/Sim.peers.size()))*i))*(yMid/1.3))+yMid;
				}
				else{
					x = xMid;
					y = yMid;
				}
				g.setColor(Sim.peers.get(i).color);
				g.fillRoundRect(x, y, 20, 20, 3, 3);
				Sim.peers.get(i).x = x;
				Sim.peers.get(i).y = y;
			}
			for(Connection connection: Sim.connections)
				g.drawLine(Sim.getFromID((connection.node1)).x, Sim.getFromID((connection.node1)).y, Sim.getFromID((connection.node2)).x, Sim.getFromID((connection.node2)).y);
			
			for(Peer peer: Sim.peers){
				for(Connection connection: peer.connections)
					g.drawLine(peer.x, peer.y, Sim.getFromID((connection.node2)).x, Sim.getFromID((connection.node2)).y);
			}
			
			
			
		}
		
		public void update(Graphics g, int w, int h){
			if(DENSITY == -1){
				System.out.println("Update!");
				particles.clear();
				return;
				}
			while(particles.size() < 100)
				particles.add(new Particle());
			while(particles.size() > 100)
				particles.remove(particles.size()-1);
			for(int i = 0; i < particles.size(); ++i){
				particles.get(i).updateLoc(w,h);
				}
			try{
					Thread.sleep(sleepTime);
				}catch(InterruptedException ex){
				  //do stuff
				}
			}
		}
}
