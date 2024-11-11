package firstPackage;

import objectCode.OBJ_Key;

public class assetSetter {
	gamePanel l;

	public assetSetter(gamePanel k) {
		this.l = k;
	}

	public void setObj() {
		l.obj[0] = new OBJ_Key();
		l.obj[0].worldX = 16 * l.tileSize;
		l.obj[0].worldY = 10 * l.tileSize;

		l.obj[1] = new OBJ_Key();
		l.obj[1].worldX = 20 * l.tileSize;
		l.obj[1].worldY = 20 * l.tileSize;

	}

}
