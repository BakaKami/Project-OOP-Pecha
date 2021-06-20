package game;

import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class HealthBooster extends Item{
	
	private ImageIcon pu_speed = new ImageIcon("fix/pu_life.png");
	
	public HealthBooster(){
		icon = pu_speed;
	}
	
	@Override
	public void affect_player(Player p) {
		p.setHealth(p.getHealth()+1);
	}

	public Image getImage() {
		return icon.getImage();
	}
	
}
