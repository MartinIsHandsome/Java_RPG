package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends superObject{

	
	public OBJ_Door() {
		name = "Door";
		
		name = "Key";
		try {
			look = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
