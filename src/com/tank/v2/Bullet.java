package com.tank.v2;

import java.util.ArrayList;

public class Bullet implements Runnable{

	int x;
	int y;
	int dir;
	int speed = 8;
	boolean flag = true;
	
	 Collection c = Collection.getCollection();
//	 ArrayList<int[]> river = c.getRiver();
	 ArrayList<int[]> iron = c.getIron();
//	 ArrayList<int[]> grass = c.getGrass();
	 ArrayList<int[]> brick = c.getBrick();
	 
	public Bullet(int x,int y,int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	@Override
	public void run() {
		while(flag){
			switch(dir){
				case 0:
					setY(y-speed);
					break;
				case 2:
					setY(y+speed);
					break;
				case 3:
					setX(x-speed);
					break;
				case 1:
					setX(x+speed);
					break;
			}
			try {
				Thread.currentThread().sleep(100);
//				if(!Thread.currentThread().isInterrupted()){
//				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(this instanceof MyBullet){
			Collection.getCollection().getMy_bullet().remove(this);
		}else{
			Collection.getCollection().getEnemy_bullet().remove(this);
		}
//		Thread.currentThread().interrupt();
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(validatex(x)){
			this.x = x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(validatey(y)){
			this.y = y;
		}
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
			this.dir = dir;
	}
	
	protected boolean validatex(int x){
		
		if(x>0&&x<500){
			if(validate()){
				return true;
			}
			return false;
		}
		return false;
	}
	
	protected boolean validatey(int y){
		
//		int[] point;
////		int point_x;
//		int point_y;
		if(y>0&&y<600){
			if(validate()){
				return true;
			}
			return false;
		}
		return false;
	}
	private boolean validate(){
		int[] point;
		int point_x;
		int point_y;
		
		for(int i=0;i<iron.size();i++){
			point = iron.get(i);
			point_x = point[0];
			point_y = point[1];
			
			if(y>point_y*50&&y<point_y*50+50&&x>point_x*50&&x<point_x*50+50){
//				iron.remove(i);
				return false;
			}
		}
		
		for(int i=0;i<brick.size();i++){
			point = brick.get(i);
			point_y = point[1];
			point_x = point[0];
			
			if(y>point_y*50&&y<point_y*50+50&&x>point_x*50&&x<point_x*50+50){
				brick.remove(i);
				return false;
			}
		}
		
		return true;
	}
}
