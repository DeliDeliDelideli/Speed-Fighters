package cpt;

import java.awt.Graphics;
import java.util.LinkedList;

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
	
}
