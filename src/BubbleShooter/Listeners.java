package BubbleShooter;

import java.awt.event.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener {

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			Player.up = true;
		}
		
		if(key == KeyEvent.VK_S) {
			Player.down = true;
		}
		
		if(key == KeyEvent.VK_A) {
			Player.left = true;
		}
		
		if(key == KeyEvent.VK_D) {
			Player.right = true;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			Player.isFiring = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			Player.up = false;
		}
		
		if(key == KeyEvent.VK_S) {
			Player.down = false;
		}
		
		if(key == KeyEvent.VK_A) {
			Player.left = false;
		}
		
		if(key == KeyEvent.VK_D) {
			Player.right = false;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			Player.isFiring = false;
		}
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Player.isFiring = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Player.isFiring = false;
		}
		
	}
	
	

}
