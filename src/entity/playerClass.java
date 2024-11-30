package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.UtilityTool;
import firstPackage.assetSetter;
import firstPackage.gamePanel;
import firstPackage.keyControl;
import tile.tile;

public class playerClass extends Entity {
	public int interactingNpcIndex = -1;
	keyControl kH;
	assetSetter n;
	// gamePanel panel;
	public int hasKey = 0;
	public final int screenX;
	public final int screenY;

	public playerClass(keyControl kH, gamePanel panel) {
		super(panel);
		this.kH = kH;
		// this.panel = panel;
		screenX = panel.screenWidgth / 2;
		screenY = panel.screenHeigh / 2;

		solidArea = new Rectangle(x, y, 10, 10);
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		solidArea.width = 16;
		solidArea.height = 16;
		setDefaultValues();
		getPlayerImage();
		setDialoge();

	}

	public void speak() {

		super.speak();

	}

	public void setDialoge() {
		dialogue[0] = "Хммм врата е заключена, явно ми трябва \n някакъв ключ, чудя се къде ли се намира?";

	}

	public void setDefaultValues() {
		x = k.tileSize * 8 - k.tileSize;
		y = k.tileSize * 8 - k.tileSize;
		speed = 4;
		direction = "down";
		// Player status
		heartMax = 6;
		life = heartMax;
	}

	public void getPlayerImage() {

		up1 = setPlayer("/player/Front");
		up2 = setPlayer("/player/Front2");
		down1 = setPlayer("/player/Back");
		down2 = setPlayer("/player/Back2");
		left1 = setPlayer("/player/Left");
		left2 = setPlayer("/player/Left2");
		right1 = setPlayer("/player/Right");
		right2 = setPlayer("/player/Right2");
	}
//	try { (old method)
//			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Front.png"));
//			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Front2.png"));
//			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Back.png"));
//			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Back2.png"));
//			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left.png"));
//			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));
//			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Right.png"));
//			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right2.png"));
//
//		}
//
//		catch (IOException e) {
//			e.printStackTrace();
//		}

	public BufferedImage setPlayer(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, k.tileSize, k.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public void updateImpractical() {
		int npcCheck = k.checkMe.checkEntity(this, k.Npcs);
		// collisionWithNPc(npcCheck);
		collisionWithNPcForASecond(npcCheck);
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

			// Check event
			k.events.CheckEvent();

			// CHECK FOR COLLISION
			collisionOn = false;
			k.checkMe.checkTile(this);

			// Collision with Objects
			int i = k.checkMe.checkObject(this, true);
			pickUpObject(i);

			int npcCheck = k.checkMe.checkEntity(this, k.Npcs);
			collisionWithNPc(npcCheck);
			// collisionWithNPcForASecond(npcCheck);
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

	public void collisionWithNPcForASecond(int i) {
		if (i != 999) {
			// k.view.showMessage("Брат туй работи страхотно бе,евала!");
			interactingNpcIndex = i; // Save the NPC index

		}

		else {
			interactingNpcIndex = -1; // Reset if no NPC is colliding
		}

	}

	public void collisionWithNPc(int i) {
		if (i != 999) {
			// k.view.showMessage("Брат туй работи страхотно бе,евала!");
			if (k.keyH.enterText == true) {

				k.gameState = k.DialogueState;
				k.Npcs[i].speak();
			}
		}

		else {
			interactingNpcIndex = -1; // Reset if no NPC is colliding
		}
		k.keyH.enterText = false;
	}

	public void pickUpObject(int i) {

//		if (i != 999) {
//			
//		}

		if (i != 999) {
			String objectname = k.obj[i].name;

			switch (objectname) {
			case "Key":
				hasKey += 1;
				k.obj[i] = null;
				k.view.showMessage("Ти взе ключ!");
				break;
			case "Door":
				if (hasKey > 0) {
					hasKey -= 1;
					k.obj[i] = null;

				}

				else if (hasKey == 0) {
					// k.view.
					k.gameState = k.DialogueState;
					k.view.currectDialogue = "Тази врата е заключена. \n Ще ми ключ.";
					k.view.drawScreenDialogueOneOnly("");
					// System.out.println("fag can't code.");
				}

				System.out.println("You got Door! F!");
				break;
			case "Drink":
				k.playSE(1);
				speed += 3;
				k.obj[i] = null;
				// System.out.println("You got Door! F!");
				break;
			case "Chest":
				k.view.GameFinished = true;
				k.stopMusic();
				k.playSE(1);
				// System.out.println("You got Door! F!");
				break;
			}
		}
	}

// In playerClass.java
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direction) {
		case "up":
			image = (spriteNum == 1) ? up1 : up2;
			break;
		case "down":
			image = (spriteNum == 1) ? down1 : down2;
			break;
		case "left":
			image = (spriteNum == 1) ? left1 : left2;
			break;
		case "right":
			image = (spriteNum == 1) ? right1 : right2;
			break;
		}

		// Draw the player image
		g2.drawImage(image, screenX, screenY, null);
	}
}
//		g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);

//		// Draw a semi-transparent blue overlay for the player
// g2.setColor(new Color(0, 0, 255, 100)); // Blue with transparency
// g2.fillRect(screenX+ solidArea.x, screenY + solidArea.y,
// solidArea.height,solidArea.width);

// Draw an outline around the player
// g2.setColor(Color.BLACK);
// g2.fillRect(screenX+ solidArea.x, screenY + solidArea.y,
// solidArea.height,solidArea.width);
