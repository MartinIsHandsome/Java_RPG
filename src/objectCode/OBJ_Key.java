package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_Key extends superObject {
	gamePanel hp;

	public OBJ_Key(gamePanel hp) {
		name = "Key";
		try {
			look = ImageIO.read(getClass().getResourceAsStream("/objects/Object.png"));
			uTool.scaleImage(look, hp.tileSize, hp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
