package breakout.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface GameObject {
	
	public abstract void run();
	
	public abstract void draw(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public abstract boolean overlaps(Rectangle r);
}
