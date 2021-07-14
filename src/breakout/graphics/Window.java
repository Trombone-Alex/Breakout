package breakout.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import breakout.Game;
import breakout.inputs.KeyInput;
import breakout.inputs.SpaceInput;
import breakout.objects.GameObject;
import breakout.objects.Map;
import breakout.objects.ObjectManager;
import breakout.objects.Player;

public class Window extends JPanel {

	/*
	 * Variables are static because there is only one instance of Window
	 */
	private static final long serialVersionUID = 3233964977517672300L;
	private static JFrame frame;
	private static boolean running = false;
	private static final int FRAMES_PER_SECOND = 30;

	/*
	 * Opens up a new frame
	 */
	public void initGui() {

		// Creates the JFrame
		frame = new JFrame("Breakout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(Map.MAP_WIDTH, Map.MAP_HEIGHT));
		frame.setResizable(false);
		setBackground(Color.BLACK);

		// Adds the listeners for the inputs
		frame.addKeyListener(new KeyInput());
		frame.addKeyListener(new SpaceInput());

		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
		// Starts the game loop
		running = true;
		run();
	}

	private void run() {

		long lastTime = System.currentTimeMillis();

		while (running) {

			// For every frame
			if (System.currentTimeMillis() - lastTime > (1000 / FRAMES_PER_SECOND)) {

				// Run each game object
				for (GameObject o : ObjectManager.getObjects()) {
					o.run();
				}

				// Reset the loop and repaint the frame
				lastTime = System.currentTimeMillis();
				repaint();
			}
		}
	}

	/*
	 * Paints the map for the game and then paints each game object.
	 */
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (!Player.canMove()) { // If game hasn't started, create a title screen
			g.setColor(Color.WHITE);
			g.setFont(g.getFont().deriveFont(40.0f));
			g.drawString("Press Space To Start", 325, 350);
		} else { // Game has started; Draw the score
			g.setColor(Color.GRAY);
			g.setFont(g.getFont().deriveFont(50.0f));
			g.drawString("Score: " + Game.getScore(), 350, 400);
		}

		// Draws each Game Object
		for (GameObject o : ObjectManager.getObjects()) {
			o.draw(g);
		}
	}

	/*
	 *  Closes the frame and stops the game loop
	 */
	public static void close() throws NullPointerException {
		frame.setVisible(false);
		frame.dispose();
		running = false;
	}
}
