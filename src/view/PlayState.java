package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import gamestate.GameState;
import gamestate.GameStateManager;
import main.GamePanel;
import model.Background;
import model.Enemy;
import model.Player;
import utils.MyDialog;



public class PlayState extends GameState implements ActionListener, Runnable{
	
	private Timer mainTimer;
	
	private Player player;
	
	private MyDialog inputDialog;
	
	private CopyOnWriteArrayList<Enemy>enemies;
	private Thread enemyGenerator;
	
	private int currentChoise;
	
	private boolean isGame;
	private boolean collision;
	
	private Background bg_layer1;
	private Background bg_layer2;
	private Background bg_layer3;
	private Background bg_layer4;
	
	private int loopLayer1;
	private int loopLayer2;
	private int houseLayer1;
	private int houseLayer2;
	private int backLayer1;
	private int backLayer2;
	
	private int layer1Speed;
	private int layer2Speed;
	private int layer3Speed;
	
	private int level;
	
	
	private long doubleJump;
	
	public PlayState(GameStateManager gsm, int flag) {
		this.gsm = gsm;
		level = flag;
		init();
	}
	@Override
    public void init() {
		    isGame = true;
		    mainTimer = new Timer(15, this);
			collision = false;
			currentChoise = -1;
			
			enemies = new CopyOnWriteArrayList<>();
			enemyGenerator = new Thread(this);
			
			switch (level) {
	           case (1):
	               initLevel1();
	               break;
	           case (2):
	               initLevel2();
	               break;
	           default:
	               break;
	       }
			
			inputDialog = new MyDialog(null);
			
			mainTimer.start();
			enemyGenerator.start();
	}

	public void update() {
		///////////////////////////update background//////////////////////////
		if(loopLayer2 - layer1Speed <= 0) {
            loopLayer1 = 0;
            loopLayer2 = GamePanel.WIDTH;
            }else {
                loopLayer1 -= layer1Speed;//координаты слоя уменьшаем
                loopLayer2 -= layer1Speed;
            }
        
        if(houseLayer2 - layer2Speed <= 0) {
            houseLayer1 = 0;
            houseLayer2 = GamePanel.WIDTH;
            }else {
                houseLayer1 -=layer2Speed;
                houseLayer2 -=layer2Speed;
            }
        
        if(backLayer2 - layer3Speed <= 0) {
            backLayer1 = 0;
            backLayer2 = GamePanel.WIDTH;
            }else {
                backLayer1 -=layer3Speed;
                backLayer2 -=layer3Speed;
            }
		
		/////////////////////////update player//////////////////////////
        player.updateMove();
        if(player.getJump() == true) {
        	player.updateJump();
        	}
        else {
        	player.updateRun();
        }
        
        //////////////////////////update enemy//////////////////////////
        Iterator<Enemy> iter = enemies.iterator();
		while(iter.hasNext()) {    
			Enemy e = iter.next();
			if(e.getX() >= 2000 || e.getX() <= -2000)
				enemies.remove(new Enemy(e.getX()));
			else {
				e.updateMove();
			}
		}
		
		///////////////////////////GAME OVER//////////////////////////////
		if(collision == true) {
			isGame = false;
			enemies.clear();
			
			String name = inputDialog.showMyDialog();
			if(!name.isEmpty())
				ScoreState.addScore(player.getPath(), name);
			ScoreState.saveToTextFile();
			currentChoise = JOptionPane.showConfirmDialog(null, "TRY AGAIN?", "GAME OVER!", JOptionPane.YES_NO_OPTION);	
			select();
			}
	}

	public void draw(Graphics2D g) {
		//draw bg
		g.drawImage(bg_layer4.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(bg_layer3.getImage(), backLayer1, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(bg_layer3.getImage(), backLayer2, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(bg_layer2.getImage(), houseLayer1, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(bg_layer2.getImage(), houseLayer2, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(bg_layer1.getImage(), loopLayer1, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.drawImage(bg_layer1.getImage(), loopLayer2, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		
		//draw scores
		if(level == 1) g.setColor(Color.BLACK);
		if(level == 2) g.setColor(Color.WHITE);
		g.setFont(new Font("Courier Bold", Font.BOLD, 25));
		g.drawString("TOTAL: "+player.getPath()+" m.", 680, 70);
		
		//draw doubleJump
		if(player.getDoubleJump())
			g.drawString("DOUBLE JUMP", 400, 70);
		
		
		//draw player
		if(collision) {
			//draw player crash//
			g.drawImage(player.getCrashImg(), player.getX(), player.getY(), null);
		}else {
			//draw player jump//
			if(player.getJump() == true) {
				if(player.getJumpVector() == true) {
					g.drawImage(player.getJumpImg(1), player.getX(), player.getY(), null);
				}else {
					g.drawImage(player.getJumpImg(0), player.getX(), player.getY(), null);
				}
			//draw player run//
			}else{
				g.drawImage(player.getRunImg(player.getAnimationIndex()), player.getX(), player.getY(), null);
			}
		}
		
		
		//draw enemy
		Iterator<Enemy> iter = enemies.iterator();
		while(iter.hasNext()) {    
			Enemy e = iter.next();
			g.drawImage(e.getEnemyImg(), e.getX(), e.getY(), null);
		}
	}
	
	@Override
	public void select() {
		if(currentChoise == 0) {
			if(level == 1)
			gsm.setState(GameStateManager.PLAYSTATE);
			if(level == 2)
			gsm.setState(GameStateManager.PLAY2STATE);
		}
        if(currentChoise == 1) {
        	gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		    if (doubleJump + 450 > System.currentTimeMillis()) {
		    	player.setDoubleJump(true);
		    }
		    player.setJump(true);
	        doubleJump = System.currentTimeMillis();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void run() {
		Random rand = new Random();
		int min = (level == 1) ? 1200 : 1500;
		int max = (level == 1) ? 3000 : 4000;
		
		while(isGame) {
			try {
				Thread.sleep(min+rand.nextInt(max-min));
				try {
					enemies.add(new Enemy(1000));
				}catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null, "can't load enemy skin");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		collisionCheck();
	}
	
	private void collisionCheck() {
		Iterator<Enemy> iter = enemies.iterator();
		while(iter.hasNext()) {
			Enemy e = iter.next();
			if(player.getRect().intersects(e.getRect())) {
				collision = true;
			}
		}
	}
	
	private void initLevel1() {
		try {
			player = new Player();
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "can't load player skin");
		}
		player.setPosition(100, 280);
		player.setSpeed(5);
		player.setJumpSpeed(10);
		
		try {
			bg_layer1 = new Background("/background/layer1_1920x1080.png");
		    bg_layer2 = new Background("/background/layer2_1920x1080.png");
		    bg_layer3 = new Background("/background/layer3_1920x1080.png");
		    bg_layer4 = new Background("/background/layer4_1920x1080.png");
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "can't load background skin");
		}
		
		layer1Speed = Player.getSpeed();
        layer2Speed = Player.getSpeed() / 2;
        layer3Speed = 1;
	}
	
	private void initLevel2() {
		try {
			player = new Player();
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "can't load player skin");
		}
		player.setPosition(100, 280);
		player.setSpeed(10);
		player.setJumpSpeed(15);
		
		try {
			bg_layer1 = new Background("/background2/layer1_1920x1080.png");
		    bg_layer2 = new Background("/background2/layer2_1920x1080.png");
		    bg_layer3 = new Background("/background2/layer3_1920x1080.png");
		    bg_layer4 = new Background("/background2/layer4_1920x1080.png");
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "can't load background skin");
		}
		
		layer1Speed = Player.getSpeed();
        layer2Speed = Player.getSpeed() / 2;
        layer3Speed = 1;
	}
		
}
