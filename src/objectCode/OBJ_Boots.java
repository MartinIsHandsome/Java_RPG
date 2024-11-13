package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_Boots extends superObject {
	gamePanel hp;

	public OBJ_Boots(gamePanel hp) {
		name = "Drink";
		try {
			look = ImageIO.read(getClass().getResourceAsStream("/objects/Drink.png"));
			uTool.scaleImage(look, hp.tileSize, hp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
