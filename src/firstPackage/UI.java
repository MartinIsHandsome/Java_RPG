package firstPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objectCode.OBJ_Key;

public class UI {
	gamePanel k;
	Font font_Keys;
	Graphics2D g2;
	BufferedImage keyPicture;
	public boolean MessageOn = false;
	public int messageCount = 0;
	public String messageNoIdea = "";
	public boolean GameFinished = false;
	double playTime;
	DecimalFormat format = new DecimalFormat("#0.0");
	public String currectDialogue = "";

	public UI(gamePanel p) {
		this.k = p;
		font_Keys = new Font("Arial", Font.PLAIN, 40);
		OBJ_Key key = new OBJ_Key(p);
		keyPicture = key.look;
	}

	public void showMessage(String n) {
		messageNoIdea = n;
		MessageOn = true;
	}

	public void draw(Graphics2D e) {
		this.g2 = e;
		if (GameFinished == true) {
			e.setFont(new Font("Arial", Font.BOLD, 55));
			e.setColor(Color.white);

			String text;
			int textLength;
			int x;
			int y;
			text = "You won!";
			textLength = (int) e.getFontMetrics().getStringBounds(text, e).getWidth();

			x = k.screenHeigh / 2 - textLength / 2;
			y = k.screenWidgth / 2 - (k.tileSize * 3);
			e.drawString(text, x, y);

		}

		else {
			e.setFont(font_Keys);
			e.setColor(Color.white);
			e.drawImage(keyPicture, k.tileSize / 3, k.tileSize / 3, k.tileSize - 6, k.tileSize - 6, null);
			e.drawString("Key:" + k.player.hasKey, 73, 50);

			playTime += (double) 1 / 60;
			e.drawString("Time:" + format.format(playTime), k.tileSize * 11, 65);
			if (MessageOn == true) {

				e.drawString(messageNoIdea, k.tileSize / 2, k.tileSize * 5);

				messageCount += 1;

//				if (messageCount >= 120) {
//					messageCount = 0;
//					MessageOn = false;
//				}
				g2.setFont(font_Keys);
				g2.setColor(Color.WHITE);
				if (k.gameState == k.playerState) {

				}
				if (k.gameState == k.pauseState) {
					drawPauseScreen();
				}
				if (k.gameState == k.DialogueState) {
					drawScreenDialogue();
				}
			}
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