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
	public final int titleState = 0;
	public EvenHandler events = new EvenHandler(this);
	final int organizedTilesSize = 16; // 16 by 16
	final int scale = 3;
	public final int tileSize = organizedTilesSize * scale; // 16 * 3 = 48
	// public superObject superObject ;
	public final int maxScreeX = 16;
	public final int maxScreenY = 16;
	public final int screenWidgth = tileSize * maxScreeX;
	public final int screenHeigh = tileSize * maxScreenY;

	public final int maxWorldCol = 30;
	public final int maxWorldRow = 30;
	public final int maxWorldWidth = tileSize * maxWorldCol;
	public final int maxWorldHeight = tileSize * maxWorldRow;
	public keyControl keyH = new keyControl(this);
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
	public final int menuState = 0;
	public final int playerState = 1;
	public final int pauseState = 2;
	public final int DialogueState = 3;
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
		aSet.setFinn();
		aSet.setJake();
		playMusic(0);
		gameState = menuState;

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

		player.updateImpractical();
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

		if (gameState == pauseState) {
			// nothing
			view.drawPauseScreen();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		long drawStart = 0;
		drawStart = System.nanoTime();

		if (gameState == menuState) {
			view.draw(g2);
		}
		// Title
		if (gameState == menuState) {

		}

		else {
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

//				// Draw "[E]" if the player is near this NPC
//				if (i == player.interactingNpcIndex) {
//					g2.setColor(Color.WHITE);
//					g2.drawString("[E]", Npcs[i].x - player.x + player.screenX,
//							Npcs[i].y - player.y + player.screenY - 10);
//				}

			view.draw(g2);
		
		if (keyH.checkTime == true) {
			long endTime = System.nanoTime();
			long averageTime = endTime - drawStart;
			System.out.println("Time:" + averageTime);
			g2.drawString("Time:" + averageTime, 12, 400);
		}

		// NPC drawing in paintComponent or similar rendering method
		for (int i = 0; i < Npcs.length; i++) {
			if (Npcs[i] != null) {
				Npcs[i].draw(g2, this);

				if (i == player.interactingNpcIndex) {
					Npcs[i].drawInteractionPrompt(g2, player.x - player.screenX, player.y - player.screenY);
				}
			}
		}
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