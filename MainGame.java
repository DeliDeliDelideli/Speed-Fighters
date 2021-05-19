/*Connor Franc
 * Feb 22 2021
 * ICS4U
 * Mr. Hofstatter
 * CPT project
 * Create a game 
 */

package cpt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MainGame extends Canvas implements Runnable{
	private static final long serialVersionUID = -95712375401497748L;

	public static final int WIDTH = 1928, HEIGHT = 1040;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	
	public MainGame(){
		handler = new Handler();
		
		handler.createLevel();
		
		this.addKeyListener(new KeyBinds(handler));
		
		new Window(WIDTH, HEIGHT, "Speed Fighters", this);
		
		handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, handler, ID.Player));
		handler.addObject(new Player(WIDTH / 2 + 64, HEIGHT / 2 - 32, handler, ID.Player2));
	}
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage background = null;
	
	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			background = loader.loadImage("/JavaBackground.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////////////////////////////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		g.fillRect(0,  0, WIDTH, HEIGHT);
		g.drawImage(background, 0, 0, null);
		////////////////////////////////////////////////////////////
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		new MainGame();
	}
}
