package firstPackage;

import java.awt.Rectangle;

public class EvenHandler {
	gamePanel k;
	Rectangle eventRect;
	int rectX, rectY;

	EvenHandler(gamePanel l) {
		this.k = l;

		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.height = 2;
		eventRect.width = 2;
		rectX = eventRect.x;
		rectY = eventRect.y;
	}

	public void CheckEvent() {
		if (hit(3, 3, "right") == true) {
			damagePit(k.DialogueState);
			System.out.println("Your health:" + k.player.life);
		}
	}

	public boolean hit(int eventCo, int eventRow, String req) {

		boolean hit = false;
		k.player.solidArea.x = k.player.x + k.player.solidArea.x;

		k.player.solidArea.y = k.player.y + k.player.solidArea.y;

		eventRect.x = eventCo * k.tileSize + eventRect.x;
		eventRect.y = eventRow * k.tileSize + eventRect.y;

		if (k.player.solidArea.intersects(eventRect)) {
			if (k.player.direction.contentEquals(req) || req.contentEquals("any")) {
				hit = true;
			}

		}

		k.player.solidArea.x = k.player.solidAreaDefaultX;
		k.player.solidArea.y = k.player.solidAreaDefaultY;
		eventRect.x = rectX;
		eventRect.y = rectY;

		return hit;
	}

	public void damagePit(int gameStae) {
		k.gameState = gameStae;
		k.view.currectDialogue = "You fell into a pit";
		k.player.life -= 1;
	}
}
