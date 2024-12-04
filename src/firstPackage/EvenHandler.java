package firstPackage;

import java.awt.Rectangle;

public class EvenHandler {
	gamePanel k;
	EventRect rect[][];

	EvenHandler(gamePanel l) {
		this.k = l;

		rect = new EventRect[k.maxWorldCol][k.maxWorldRow];

	
		for (int col = 0; col < k.maxWorldCol; col++) {
		    for (int row = 0; row < k.maxWorldRow; row++) {
		        rect[col][row] = new EventRect();
		        rect[col][row].x = 23;
		        rect[col][row].y = 23;
		        rect[col][row].height = 10;
		        rect[col][row].width = 10;
		        rect[col][row].eventRectThisX = rect[col][row].x;
		        rect[col][row].eventRectThisY = rect[col][row].y;
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
		
		
		if(hit(13,15,"right") == true) {
			messageWrite(13,15,k.DialogueState,"Леле не мога да повярвам че...\n вече имаме годишнина!","Сигурна съм че ще е супер ден!", "Макар че ми е чудно...\n какво ли е направил за датата ни?","И кога ще мога да видя мъжа ми? :(");
		}
		if(hit(12,15,"left") == true) {
			messageWriteLooping(12,15,k.DialogueState,"Няма какво да правя навън днес. ");
		}
		if(hit(11,15,"left") == true) {
			messageWriteLooping(11,15,k.DialogueState,"Ама наистина не ми се излиза...","Защо изобщо ходя към вратата?");
		}
		if(hit(10,15,"left") == true) {
			messageWriteLooping(10,15,k.DialogueState,"Не мога да отворя?","Вратата е заяла.","По-добре да изчакам нашите.");
		}
		if(hit(17,10,"down") == true) {
			messageWriteLooping(17,10,k.DialogueState,"Ех че спомени има тук.","Спомням си колко се радва Марти...","...когато дойде за първи път тук...","...и видя че имаме 2 тоалетни.");
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
	

	public void messageWrite(int col, int row, int gameState, String... messages) {
	    k.gameState = gameState;

	    // Store the messages in the UI queue for sequential display
	    for (String message : messages) {
	        k.view.addMessage(message);
	    }

	    rect[col][row].eventDone = true; // Trigger the event only once
	}


	public void messageWriteLooping(int col,int row, int gameStae,String... writeMessageHere) {
		k.gameState = gameStae;
		   for (String message :  writeMessageHere) {
		        k.view.addMessage(message);
		    }

		
		rect[col][row].eventDone = false;//it works only once.ddddd
	}


	public void HealPit(int col,int row, int gameStae) {
		
		if(k.keyH.enterText == true) {
		k.gameState = gameStae;
		k.view.currectDialogue = "You managed to heal up!";
		k.player.life = k.player.heartMax;
		}
	}
}
