package firstPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objectCode.OBJ_Heart;
import objectCode.OBJ_Key;
import objectCode.superObject;

public class UI {
	gamePanel k;
	// superObject so;
	Font font_Keys;
	Graphics2D g2;
	BufferedImage heart, heartHalf, heartEmpty;
	BufferedImage keyPicture;
	public boolean MessageOn = false;
	public int messageCount = 0;
	public String messageNoIdea = "";
	public boolean GameFinished = false;
	double playTime;
	DecimalFormat format = new DecimalFormat("#0.0");
	public String currectDialogue = "";
	public int commandNum = 0;

	public UI(gamePanel p) {
		this.k = p;
		font_Keys = new Font("Arial", Font.PLAIN, 40);
		OBJ_Key key = new OBJ_Key(p);
		keyPicture = key.look;
		superObject heartS = new OBJ_Heart(k);
		heart = heartS.look;
		heartHalf = heartS.look2;
		heartEmpty = heartS.look3;
//		I ran into the same issue. I initialised an instance of the SuperObject class 
//		as "SuperObject so;" under the "GamePanel gp;" and then I was able to import the SuperObject and OBJ_Heart classes.

	}

	public void showMessage(String n) {
		messageNoIdea = n;
		MessageOn = true;
	}

	public void draw(Graphics2D gp2) {
		this.g2 = gp2;
//		if (GameFinished == true) {
//			e.setFont(new Font("Arial", Font.BOLD, 55));
//			e.setColor(Color.white);
//
//			String text;
//			int textLength;
//			int x;
//			int y;
//			text = "You won!";
//			textLength = (int) e.getFontMetrics().getStringBounds(text, e).getWidth();
//
//			x = k.screenHeigh / 2 - textLength / 2;
//			y = k.screenWidgth / 2 - (k.tileSize * 3);
//			e.drawString(text, x, y);
//
//		}
//
//		else {
		gp2.setFont(font_Keys);
		gp2.setColor(Color.white);
		gp2.drawImage(keyPicture, k.tileSize / 3, k.tileSize / 3, k.tileSize - 6, k.tileSize - 6, null);
		gp2.drawString("Key:" + k.player.hasKey, 73, 50);

		playTime += (double) 1 / 60;
		gp2.drawString("Time:" + format.format(playTime), k.tileSize * 11, 65);
		if (MessageOn == true) {

			gp2.drawString(messageNoIdea, k.tileSize / 2, k.tileSize * 5);

			messageCount += 1;

				if (messageCount >= 120) {
					messageCount = 0;
					MessageOn = false;
				}
		}

		g2.setFont(font_Keys);
		g2.setColor(Color.WHITE);

		if (k.gameState == k.menuState) {
			System.out.print("Ok.");
			drawTitle();
			
		}
		if (k.gameState == k.playerState) {
			drawPlayerLife();
		}
		if (k.gameState == k.pauseState) {
			drawPauseScreen();
		}
		if (k.gameState == k.DialogueState) {
			drawScreenDialogue();
		}
	}

	public void drawPlayerLife() {
		
		//k.player.life = 5;
		int x = k.tileSize / 2;
		int y = k.tileSize / 2;
		int i = 0;

		while (i < k.player.heartMax / 2) {
			g2.drawImage(heartEmpty, x, y, null);
			i += 1;
			x += k.tileSize;
		}
		x = k.tileSize / 2;
		y = k.tileSize / 2;
		i = 0;

		// draw painting

		while (i < k.player.life) {
			g2.drawImage(heartHalf, x, y, null);
			i += 1;
			
			if(i < k.player.life) {
				g2.drawImage(heart, x, y, null);
			}
			i++;
			x+=k.tileSize;
		}

	}

	// }

	public void drawTitle() {

		Color darkGreen = new Color(1, 100, 5);
		g2.setColor(darkGreen);
		g2.fillRect(0, 0, k.screenWidgth, k.screenHeigh);

		g2.setColor(Color.gray);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56F));
		String title = "[Великото приключение]";
		int x = getXForCenterText(title);

		int y = k.tileSize * 3;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56F));

		g2.drawString(title, x, y);
		String title1 = "[Великото приключение]";
		int x1 = getXForCenterText(title);

		int y1 = k.tileSize * 3;
		g2.setColor(Color.white);
		g2.drawString(title1, x - 3, y - 3);

		x = (k.screenWidgth / 2) - (k.tileSize * 2) / 2;
		y += k.tileSize * 2;
		g2.drawImage(k.player.up1, x, y, k.tileSize * 2, k.tileSize * 2, null);

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

		title = "Нова игра";
		x = getXForCenterText(title);
		y += k.tileSize * 2;
		g2.drawString(title, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - k.tileSize, y);

		}
		title = "Продължи игра";
		x = getXForCenterText(title);
		y += k.tileSize * 1;
		g2.drawString(title, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - k.tileSize, y);

		}

		title = "Изход";
		x = getXForCenterText(title);
		y += k.tileSize * 1;
		g2.drawString(title, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - k.tileSize, y);

		}

	}

	public void drawScreenDialogueOneOnly(String s) {
		int x = k.tileSize * 2;
		int y = k.tileSize / 2;
		int wid = k.screenWidgth - (k.tileSize * 4);
		int high = k.screenHeigh / 5;
		miniWindow(x, y, wid, high);
		x += k.tileSize;
		y += k.tileSize;
		for (String line : currectDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += k.tileSize;
		}
		}
	
		
	public void drawScreenDialogue() {
		int x = k.tileSize * 2;
		int y = k.tileSize / 2;
		int wid = k.screenWidgth - (k.tileSize * 4);
		int high = k.screenHeigh / 5;
		miniWindow(x, y, wid, high);
		x += k.tileSize;
		y += k.tileSize;
		for (String line : currectDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += k.tileSize;
		}
	}

	public void miniWindow(int x, int y, int w, int h) {
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, w, h, 0, 0);

		c = new Color(225, 225, 225);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(x + 5, y + 5, w - 10, h - 10, 0, 0);

	}

	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXForCenterText(text);
		int y = k.screenHeigh / 2;
		g2.drawString(text, x, y);
	}

	public int getXForCenterText(String text) {

		int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = k.screenWidgth / 2 - textLength / 2;
		return x;
	}

}

// if (GameFinished == true) {
//			e.setFont(new Font("Arial", Font.BOLD, 55));
//			e.setColor(Color.white);
//
//			String text;
//			int textLength;
//			int x;
//			int y;
//			text = "You won!";
//			textLength = (int) e.getFontMetrics().getStringBounds(text, e).getWidth();
//
//			x = k.screenHeigh / 2 - textLength / 2;
//			y = k.screenWidgth / 2 - (k.tileSize * 3);
//			e.drawString(text, x, y);
//
//		} else {
//			e.setFont(font_Keys);
//			e.setColor(Color.white);
//			e.drawImage(keyPicture, k.tileSize / 3, k.tileSize / 3, k.tileSize - 6, k.tileSize - 6, null);
//			e.drawString("Key:" + k.player.hasKey, 73, 50);
//
//			playTime += (double) 1 / 60;
//			e.drawString("Time:" + format.format(playTime)	, k.tileSize * 11, 65);
//			if (MessageOn == true) {
//
//				e.drawString(messageNoIdea, k.tileSize / 2, k.tileSize * 5);
//
//				messageCount += 1;
//
//				if (messageCount >= 120) {
//					messageCount = 0;
//					MessageOn = false;
//				}
//			}
//		}