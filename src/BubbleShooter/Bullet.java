package BubbleShooter;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {
	
	//Fields
	private double x;
	private double y;
	private int r;
	
	private int speedY;
	private int speedX;
	
	private Color color;
	
	//Constructor
	public Bullet(int spX){
		x = GamePanel.player.getX();
		y = GamePanel.player.getY();
		r = 3;
		
		speedY = 10;		
		speedX = spX;
		
		color = Color.WHITE;
	}
	
	//Functions
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public int getR(){
		return r;
	}
	
	public boolean remove(){
	
		if(y<0){
			return true;
		}
		return false;
		
	}
	
	public void update(){
		y -= speedY;
		x -= speedX;
		
	}
	
	public void draw(Graphics2D g){
		g.setColor(color);
		g.fillOval((int) x-1, (int) y, r, 2 * r);
	}

}
