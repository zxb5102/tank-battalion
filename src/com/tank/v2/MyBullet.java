package com.tank.v2;

import java.util.ArrayList;

public class MyBullet extends Bullet{

	ArrayList<Bullet> mybullets;
	public MyBullet(int x, int y, int dir) {
		super(x, y, dir);
		mybullets = Collection.getCollection().getMy_bullet();
	}
	
	@Override
	protected boolean validatex(int x) {
		 if(!super.validatex(x)){
			 mybullets.remove(this);
			 this.flag  = false;
		 }
			 return true;
	}
	
	@Override
	protected boolean validatey(int y) {

		if(!super.validatey(y)){
			 mybullets.remove(this);
				 this.flag = false;
		 }
			 return true;
	}

}
