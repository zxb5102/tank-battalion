package com.tank.v2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MyPanel extends Panel implements Runnable {

	ArrayList<Bullet> mybullets;
	final MyTank mytank;
	ArrayList<Bullet> enemybullets;
	ArrayList<Tank> enemytanks;

	Collection c;
	ArrayList<int[]> river;
	ArrayList<int[]> iron;
	ArrayList<int[]> grass;
	ArrayList<int[]> brick;
	
	Image img_river;
	Image img_iron;
	Image img_grass;
	Image img_brick;
	
	
	public MyPanel() {
		
		c = Collection.getCollection();

		river = c.getRiver();
		iron = c.getIron();
		grass = c.getGrass();
		brick = c.getBrick();
		String river_path = "com/tank/v2/img/river.png";
		String brick_path = "com/tank/v2/img/brick.png";
		String iron_path = "com/tank/v2/img/iron.png";
		String grass_path = "com/tank/v2/img/graSS.png";
		
		URL url = ClassLoader.getSystemResource(river_path);
		img_river = Toolkit.getDefaultToolkit().getImage(url);
		url = ClassLoader.getSystemResource(iron_path);
		img_iron = Toolkit.getDefaultToolkit().getImage(url);
		url = ClassLoader.getSystemResource(brick_path);
		img_brick = Toolkit.getDefaultToolkit().getImage(url);
		url = ClassLoader.getSystemResource(grass_path);
		img_grass = Toolkit.getDefaultToolkit().getImage(url);
		
		mybullets = c.getMy_bullet();
		mytank = (MyTank) c.getMytank();
		enemybullets = c.getEnemy_bullet();
		enemytanks = c.getEnemy_tanks();

		KeyAdapter kapt = new KeyAdapter() {
			int dir;

			@Override
			public void keyPressed(KeyEvent e) {
				if (mytank.flag) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						dir = 0;
						mytank.concrolXY(dir);
						break;
					case KeyEvent.VK_DOWN:
						dir = 2;
						mytank.concrolXY(dir);
						break;
					case KeyEvent.VK_LEFT:
						dir = 3;
						mytank.concrolXY(dir);
						break;
					case KeyEvent.VK_RIGHT:
						dir = 1;
						mytank.concrolXY(dir);
						break;
					case KeyEvent.VK_X:
						mytank.shoot();
						break;
					}
				}
			}
		};

		this.addKeyListener(kapt);

	}

	@Override
	public void paint(Graphics g) {
		Graphics _this_g = g;
		Image createImage = createImage(510, 630);
		Graphics graphics = createImage.getGraphics();
		g = graphics;

		super.paint(g);
		hitEnemyPaint(g);
		hitMyPaint(g);
		// hitBarriers(g);
		paintBarriers(g);

		_this_g.drawImage(createImage, 0, 0, null);
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void hitEnemyPaint(Graphics g) {

		int bullet_x;
		int bullet_y;
		Bullet bullet;
		Tank tank;
		int tank_x;
		int tank_y;
		if (mybullets.size() == 0) {

			for (int j = 0; j < enemytanks.size(); j++) {
				tank = enemytanks.get(j);
				tank_x = tank.getX();
				tank_y = tank.getY();
				paintTank(tank_x, tank_y, g, tank.getDir(), 1);
			}
		} else {

			for (int i = 0; i < mybullets.size(); i++) {
				// System.out.println("fsf");
				bullet = mybullets.get(i);
				bullet_x = bullet.getX();
				bullet_y = bullet.getY();
				if (enemytanks.size() != 0) {
					for (int j = 0; j < enemytanks.size(); j++) {
						tank = enemytanks.get(j);
						tank_x = tank.getX();
						tank_y = tank.getY();
						if ((tank.dir == 0 || tank.dir == 2) && bullet_x > tank_x - 5 && bullet_x < tank_x + 23
								&& bullet_y > tank_y - 3 && bullet_y < tank_y + 30) {
							bullet.flag = false;
							enemytanks.remove(tank);
							// System.out.println("dfsdf");
							tank.flag = false;
						} else if ((tank.dir == 1 || tank.dir == 3) && bullet_x > tank_x - 5 && bullet_x < tank_x + 25
								&& bullet_y > tank_y - 3 && bullet_y < tank_y + 25) {
							bullet.flag = false;
							enemytanks.remove(tank);
							// System.out.println("fsdfs");
							tank.flag = false;
						} else {
							PaintBullet(bullet_x, bullet_y, g, 1);
							paintTank(tank_x, tank_y, g, tank.getDir(), 1);
						}
					}
				} else {
					PaintBullet(bullet_x, bullet_y, g, 1);
				}
			}
		}
	}

	private void hitMyPaint(Graphics g) {

		int bullet_x;
		int bullet_y;
		Bullet bullet;
		Tank tank = mytank;

		int tank_x = tank.getX();
		int tank_y = tank.getY();
		if (enemybullets.size() == 0) {
			paintTank(tank_x, tank_y, g, tank.getDir(), 0);
		} else {
			for (int i = 0; i < enemybullets.size(); i++) {
				bullet = enemybullets.get(i);
				bullet_x = bullet.getX();
				bullet_y = bullet.getY();
				if (tank.flag) {
					if ((tank.dir == 0 || tank.dir == 2) && bullet_x >= tank_x - 5 && bullet_x <= tank_x + 23
							&& bullet_y >= tank_y - 3 && bullet_y <= tank_y + 30) {
						bullet.flag = false;
						tank.flag = false;
						// System.out.println("ok");
					} else if ((tank.dir == 1 || tank.dir == 3) && bullet_x >= tank_x - 5 && bullet_x <= tank_x + 25
							&& bullet_y >= tank_y - 3 && bullet_y <= tank_y + 25) {
						bullet.flag = false;
						tank.flag = false;
						// System.out.println("ok");

					} else {
						paintTank(tank_x, tank_y, g, tank.getDir(), 0);

						PaintBullet(bullet_x, bullet_y, g, 0);
					}
				} else {
					PaintBullet(bullet_x, bullet_y, g, 0);
				}
			}
		}
	}

	private void paintBarriers(Graphics g) {
		int[] point;
		int point_x;
		int point_y;
		int i;
		
		
		for(i=0;i<river.size();i++){
			point = river.get(i);
			point_x = point[0];
			point_y = point[1];
			g.drawImage(img_river, point_x*50, point_y*50, null);
		}
		
		for(i=0;i<brick.size();i++){
			point = brick.get(i);
			
			point_x = point[0];
			point_y = point[1];
//			System.out.println(point_x+"   "+point_y);
//		System.exit(0);
			g.drawImage(img_brick, point_x*50, point_y*50, null);
//			System.out.println(point_x+"    "+point_y);
		}
		for(i=0;i<iron.size();i++){
			point = iron.get(i);
			point_x = point[0];
			point_y = point[1];
			g.drawImage(img_iron, point_x*50, point_y*50, null);
		}
		for(i=0;i<grass.size();i++){
			point = grass.get(i);
			point_x = point[0];
			point_y = point[1];
			g.drawImage(img_grass, point_x*50, point_y*50, null);
		}

	}

	public void paintTank(int x, int y, Graphics g, int direction, int type) {
		switch (type) {
		case 0:
			g.setColor(Color.RED);
			break;
		case 1:
			g.setColor(Color.BLUE);
			break;
		}
		switch (direction) {
		// height:25 width:21
		case 0:
//			PaintBullet(x, y, g, 0);
			g.fill3DRect(x, y, 6, 25, false);
			g.fill3DRect(x + 15, y, 6, 25, false);
			g.fill3DRect(x + 6, y + 6, 9, 15, false);
			g.fillOval(x + 5, y + 8, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y);
			break;
		case 1:
//			PaintBullet(x, y, g, 0);
			g.fill3DRect(x, y, 25, 6, false);
			g.fill3DRect(x, y + 15, 25, 6, false);
			g.fill3DRect(x + 4, y + 6, 15, 9, false);
			g.fillOval(x + 6, y + 5, 10, 10);
			g.drawLine(x + 12, y + 10, x + 27, y + 10);
			break;
		case 2:
//			PaintBullet(x, y, g, 0);
			g.fill3DRect(x, y, 6, 25, false);
			g.fill3DRect(x + 15, y, 6, 25, false);
			g.fill3DRect(x + 6, y + 5, 9, 15, false);
			g.fillOval(x + 5, y + 7, 10, 10);
			g.drawLine(x + 10, y + 15, x + 10, y + 25);
			break;
		case 3:
//			PaintBullet(x, y, g, 0);
			g.fill3DRect(x, y, 25, 6, false);
			g.fill3DRect(x, y + 15, 25, 6, false);
			g.fill3DRect(x + 6, y + 6, 15, 9, false);
			g.fillOval(x + 8, y + 5, 10, 10);
			g.drawLine(x + 15, y + 10, x, y + 10);
		}

	}

	public void PaintBullet(int x, int y, Graphics g, int type) {
		if (type == 0) {
			g.setColor(Color.blue);
		}
		if (type == 1) {
			g.setColor(Color.RED);
		}
		g.fillOval(x, y, 5, 5);
	}

}
