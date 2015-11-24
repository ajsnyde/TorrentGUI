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
	static int peerWidth = 50;
	static int peerHeight = 20;
	static int x = 0;
	static int y = 0;
	
	public static class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			int w = getWidth();
			int h = getHeight();
			int xMid = w/2;
			int yMid = h/2;
			
			if(run){
				displayPanel.repaint();
				}
			update(g,w,h);
			
			//Draw peer connections
			g.setColor(Color.BLACK);
			for(Peer peer: Peer.peers)
				for(Connection connection: peer.connections)
					g.drawLine(peer.x, peer.y, Peer.getFromID(connection.peer2).x, Peer.getFromID(connection.peer2).y);
			
			//Draw peers
			for(int i = 0; i<Peer.peers.size(); ++i){
				if(Peer.peers.size() > 1){
				x = (int) ((Math.sin(Math.toRadians((360.0/Peer.peers.size()))*i))*(xMid/1.3))+xMid;
				y = (int) ((Math.cos(Math.toRadians((360.0/Peer.peers.size()))*i))*(yMid/1.3))+yMid;
				}
				else{
					x = xMid;
					y = yMid;
				}
				
				g.setColor(Color.BLACK);
				g.drawRoundRect(x- peerWidth/2, y- peerHeight/2, peerWidth, peerHeight, 3, 3);
				g.setColor(Color.WHITE);
				g.fillRoundRect(x+1- peerWidth/2, y+1- peerHeight/2, peerWidth-2, peerHeight-2, 3, 3);
				g.setColor(Color.BLACK);
				g.drawString(Peer.peers.get(i).name, x-peerHeight/2, y-(peerHeight/2)-1);
				Peer.peers.get(i).x = x;
				Peer.peers.get(i).y = y;
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
