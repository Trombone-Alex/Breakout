package breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import breakout.Game;
import breakout.graphics.GameOverWindow;
import breakout.graphics.Window;

public class Ball implements GameObject {

	private static final int BALL_RADIUS = 25;
	private static int x = Map.MAP_WIDTH / 2;
	private static int y = (Map.MAP_WIDTH / 3);
	private static int velX = 0;
	private static int velY = 0;
	@Override
	public void run() {

		// Updates the balls location every frame
		Ball.x += Ball.velX;
		Ball.y += Ball.velY;
		
		// If the ball hits the walls, reverse its velocity
		if (Ball.x < 25 || Ball.x > Map.MAP_WIDTH - 50) {
			Ball.reverseXVelocity();
		}

		if (Ball.y < 0)
			Ball.reverseYVelocity();

		// If the ball hits the bottom of the map
		if (Ball.y > Map.MAP_HEIGHT - 10) {

			// Close the window
			Window.close();

			// Waits 1 second before opening the game over window
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Your final score was: " + Game.getScore());
				System.exit(1);
			}

			GameOverWindow go = new GameOverWindow();
			go.open();
		}

		/*
		 * If the ball is going in the opposite direction of the side it hits, reverse
		 * its X velocity
		 */

		if ((overlaps(Player.getRightBounds()) && velX < 0) || (overlaps(Player.getLeftBounds()) && velX > 0)) {
			Ball.reverseXVelocity();
		}
		
		// Makes sure the ball never gets stuck
		if(Player.canMove() && Ball.velX == 0)
			Ball.setVelX(-1);
		
		if(Player.canMove() && Ball.velY == 0)
			Ball.setVelY(-1);
		
		// So the ball doesn't move too slow
		if (Ball.velX < 2 && Ball.velX > -2)
			Ball.velX *= 2;
	}

	/*
	 * Draws the Ball Object
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, BALL_RADIUS, BALL_RADIUS);

		if (Game.isDebugMode()) {
			g.setColor(Color.GREEN);
			g.drawRect(x, y, BALL_RADIUS, BALL_RADIUS);
		}

	}

	/*
	 * Returns the bounds of the ball
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, BALL_RADIUS, BALL_RADIUS);
	}

	/*
	 * Returns true if the bounds of the rectangle intersect with the location of
	 * the ball
	 */
	public boolean overlaps(Rectangle r) {
		return x < r.x + r.width && x + BALL_RADIUS > r.x && y < r.y + r.height && y + BALL_RADIUS > r.y;
	}

	/*
	 * Sets the balls velocity horizontally
	 */
	public static void setVelX(int velocity) {
		velX = velocity;
	}

	/*
	 * Returns the balls horizontal velocity
	 */
	public static int getVelX() {
		return velX;
	}

	/*
	 * Sets the balls velocity vertically
	 */
	public static void setVelY(int velocity) {
		velY = velocity;
	}

	/*
	 * Sets the balls velocity vertically
	 */
	public static int getVelY() {
		return velY;
	}

	/*
	 * Reverses the balls X Velocity
	 */
	public static void reverseXVelocity() {
		velX *= -1;
	}

	/*
	 * Reverses the balls Y Velocity
	 */
	public static void reverseYVelocity() {
		velY *= -1;
	}
}
