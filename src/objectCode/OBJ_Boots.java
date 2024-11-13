package objectCode;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends superObject {
	public OBJ_Boots() {
		name = "Drink";
		try {
			look = ImageIO.read(getClass().getResourceAsStream("/objects/Drink.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
