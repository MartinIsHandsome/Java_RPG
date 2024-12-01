package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import firstPackage.UtilityTool;
import firstPackage.gamePanel;

//well I hope it works
public class tileManager {
	
	    public int[][] mapTileNum;


	gamePanel gp;
	public tile[] tile;
	public int mapTilesNum[][];
	ArrayList<String> fileNames = new ArrayList<String>();
	ArrayList<String> collisionStatus = new ArrayList<String>();

	public tileManager(gamePanel gp) {
		this.gp = gp;
		InputStream is = getClass().getResourceAsStream("/maps/levelOneData.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		String line;
		try {
			while ((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tile = new tile[fileNames.size()];// tiles types
		getTileImage();

		is = getClass().getResourceAsStream("/maps/levelOne.txt");
		br = new BufferedReader(new InputStreamReader(is));

		try {
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");

			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			mapTilesNum = new int[gp.maxWorldCol][gp.maxWorldRow];

			br.close();
		} catch (IOException e) {
			System.out.println("Exception!");
		}
loadingInTheMap("/maps/levelOne.txt");
	//	loadingInTheMap("/maps/mapo1.txt");
	}

	public void getTileImage() {

		for (int i = 0; i < fileNames.size(); i++) {
			String fileName;
			boolean Collision;

			fileName = fileNames.get(i);
			if (collisionStatus.get(i).equals("true")) {
				Collision = true;
			}

			else {

				Collision = false;
			}

			setUpScale(i, fileName, Collision);
		}

	}


	public void setUpScale(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/BackGround/" + imagePath));
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

	        ArrayList<int[]> mapData = new ArrayList<>();
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] nums = line.split(" ");
	            int[] rowData = new int[nums.length];
	            for (int col = 0; col < nums.length; col++) {
	                rowData[col] = Integer.parseInt(nums[col]);
	            }
	            mapData.add(rowData);
	        }

	        // Set map dimensions dynamically
	        gp.maxWorldRow = mapData.size();
	        gp.maxWorldCol = mapData.get(0).length;
	        mapTilesNum = new int[gp.maxWorldCol][gp.maxWorldRow];

	        for (int row = 0; row < gp.maxWorldRow; row++) {
	            int[] rowData = mapData.get(row);
	            for (int col = 0; col < gp.maxWorldCol; col++) {
	                mapTilesNum[col][row] = rowData[col];
	            }
	        }

	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void draw(Graphics2D e) {
	    int startCol = Math.max(0, (gp.player.x - gp.player.screenX) / gp.tileSize);
	    int endCol = Math.min(mapTilesNum.length, (gp.player.x + gp.player.screenX) / gp.tileSize + 2);

	    int startRow = Math.max(0, (gp.player.y - gp.player.screenY) / gp.tileSize);
	    int endRow = Math.min(mapTilesNum[0].length, (gp.player.y + gp.player.screenY) / gp.tileSize + 2);

	    for (int row = startRow; row < endRow; row++) {
	        for (int col = startCol; col < endCol; col++) {
	            if (col >= mapTilesNum.length || row >= mapTilesNum[0].length) continue;
	            int tileIndex = mapTilesNum[col][row];

	            if (tileIndex >= 0 && tileIndex < tile.length) {
	                int worldX = col * gp.tileSize;
	                int worldY = row * gp.tileSize;
	                int screenX = worldX - gp.player.x + gp.player.screenX;
	                int screenY = worldY - gp.player.y + gp.player.screenY;

	                e.drawImage(tile[tileIndex].image, screenX, screenY, null);
	            }
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
