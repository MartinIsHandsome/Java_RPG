package objectCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import firstPackage.gamePanel;

public class superObject {
	public BufferedImage look;
	
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefX = 0;
	public int solidAreaDefY = 0;

	// In superObject.java
	public void draw(Graphics2D e, gamePanel gp) {
		int screenX = worldX - gp.player.x + gp.player.screenX;
		int screenY = worldY - gp.player.y + gp.player.screenY;

		// Draw the object image if within the player’s screen bounds
		if (worldX - gp.tileSize < gp.player.x + gp.player.screenX
				&& worldX + gp.tileSize > gp.player.x - gp.player.screenX
				&& worldY - gp.tileSize < gp.player.y + gp.player.screenY
				&& worldY + gp.tileSize > gp.player.y - gp.player.screenY) {
			e.drawImage(look, screenX, screenY, gp.tileSize, gp.tileSize, null);
//
//			// Draw a semi-transparent green overlay for objects
//			e.setColor(new Color(0, 255, 0, 100)); // Green with transparency
//			e.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//
//			// Draw an outline around each object
//			e.setColor(Color.BLACK);
//			e.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
		}
	}

}
