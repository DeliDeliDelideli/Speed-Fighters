package cpt;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler {
	LinkedList<GameObj> object = new LinkedList<GameObj>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObj tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObj tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObj object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObj object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		for (int xx = 0; xx < MainGame.WIDTH + 64; xx += 32) {
			addObject(new Block(xx, MainGame.HEIGHT - 64, ID.Block));
		}
		////for (int yy = 64; yy < MainGame.HEIGHT - 10; yy -= 10) {
			//addObject(new Block(yy, MainGame.WIDTH + 64, ID.Block));
		//}
	}
}
