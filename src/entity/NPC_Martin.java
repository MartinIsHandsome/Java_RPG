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
		dialogue[0] = "You speak with me, so now I will be fast.";
		dialogue[1] = "Stop I will be even faster now.";
		dialogue[2] = "Well I am super \n fast now-meow. ";

	}

	public void getImage() {

		up1 = setPlayer("/NPC/FrontAlly");
		up2 = setPlayer("/NPC/FrontAlly2");
		down1 = setPlayer("/NPC/BackAlly");
		down2 = setPlayer("/NPC/BackAlly2");
		left1 = setPlayer("/NPC/LeftAlly1");
		left2 = setPlayer("/NPC/LeftAlly2");
		right1 = setPlayer("/NPC/RightAlly");
		right2 = setPlayer("/NPC/RightAlly2");
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
