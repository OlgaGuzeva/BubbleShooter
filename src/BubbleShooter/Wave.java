package BubbleShooter;

import java.awt.*;

public class Wave {

	//Fields
	//private int waveMultiplier;
	private int waveNumber;
	
	private long waveTimer;
	private long waveDelay;
	private long waveTimerDiff;
	
	private String waveText;
	
	//Constructor
	public Wave(){
		
		waveNumber = 1;
		//waveMultiplier = 5;
		
		waveTimer = 0;
		waveDelay = 5000;
		waveTimerDiff = 0;
				
		waveText = "W A V E ";
	}
	
	//Functions
	public void createEnemies(){
		int enemyCount = 0;
		if(waveNumber == 1){enemyCount = 5;}
		else if (waveNumber > 1){enemyCount = 10;}
		while(enemyCount > 0){
			GamePanel.enemies.add(new Enemy(1,1));
			enemyCount--;
		}
		if(waveNumber == 3){enemyCount = 5;}
		else if (waveNumber > 3){enemyCount = 10;}
		while(enemyCount > 0){
			GamePanel.enemies.add(new Enemy(1,2));
			enemyCount--;
		}
		if(waveNumber >= 5 && waveNumber < 9){enemyCount = 2 * (waveNumber - 4);}
		else if (waveNumber >= 9){enemyCount = 10;}
		while(enemyCount > 0){
			GamePanel.enemies.add(new Enemy(2,1));
			enemyCount--;
		}
		if(waveNumber >= 7 && waveNumber < 9){enemyCount = 1;}
		else if (waveNumber >= 9){enemyCount = 2;}
		while(enemyCount > 0){
			GamePanel.enemies.add(new Enemy(3,1));
			enemyCount--;
		}
		waveNumber++;
	}
	
		
	public void update(){
		
		if(GamePanel.enemies.size() == 0 && waveTimer == 0){
			waveTimer = System.nanoTime();
		}
		if(waveTimer>0){
			waveTimerDiff += (System.nanoTime() - waveTimer)/1000000;
			waveTimer = System.nanoTime();
		}
		if(waveTimerDiff>waveDelay){
			createEnemies();
			waveTimer = 0;
			waveTimerDiff = 0;
		}
	}
	public boolean showWave(){
		if(waveTimer != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public void draw(Graphics2D g){
		String s = waveText + " " + waveNumber;
		double length = g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.setFont(new Font("consolas", Font.PLAIN,20));		
		g.setColor(new Color(255,255,255,(int) (255*waveTimerDiff/waveDelay)));
		g.drawString(s, GamePanel.WIDTH/2 - (int)(length/2), GamePanel.HEIGHT/2);
		
	}
	
}
