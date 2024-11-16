package firstPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyControl implements KeyListener {

	public boolean upPress, downPress, leftPress, rightPress;

	public boolean checkTime = false;
	gamePanel gpReal;

	public keyControl(gamePanel gpNo) {
		this.gpReal = gpNo;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		//play state
		if(gpReal.gameState == gpReal.playerState) {
			
		
			if (keyCode == KeyEvent.VK_W) {
				upPress = true;
			}

			if (keyCode == KeyEvent.VK_S) {
				downPress = true;
			}

			if (keyCode == KeyEvent.VK_A) {
				leftPress = true;
			}

			if (keyCode == KeyEvent.VK_D) {
				rightPress = true;
			}
			
		}
		//play state
		else if(gpReal.gameState == gpReal.DialogueState) {
				
					if (keyCode == KeyEvent.VK_ENTER) {
						gpReal.gameState = gpReal.playerState;
					}
				
				
				}

	}
//			if (keyCode == KeyEvent.VK_P) {
//				if (gpReal.gameState == gpReal.playerState) {
//					gpReal.gameState = gpReal.pauseState;
//				}
//				
//				else if(gpReal.gameState == gpReal.pauseState) {
//					gpReal.gameState = gpReal.playerState;
//				}
//
//			}

	
			
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_W) {
			upPress = false;
		}

		if (keyCode == KeyEvent.VK_S) {
			downPress = false;
		}

		if (keyCode == KeyEvent.VK_A) {
			leftPress = false;
		}

		if (keyCode == KeyEvent.VK_D) {
			rightPress = false;
		}

		if (keyCode == KeyEvent.VK_P) {
			if (gpReal.gameState == gpReal.playerState) {
				gpReal.gameState = gpReal.pauseState;
			}
			
			else if(gpReal.gameState == gpReal.pauseState) {
				gpReal.gameState = gpReal.playerState;
			}

		}

		if (keyCode == KeyEvent.VK_T) {

			if (checkTime == true) {
				checkTime = false;
			} else if (checkTime == false) {
				checkTime = true;
			}
		}

	}

}
