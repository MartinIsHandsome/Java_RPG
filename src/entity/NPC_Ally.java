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

	public void setAction() {

		actionLock += 1;

		if (actionLock == 120) {
			Random r = new Random();
			int i = r.nextInt(100) + 1; // pick up random Num
			if (i <= 25 ) {
				direction = "up";
			}
			if (i > 25 && i <= 50 ) {
				direction = "down";
			}
			if (i > 50 && i <= 75 ) {
				direction = "left";
				
			}
			if (i > 75 && i <= 100 ) {
				direction = "right";
			}

			actionLock = 0;
		}
	}

}