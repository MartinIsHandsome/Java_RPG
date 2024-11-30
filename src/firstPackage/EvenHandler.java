package firstPackage;

import java.awt.Rectangle;

public class EvenHandler {
	gamePanel k;
	EventRect rect[][];

	EvenHandler(gamePanel l) {
		this.k = l;

		rect = new EventRect[k.maxWorldCol][k.maxWorldRow];

		int col = 0;

		int row = 0;

		while (col < k.maxWorldCol && row < k.maxWorldRow) {

			rect[col][row] = new EventRect();
			rect[col][row].x = 23;
			rect[col][row].y = 23;
			rect[col][row].height = 10;
			rect[col][row].width = 10;
			rect[col][row].eventRectThisX = rect[col][row].x;
			rect[col][row].eventRectThisY = rect[col][row].y;
			col += 1;

			if (col == k.maxWorldCol) {
				col = 0;
				row += 1;
			}
		}
	}

	public void CheckEvent() {
		if (hit(3, 3, "right") == true) {
			damagePit(3,3,k.DialogueState);
			System.out.println("Your health:" + k.player.life);
		}
		if (hit(4, 3, "right") == true) {
			HealPit(4,3,k.DialogueState);
			System.out.println("Your health:" + k.player.life);
		}
		if (hit(3, 4, "right") == true) {
			HealPit(3,4,k.DialogueState);
			System.out.println("Your health:" + k.player.life);
		}
		if (hit(4, 4, "right") == true) {
			damagePit(4,4,k.DialogueState);
			System.out.println("Your health:" + k.player.life);
		}
		
		if(hit(10,9,"up") == true) {
			messageWrite(10,9,k.DialogueState,"Това място ми се струва познато...");
		}
		if(hit(15,14,"right") == true) {
			messageWrite(15,14,k.DialogueState,"Ама играта е страхотна!");
		}
	}

	public boolean hit(int col, int row, String req) {

		boolean hit = false;
		k.player.solidArea.x = k.player.x + k.player.solidArea.x + 10;

		k.player.solidArea.y = k.player.y + k.player.solidArea.y  + 10;

		rect[col][row].x = col * k.tileSize + rect[col][row].x;
		rect[col][row].y = row * k.tileSize + rect[col][row].eventRectThisY;

		if (k.player.solidArea.intersects(rect[col][row]) && rect[col][row].eventDone == false) {
			if (k.player.direction.contentEquals(req) || req.contentEquals("any")) {
				hit = true;
			}

		}

		k.player.solidArea.x = k.player.solidAreaDefaultX;
		k.player.solidArea.y = k.player.solidAreaDefaultY;
		rect[col][row].x = rect[col][row].eventRectThisX;
		rect[col][row].y = rect[col][row].eventRectThisY;

		return hit;
	}

	public void damagePit(int col,int row, int gameStae) {
		k.gameState = gameStae;
		k.view.currectDialogue = "You fell into a pit";
		k.player.life -= 1;
		
		
		rect[col][row].eventDone = true;//it works only once.ddddd
	}
	

	public void messageWrite(int col,int row, int gameStae,String writeMessageHere) {
		k.gameState = gameStae;
		k.view.currectDialogue = "Гери:"+writeMessageHere;
		//k.player.life -= 1;
		
		
		rect[col][row].eventDone = true;//it works only once.ddddd
	}


	public void HealPit(int col,int row, int gameStae) {
		
		if(k.keyH.enterText == true) {
		k.gameState = gameStae;
		k.view.currectDialogue = "You managed to heal up!";
		k.player.life = k.player.heartMax;
		}
	}
}
