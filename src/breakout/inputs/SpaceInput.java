package breakout.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import breakout.objects.Ball;
import breakout.objects.Player;

public class SpaceInput implements KeyListener {

	/*
	 * Starts the game if the game is on the title screen. Determines a random
	 * velocity for the ball.
	 */

	private Random random = new Random();

	@Override
	public void keyPressed(KeyEvent key) {

		// If still on the title screen and player presses space
		if (!Player.canMove() && key.getKeyChar() == ' ') {

			/*
			 * Sets a range for the velocity so the ball does not go too far left or right.
			 */

			int velX = random.nextInt(4) + 2;

			// Determines if the ball will move left or right
			if (random.nextBoolean()) {
				velX *= -1;
			}

			// Sets the balls velocity. The ball will always have the same velocity going up
			// or down.
			Ball.setVelX(velX);
			Ball.setVelY(25);
			
			// Allows the player to move
			Player.setMoveable(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Do nothing on key release
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Do nothing on key typed
	}

}
