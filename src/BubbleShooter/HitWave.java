package BubbleShooter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class HitWave {
	private double x;
	private double y;
	private int r;
	
	private int speed;
	
	private Color color;
	
	
	//Constructor
	public HitWave(Enemy e){
		x = e.getX();
		y = e.getY();
		r = e.getR();
		
		speed = 2;
		
		color = Color.WHITE;
	}
	
	//Function
	public boolean remove(){
		
		if(r>120){
			return true;
		}
		return false;		
	}
	
	public void update(){
		r += speed;
		
	}
	
	public void draw(Graphics2D g){
		g.setStroke(new BasicStroke(2));
		g.setColor(color);
		
		g.drawOval((int) x, (int) y, r, r);
	}
	
}
