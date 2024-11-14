package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.UtilityTool;
import firstPackage.gamePanel;

public class Entity {
	gamePanel k;
	public int x, y;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 1;
	public int spriteNum = 1;
	public int speedOfChaningTheFrames = 20;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
public int actionLock = 0;
	public Entity(gamePanel k) {
		this.k = k;
	}
public void setAction() {
	
}

public void update() {
	setAction();
	collisionOn = false;
	k.checkMe.checkTile(this);
	k.checkMe.checkObject(this, false);
	k.checkMe.checkPlayer(this);
	
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
	
	public void draw(Graphics2D e, gamePanel gp) {
		int screenX = x - gp.player.x + gp.player.screenX;
		int screenY = y - gp.player.y + gp.player.screenY;

		BufferedImage image = null; 
		// Draw the object image if within the player’s screen bounds
		if (y - gp.tileSize < gp.player.x + gp.player.screenX
				&& x + gp.tileSize > gp.player.x - gp.player.screenX
				&& x - gp.tileSize < gp.player.y + gp.player.screenY
				&& x + gp.tileSize > gp.player.y - gp.player.screenY) {

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
			
			e.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
	
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
}
