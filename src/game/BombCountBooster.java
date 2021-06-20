package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BombCountBooster extends Item{
	
	private ImageIcon pu_bomb = new ImageIcon("fix/pu_bomb.png");
	
	public BombCountBooster(){
		icon = pu_bomb;
	}
	
	public void affect_player(Player p){
		p.setBomb_limit_max(p.getBomb_limit_max()+1);
		p.setBomb_limit(p.getBomb_limit()+1);
	}

	public Image getImage() {
		return icon.getImage();
	}
	
}
