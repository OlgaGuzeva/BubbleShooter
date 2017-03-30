package BubbleShooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameBack {
	
	//Fields
	private Color color;
	
	//Constructor
	public GameBack(){
		
		float[] hsb = Color.RGBtoHSB(100, 200, 255, null);

		color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		//color = Color.BLUE;
	}
	
	//Functions
	public void update(){
		
	}
	
	public void draw(Graphics2D g){		
		
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.setColor(Color.WHITE);
		Font font = new Font("TimesRoman", Font.PLAIN, 15);
		g.setFont(font);
		g.drawString("Health: " + (int) Player.health + "    Protection: " + (int) Player.protection, 20, 20);
	}

}
