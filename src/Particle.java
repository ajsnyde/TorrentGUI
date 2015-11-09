import java.awt.Color;
import java.awt.Graphics;


/*							ADDISON SNYDER
 * 		Inspired by: http://goeagle.io/
 * 		Particle contains data for an individual particle:
 * 
 * 			Size
 * 			base velocities	
 */

public class Particle{
	private double xLocation, yLocation, xVelocity, yVelocity, degVector, spdVector;
	private int size = 7;

	Color color = new Color(255,0,0);
	
	public Particle()
	{
		this.xLocation = Math.random();
		this.yLocation = Math.random();
		degVector = Math.random();
		spdVector = .2+(Math.random());
		xVelocity = Math.sin(degVector)*spdVector;
		yVelocity = Math.cos(degVector)*spdVector;
	}
	
	public void draw(Graphics g, int w, int h)
	{
		g.setColor(color);
		g.fillOval((int)(xLocation*w), (int)(yLocation*h), size, size);  // the Particle is drawn
	}
	public void updateLoc(int w, int h)
	{
		xLocation += (xVelocity/w);
		yLocation += (yVelocity/h);
		if(xLocation < 0 || xLocation > 1)
			xVelocity = -xVelocity;
		if(yLocation < 0 || yLocation > 1)
			yVelocity = -yVelocity;
	}
}

