package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Map extends JFrame {
	private int monsterX;
	private int monsterY;
	private int width = 1366;
	private int height = 768;
	private int tile_size = 30;
	private Vector<Vector<Tiles>> mapTiles = new Vector<Vector<Tiles>>();
	private Player player = new Player(10, 3, 2);
	private boolean drawLayout = true;
	private boolean drawMap = true;
	private boolean drawFire = false;
	private boolean drawBomb;
	private boolean drawWin;
	private boolean drawLose;
	private int horizontal_tiles_total = 19;
	private int vertical_tiles_total = 19;
	private boolean key_has_obtained = false;
	private int start_y = 60;
	private int bonus = 0;
	private int start_x = 240 + bonus;
	private int bomb_x, bomb_y;
	private int percentage_of_brick = 50;
	private int pxlX, pxlY, coorX, coorY; 
	private boolean drawPlayer = true;

	public int getTile_size() {
		return tile_size;
	}

	public void setTile_size(int tile_size) {
		this.tile_size = tile_size;
	}

	public Vector<Vector<Tiles>> getMapTiles() {
		return mapTiles;
	}

	public void setMapTiles(Vector<Vector<Tiles>> mapTiles) {
		this.mapTiles = mapTiles;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int get_random() {
		if (key_has_obtained == false)
			return 4;
		else
			return 3;
	}

	public Thread tempThread1 = new Thread(new Runnable() {

		@SuppressWarnings("deprecation")
		@Override

		public void run() {
			Scanner in = new Scanner(System.in);
			while (true) {
				if (player.getBomb_limit() == 0) {
					System.out.println("No Bomb Available");
					continue;
				}
				System.out.println("Input X");
				int x = in.nextInt();
				System.out.println("Input Y");
				int y = in.nextInt();
				bomb_x = x;
				bomb_y = y;
				drawBomb = true;
				repaint();
				Tiles temp_tiles = mapTiles.get(bomb_y).get(bomb_x);
				if (temp_tiles instanceof SolidWall)
					continue;
				if ((((Grass) temp_tiles).getBrick() != null))
					System.out.println("Brick");
				else {
					((Grass) temp_tiles).setBomb(new Bomb(player.getRange()));
					player.setBomb_limit(player.getBomb_limit() - 1);
					System.out.print("*");
					((Grass) temp_tiles).getBomb().explode((Grass) temp_tiles, mapTiles, key_has_obtained, player);
					System.out.println("Bomb Placed&exploded");
					drawMap = true;
					drawLayout = true;
					repaint();

				}
			}
		}
	});

	public Map() {
		fill_tile();
		player.setHas_obtain_key(false);
		((Grass) mapTiles.get(coorY).get(coorX)).setPlayer(player);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
		tempThread1.start();
		checkBomb.start();
		move();
	}

	public Image temp(Tiles tile) {
		return tile.getImage();
	}

	public Image temp2(Tiles tile) {
		if (tile instanceof Grass) {
			if (((Grass) tile).getBrick() != null)
				return new ImageIcon("fix/brick.png").getImage();
			Item itemm = ((Grass) tile).getItem();
			if (itemm != null) {
				try {
					if (itemm instanceof Door && player.isHas_obtain_key() == true)
						itemm.icon = new ImageIcon("fix/door_opened.png");
					return itemm.icon.getImage();
				} catch (Exception e) {
					return new ImageIcon("fix/grass.png").getImage();
				}
			}
			if (((Grass) tile).getBomb() != null)
				return new ImageIcon("fix/bom.png").getImage();
		}
		return tile.getImage();
	}

	public void paint(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.fillRect(1000, 0, 1366, 768);

		if (drawLayout == true) {
			ImageIcon lives = new ImageIcon("fix/pu_life.png");
			ImageIcon bomb = new ImageIcon("fix/pu_bomb.png");
			ImageIcon fire = new ImageIcon("fix/pu_fire.png");
			ImageIcon key = new ImageIcon("fix/golden-key.png");
//			g.clearRect(0, 0, 2000, 2000);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.setColor(Color.ORANGE);
			g.fillRect(820 + bonus, 140, 160, 320);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(830 + bonus, 150, 140, 300);
			g.setColor(Color.GRAY);
			g.drawString("STATS", 850, 130);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Calibri", Font.PLAIN, 20));
			g.drawImage(lives.getImage(), 860, 180, lives.getImageObserver());
			String str = new String("X ");
			g.drawString(str + player.getHealth() , 900, 200);
			g.drawImage(bomb.getImage(), 860, 230, bomb.getImageObserver());
			g.drawString(str + player.getBomb_limit_max(), 900, 250);
			g.drawImage(fire.getImage(), 860, 280, fire.getImageObserver());
			g.drawString(str + player.getRange() , 900, 300);
			if(player.isHas_obtain_key())
				g.drawImage(key.getImage(), 860, 330, key.getImageObserver());
			drawLayout = false;
		}
		if (drawMap == true) {
			drawMap = false;
			for (int i = 0, curr_y = start_y; i < mapTiles.size(); i++) {
				for (int j = 0, curr_x = start_x; j < mapTiles.get(0).size(); j++) {
					g.drawImage(temp(mapTiles.get(i).get(j)), curr_x, curr_y, tile_size, tile_size,
							mapTiles.get(i).get(j).getImageObserver());
					if (mapTiles.get(i).get(j).is_accesible == true) {
						if (((Grass) mapTiles.get(i).get(j)).getItem() instanceof Key)
							key_has_obtained = true;
					}
				}
				curr_y += tile_size;
			}
		}
		if (drawBomb == true) {
			Bomb bomb = new Bomb(2);
			g.drawImage(bomb.getImage(), start_x + bomb_x * tile_size, start_y + bomb_y * tile_size, tile_size,
					tile_size, mapTiles.get(bomb_x).get(bomb_y).getImageObserver());
			drawBomb = false;
		}
		if (drawFire == true) {
			for (int i = 0, curr_y = start_y; i < mapTiles.size(); i++) {
				for (int j = 0, curr_x = start_x; j < mapTiles.get(0).size(); j++) {
					if (mapTiles.get(i).get(j).drawFire == true) {
						mapTiles.get(i).get(j).drawFire = false;
						if (mapTiles.get(i).get(j) instanceof SolidWall)
							continue;
						g.drawImage(new ImageIcon("fix/fire.png").getImage(), curr_x, curr_y, tile_size, tile_size,
								mapTiles.get(i).get(j).getImageObserver());
						if (((Grass) mapTiles.get(i).get(j)).getMonster() != null)
							((Grass) mapTiles.get(i).get(j)).getMonster().setHealth(0);
					}
					curr_x += tile_size;
				}
				curr_y += tile_size;
			}
			drawFire = false;
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < 100)
				;
		}
		if (drawPlayer == true) {
			player.printData();
			drawPlayer = false;
			for (int i = 0, curr_y = start_y; i < mapTiles.size(); i++) {
				for (int j = 0, curr_x = start_x; j < mapTiles.get(0).size(); j++) {
					if (i == 1 && j == 1)
						System.out.println(curr_x + " & " + curr_y);
					g.drawImage(temp(mapTiles.get(i).get(j)), curr_x, curr_y, tile_size, tile_size,
							mapTiles.get(i).get(j).getImageObserver());
					g.drawImage(temp2(mapTiles.get(i).get(j)), curr_x, curr_y, tile_size, tile_size,
							mapTiles.get(i).get(j).getImageObserver());
					if (mapTiles.get(i).get(j) instanceof Grass) {
						if (((Grass) mapTiles.get(i).get(j)).getItem() instanceof Key)
							key_has_obtained = true;
						if (((Grass) mapTiles.get(i).get(j)).getMonster() != null) {
							g.drawImage(new ImageIcon("fix/pu_speed.png").getImage(), curr_x, curr_y, tile_size,
									tile_size, mapTiles.get(i).get(j).getImageObserver());
							g.drawString("Health: " + ((Grass) mapTiles.get(i).get(j)).getMonster().getHealth(), 1000,
									400);
						}

					}

					curr_x += tile_size;

				}
				curr_y += tile_size;
			}
			if (this.player.isHas_obtain_key() == true)
				g.setColor(Color.yellow);
			g.fillRect(pxlX, pxlY, tile_size, tile_size);
			g.drawImage(new ImageIcon("fix/monster.png").getImage(), pxlX, pxlY, tile_size, tile_size, null);

			repaint();
		}
		if (drawWin == true) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 1366, 768);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("You Win", 500, 400);
			return;
		}
		if (drawLose == true) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 1366, 768);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("You Lose", 500, 400);
			return;
		}
		stateChecker();
	}

	public void fill_tile() {
		for (int i = 0; i < vertical_tiles_total; i++) {
			mapTiles.add(new Vector<Tiles>());
			for (int j = 0; j < horizontal_tiles_total; j++) {
				if (i == 0 || j == 0 || i == vertical_tiles_total - 1 || j == horizontal_tiles_total - 1
						|| (i % 2 == 0 && j % 2 == 0))
					mapTiles.get(mapTiles.size() - 1).add((Tiles) new SolidWall());
				else
					mapTiles.get(mapTiles.size() - 1).add((Tiles) new Grass());
				mapTiles.get(i).get(j).coorX = j;
				mapTiles.get(i).get(j).coorY = i;
			}
		}
		for (int i = 0; i < horizontal_tiles_total * vertical_tiles_total * percentage_of_brick / 100; i++) {
			int x;
			int y;
			while (true) {
				x = new Random().nextInt(horizontal_tiles_total);
				y = new Random().nextInt(vertical_tiles_total);
				if (!(mapTiles.get(y).get(x) instanceof Grass))
					continue;
				((Grass) mapTiles.get(y).get(x)).setBrick(new Brick());
				((Grass) mapTiles.get(y).get(x)).is_accesible = false;
				break;
			}
		}
		((Grass) mapTiles.get(1).get(1)).setBrick(null);
		((Grass) mapTiles.get(1).get(2)).setBrick(null);
		((Grass) mapTiles.get(2).get(1)).setBrick(null);
		((Grass) mapTiles.get(2).get(1)).is_accesible = true;
		((Grass) mapTiles.get(1).get(2)).is_accesible = true;
		((Grass) mapTiles.get(1).get(1)).is_accesible = true;
		coorX = 1;
		coorY = 1;
		pxlX = coorX * tile_size + start_x;
		pxlY = coorY * tile_size + start_y;
		int keyx = new Random().nextInt(horizontal_tiles_total);
		int keyy = new Random().nextInt(vertical_tiles_total);
		while (true) {
			keyx = new Random().nextInt(horizontal_tiles_total);
			keyy = new Random().nextInt(vertical_tiles_total);
			System.out.println(keyx + " " + keyy);
			if (keyy >= vertical_tiles_total || keyx >= horizontal_tiles_total)
				continue;
			if (!(mapTiles.get(keyy).get(keyx) instanceof Grass))
				continue;
			if (((Grass) mapTiles.get(keyy).get(keyx)).getBrick() == null)
				continue;
			((Grass) mapTiles.get(keyy).get(keyx)).setItem(new Key());
			break;
		}
		while (true) {
			keyx = new Random().nextInt(horizontal_tiles_total);
			keyy = new Random().nextInt(vertical_tiles_total);
			System.out.println(keyx + " " + keyy);
			if (keyy >= vertical_tiles_total || keyx >= horizontal_tiles_total)
				continue;
			if (!(mapTiles.get(keyy).get(keyx) instanceof Grass))
				continue;
			if (((Grass) mapTiles.get(keyy).get(keyx)).getBrick() == null)
				continue;
			if (((Grass) mapTiles.get(keyy).get(keyx)).getItem() != null)
				continue;
			((Grass) mapTiles.get(keyy).get(keyx)).setItem(new Door());
			break;
		}
	}

	public KeyListener moving = new KeyListener() {

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
			int key = e.getKeyCode();
			((Grass) mapTiles.get(coorY).get(coorX)).setPlayer(null);
			switch (key) {
			case KeyEvent.VK_RIGHT:
				if (mapTiles.get(coorY).get(coorX + 1) instanceof SolidWall && pxlX % 30 != 15)
					break;
				if (pxlX % 30 != 15 && ((Grass) mapTiles.get(coorY).get(coorX + 1)).getBrick() != null)
					break;
				if (pxlX % 30 != 15 && !(mapTiles.get(coorY).get(coorX + 1) instanceof SolidWall) && ((Grass) mapTiles.get(coorY).get(coorX + 1)).getBomb() != null)
					break;
				if (pxlY % 30 == 15)
					break;
				pxlX += 15;
				if (pxlX % 30 == 15) {
					coorX = (pxlX - start_x) / 30 + 1;
				} else {
					coorX = (pxlX - start_x) / 30;
				}
				break;
			case KeyEvent.VK_LEFT:
				if (mapTiles.get(coorY).get(coorX - 1) instanceof SolidWall && pxlX % 30 != 15)
					break;
				if (((Grass) mapTiles.get(coorY).get(coorX - 1)).getBrick() != null && pxlX % 30 != 15)
					break;
				if (pxlY % 30 == 15)
					break;
				if (pxlX % 30 != 15 && ((Grass) mapTiles.get(coorY).get(coorX - 1)).getBomb() != null)
					break;
				pxlX -= 15;
				if (pxlX % 30 == 0) {
					coorX = (pxlX - start_x) / 30;
				} else {
					coorX = (pxlX - start_x) / 30 + 1;
				}
				break;
			case KeyEvent.VK_UP:
				if (mapTiles.get(coorY - 1).get(coorX) instanceof SolidWall && pxlY % 30 != 15)
					break;
				if (pxlY % 30 != 15 && ((Grass) mapTiles.get(coorY - 1).get(coorX)).getBrick() != null)
					break;
				if (pxlX % 30 == 15)
					break;
				if (pxlY % 30 != 15 && ((Grass) mapTiles.get(coorY - 1).get(coorX)).getBomb() != null)
					break;
				pxlY -= 15;
				if (pxlY % 30 == 0) {
					coorY = (pxlY - start_y) / 30;
				} else {
					coorY = (pxlY - start_y) / 30 + 1;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (mapTiles.get(coorY + 1).get(coorX) instanceof SolidWall && pxlY % 30 != 15)
					break;
				if (((Grass) mapTiles.get(coorY + 1).get(coorX)).getBrick() != null && pxlY % 30 != 15)
					break;
				if (pxlX % 30 == 15)
					break;
				if (pxlY % 30 != 15 && ((Grass) mapTiles.get(coorY + 1).get(coorX)).getBomb() != null)
					break;
				pxlY += 15;
				if (pxlY % 30 == 15) {
					coorY = (pxlY - start_y) / 30 + 1;
				}
				coorY = (pxlY - start_y) / 30;
				break;
			case KeyEvent.VK_SPACE:
				if (player.getBomb_limit() == 0)
					break;
				player.setBomb_limit(player.getBomb_limit() - 1);
				bomb_x = coorX;
				bomb_y = coorY;
				Bomb temp = new Bomb(player.getRange());
				temp.setStartTime(System.currentTimeMillis());
				((Grass) mapTiles.get(bomb_y).get(bomb_x)).setBomb(temp);
				drawBomb = true;
				repaint();
				break;

			}
			if (((Grass) mapTiles.get(coorY).get(coorX)).getItem() != null) {
				((Grass) mapTiles.get(coorY).get(coorX)).getItem().affect_player(player);
				if (!(((Grass) mapTiles.get(coorY).get(coorX)).getItem() instanceof Door))
					((Grass) mapTiles.get(coorY).get(coorX)).setItem(null);
			}
			((Grass) mapTiles.get(coorY).get(coorX)).setPlayer(player);
			drawPlayer = true;
			drawLayout = true;
			repaint();
		}
	};

	private void move() {
		addKeyListener(moving);
	}

	private void endGame() {
		removeKeyListener(moving);
		drawMap = false;
		drawBomb = false;
		drawFire = false;
		drawPlayer = false;
		drawLayout = false;
		if (player.win == true)
			drawWin = true;
		else if (player.lose == true)
			drawLose = true;
		repaint();
	}

	private void stateChecker() {
		if (player.getHealth() <= 0)
			player.lose = true;
		if (player.win == true || player.lose == true)
			endGame();
	}

	private void monsterInit() {
		monsterX = 2;
		monsterY = 1;
		((Grass) mapTiles.get(monsterY).get(monsterX)).setMonster(new Monster());
		monsterMove.start();
	}

	public Thread monsterMove = new Thread(new Runnable() {

		@Override
		public void run() {

			while (true) {
				System.out.println("\tMonster X: " + monsterX + "\n\tMonster Y: " + monsterY + "\n\tMomon Health: "
						+ ((Grass) mapTiles.get(monsterY).get(monsterX)).getMonster().getHealth());
				if (player.getHealth() > 0
						&& ((Grass) mapTiles.get(monsterY).get(monsterX)).getMonster().getHealth() > 0)
					break;
				int x = new Random().nextInt() % 4;
				((Grass) mapTiles.get(monsterY).get(monsterX)).setMonster(null);
				switch (x) {
				case 0: // Left,Right,Up,Down
					if (mapTiles.get(monsterY).get(monsterX - 1) instanceof SolidWall)
						break;
					if (((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getBrick() != null
							|| ((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getBomb() != null)
						break;
					if (((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getPlayer() != null) {
						((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getPlayer().setHealth(
								((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getPlayer().getHealth() - 1);
					} else
						monsterX--;
					break;
				case 1: // Left,Right,Up,Down
					if (mapTiles.get(monsterY).get(monsterX + 1) instanceof SolidWall)
						break;
					else if (((Grass) mapTiles.get(monsterY).get(monsterX + 1)).getBrick() != null
							|| ((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getBomb() != null)
						break;
					else if (((Grass) mapTiles.get(monsterY).get(monsterX + 1)).getPlayer() != null) {
						((Grass) mapTiles.get(monsterY).get(monsterX + 1)).getPlayer().setHealth(
								((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getPlayer().getHealth() - 1);
					} else
						monsterX++;
					break;
				case 2: // Left,Right,Up,Down
					if (mapTiles.get(monsterY - 1).get(monsterX) instanceof SolidWall)
						break;
					if (((Grass) mapTiles.get(monsterY - 1).get(monsterX)).getBrick() != null
							|| ((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getBomb() != null)
						break;
					if (((Grass) mapTiles.get(monsterY - 1).get(monsterX)).getPlayer() != null) {
						((Grass) mapTiles.get(monsterY - 1).get(monsterX)).getPlayer().setHealth(
								((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getPlayer().getHealth() - 1);
					} else
						monsterY--;
					break;
				case 3: // Left,Right,Up,Down
					if (mapTiles.get(monsterY + 1).get(monsterX) instanceof SolidWall)
						break;
					if (((Grass) mapTiles.get(monsterY + 1).get(monsterX)).getBrick() != null
							|| ((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getBomb() != null)
						break;
					if (((Grass) mapTiles.get(monsterY + 1).get(monsterX)).getPlayer() != null) {
						((Grass) mapTiles.get(monsterY + 1).get(monsterX)).getPlayer().setHealth(
								((Grass) mapTiles.get(monsterY).get(monsterX - 1)).getPlayer().getHealth() - 1);
					} else
						monsterY++;
					break;
				}
				((Grass) mapTiles.get(monsterY).get(monsterX)).setMonster(new Monster());
				drawPlayer = true;
				repaint();
				long start = System.currentTimeMillis();
				while (System.currentTimeMillis() - start < 200);
			}
		}
	});
	
	public Thread checkBomb = new Thread(new Runnable() {

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < vertical_tiles_total; i++) {
					for (int j = 0; j < horizontal_tiles_total; j++) {
						if (mapTiles.get(i).get(j) instanceof SolidWall)
							continue;
						if (((Grass) mapTiles.get(i).get(j)).getBomb() != null) {
							if (((Grass) mapTiles.get(i).get(j)).getBomb().getStartTime() + 2500 < System
									.currentTimeMillis()) {
								((Grass) mapTiles.get(i).get(j)).getBomb().explode((mapTiles.get(i).get(j)), mapTiles,
										key_has_obtained, player);
								drawMap = true;
								drawPlayer = true;
								drawFire = true;
								repaint();
							}

						}
					}
				}
				long starting = System.currentTimeMillis();
				while (System.currentTimeMillis() - starting < 10);
			}

		}
	});

	public static void main(String args[]) {
		Map m = new Map();

	}
}
