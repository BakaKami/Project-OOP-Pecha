package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Grass extends Tiles {
	private Player player;
	private Monster monster;
	private Item item = null;
	private Brick brick = null;
	private Bomb bomb = null;
	private ImageIcon brickWall = new ImageIcon("fix/brick.png");
	private ImageIcon grass = new ImageIcon("fix/grass.png");
	private ImageIcon image = grass;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Brick getBrick() {
		return brick;
	}

	public void setBrick(Brick brick) {
		if (brick == null) {
			this.is_accesible = true;
			this.brick = null;
			this.image = grass;
		} else {
			this.is_accesible = false;
			this.brick = brick;
			this.image = brickWall;
		}
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public Grass() {
		is_accesible = true;
	}

	public void assign_item(int i) {
		if (item != null)
			return;
		switch (i) {
		case 0:
			item = (Item) new HealthBooster();
			break;
		case 1:
			item = (Item) new RangeBooster();
			break;
		case 2:
			item = (Item) new BombCountBooster();
			break;
		case 3:
			item = (Item) new Key();
			break;
		default:
			item = (Item) new BombCountBooster();
		}

	}

	public Image getImage() {
		return image.getImage();
	}

}
