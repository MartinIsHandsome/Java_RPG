package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;
//well I hope it works
public class tileManager {
	gamePanel gp;
	tile[] tile;

	public tileManager(gamePanel gp) {
		this.gp = gp;
		tile = new tile[10];
		getTileImage();
	}

	public void getTileImage() {
		try {
			tile[0] = new tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/grass.png"));

			tile[1] = new tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/RockTile.png"));

			tile[2] = new tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/sea.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D e) {
		e.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
		e.drawImage(tile[0].image,48,0,gp.tileSize,gp.tileSize,null);
		e.drawImage(tile[0].image,96,0,gp.tileSize,gp.tileSize,null);

	}

}
