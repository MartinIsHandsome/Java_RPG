package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends superObject {

	public OBJ_Chest() {
	name = "Chest";
	try {
		look = ImageIO.read(getClass().getResourceAsStream("/objects/Chest.png"));
	}
	catch(IOException e){
		e.printStackTrace();
	}
	}
}
