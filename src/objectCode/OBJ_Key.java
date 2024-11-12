package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends superObject {

	public OBJ_Key() {
	name = "Key";
	try {
		look = ImageIO.read(getClass().getResourceAsStream("/objects/Object.png"));
	}
	catch(IOException e){
		e.printStackTrace();
	}
	
	
	}
}
