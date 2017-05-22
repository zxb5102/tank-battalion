package com.tank.v2;

import java.util.ArrayList;

public class Tank {
	boolean flag=true;
	int x;
	int y;
	int dir;
	Collection c;
	ArrayList<int[]> river;
	ArrayList<int[]> brick;
	ArrayList<int[]> grass;
	ArrayList<int[]> iron;
	
	public Tank(int x,int y,int dir){
		c = Collection.getCollection();
		river = c.getRiver();
		brick = c.getBrick();
		iron = c.getIron();
		grass = c.getGrass();
		
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(validatex(x)){
//			System.out.println(x);
			this.x = x;
//			System.out.println(this.getX());
		}
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(validatey(y)){
//			System.out.println(y);
			this.y = y;
//			System.out.println(this.y);
		}
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	private boolean validatex(int x){
//		int [] point;
//		int point_x;
//		int point_y;
//		
		if(x>0&&x<500){
			if(validate(x,y)){
				
				return true;
			}
			return false;
		}
		return false;
	}
	private boolean validatey(int y){
//		int[] point;
////		int point_x;
//		int point_y;
		
		if(y>0&&y<600){
			if(validate(x,y)){
				return true;
			}
			return false;
		}
		return false;
	}
	
	
	private boolean validate(int x,int y){
		int[] point;
		int point_x;
		int point_y;
		for(int i=0;i<brick.size();i++){
			point = brick.get(i);
			point_y = point[1];
			point_x = point[0];
			if(!judge(point_x,point_y)){
				return false;
			}
		}
		for(int i=0;i<iron.size();i++){
			point = iron.get(i);
			point_y = point[1];
			point_x = point[0];
			if(!judge(point_x,point_y)){
				return false;
			}
		}
		for(int i=0;i<river.size();i++){
			point = river.get(i);
			point_y = point[1];
			point_x = point[0];
			if(!judge(point_x,point_y)){
				return false;
			}
		}
//		for(int i=0;i<grass.size();i++){
//			point = grass.get(i);
//			point_y = point[1];
//			point_x = point[0];
//			if(!judge(point_x,point_y)){
//				return false;
//			}
//		}
		
		return true;
	}
	
	private boolean judge(int point_x,int point_y){
		if(this.dir==1){
			if((x+30>point_x*50&&x+30<point_x*50+50&&y+25>point_y*50&&y+25<point_y*50+50)||
					(x+30>point_x*50&&x+30<point_x*50+50&&y>point_y*50&&y<point_y*50+50)){
				return false;
			}
		}else if(this.dir==0){
			if((x>point_x*50&&x<point_x*50+50&&y>point_y*50&&y<point_y*50+50)||
					(x+25>point_x*50&&x+25<point_x*50+50&&y>point_y*50&y<point_y*50+50)){
				return false;
			}
			
		}else if(this.dir == 2){
			if((x>point_x*50&&x<point_x*50+50&&y+30>point_y*50&&y+30<point_y*50+50)||
					(x+25>point_x*50&&x+25<point_x*50+50&&y+30>point_y*50&&y+30<point_y*50+50)){
				return false;
			}
			
		}else if(this.dir == 3){
			if((x>point_x*50&&x<point_x*50+50&&y>point_y*50&&y<point_y*50+50)||
					(x>point_x*50&&x<point_x*50+50&&y+25>point_y*50&&y+25<point_y*50+50)){
				return false;
			}
		} 
		return true;
	}
}
