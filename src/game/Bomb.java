package game;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Bomb {
	private long startTime;
	private int range;
	private ImageIcon bomb = new ImageIcon("fix/bom.png");

	public Bomb(int assignedRange) {
		this.range = assignedRange;
	}

	public void explode(Tiles location, Vector<Vector<Tiles>> mapTiles, boolean key_has_obtained, Player p) {
		if (System.currentTimeMillis() - startTime < 200)
			return;
		if (((Grass) location).getPlayer() != null)
			((Grass) location).getPlayer().setHealth(((Grass) location).getPlayer().getHealth() - 1);

		Thread moveLeft = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					// left=i;
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY).get(location.coorX - i);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null
								&& !(((Grass) CheckedTiles).getItem() instanceof Key)
								&& !(((Grass) CheckedTiles).getItem() instanceof Door)) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}
			}
		});
		Thread moveRight = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY).get(location.coorX + i);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null
								&& !(((Grass) CheckedTiles).getItem() instanceof Key)
								&& !(((Grass) CheckedTiles).getItem() instanceof Door)) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}
			}
		});
		Thread moveUp = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					// up=i;
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY - i).get(location.coorX);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null
								&& !(((Grass) CheckedTiles).getItem() instanceof Key)
								&& !(((Grass) CheckedTiles).getItem() instanceof Door)) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}
			}

		});
		Thread moveDown = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < range; i++) {
					boolean broke = false;
					Vector<Vector<Tiles>> tempTiles = mapTiles;
					Tiles CheckedTiles = tempTiles.get(location.coorY + i).get(location.coorX);
					if (CheckedTiles instanceof SolidWall)
						break;
					if (CheckedTiles instanceof Grass) {
						if (((Grass) CheckedTiles).getPlayer() != null) {
							Player temp_player = ((Grass) CheckedTiles).getPlayer();
							temp_player.setHealth(temp_player.getHealth() - 1);
							((Grass) CheckedTiles).setPlayer(temp_player);
						} else if (((Grass) CheckedTiles).getItem() != null
								&& !(((Grass) CheckedTiles).getItem() instanceof Key)
								&& !(((Grass) CheckedTiles).getItem() instanceof Door)) {
							((Grass) CheckedTiles).setItem(null);
							broke = true;
						} else if (((Grass) CheckedTiles).getBrick() != null) {
							((Grass) CheckedTiles).getBrick().destroyed(((Grass) CheckedTiles), key_has_obtained);
							broke = true;
						} else if (((Grass) CheckedTiles).getMonster() != null) {
							((Grass) CheckedTiles).getMonster()
									.setHealth(((Grass) CheckedTiles).getMonster().getHealth() - 1);
							broke = true;
						}
					}
					if (broke == true)
						break;
					else
						CheckedTiles.drawFire = true;
				}

			}
		});

		moveLeft.start();
		moveRight.start();
		moveUp.start();
		moveDown.start();
		p.setBomb_limit(p.getBomb_limit() + 1);
		location.setImage(new ImageIcon("fix/fire.png"));
		((Grass) location).setBomb(null);
		location.drawFire = true;
		return;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Image getImage() {
		return bomb.getImage();
	}


}
