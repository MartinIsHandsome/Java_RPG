package firstPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyControl implements KeyListener {

	public boolean upPress, downPress, leftPress, rightPress;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

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

	}

}
