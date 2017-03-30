package BubbleShooter;

import java.awt.*;
import java.util.ArrayList;

public class Player {
	
	//Fields
	private int timeout = 0;
	
	public static double health;
	
	private double x;
	private double y;
	private int r;
	
	private double dx;//move coeff
	private double dy;
	
	private int speed;
	
	private Color color1;
	private Color color2;
	
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	
	public static boolean isFiring;	
	public static int weaponLevel;
	public static int protection;
	
	//Constructor
	public Player(){
		health = 3;
		
		x = GamePanel.WIDTH/2;
		y = GamePanel.HEIGHT/2;
		
		r = 5;
		
		speed = 5;
		
		dx = 0;
		dy = 0;
		
		color1 = Color.WHITE;
		
		up = false;
		down = false;
		right = false;
		left = false;
		
		isFiring = false;
		weaponLevel = 1;
		
		protection = 0;
	}
	
	//Functions
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getR(){
		return r + protection;
	}
	
	public void setX(double pX){
		x = pX;
	}
	
	public void setY(double pY){
		y = pY;
	}
	
	public void hit(){
		timeout = 10;
		
		if(protection > 0) {
			if(protection>0) protection--;
		}
		else {
			if(health > 0) health--;
		    if(weaponLevel>1) weaponLevel--;
		    
		}
		
	}
	
	public void upgrade(int type){
		if(type == 1){ 
			if(weaponLevel< 5) weaponLevel++;
		}	
		else if(type == 2){
			if(protection < 10) protection++;
		}
			
	}
	
	public void update(){
		
		if(timeout > 0){
			timeout--;
			return;
		}
		
		if(up && y > r){
			dy -= speed;
		}
		
		if(down && y < (GamePanel.HEIGHT-r)){
			dy += speed;
		}
		
		if(left && x > r){
			dx -= speed;
		}
		
		if(right && x < (GamePanel.WIDTH-r)){
			dx += speed;
		}
		
		if(up && left || up && right || down && left || down && right){
			double angle = Math.toRadians(45);
			dy = dy * Math.sin(angle);
			dx = dx * Math.cos(angle);
		}
		
		y += dy;
		x += dx;
		
		dy = 0;
		dx = 0;
		
		if(isFiring){
			if(weaponLevel == 1) {
				GamePanel.bullets.add(new Bullet(0));
			}else if(weaponLevel == 2){
				GamePanel.bullets.add(new Bullet(2));
				GamePanel.bullets.add(new Bullet(-2));
			}else if(weaponLevel == 3){
				GamePanel.bullets.add(new Bullet(3));
				GamePanel.bullets.add(new Bullet(0));
				GamePanel.bullets.add(new Bullet(-3));
			}else if(weaponLevel == 4){
				GamePanel.bullets.add(new Bullet(3));
				GamePanel.bullets.add(new Bullet(1));
				GamePanel.bullets.add(new Bullet(-1));
				GamePanel.bullets.add(new Bullet(-3));
			}else {
				GamePanel.bullets.add(new Bullet(6));
				GamePanel.bullets.add(new Bullet(3));
				GamePanel.bullets.add(new Bullet(0));
				GamePanel.bullets.add(new Bullet(-3));
				GamePanel.bullets.add(new Bullet(-6));
			}
		}	
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(color1);
		g.fillOval((int) (x-r), (int) (y-r), 2*r, 2*r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval((int) (x-r), (int) (y-r), 2*r, 2*r);
		g.setStroke(new BasicStroke(1));
		
		if(protection > 0){
			g.setColor(Color.RED);
			g.drawOval((int) (x-r-protection), (int) (y-r-protection), 2*(r+protection), 2*(r+protection));
			g.setStroke(new BasicStroke(1));
		}
	}

}
