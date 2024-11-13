package objectCode;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_Door extends superObject {
	// In superObject or OBJ_Chest
	gamePanel hp;

	public OBJ_Door(gamePanel hp) {
		name = "Door";

		try {

			look = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
			uTool.scaleImage(look, hp.tileSize, hp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}

		collision = true;
	}
}
