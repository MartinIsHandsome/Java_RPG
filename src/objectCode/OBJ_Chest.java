package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

import firstPackage.gamePanel;

public class OBJ_Chest extends superObject {
	gamePanel hp;
	public OBJ_Chest(gamePanel hp) {
	name = "Chest";
	try {
		look = ImageIO.read(getClass().getResourceAsStream("/objects/Chest.png"));
		uTool.scaleImage(look, hp.tileSize, hp.tileSize);
}
	catch(IOException e){
		e.printStackTrace();
	}
	}
}
