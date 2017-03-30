package BubbleShooter;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {

	//Fields
	private double x;
	private double y;
	private int r;
	
	private double speed;
	private double dx;
	private double dy;
	
	private double health;
	
	private int type;
	private int rank;
	
	private Color color;
	
	private float[] hsb;
	
	public HitWave thisHitWave;
	
	//Constructor	
	public Enemy(int type, int rank){
		this.type = type;
		this.rank = rank;
		
		if(type==1){
			
			if(rank==1){enemyOneOne();} 
			else if (rank==2){enemyOneTwo();}
			
		} else if(type==2){
			if(rank==1){enemyTwoOne();}
		} else if(type==3){
			if(rank==1){enemyThreeOne();}
		}
		double angle = Math.toRadians(Math.random()*360);
		dx = Math.sin(angle)*speed;
		dy = Math.cos(angle)*speed;
	}
	
	//Functions
	private void enemyOneOne() {
		x = Math.random() * GamePanel.WIDTH;
		y = 0;
	
		hsb = Color.RGBtoHSB(70, 240, 50, null);
		color =  Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		r = 7;				
		speed = 2;				
		health = 5;
	}
	
	private void enemyOneTwo() {
		x = Math.random() * GamePanel.WIDTH;
		y = 0;
			
		hsb = Color.RGBtoHSB(100, 255, 0, null);
		color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		r = 8;				
		speed = 4;				
		health = 8;
	}
	
	private void enemyTwoOne() {
		x = Math.random() * GamePanel.WIDTH;
		y = 0;
			
		hsb = Color.RGBtoHSB(255, 0, 0, null);
		color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		r = 6;				
		speed = 6;				
		health = 9;
	}
	
	private void enemyThreeOne() {
		x = Math.random() * GamePanel.WIDTH;
		y = 0;
			
		hsb = Color.RGBtoHSB(255, 255, 255, null);
		color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		r = 50;				
		speed = 2;				
		health = 50;
	}
		
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getR(){
		return r;
	}
	
	public int getType(){
		return type;
	}
	
	public boolean remove(){
		if(health<=0){
			GamePanel.hitWaves.add(new HitWave(this));			
			return true;
		}
		return false;
	}
	
	public void hit(){
		health--;
		dx = -dx;
		dy = -dy;
	}
	
	public void update(){
		x += dx;
		y += dy;
		
		if(x<0 && dx < 0) dx = -dx;
		if(x>GamePanel.WIDTH && dx>0) dx = -dx;
		if(y<0 && dy<0) dy = -dy;
		if(y>GamePanel.HEIGHT && dy>0) dy = -dy;
			
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(color);
		g.fillOval((int) (x-r), (int) (y-r), 2*r, 2*r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color.darker());
		g.drawOval((int) (x-r), (int) (y-r), 2*r, 2*r);
		g.setStroke(new BasicStroke(1));
	}
		
}
