package cpt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBinds extends KeyAdapter{
	
	private Handler handler;
	
	public KeyBinds(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode ();
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObj tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(-13);
				//if(key == KeyEvent.VK_S) tempObject.setVelY(13);
				if(key == KeyEvent.VK_D) tempObject.setVelX(13);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-13);
			}
			
			if(tempObject.getId() == ID.Player2) {
				//key events for player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-13);
				//if(key == KeyEvent.VK_DOWN) tempObject.setVelY(13);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(13);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-13);
			}
		}
	}
	
	public void keyReleased (KeyEvent e){
		int key = e.getKeyCode ();

		for(int i = 0; i < handler.object.size(); i++) {
			GameObj tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(18);
				//if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
			
			if(tempObject.getId() == ID.Player2) {
				//key events for player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(18);
				//if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
			}
		}
	}
}
