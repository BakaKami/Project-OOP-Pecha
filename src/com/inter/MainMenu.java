package com.inter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Map;

public class MainMenu extends JPanel {
	private JFrame frame = null;
	private ImageIcon[] image = new ImageIcon[4];
	Clip clip;

	public MainMenu() {

		frame = new JFrame("PECHA v1.0");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);

		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel bg = new JLabel(new ImageIcon("fix/TampilanAwal.jpg"));
		frame.add(bg);

		image[0] = new ImageIcon("fix/buttonPlayUP.png");
		image[1] = new ImageIcon("fix//buttonPlayDown.png");
		JLabel start = new JLabel(image[0]);
		start.setBounds(325, 200, 150, 100);
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
				start.setIcon(image[0]);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(MainMenu.this);
				JFrame panel = new Map();
				add(panel);
				panel.requestFocus();
				panel.validate();
				start.setIcon(image[1]);
				
			}

		});
		bg.add(start);

		image[2] = new ImageIcon("fix/buttonInstructionUp.png");
		image[3] = new ImageIcon("fix/buttonInstructionDown.png");
		JLabel htp = new JLabel(image[2]);
		htp.setBounds(300, 300, 200, 150);
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
				htp.setIcon(image[2]);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				htp.setIcon(image[3]);
				
			}
		});
		bg.add(htp);

		JLabel exit = new JLabel("EXIT");
		exit.setBounds(700, 450, 75, 75);
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
				exit.setFont(new Font("Chiller", Font.PLAIN, 26));
				exit.setForeground(Color.BLACK);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setFont(new Font("Chiller", Font.PLAIN, 36));
				exit.setForeground(Color.RED);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);

			}
		});
		bg.add(exit);

		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new MainMenu();
	}
}