package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Key extends Item {

	public Key() {
		icon = new ImageIcon("fix/golden-key.png");
	}

	@Override
	public void affect_player(Player p) {
		p.setHas_obtain_key(true);
	}

	public Image getImage() {
		return icon.getImage();
	}
}
