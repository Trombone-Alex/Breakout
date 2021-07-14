package breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import breakout.Game;

public class Block implements GameObject {

	private int health;
	private boolean broken = false;
	private int x;
	private int y;
	public static final int BLOCK_WIDTH = (int)(Map.MAP_WIDTH - 30)/Map.BLOCK_COLUMNS;
	public static final int BLOCK_HEIGHT = (Map.MAP_HEIGHT/4)/Map.BLOCK_ROWS;

	/*
	 * Creates a block object with specific health and location
	 */
	public Block(int health, int x, int y) {
		this.health = health;
		this.setX(x);
		this.setY(y);
	}

	/*
	 * The run() method in Ball.java with check if each block overlaps with the ball
	 */
	@Override
	public void run() {
		
		//If the block isn't broken
		if (!broken) {
			Ball ball = new Ball();

			if (overlaps(ball.getBounds())) {
				if (health > 0) {
					health--;
					Ball.reverseYVelocity();
					Game.setScore(Game.getScore() + 100);
				} else {
					broken = true;
				}
			}
		}
	}

	/*
	 * Changes the color based on the health and then draws the block
	 */
	public void draw(Graphics g) {
		if(health == 3)
			g.setColor(Color.RED);
		else if(health == 2)
			g.setColor(Color.ORANGE);
		else if (health == 1)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.BLACK);
		
		g.fillRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
		
		if(Game.isDebugMode()) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y, BLOCK_WIDTH + 15, BLOCK_HEIGHT);
		}
		
	}

	/*
	 * Returns the bounds of the block
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, BLOCK_WIDTH + 15, BLOCK_HEIGHT);
	}

	/*
	 * Getters and Setters for the X, Y, and health of the block
	 */
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean overlaps(Rectangle r) {
		return x < r.x + r.width && x + BLOCK_WIDTH > r.x && y < r.y + r.height && y + BLOCK_HEIGHT > r.y;
	}

}
