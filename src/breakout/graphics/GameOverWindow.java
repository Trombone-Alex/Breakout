package breakout.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import breakout.Game;

public class GameOverWindow {

	public void open() {
		JFrame gameover = new JFrame("Game Over");
		JLabel score = new JLabel("Score: " + Game.getScore());
		JLabel lost = new JLabel("You Lost");

		gameover.setPreferredSize(new Dimension(750, 750));
		gameover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameover.getContentPane().setBackground(Color.BLACK);
		gameover.setResizable(false);
		gameover.setLayout(new BorderLayout());

		score.setPreferredSize(new Dimension(750, 250));
		score.setBackground(Color.BLACK);
		score.setForeground(Color.RED);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setVerticalAlignment(JLabel.CENTER);
		score.setFont(score.getFont().deriveFont(40.0f));

		lost.setPreferredSize(new Dimension(750, 250));
		lost.setForeground(Color.RED);
		lost.setBackground(Color.RED);
		lost.setHorizontalAlignment(JLabel.CENTER);
		lost.setVerticalAlignment(JLabel.CENTER);
		lost.setFont(score.getFont().deriveFont(60.0f));

		gameover.add(lost, BorderLayout.BEFORE_FIRST_LINE);
		gameover.add(score, BorderLayout.AFTER_LINE_ENDS);
		gameover.pack();
		gameover.setVisible(true);
	}
}
