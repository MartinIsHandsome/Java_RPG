package firstPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class gamePanel extends JPanel implements Runnable {

	// Screen settings

	final int organizedTilesSize = 16; // 16 by 16
	final int scale = 3;
	final int tileSize = organizedTilesSize * scale; // 16 * 3 = 48

	final int maxScreeX = 16;
	final int maxScreenY = 16;
	final int screenWidgth = tileSize * maxScreeX;
	final int screenHeigh = tileSize * maxScreenY;

	Thread gameThread;

	keyControl keyH = new keyControl();

	int playerX = 100;
	int playerY = 100;
	int speed = 4;

	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidgth, screenHeigh));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); //it doesn't work without this?
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / 60; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

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
		if (keyH.downPress == true) {
			playerY += speed;
		}

		else if (keyH.upPress == true) {
			playerY -= speed;
			System.out.print("Working");
		}
		else if (keyH.leftPress == true) {
			playerX -= speed;
		}

		else if (keyH.rightPress == true) {
			playerX += speed;
		}
	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}
}
