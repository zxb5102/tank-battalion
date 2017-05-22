package com.tank.v2;

import java.util.ArrayList;

public class EnemyBullet extends Bullet{

	ArrayList<Bullet> enemybullets;
	public EnemyBullet(int x, int y, int dir) {
		super(x, y, dir);
		enemybullets = Collection.getCollection().getEnemy_bullet();
	}
	
	@Override
	protected boolean validatex(int x) {
	
		 if(!super.validatex(x)){
			 enemybullets.remove(this);
			 this.flag = false;
//			 Thread.currentThread().interrupt();
		 }
			 return true;
		 
	}
	
	@Override
	protected boolean validatey(int y) {

		if(!super.validatey(y)){
			 enemybullets.remove(this);
			 this.flag = false;
//			 Thread.currentThread().interrupt();
		 }
			 return true;
	}


}
