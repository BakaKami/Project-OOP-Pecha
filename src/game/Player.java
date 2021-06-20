package game;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Player extends JFrame {
	private int health = 3;
	private int bomb_limit_max = 3;
	private int bomb_limit = 0;
	private int range = 1;
	private boolean has_obtain_key=false;
	public int coorX;
	public int coorY;
	public int temp_coorX;
	public int temp_coorY;
	public int locX;
	public int locY;
	public int temp_locX;
	public int temp_locY;
	public boolean win = false;
	public boolean lose = false;

	public boolean isHas_obtain_key() {
		return has_obtain_key;
	}

	public void setHas_obtain_key(boolean has_obtain_key) {
		this.has_obtain_key = has_obtain_key;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBomb_limit() {
		return bomb_limit;
	}

	public void setBomb_limit(int bomb_limit) {
		this.bomb_limit = bomb_limit;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public Player(int health, int bomb_limit, int range) throws HeadlessException {
		super();
		this.health = health;
		this.bomb_limit = bomb_limit;
		this.range = range;
	}

	public int getBomb_limit_max() {
		return bomb_limit_max;
	}

	public void setBomb_limit_max(int bomb_limit_max) {
		this.bomb_limit_max = bomb_limit_max;
	}

	public void printData() {
		System.out.println("health: " + health + "\nBomb_limit: " + bomb_limit + "\nRange: " + range + "\nBomb_limit_max: " + bomb_limit_max);
	}
	
}
