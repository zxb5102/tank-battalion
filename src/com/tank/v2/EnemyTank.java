package com.tank.v2;

import java.util.ArrayList;

public class EnemyTank extends Tank implements Runnable{

	int speed=5;
	ArrayList<Bullet> enemybullets=null;
	Tank mytank;
	public EnemyTank(int x,int y,int dir) {
		super(x,y,dir);
		this.enemybullets = Collection.getCollection().getEnemy_bullet();
		mytank = Collection.getCollection().getMytank();
	}
	
	@Override
	public void run() {
		int num;
		while(flag){
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num = (int)(Math.random()*3);
//			num=3;
			switch(num){
				case 0:
					randomDir();
					break;
				case 1:
					randomXY();
					break;
				case 2:
					randomShoot();
					break;
				default:
					System.out.println("EnenmyTank类里面的Run方法里面生成了一个3");
			}
		}
		Collection.getCollection().getEnemy_tanks().remove(this);
		Thread.currentThread().interrupt();
		
	}
	
	private void randomDir(){
		Double random = Math.random();
		int flag = 0;
		int dir1=0;
		int dir2=1;
		if(random>=0&&random<0.65){
			flag = 0;
		}else{
			flag = 1;
		}
		if(flag == 0&&mytank.flag == true){
				if(this.x<=mytank.x){
					dir1 = 1;
				}else{
					dir1 = 3;
				}
				if(this.y <= mytank.y){
					dir2 = 2;
				}else{
					dir2 = 0;
				}
			flag = (int)(Math.random()*2);
			if(flag==0){
				this.setDir(dir1);
			}else{
				this.setDir(dir2);
			}
		}else{
			
			flag = (int)(Math.random()*4);
			this.setDir(flag);
		}
	}
	
	private void randomXY(){
		switch(this.dir){
			case 0:
				this.setY(y-speed);
				break;
			case 1:
				this.setX(speed+x);
				break;
			case 2:
				this.setY(speed+y);
				break;
			case 3:
				this.setX(x-speed);
				break;
		}
	}
	
	private void randomShoot(){
		int num=0;
//		while(true){
//			num = (int)(Math.random()*4);
//			if(num!=0){
//				break;
//			}
//		}
//		if(enemybullets.size()<5){
			
			EnemyBullet t=null;
//			while(num>0){
			t = new EnemyBullet(x+10, y+10,dir);
			new Thread(t).start();
			enemybullets.add(t);
//			}
//			num--;
//			try {
//				Thread.currentThread().sleep(300);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
