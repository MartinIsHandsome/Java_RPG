package tile;

import java.awt.Color;
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
	public tile[] tile;
	public int mapTilesNum[][];

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
			tile[1].collision = true;

			tile[2] = new tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/sea.png"));
			tile[2].collision = true;

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

    while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
        int tilesR = mapTilesNum[col][row];
        int worldX = col * gp.tileSize;
        int worldY = row * gp.tileSize;
        int screenX = worldX - gp.player.x + gp.player.screenX;
        int screenY = worldY - gp.player.y + gp.player.screenY;

        // Draw the tile image
        if (worldX - gp.tileSize < gp.player.x + gp.player.screenX
                && worldX + gp.tileSize > gp.player.x - gp.player.screenX
                && worldY - gp.tileSize < gp.player.y + gp.player.screenY
                && worldY + gp.tileSize > gp.player.y - gp.player.screenY) {
            e.drawImage(tile[tilesR].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            // Draw a semi-transparent overlay on each tile
//            e.setColor(new Color(255, 0, 0, 100)); // Red with transparency
//            e.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//
//            // Draw an outline around each tile
//            e.setColor(Color.BLACK);
//            e.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
        }

        col++;

        if (col == gp.maxWorldCol) {
            row++;
            col = 0;
        }
    }
}
}
