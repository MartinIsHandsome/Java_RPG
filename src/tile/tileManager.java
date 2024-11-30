package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import firstPackage.UtilityTool;
import firstPackage.gamePanel;

//well I hope it works
public class tileManager {
	gamePanel gp;
	public tile[] tile;
	public int mapTilesNum[][];

	public tileManager(gamePanel gp) {
		this.gp = gp;
		tile = new tile[100];// tiles types
		mapTilesNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		
		getTileImage();
		loadingInTheMap("/maps/mapo1.txt");
	}

	public void getTileImage() {
	//	try {
			//Old instantiation ways
			//tile[0] = new tile();
			//tile[0].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/grass.png"));
			//tile[1] = new tile();
//			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/RockTile.png"));
//			tile[1].collision = true;
//
//			tile[2] = new tile();
//			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/sea.png"));
//			tile[2].collision = true;

			setUpScale(0,"grassTile",false);
			setUpScale(1,"wall",true);
			setUpScale(2,"water00",true);
			setUpScale(3,"road00",false);
			setUpScale(4,"road01",false);
			setUpScale(5,"road02",false);
			setUpScale(6,"road03",false);
			setUpScale(7,"road04",false);
			setUpScale(8,"road05",false);
			setUpScale(9,"road06",false);
			setUpScale(10,"road07",false);
			setUpScale(11,"road08",false);
			setUpScale(12,"road09",false);
			setUpScale(13,"road10",false);
			setUpScale(14,"road11",false);
			setUpScale(15,"road12",false);
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void setUpScale(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

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
			while (row < gp.maxWorldRow) {
			    String line = br.readLine();
			    String[] nums = line.split(" ");
			    for (col = 0; col < gp.maxWorldCol; col++) {
			        if (col < nums.length) {
			            int num = Integer.parseInt(nums[col]);
			            mapTilesNum[col][row] = num;
			        } else {
			            // Handle missing columns in the map file if necessary
			            mapTilesNum[col][row] = 0; // Default tile
			        }
			    }
			    row++;
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
				e.drawImage(tile[tilesR].image, screenX, screenY, null);

//				 Draw a semi-transparent overlay on each tile
	            e.setColor(new Color(255, 0, 0, 100)); // Red with transparency
	            e.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
	
	            // Draw an outline around each tile
	            e.setColor(Color.BLACK);
	            e.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
			}

			col++;

			if (col == gp.maxWorldCol) {
				row++;
				col = 0;
			}
		}
	}
}
		
				
				// Draw a semi-transparent overlay on each tile
//            e.setColor(new Color(255, 0, 0, 100)); // Red with transparency
//            e.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//
//            // Draw an outline around each tile
//            e.setColor(Color.BLACK);
//            e.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
