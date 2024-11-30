
package entity;

	import java.util.Random;

import firstPackage.assetSetter;
import firstPackage.gamePanel;

	public class NPC_Jake extends Entity{
 
		
		assetSetter n;
		
		
		public NPC_Jake(gamePanel k1) {
			super(k1);
			direction = "down";// TODO Auto-generated constructor stub
			speed = 2;

			getImage();
			setDialoge();
		}

		public void setDialoge() {
			dialogue[0] = "Благодаря ти че ме освободи! \n приключения! Ето ти една енергийна за мерси..";
			dialogue[1] = "Кликни ме 3 пъти за да спра.";
			dialogue[2] = "Ох шаро. \n Как сме? ";
			
		}

		
	
		
		public void getImage() {

			up1 = setPlayer("/NPC/FrontJake1");
			up2 = setPlayer("/NPC/FrontJake2");
			down1 = setPlayer("/NPC/BackJake1");
			down2 = setPlayer("/NPC/BackJake2");
			left1 = setPlayer("/NPC/JakeLeft1");
			left2 = setPlayer("/NPC/JakeLeft2");
			right1 = setPlayer("/NPC/JakeRight1");
			right2 = setPlayer("/NPC/JakeRight2");
		}

		public void speak() {

		super.speak();

		super.choice =0;
		super.setAction();

		}

		public void setAction() {

			actionLock += 1;

			if (actionLock == 200) {
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


