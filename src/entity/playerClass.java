package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;
import firstPackage.keyControl;

public class playerClass extends Entity {
	keyControl kH;
	gamePanel panel;

	public final int screenX;
	public final int screenY;

	public playerClass(keyControl kH, gamePanel panel) {
		this.kH = kH;
		this.panel = panel;
		screenX = panel.screenWidgth / 2;
		screenY = panel.screenHeigh / 2;

		solidArea = new Rectangle(x, y, 10, 10);
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = panel.tileSize * 8 - panel.tileSize;
		y = panel.tileSize * 8 - panel.tileSize;
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

		if (kH.downPress == true || kH.leftPress == true || kH.rightPress == true || kH.upPress == true) {

			if (kH.downPress == true) {

				direction = "up";
//				y += speed;
			}

			else if (kH.upPress == true) {
				direction = "down";
//				y -= speed;
			} else if (kH.leftPress == true) {
				direction = "left";
//				x -= speed;
			}

			else if (kH.rightPress == true) {
				direction = "right";
//				x += speed;
			}
			
			// CHECK FOR COLLISION
			collisionOn = false;
			panel.checkMe.checkTile(this);

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					y += speed;
					break;
				case "down":
					y -= speed;
					break;
				case "left":
					x -= speed;
					break;
				case "right":
					x += speed;
					break;
				}
			}
			spriteCounter++;
			if (spriteCounter > speedOfChaningTheFrames) {
				if (spriteNum == 1) {
					System.out.println("Working,homie chill");
					spriteNum = 2;
				}

				else if (spriteNum == 2) {
					spriteNum = 1;
				}

				spriteCounter = 0;
			}

		}

	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.RED);
//		g2.fillRect(x, y, panel.tileSize, panel.tileSize);

		BufferedImage image = null;
		switch (direction) {
		case "up":

			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;

		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;

		}

		g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, panel);
	}
}
