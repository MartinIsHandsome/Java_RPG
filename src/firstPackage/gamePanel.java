package firstPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.playerClass;
import tile.tileManager;

public class gamePanel extends JPanel implements Runnable {

	// Screen settings

	final int organizedTilesSize = 16; // 16 by 16
	final int scale = 3;
	public final int tileSize = organizedTilesSize * scale; // 16 * 3 = 48

	public final int maxScreeX = 16;
	public final int maxScreenY = 16;
	public final int screenWidgth = tileSize * maxScreeX;
	public final int screenHeigh = tileSize * maxScreenY;

	Thread gameThread;

	
	public final int maxWorldCol = 30;
	public final int maxWorldRow = 30;
	public final int maxWorldWidth = tileSize *  maxWorldCol;
	public final int maxWorldHeight = tileSize *  maxWorldRow;
	keyControl keyH = new keyControl();
	public collisionChecker checkMe = new collisionChecker(this);
	tileManager n = new tileManager(this);
	public playerClass player = new playerClass(keyH, this);
//	int playerX = 100;
//	int playerY = 100;
//	int speed = 4;

	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidgth, screenHeigh));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		//this.col
		this.setFocusable(true); // it doesn't work without this?
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / 60; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		long timer = 0;
		int drawCount = 0;
		while (gameThread != null) {
			update();
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		Graphics2D g2 = (Graphics2D) g;
		n.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
}
