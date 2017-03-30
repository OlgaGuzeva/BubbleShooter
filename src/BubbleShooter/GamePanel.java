package BubbleShooter;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	//Field
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	
	private Thread thread;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS;
	private double millisToFPS;
	private long timerFPS;
	private int sleepTime;
	
	public static GameBack background;
	public static Player player;
	
	public static ArrayList<Bullet> bullets;
	
	public static ArrayList<Enemy> enemies;
	
	public static ArrayList<HitWave> hitWaves;
	
	public static ArrayList<UpgradeItem> upgradeItem;
	
	public static Wave wave;
	
	
	//Constructor
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();	
		
		addKeyListener(new Listeners());
		addMouseMotionListener(new Listeners());
		addMouseListener(new Listeners());
	}

	//Functions
	public void start(){
		
		thread = new Thread(this);
		thread.start();
		
	}
	
	
	public void run() {
		
		FPS = 30;
		millisToFPS = 1000/FPS;		
		sleepTime = 0;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		background = new GameBack();
		player = new Player();		
		
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		hitWaves = new ArrayList<HitWave>(); 
		upgradeItem = new ArrayList<UpgradeItem>();
		wave = new Wave();
				
		while(true) { //TODO States
			
			timerFPS = System.nanoTime();
			
			gameUpdate();
			gameRender();
			gameDraw();
			
			timerFPS = (System.nanoTime() - timerFPS)/1000000;
			if(millisToFPS > timerFPS){
				sleepTime = (int) (millisToFPS - timerFPS);
			} else {
				sleepTime = 1;
			}
			try {
				thread.sleep(sleepTime);//TODO FPS
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			timerFPS = 0;
			sleepTime = 1;
		}
		
	}
	
	public void gameUpdate(){
		//Background update
		background.update();
		
		//player update
		player.update();
		
		//Bullets update
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).update();
			boolean remove = bullets.get(i).remove();
			if(remove){
				bullets.remove(i);
				i-=1;
			}
		}
		
		//Enemies update
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update();
		}
		
		for(int i = 0; i < upgradeItem.size(); i++){
			upgradeItem.get(i).update();
			if(upgradeItem.get(i).remove()) upgradeItem.remove(i);
			else{
				//upgradeItem-player collide
				double difX = upgradeItem.get(i).getX() - player.getX();
				double difY = upgradeItem.get(i).getY() - player.getY();
				
				double dist = Math.sqrt(difX*difX + difY*difY);
				if((int) dist < upgradeItem.get(i).getR() + player.getR()){
					player.upgrade((int) upgradeItem.get(i).getType());
					upgradeItem.remove(i);
					i--;
					break;
				}
			}
		}
		
		
		
		
		//Bullets or player-enemies collide
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();
			int er = e.getR();
			
			for(int j = 0; j < bullets.size(); j++){
				Bullet b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();				
				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx*dx + dy*dy);
				if((int) dist < er+b.getR()){
					e.hit();
					bullets.remove(j);
					j--;
					if (e.remove()) {
						if(Math.random()<0.1) upgradeItem.add(new UpgradeItem(enemies.get(i)));
						enemies.remove(i);
						i--;
						break;
					}
				}				
			}
			
			double px = player.getX();
			double py = player.getY();
			
			double pdx = ex - px;
			double pdy = ey - py;
			
			double dist = Math.sqrt(pdx*pdx + pdy*pdy);
			if((int) dist < er+player.getR()){
				e.hit();
				player.setX(player.getX()-pdx);
				player.setY(player.getY()-pdy);
				player.hit();
				if (e.remove()) {
					enemies.remove(i);
					i--;
					break;
				}
			}
			
		}
		
		//hitWaves update
		for(int i = 0; i < hitWaves.size(); i++){
			hitWaves.get(i).update();
			if(hitWaves.get(i).remove()){
				hitWaves.remove(i);
				i-=1;
			}
		}
		
		//Wave update
		wave.update();
		
	}
	
	public void gameRender(){
		
		//Background draw
		background.draw(g);
		
		//player draw
		player.draw(g);
		
		//Bullets draw
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).draw(g);
		}
		
		//Enemies draw
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
		
		//Hit waves draw
		for(int i = 0; i < hitWaves.size(); i++){
			hitWaves.get(i).draw(g);
		}
			
		//Wave draw
		if(wave.showWave()){
			wave.draw(g);
		}
		
		//Upgrade items
		for(int i = 0; i < upgradeItem.size(); i++){
			upgradeItem.get(i).draw(g);
		}
		
	}
	
	private void gameDraw(){
		
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();//���������� ���������� g2
		
	}
	
	
	

}
