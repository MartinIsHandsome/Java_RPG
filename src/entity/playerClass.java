package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;
import firstPackage.keyControl;

public class playerClass extends Entity {
	keyControl kH;
	gamePanel panel;

	public playerClass(keyControl kH, gamePanel panel) {
		this.kH = kH;
		this.panel = panel;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {

		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Front.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Front2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Back.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Back2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Right.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right2.png"));

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (kH.downPress == true) {
			direction = "up";
			y += speed;
		}

		else if (kH.upPress == true) {
			direction = "down";
			y -= speed;
		} else if (kH.leftPress == true) {
			direction = "left";
			x -= speed;
		}

		else if (kH.rightPress == true) {
			direction = "right";
			x += speed;
		}

	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.RED);
//		g2.fillRect(x, y, panel.tileSize, panel.tileSize);

		BufferedImage image = null;
		switch (direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			System.out.print("Hi");
			break;
		}

		g2.drawImage(image, x, y, panel.tileSize, panel.tileSize, panel);
	}
}
