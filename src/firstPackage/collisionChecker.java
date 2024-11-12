package firstPackage;

import entity.Entity;

public class collisionChecker {
	gamePanel nzBrat;

	public collisionChecker(gamePanel nzBrat) {
		this.nzBrat = nzBrat;	
	}

	public void checkTile(Entity randomEntity) {
		int entityLeftWorldX = randomEntity.x + randomEntity.solidArea.x;
		int entityRightWorldX = randomEntity.x + randomEntity.solidArea.x + randomEntity.solidArea.width;
		int entityTopWorldY = randomEntity.y + randomEntity.solidArea.y;
		int entityBottomWorldY = randomEntity.y + randomEntity.solidArea.y + randomEntity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / nzBrat.tileSize;
		int entityRightCol = entityRightWorldX / nzBrat.tileSize;
		int entityTopRow = entityTopWorldY / nzBrat.tileSize;
		int entityBottomRow = entityBottomWorldY / nzBrat.tileSize;

		int tileNum1, tileNum2;

		switch (randomEntity.direction) {
		case "up":
			// Check the bottom instead of top
			entityBottomRow = (entityBottomWorldY + randomEntity.speed) / nzBrat.tileSize;
			tileNum1 = nzBrat.n.mapTilesNum[entityLeftCol][entityBottomRow];
			tileNum2 = nzBrat.n.mapTilesNum[entityRightCol][entityBottomRow];
			if (nzBrat.n.tile[tileNum1].collision || nzBrat.n.tile[tileNum2].collision) {
				randomEntity.collisionOn = true;
			}
			break;
		case "down":
			// Check the top instead of bottom
			entityTopRow = (entityTopWorldY - randomEntity.speed) / nzBrat.tileSize;
			tileNum1 = nzBrat.n.mapTilesNum[entityLeftCol][entityTopRow];
			tileNum2 = nzBrat.n.mapTilesNum[entityRightCol][entityTopRow];
			if (nzBrat.n.tile[tileNum1].collision || nzBrat.n.tile[tileNum2].collision) {
				randomEntity.collisionOn = true;
			}
			break;
		case "left":

			// Check the left instead of right
			entityLeftCol = (entityLeftWorldX - randomEntity.speed) / nzBrat.tileSize;
			tileNum1 = nzBrat.n.mapTilesNum[entityLeftCol][entityTopRow];
			tileNum2 = nzBrat.n.mapTilesNum[entityLeftCol][entityBottomRow];
			if (nzBrat.n.tile[tileNum1].collision || nzBrat.n.tile[tileNum2].collision) {
				randomEntity.collisionOn = true;
			}
			break;
		case "right":
			// Check the right instead of left
			entityRightCol = (entityRightWorldX + randomEntity.speed) / nzBrat.tileSize;
			tileNum1 = nzBrat.n.mapTilesNum[entityRightCol][entityTopRow];
			tileNum2 = nzBrat.n.mapTilesNum[entityRightCol][entityBottomRow];
			if (nzBrat.n.tile[tileNum1].collision || nzBrat.n.tile[tileNum2].collision) {
				randomEntity.collisionOn = true;
			}
			break;
		}
	}

	public int checkObject(Entity nz, boolean player) {
		int index = 999;

		for (int i = 0; i < nzBrat.obj.length; i++) {
			if (nzBrat.obj[i] != null) {
				// Initialize entity's and object's solidArea positions
				nz.solidArea.x = nz.x + nz.solidAreaDefaultX;
				nz.solidArea.y = nz.y + nz.solidAreaDefaultY;

				nzBrat.obj[i].solidArea.x = nzBrat.obj[i].worldX + nzBrat.obj[i].solidAreaDefX;
				nzBrat.obj[i].solidArea.y = nzBrat.obj[i].worldY + nzBrat.obj[i].solidAreaDefY;

				switch (nz.direction) {
				case "up":
					nz.solidArea.y -= nz.speed - 7;
					if (nz.solidArea.intersects(nzBrat.obj[i].solidArea)) {
						if(nzBrat.obj[i].collision  == true) {
							nz.collisionOn = true;
						} 
						
						if(player == true) {
							index = i;
						}
						System.out.println("Up collision!");
						// Add additional collision handling here if needed
						//index = i; // Set index to the colliding object
					}
					break;

				case "down":
					nz.solidArea.y += nz.speed - 15;
					if (nz.solidArea.intersects(nzBrat.obj[i].solidArea)) {
						
						if(nzBrat.obj[i].collision == true) {
							nz.collisionOn = true;
						} 
						
						if(player == true) {
							index = i;
						}
						System.out.println("Down collision!");
						//index = i;
					}
					break;

				case "left":
					nz.solidArea.x -= nz.speed;
					if (nz.solidArea.intersects(nzBrat.obj[i].solidArea)) {
						if(nzBrat.obj[i].collision  == true) {
							nz.collisionOn = true;
						} 
						
						if(player == true) {
							index = i;
						}
						System.out.println("Left collision!");
						//index = i;
					}
					break;

				case "right":
					nz.solidArea.x += nz.speed;
					if (nz.solidArea.intersects(nzBrat.obj[i].solidArea)) {
						if(nzBrat.obj[i].collision  == true) {
							nz.collisionOn = true;
						} 
						
						if(player == true) {
							index = i;
						}
						System.out.println("Right collision!");
						//index = i;
					}
					break;
				}

				// Reset solid areas to their default positions after each check
				nz.solidArea.x = nz.solidAreaDefaultX;
				nz.solidArea.y = nz.solidAreaDefaultY;
				nzBrat.obj[i].solidArea.x = nzBrat.obj[i].solidAreaDefX;
				nzBrat.obj[i].solidArea.y = nzBrat.obj[i].solidAreaDefY;
			}
		}
		return index;
	}
}
