package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Door extends Item {

	public Door() {
		icon = new ImageIcon("fix/door_closed.png");
	}

	@Override
	public void affect_player(Player P) {
		if (P.isHas_obtain_key() == true)
			P.win = true;
	}
	
	public Image getImage() {
		return icon.getImage();
	}

}
