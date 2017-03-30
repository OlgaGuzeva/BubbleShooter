package BubbleShooter;

import java.awt.*;

public class UpgradeItem {
	//Fields
	private double x;
	private double y;
	private int r;
			
	//type: 1 - upgrade weapon
	//type: 2 - helmet
	private int type;
	private double random;
		
	//Constructor	
	public UpgradeItem(Enemy e){
		if(e.getType() == 1){
			this.type = 2;
		}else{
			random = Math.random();
			if(random >= 0.5)
				this.type = 1;
			else this.type = 2;
		}
		this.x = e.getX();
		this.y = e.getY();
		r = 10;
	}
		
	//Functions
	public double getX(){
		return x;
	}
		
	public double getY(){
		return y;
	}
	
	public double getR(){
		return r;
	}
	
	public double getType(){
		return type;
	}
	
	public void update(){
		y += 1;
			
	}
	
	public boolean remove(){
		
		if(y>GamePanel.HEIGHT+10) 
		{
			return true;
		}
		return false;
		
	}
	
	public void draw(Graphics2D g){
		if(type==1){
			g.setColor(Color.WHITE);
			g.fillOval((int) x+8, (int) y+4, 4, 12);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.YELLOW);
			g.drawOval((int) (x), (int) (y), 20, 20);
			g.setStroke(new BasicStroke(1));
		}else{
			g.setColor(Color.WHITE);
			g.fillOval((int) x+4, (int) y+4, 13, 13);
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.RED);
			g.drawOval((int) (x), (int) (y), 21, 21);
			g.setStroke(new BasicStroke(1));
		}
	}

}
