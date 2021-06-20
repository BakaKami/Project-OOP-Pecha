package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Coba2 {
	Thread tempTh= new Thread(new Runnable() {
		
		@Override
		public void run() {
			while(true) {
				System.out.println(System.currentTimeMillis());
			}
		}
	});
	
	Timer timer = new Timer(1500,new ActionListener() {
		
		//@SuppressWarnings("deprecation")
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			tempTh.stop();
			
		}
	});
	
	Coba2(){
		tempTh.start();
		timer.start();
	}
	
	public static void main(String args[]) {
		new Coba2();
	}
}
