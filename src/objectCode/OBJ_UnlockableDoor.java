package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_UnlockableDoor extends superObject {
	// In superObject or OBJ_Chest
	gamePanel hp;

	public OBJ_UnlockableDoor(gamePanel hp) {
		name = "LockDoor";

		try {

			look = ImageIO.read(getClass().getResourceAsStream("/objects/060.png"));
			uTool.scaleImage(look, hp.tileSize, hp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}

		collision = true;
	}
}

