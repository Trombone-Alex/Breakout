package breakout;

import breakout.graphics.Window;
import breakout.objects.Ball;
import breakout.objects.Map;
import breakout.objects.ObjectManager;
import breakout.objects.Player;

public class Game {

	private static int score;
	private static boolean debug = false;

	/*
	 * Creates new map and initializes window
	 */
	public static void startGame(boolean debugMode) {

		debug = debugMode;
		score = 0;

		// Adds objects for the game
		ObjectManager.addObject(new Map());
		ObjectManager.addObject(new Ball());
		ObjectManager.addObject(new Player());

		new Window().initGui();
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Game.score = score;
	}

	public static boolean isDebugMode() {
		return debug;
	}
	
	public static void main(String[] args) {
		Game.startGame(false);
	}

}
