package objectCode;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends superObject{
	// In superObject or OBJ_Chest
	
	public OBJ_Door() {
		name = "Door";
		
		try {
			
			look = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		collision = true;
	}	
}
