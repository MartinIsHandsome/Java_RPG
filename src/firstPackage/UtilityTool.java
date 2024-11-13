package firstPackage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage original,int width, int heigth){
		BufferedImage scaleImage = new BufferedImage(width, heigth, original.getType());
		Graphics2D g2 = scaleImage.createGraphics();
		g2.drawImage(original, 0, 0, width,heigth, null);
		g2.dispose();
		return scaleImage;
	}
}
