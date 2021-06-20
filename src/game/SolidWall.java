package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SolidWall extends Tiles {

	ImageIcon solidWall = new ImageIcon("fix/solidwall.jpg");

	public SolidWall() {
		is_accesible = false;
	}

	public Image getImage() {
		return solidWall.getImage();
	}

}
