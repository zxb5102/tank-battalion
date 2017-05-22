package com.tank.v2;

import java.util.ArrayList;

public class Collection {
	
	private static  ArrayList<Bullet> my_bullet = new ArrayList<Bullet>();
	private static ArrayList<Bullet> enemy_bullet = new ArrayList<Bullet>();
	private static ArrayList<Tank> enemy_tanks = new ArrayList<Tank>();
	private static Tank mytank;
	
	private static Collection collection;
	
	private ArrayList<int[]> river = new ArrayList<int[]>();
	private ArrayList<int[]> iron = new ArrayList<int[]>();
	private ArrayList<int[]> grass = new ArrayList<int[]>();
	private ArrayList<int[]> brick = new ArrayList<int[]>();
	
	
	public ArrayList<int[]> getRiver() {
		return river;
	}

	public void setRiver(ArrayList<int[]> river) {
		this.river = river;
	}

	public ArrayList<int[]> getIron() {
		return iron;
	}

	public void setIron(ArrayList<int[]> iron) {
		this.iron = iron;
	}

	public ArrayList<int[]> getGrass() {
		return grass;
	}

	public void setGrass(ArrayList<int[]> grass) {
		this.grass = grass;
	}

	public ArrayList<int[]> getBrick() {
		return brick;
	}

	public void setBrick(ArrayList<int[]> brick) {
		this.brick = brick;
	}

	private Collection(){
		
	}
	
	public static Collection getCollection(){
		if(collection==null){
			collection = new Collection();
		}
		return collection;
	}

	public ArrayList<Bullet> getMy_bullet() {
		return collection.my_bullet;
	}

	public void setMy_bullet(Bullet b) {
		collection.my_bullet.add(b);
	}

	public ArrayList<Bullet> getEnemy_bullet() {
		return collection.enemy_bullet;
	}

	public void setEnemy_bullet(Bullet b) {
		collection.enemy_bullet.add(b);
	}

	public ArrayList<Tank> getEnemy_tanks() {
		return collection.enemy_tanks;
	}

	public void setEnemy_tanks(Tank t) {
		Collection.enemy_tanks.add(t);
	}

	public Tank getMytank() {
		return collection.mytank;
	}
	
	public void setMytank(Tank t){
		mytank = t;
	}
	
	 
}
