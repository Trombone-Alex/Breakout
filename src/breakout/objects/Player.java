package breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import breakout.Game;

public class Player implements GameObject {

	private static int x = 440; // Players location
	private static int velX = 0; // Players velocity
	private static boolean canMove = false;
	public static final int PLAYER_WIDTH = 200;
	public static final int PLAYER_HEIGHT = 20;
	public static final int PLAYER_LOCATION_Y = Map.MAP_HEIGHT - 100;
	public static final int PLAYER_SPEED = 15;
	private Random random = new Random();

	/*
	 * Updates the players location every frame
	 */
	@Override
	public void run() {
		x += velX;

		// If player is out of bounds, reverse their velocity
		if (x < 0 || x > Map.MAP_WIDTH) {
			x -= velX;
		}
		
		// Creates a new instance of the ball class
		Ball ball = new Ball();
		
		// If the ball hits the player
		if (overlaps(ball.getBounds())) {

			// Determines a new velocity for the ball between 1 and 4
			int newVelX = random.nextInt(3) + 1;

			// Makes sure the ball continues in the same direction when hitting the center
			// of the player
			if (Ball.getVelX() < 0)
				newVelX *= -1;

			Ball.setVelX(newVelX);
			Ball.setVelY(Ball.getVelY() * -1); // Ball bounces back upwards
		}		
	}

	/*
	 * Draws the player object every frame
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(x, PLAYER_LOCATION_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		if(Game.isDebugMode()) {
			g.setColor(Color.CYAN);
			g.drawRect(x, PLAYER_LOCATION_Y, 40, PLAYER_HEIGHT);
			g.drawRect(x + PLAYER_WIDTH - 40, PLAYER_LOCATION_Y, 40, PLAYER_HEIGHT);
		}
	}
	
	/*
	 * Returns true if the bounds of the rectangle intersect with the location of
	 * the ball
	 */
	public boolean overlaps(Rectangle r) {
		return x < r.x + r.width && x + PLAYER_WIDTH > r.x && PLAYER_LOCATION_Y < r.y + r.height && PLAYER_LOCATION_Y + PLAYER_HEIGHT > r.y;
	}

	/**
	 * Sets the players velocity
	 */
	public static void setVelX(int velX) {
		Player.velX = velX;
	}

	/**
	 * @return the player's velocity
	 */
	public static int getVelX() {
		return velX;
	}

	/**
	 * Returns false is the game is in the title screen and true if the game has
	 * started.
	 */
	public static boolean canMove() {
		return canMove;
	}

	/*
	 * Determines whether the player can move
	 */
	public static void setMoveable(boolean move) {
		canMove = move;
	}

	/*
	 * Gets the rectangle dimensions for the player
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, PLAYER_LOCATION_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}
	
	/*
	 * Gets the rectangle dimensions for the left side and right side of the player
	 * Velocity changes different if the ball hits the left or right side versus the ball hitting the center of the player
	 */
	public static Rectangle getLeftBounds() {
		return new Rectangle(x, PLAYER_LOCATION_Y, 40, PLAYER_HEIGHT);
	}

	public static Rectangle getRightBounds() {
		return new Rectangle(x + PLAYER_WIDTH - 40, PLAYER_LOCATION_Y, 40, PLAYER_HEIGHT);
	}

}
