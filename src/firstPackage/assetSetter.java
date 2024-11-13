package firstPackage;

import objectCode.OBJ_Boots;
import objectCode.OBJ_Chest;
import objectCode.OBJ_Door;
import objectCode.OBJ_Key;

public class assetSetter {
	gamePanel l;

	public assetSetter(gamePanel k) {
		this.l = k;
	}

	public void setObj() {
		l.obj[0] = new OBJ_Boots(l);
		l.obj[0].worldX = 16 * l.tileSize;
		l.obj[0].worldY = 10 * l.tileSize;

		l.obj[1] = new OBJ_Key(l);
		l.obj[1].worldX = 20 * l.tileSize;
		l.obj[1].worldY = 20 * l.tileSize;
		

		l.obj[2] = new OBJ_Key(l);
		l.obj[2].worldX = 25 * l.tileSize;
		l.obj[2].worldY = 25 * l.tileSize;

		l.obj[3] = new OBJ_Door(l);
		l.obj[3].worldX = 10 * l.tileSize;
		l.obj[3].worldY = 6 * l.tileSize;

		l.obj[4] = new OBJ_Chest(l);
		l.obj[4].worldX = 7 * l.tileSize;
		l.obj[4].worldY = 5 * l.tileSize;
		

		l.obj[5] = new OBJ_Boots(l);
		l.obj[5].worldX = 10 * l.tileSize;
		l.obj[5].worldY = 5 * l.tileSize;
		
	}

}
