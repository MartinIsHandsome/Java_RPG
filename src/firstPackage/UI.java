package firstPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objectCode.OBJ_Key;

public class UI {
	gamePanel k;
	Font font_Keys;
	BufferedImage keyPicture;
	public boolean MessageOn = false;
	public int messageCount = 0;
	public String messageNoIdea = "";
	public boolean GameFinished = false;
	double playTime;
	DecimalFormat format = new DecimalFormat("#0.0");

	public UI(gamePanel p) {
		this.k = p;
		font_Keys = new Font("Arial", Font.PLAIN, 40);
		OBJ_Key key = new OBJ_Key();
		keyPicture = key.look;
	}

	public void showMessage(String n) {
		messageNoIdea = n;
		MessageOn = true;
	}

	public void draw(Graphics2D e) {

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

		} else {
			e.setFont(font_Keys);
			e.setColor(Color.white);
			e.drawImage(keyPicture, k.tileSize / 3, k.tileSize / 3, k.tileSize - 6, k.tileSize - 6, null);
			e.drawString("Key:" + k.player.hasKey, 73, 50);

			playTime += (double) 1 / 60;
			e.drawString("Time:" + format.format(playTime)	, k.tileSize * 11, 65);
			if (MessageOn == true) {

				e.drawString(messageNoIdea, k.tileSize / 2, k.tileSize * 5);

				messageCount += 1;

				if (messageCount >= 120) {
					messageCount = 0;
					MessageOn = false;
				}
			}
		}

	}

}
