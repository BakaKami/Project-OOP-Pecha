package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Window extends JFrame{
	int box_size=20;
	//paint
	//int increment=box_size/2;
	//int obj_x;
	//int obj_y;
	int height=720;
	int width=720/16*9; 
	int player_x=30;
	int player_y=60;
	int temp_x=10;
	int temp_y=30;
	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(height,width);
		setVisible(true);
		addKeyListener(player_move);
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, 1000, 1000);
		for(int i=10;i<height;i+=box_size) {
			if(i+20>height) break;
			for (int j=30;j<width;j+=box_size) {
				if(j+20>width) break;
				g.setColor(Color.black);
				g.drawRect(i, j, box_size, box_size);
				//g.drawString(""+((i/20+1) * (j/20+1))/3, i, j);
				//g.f
				//System.out.println(i + " "+ j);
			}
		}
		g.fillRect((player_x/20)*20+10, (player_y/20)*20+10, box_size, box_size);
		g.setColor(Color.green);
		g.fillRect(player_x+1, player_y+1, box_size-2, box_size-2);
		System.out.println("x: "+(player_x-10)/20 + " y: " + (player_y-30)/20+" temp_x");
		
	}
	
	private KeyListener player_move = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_A:
					temp_x-=5; //TODO: '2' diganti dengan tiles size dibagi sekian
					break;
				case KeyEvent.VK_D:
					temp_x+=5;
					break;
				case KeyEvent.VK_W:
					temp_y-=5;
					break;
				case KeyEvent.VK_S:
					temp_y+=5;
					break;
				case KeyEvent.VK_SPACE:
					//TODO: PlaceBombFunction
					break;
			}
			if(player_x%20<5||player_x%20>15) {
				if(player_x%20>10)player_x = (player_x/20 + 1)*20+1;
				else player_x = (player_x/20 + 0)*20+1;
			}
			if(player_y%20<5||player_y%20>15) {
				if(player_y%20>10)player_y = (player_y/20 + 1)*20+1;
				else player_y = (player_y/20 + 0)*20+1;
			}
			if(true||((temp_x-10)/20 + (temp_y-30)/20)%2==0) {
				//System.out.println(((temp_x-10)/20)+" "+((temp_y-30)/20));
				player_x=temp_x;
				player_y=temp_y;
			}
			repaint();
		}
	};
	public static void main(String args[]) {
		new Window();
	}
}
