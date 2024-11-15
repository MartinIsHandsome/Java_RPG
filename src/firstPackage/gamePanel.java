package firstPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.NPC_Ally;
import entity.playerClass;
import objectCode.superObject;
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

	public final int maxWorldCol = 30;
	public final int maxWorldRow = 30;
	public final int maxWorldWidth = tileSize * maxWorldCol;
	public final int maxWorldHeight = tileSize * maxWorldRow;
	keyControl keyH = new keyControl(this);
	public collisionChecker checkMe = new collisionChecker(this);
	tileManager n = new tileManager(this);
	public assetSetter aSet = new assetSetter(this);
	public playerClass player = new playerClass(keyH, this);
	public superObject obj[] = new superObject[10];
	public Entity Npcs[] = new Entity[3];
	soundController soundEffects = new soundController();
	soundController BackgroundMusic = new soundController();
	public UI view = new UI(this);
	Thread gameThread;

	public int gameState;
	public final int playerState = 1;
	public final int pauseState = 2;
	// int playerX = 100;
	// int playerY = 100;
	// int speed = 4;

	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidgth, screenHeigh));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		// this.col
		this.setFocusable(true); // it doesn't work without this?
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void setUpGame() {
		aSet.setObj();
		aSet.setNPC();
		playMusic(0);
		gameState = playerState;

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
		if (gameState == playerState) {
			// player
			player.update();
			
			// Npc
			for (int i = 0; i < Npcs.length; i++) {
				if (Npcs[i] != null) {
					Npcs[i].update();
				}
			}

		}

		else if (gameState == pauseState) {
			// nothing
			view.drawPauseScreen();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		long drawStart = 0;
		drawStart = System.nanoTime();
		// tiles (they are the first layer so the objects are over them)
		n.draw(g2);
		// objects (they are in 2nd layer so player is seen over them)
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

	
		// player (last so you can see it the best)
		player.draw(g2);

		// NPC
		for (int i = 0; i < Npcs.length; i++) {
			if (Npcs[i] != null) {
				Npcs[i].draw(g2, this);
			}
		}
		view.draw(g2);

		if (keyH.checkTime == true) {
			long endTime = System.nanoTime();
			long averageTime = endTime - drawStart;
			System.out.println("Time:" + averageTime);
			g2.drawString("Time:" + averageTime, 12, 400);
		}

		g2.dispose();
	}

	public void playMusic(int i) {
		BackgroundMusic.setFile(i);
		BackgroundMusic.playFile();
		BackgroundMusic.loop();
	}

	public void stopMusic() {
		BackgroundMusic.stop();
	}

	public void playSE(int i) {
		soundEffects.setFile(i);
		soundEffects.playFile();
	}
}