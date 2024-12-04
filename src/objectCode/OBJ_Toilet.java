package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_Toilet extends superObject {
	// In superObject or OBJ_Chest
	gamePanel hp;

	public OBJ_Toilet(gamePanel hp) {
		name = "Toilet";

		try {

			look = ImageIO.read(getClass().getResourceAsStream("/objects/056.png"));
			uTool.scaleImage(look, hp.tileSize, hp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}

		collision = true;
	}
}

