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
}
