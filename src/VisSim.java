import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class VisSim {
	
	public static JPanel displayPanel = new Canvas();      // graphics are drawn here
	public static boolean run = true;
	static boolean particlesVisible = true;
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
			int radius = xMid/3;
			for(int i = 0; i<Sim.peers.size(); ++i){
				x = (int) ((Math.sin((360.0/Sim.peers.size())*i))*radius)+xMid;
				y = (int) ((Math.cos((360.0/Sim.peers.size())*i))*radius)+yMid;
				g.setColor(Sim.peers.get(i).color);
				g.fillRoundRect(x, y, 20, 20, 3, 3);
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
