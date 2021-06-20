package game;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Cobates extends JFrame{
	ImageIcon img = new ImageIcon("fix/flame_center.png");
	@Override
	public void paint(Graphics g) {
		g.drawImage(img.getImage(), 100, 100, 30, 30, null);
		//g.draw
		//g.fillRect(100, 100, 100, 100);
	}
	
	Cobates(){
		setSize(500, 500);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new Cobates();
	}
}
