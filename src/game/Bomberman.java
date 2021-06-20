package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomberman {
	
	private ImageIcon player = new ImageIcon("fix/char.png");
	
	public Image getImage() {
		return player.getImage();
	}
	
}
