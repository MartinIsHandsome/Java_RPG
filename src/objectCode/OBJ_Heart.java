package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_Heart extends superObject {
	gamePanel k;

	public OBJ_Heart(gamePanel hp) {
		name = "Heart";
		try {
			look = ImageIO.read(getClass().getResourceAsStream("/objects/heart.png"));
			look2 = ImageIO.read(getClass().getResourceAsStream("/objects/heartHalf.png"));
			look3 = ImageIO.read(getClass().getResourceAsStream("/objects/heartEmpty.png"));
			// look =
			// look2 =
			// look3 =
			uTool.scaleImage(look, hp.tileSize, hp.tileSize);
			uTool.scaleImage(look2, hp.tileSize, hp.tileSize);
			uTool.scaleImage(look3, hp.tileSize, hp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
