package breakout.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import breakout.objects.Player;

public class KeyInput implements KeyListener {

	/*
	 * Moves the player object left or right depending on the key pressed
	 */

	@Override
	public void keyPressed(KeyEvent key) {
		if (Player.canMove()) {
			if (key.getKeyChar() == 'd') {
				Player.setVelX(Player.PLAYER_SPEED);
			}
			if (key.getKeyChar() == 'a') {
				Player.setVelX(Player.PLAYER_SPEED * -1);
			}
		}
	}
	
	/*
	 * Stops the players momentum if they release a key.
	 */

	@Override
	public void keyReleased(KeyEvent arg0) {
		Player.setVelX(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Do nothing on key typed
	}

}
