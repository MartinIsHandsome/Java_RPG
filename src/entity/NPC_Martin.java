package entity;

import java.util.Random;

import firstPackage.gamePanel;

public class NPC_Martin extends Entity{

	public NPC_Martin(gamePanel k1) {
		super(k1);
		direction = "down";// TODO Auto-generated constructor stub
		speed = 5;

		getImage();
		setDialoge();
	}

	public void setDialoge() {
		dialogue[0] = "Хей аз съм Фин от време за \n приключения!";
		dialogue[1] = "Сега ще съм бавен. Хех.";
		dialogue[2] = "Как си иначе ти? \n Всичко топ? ";

	}

	public void getImage() {

		up1 = setPlayer("/NPC/FrontFinn");
		up2 = setPlayer("/NPC/FrontFinn2");
		down1 = setPlayer("/NPC/FinnBack");
		down2 = setPlayer("/NPC/FinnBack2");
		left1 = setPlayer("/NPC/FinLeftOneCorrect");
		left2 = setPlayer("/NPC/PinLeftTwoCorrect");
		right1 = setPlayer("/NPC/FinnRightOne");
		right2 = setPlayer("/NPC/FinnRightTwo");
	}

	public void speak() {

	super.speak();
	speed-=1;
	}

	public void setAction() {

		actionLock += 2;

		if (actionLock == 140) {
			Random r = new Random();
			int i = r.nextInt(100) + 1; // pick up random Num
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";

			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}

			actionLock = 0;
		}
	}
}
