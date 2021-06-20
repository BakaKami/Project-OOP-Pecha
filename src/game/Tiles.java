package game;

import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Tiles {
	protected ImageIcon icon = new ImageIcon();
	protected boolean is_accesible = true;
	protected int coorX;
	protected int coorY;
	protected boolean drawFire = false;

	protected void setImage(ImageIcon icon) {
		this.icon = icon;
	}

	protected Image getImage() {
		return icon.getImage();
	}

	protected ImageObserver getImageObserver() {
		return icon.getImageObserver();
	}

}
