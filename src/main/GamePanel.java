package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gamestate.GameStateManager;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	//размеры
	public static final int WIDTH = 960;
	public static final int HEIGHT = 480;
	
	//Game Thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/FPS;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//GameStateManager
	private GameStateManager gsm;
	
	public GamePanel() {
		super();
    	setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	setFocusable(true);
    	requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null)
			thread = new Thread(this);
		addKeyListener(this);
		thread.start();
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e);
	}
	
	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}

	@Override
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		
		//game loop
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if(wait < 0 ) wait = 5;
 			try {
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void  update() {
		gsm.update();
	}
	private void  draw() {
		gsm.draw(g);
	}
	
	private void  drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
}

