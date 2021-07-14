package breakout.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import breakout.Game;

public class GameWonWindow {

	public void run() {
		JFrame gameWon = new JFrame("Game Over");
		JLabel score = new JLabel("Score: " + Game.getScore());
		JLabel won = new JLabel("You Won");

		gameWon.setPreferredSize(new Dimension(750, 750));
		gameWon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWon.getContentPane().setBackground(Color.BLACK);
		gameWon.setResizable(false);
		gameWon.setLayout(new BorderLayout());

		score.setPreferredSize(new Dimension(750, 250));
		score.setBackground(Color.BLACK);
		score.setForeground(Color.GREEN);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setVerticalAlignment(JLabel.CENTER);
		score.setFont(score.getFont().deriveFont(40.0f));

		won.setPreferredSize(new Dimension(750, 250));
		won.setForeground(Color.GREEN);
		won.setBackground(Color.BLACK);
		won.setHorizontalAlignment(JLabel.CENTER);
		won.setVerticalAlignment(JLabel.CENTER);
		won.setFont(score.getFont().deriveFont(60.0f));

		gameWon.add(won, BorderLayout.BEFORE_FIRST_LINE);
		gameWon.add(score, BorderLayout.AFTER_LINE_ENDS);
		gameWon.pack();
		gameWon.setVisible(true);
	}

}
