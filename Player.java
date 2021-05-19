package cpt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends GameObj {
	
	private float width = 64, height = 128;
	
	private float gravity = 0.1f;
	private final float MAX_SPEED = 10;
	
	private Handler handler;
	
	public Player(int x, int y, Handler handler, ID id) {
		super (x, y, id);
		this.handler = handler;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (falling || jumping) {
			velY += gravity;
			
			if (velY > MAX_SPEED)
				 velY = MAX_SPEED;
		}
		Collision();
	}
	
	private void Collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObj tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = (int) (tempObject.getY() - height);
					velY = 0;
					falling = false;
					jumping = false;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.black);
		else if(id == ID.Player2) g.setColor(Color.orange);
		g.fillRect(x, y, 64, 128);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y + (height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
	}
}