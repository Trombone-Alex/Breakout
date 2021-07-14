package breakout.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import breakout.Game;
import breakout.graphics.GameWonWindow;
import breakout.graphics.Window;

public class Map implements GameObject {

	public static final int MAP_HEIGHT = 800;
	public static final int MAP_WIDTH = 1000;
	public static final int BLOCK_ROWS = 6;
	public static final int BLOCK_COLUMNS = 10;
	private static boolean isCreated = false;
	private static Block[][] blocks = new Block[BLOCK_ROWS][BLOCK_COLUMNS];

	public Map() {
		if (!isCreated)
			create();
	}

	@Override
	public void run() {

		if (!Player.canMove())
			return;

		if (isEmpty()) {
			try {
				Window.close();
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("You Won! Your final score is " + Game.getScore());
				System.exit(1);
			}

			// Opens up winner window
			GameWonWindow gw = new GameWonWindow();
			gw.run();
		}
	}

	/*
	 * The map doesn't need to draw anything because each block has its own draw
	 * method
	 */
	@Override
	public void draw(Graphics g) {
		// Map does not draw anything because each object has its own draw method
	}

	/*
	 * Creates a new map by setting the location
	 */
	public void create() {
		for (int i = 0; i < BLOCK_ROWS; i++) {
			for (int j = 0; j < BLOCK_COLUMNS; j++) {

				// Sets the health based on the row
				int health;

				if (i < 2)
					health = 3;
				else if (i < 4)
					health = 2;
				else
					health = 1;

				// Creates a new block in a grid like pattern
				Block b = new Block(health, (1 * j) + (Block.BLOCK_WIDTH * j), 10 + (10 * i) + (Block.BLOCK_HEIGHT * i));
				blocks[i][j] = b;

				// Adds the object
				ObjectManager.addObject(b);
			}
		}
		isCreated = true;
	}

	/*
	 * Returns true if all the blocks have a health of 0
	 */
	public boolean isEmpty() {
		for (Block[] row : blocks) {
			for (Block b : row) {
				if (b.getHealth() != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Returns the array of blocks
	 */
	public Block[][] getBlocks() {
		return blocks;
	}

	public Rectangle getBounds() {
		// Return rectangle the size of the map
		return new Rectangle(0, 0, Map.MAP_WIDTH, Map.MAP_HEIGHT);
	}

	public boolean overlaps(Rectangle r) {
		// Return true because every object exists inside of the map
		return true;
	}
}
