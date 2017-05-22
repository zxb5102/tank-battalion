package com.tank.v2;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import javax.swing.plaf.multi.MultiButtonUI;

public class MyTank extends Tank{
	
	int speed=5;
	int bullet_num=3;
	ArrayList<Bullet> mybullets;
//	Tank mytank;
	
	public MyTank(int x,int y,int dir){
		super(x,y,dir);
		Collection c = Collection.getCollection();
		mybullets = c.getMy_bullet();
//		mytank = c.getMytank();
	}
	public void concrolXY(int x){
		setDir(x);
		switch(this.dir){
			case 0:
				this.setY(this.y-speed);
				break;
			case 1:
				this.setX(this.x+speed);
				break;
			case 2:
				this.setY(this.y+speed);
				break;
			case 3:
				this.setX(this.x-speed);
//				System.out.println(this.x-speed);
				break;
		}
	}
	public void shoot(){
		int size = mybullets.size();
//		System.out.println(size);
//		bullet_num = Collection.getCollection().getMy_bullet().size();
		Bullet bullet;
//		if(size<bullet_num){
			bullet = new MyBullet(this.getX()+10, this.getY()+10, this.getDir());
			new Thread(bullet).start();
			mybullets.add(bullet);
//			System.out.println("fsf");
//			System.out.println(Collection.getCollection().getMy_bullet().size());
			
//		}
	}
}
