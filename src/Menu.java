
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	public Menu() {
		JFrame frame = new JFrame("PECHA v1.0");
		frame.setSize(1366, 768);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		
		JLabel start = new JLabel("Start");
		start.setBounds(100, 200, 100, 50);
		start.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		start.setForeground(Color.BLACK);
		start.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				start.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				start.setForeground(Color.RED);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				start.setForeground(Color.WHITE);
				
			}
		});
		frame.add(start);
		
		JLabel htp = new JLabel("Instruction");
		htp.setBounds(100, 300, 200, 100);
		htp.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		htp.setForeground(Color.BLACK);
		htp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				htp.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				htp.setForeground(Color.RED);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				htp.setForeground(Color.WHITE);
				
			}
		});
		frame.add(htp);
		
		JLabel exit = new JLabel("EXIT");
		exit.setBounds(1250, 600, 200, 100);
		exit.setFont(new Font("Chiller", Font.PLAIN, 26));
		exit.setForeground(Color.BLACK);
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.RED);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
		});
		frame.add(exit);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Menu();
	}

}
