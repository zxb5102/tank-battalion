package com.tank.v2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.ArrayList;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Main {
	public static void main(String[] args) {
		
		Frame fr = new Frame("̹坦克大战");
		fr.setBackground(Color.black);
		fr.setBounds(400, 200, 530, 650);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initank();
		initbarriers();
		MyPanel mypanel = new MyPanel();
		fr.add(mypanel);
		new Thread(mypanel).start();
		
		new Thread(){
			public void run() {
				try { 
					InputStream xm = ClassLoader.getSystemResourceAsStream("xm.au");
					AudioStream as;
						 as=new AudioStream(xm);  
						AudioPlayer.player.start(as);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			};
		}.start();
		
	}
	private static void initank(){
		Collection c = Collection.getCollection();
		ArrayList<Tank> enemytanks = c.getEnemy_tanks();
		EnemyTank enemytank;
		c.setMytank(new MyTank(400, 400, 0));
		
		for(int i=1;i<=5;i++){
			enemytank = new EnemyTank(i*10, 0, 2);
			enemytanks.add(enemytank);
			new Thread(enemytank).start();
		}
	}
	
	private static void initbarriers(){
		 Collection c = Collection.getCollection();
		 ArrayList<int[]> river = c.getRiver();
		 ArrayList<int[]> iron = c.getIron();
		 ArrayList<int[]> grass = c.getGrass();
		 ArrayList<int[]> brick = c.getBrick();
		
		int[][] a = new int[][]{
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,3,0,0,0,0,0},
			{0,3,3,3,3,3,3,3,0,0},
			{0,0,0,0,1,0,0,2,0,0},
			{1,4,0,0,2,0,0,2,0,0},
			{1,0,0,0,3,0,0,0,0,0},
			{0,0,0,0,4,0,0,4,0,0},
			{2,2,2,0,4,0,0,4,0,0},
			{0,0,0,0,3,0,0,0,0,0},
			{0,4,0,0,3,0,3,3,3,3},
			{0,0,0,0,3,0,3,0,0,0},
			{0,0,0,0,0,0,3,0,0,0}
		};
		int point;
		int[] able;
		for(int i=0;i<12;i++){
			for(int j=0;j<10;j++){
				point = a[i][j];
				if(point!=0){
					able = new int[]{j,i};
					if(point==3){
						brick.add(able);
					}else if(point==2){
						river.add(able);
					}else if(point==1){
						grass.add(able);
					}else{
							iron.add(able);
					}
				}
			}
		}
	}
}
