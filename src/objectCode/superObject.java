package objectCode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import firstPackage.gamePanel;

public class superObject {
	public BufferedImage look;

	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw(Graphics2D e, gamePanel gp) {
		int screenX = worldX - gp.player.x + gp.player.screenX;
		int screenY = worldY - gp.player.y + gp.player.screenY;

		if (worldX - gp.tileSize < gp.player.x + gp.player.screenX
				&& worldX + gp.tileSize > gp.player.x - gp.player.screenX
				&& worldY - gp.tileSize < gp.player.y + gp.player.screenY
				&& worldY + gp.tileSize > gp.player.y - gp.player.screenY) {
			e.drawImage(look, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	
}
