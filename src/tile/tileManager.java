package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

//well I hope it works
public class tileManager {
	gamePanel gp;
	tile[] tile;
	int mapTilesNum[][];

	public tileManager(gamePanel gp) {
		this.gp = gp;
		tile = new tile[10];
		mapTilesNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadingInTheMap("/maps/mapo1.txt");
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

	public void loadingInTheMap(String tileMath) {

		try {
			InputStream is = getClass().getResourceAsStream(tileMath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String nums[] = line.split(" ");
					int num = Integer.parseInt(nums[col]);
					mapTilesNum[col][row] = num;
					col += 1;
				}

				if (col == gp.maxWorldRow) {
					col = 0;
					row += 1;
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D e) {
		int col = 0;
		int row = 0;
//		int x = 0;
//		int y = 0;

		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

			int tilesR = mapTilesNum[col][row];
			int worldX = col * gp.tileSize;
			int worldY = row * gp.tileSize;
			int screenX = worldX - gp.player.x + gp.player.screenX;
			int screenY = worldY - gp.player.y + gp.player.screenY;
			
			e.drawImage(tile[tilesR].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

			col += 1;

			// x += gp.tileSize;

			if (col == gp.maxWorldCol) {
				// y += gp.tileSize;
				row += 1;
				col = 0;
				// x = 0;
			}
		}
		// e.drawRenderedImage(null, null);
	}

}
