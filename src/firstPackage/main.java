package firstPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Testing RPG [0.04]");
		gamePanel gamePanel = new gamePanel();
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.setUpGame();
		gamePanel.startGameThread();
	}

}
