package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import firstPackage.gamePanel;
import firstPackage.keyControl;

public class playerClass extends Entity {
	keyControl kH;
	gamePanel panel;

	public playerClass(keyControl kH, gamePanel panel) {
		this.kH = kH;
		this.panel = panel;
		setDefaultValues();
	}

	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
	}

	public void update() {
		if (kH.downPress == true) {
			y += speed;
		}

		else if (kH.upPress == true) {
			y -= speed;
			System.out.print("Working");
		} else if (kH.leftPress == true) {
			x -= speed;
		}

		else if (kH.rightPress == true) {
			x += speed;
		}

	}

	public void draw(Graphics2D g2) {

		g2.setColor(Color.RED);
		g2.fillRect(x, y, panel.tileSize, panel.tileSize);
	}
}
