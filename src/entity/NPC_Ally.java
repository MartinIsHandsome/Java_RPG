package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import firstPackage.UtilityTool;
import firstPackage.gamePanel;

public class NPC_Ally extends Entity {

	public NPC_Ally(gamePanel k) {
		super(k);
		direction = "down";// TODO Auto-generated constructor stub
		speed = 1;

		getImage();
		setDialoge();
	}

	public void setDialoge() {
		dialogue[0] = "Greetings! My dearest of \n human speciments.";
		dialogue[1] = "How are you doing? I am \n incredibly human. Rawr.";
		dialogue[2] = "I hope you are doing well, \n and this is what your ";

	}

	public void getImage() {

		up1 = setPlayer("/NPC/IceKingFront1");
		up2 = setPlayer("/NPC/IceKingFront2");
		down1 = setPlayer("/NPC/IceKingBack1");
		down2 = setPlayer("/NPC/IceKingBack2");
		left1 = setPlayer("/NPC/IceKingLeft1");
		left2 = setPlayer("/NPC/IceKingLeft2");
		right1 = setPlayer("/NPC/IceKingRight");
		right2 = setPlayer("/NPC/IceKingRight2");
	}

	public void speak() {

	super.speak();
	}

	public void setAction() {

		actionLock += 1;

		if (actionLock == 120) {
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