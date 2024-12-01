package firstPackage;

import entity.NPC_Ally;
import entity.NPC_Jake;
import entity.NPC_Martin;
import objectCode.OBJ_Boots;
import objectCode.OBJ_Chest;
import objectCode.OBJ_Door;
import objectCode.OBJ_Key;

public class assetSetter {
	gamePanel l;

	public assetSetter(gamePanel k) {
		this.l = k;
	}

	

	public void setEnergyDrink() {
		l.obj[6] = new OBJ_Boots(l);
		l.obj[6].worldX = 10 * l.tileSize;
		l.obj[6].worldY = 3 * l.tileSize;
	}
	

	public void setQuestForFinn() {
		l.obj[7] = new OBJ_Key(l);
		l.obj[7].worldX = 25 * l.tileSize;
		l.obj[7].worldY = 3 * l.tileSize;
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
		l.obj[3].worldX = 15 * l.tileSize;
		l.obj[3].worldY = 5 * l.tileSize;

		l.obj[4] = new OBJ_Door(l);
		l.obj[4].worldX = 7 * l.tileSize;
		l.obj[4].worldY = 5 * l.tileSize;

		l.obj[5] = new OBJ_Boots(l);
		l.obj[5].worldX = 10 * l.tileSize;
		l.obj[5].worldY = 5 * l.tileSize;

	}

	public void setJake() {
		l.Npcs[2] = new NPC_Jake(l);
		l.Npcs[2].x = l.tileSize * 10;
		l.Npcs[2].y = l.tileSize * 8;

	}

	public void setFinn() {
		l.Npcs[1] = new NPC_Martin(l);
		l.Npcs[1].x = l.tileSize * 19;
		l.Npcs[1].y = l.tileSize * 23;

	}

	public void setNPC() {
		l.Npcs[0] = new NPC_Ally(l);
		l.Npcs[0].x = l.tileSize*21;
		l.Npcs[0].y = l.tileSize*21;
	}
}

//		
//		l.Npcs[1] = new NPC_Ally(l);
//		l.Npcs[1].x = l.tileSize*3;
//		l.Npcs[1].y = l.tileSize*6;
//		
//		l.Npcs[2] = new NPC_Martin(l);
//		l.Npcs[2].x = l.tileSize*9;
//		l.Npcs[2].y = l.tileSize*13;
