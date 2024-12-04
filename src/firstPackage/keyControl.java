package firstPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyControl implements KeyListener {

	public boolean upPress, downPress, leftPress, rightPress, enterText;

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
		// title select
		if (gpReal.gameState == gpReal.menuState) {
			if (keyCode == KeyEvent.VK_W) {
				gpReal.view.commandNum -= 1;
				if (gpReal.view.commandNum < 0) {
					gpReal.view.commandNum = 2;
				}
			}

			if (keyCode == KeyEvent.VK_S) {
				gpReal.view.commandNum += 1;

				if (gpReal.view.commandNum > 2) {
					gpReal.view.commandNum = 0;
				}
			}

			if (keyCode == KeyEvent.VK_ENTER && gpReal.view.commandNum == 0) {
				gpReal.gameState = gpReal.playerState;
			}
			
			if (keyCode == KeyEvent.VK_ENTER && gpReal.view.commandNum == 1) {
				gpReal.gameState = gpReal.playerState;
			}
			
			if (keyCode == KeyEvent.VK_ENTER && gpReal.view.commandNum == 2) {
			System.exit(0);
			}
		}
		// play state
		if (gpReal.gameState == gpReal.playerState) {

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
			if (keyCode == KeyEvent.VK_P) {
				if (gpReal.gameState == gpReal.playerState) {
					gpReal.gameState = gpReal.pauseState;
				}

			}
			if (keyCode == KeyEvent.VK_ENTER) {
				enterText = true;

			}

		}
		// play state
		else if (gpReal.gameState == gpReal.DialogueState) {
		    if (keyCode == KeyEvent.VK_ENTER) {
		        if (gpReal.view.isDisplayingMessages) {
		            gpReal.view.nextMessage(); // Show the next message
		        } else {
		            gpReal.gameState = gpReal.playerState; // End conversation if no more messages
		        }
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

			else if (gpReal.gameState == gpReal.pauseState) {
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
