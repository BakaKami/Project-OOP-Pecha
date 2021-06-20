package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RangeBooster extends Item{
	
	private ImageIcon fire = new ImageIcon("fix/pu_fire.png");
	
	public RangeBooster(){
		icon = fire;
	}
	
	@Override
	public void affect_player(Player p) {
		p.setRange(p.getRange()+1);
	}
	
	public Image getImage() {
		return icon.getImage();
	}
	
}
