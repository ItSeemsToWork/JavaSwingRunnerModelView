package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import gamestate.GameState;
import gamestate.GameStateManager;
import main.GamePanel;
import model.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoise = 0;
	private String[] options = {"Level 1", "Level 2", "Score", "Quit"};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	
	public MenuState(GameStateManager gsm, int flag) {
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init() {
		try {
			bg = new Background("/background/menuBackground.jpg");
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.BOLD, 80);
			font = new Font("Arial", Font.BOLD, 45);
		}catch(NullPointerException e) {
				JOptionPane.showMessageDialog(null, "can't load background in Menu");
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics2D g) {
		// bg draw
		g.drawImage(bg.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Run Or Die", 265, 100);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoise) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(titleColor);
			}
			g.drawString(options[i], 430, 265 + i * 40);
		}
	}
	
	@Override
	public void select() {
		if(currentChoise == 0) {
			gsm.setState(GameStateManager.PLAYSTATE);
		}
        if(currentChoise == 1) {
        	gsm.setState(GameStateManager.PLAY2STATE);
		}
        if(currentChoise == 2) {
        	gsm.setState(GameStateManager.SCORESTATE);
        }
        if(currentChoise == 3) {
        	System.exit(0);
        }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_ENTER) {
			select();
		}
		if(key==KeyEvent.VK_UP) {
			currentChoise--;
			if(currentChoise == -1)
				currentChoise = options.length -1;
		}
		if(key==KeyEvent.VK_DOWN) {
			currentChoise++;
			if(currentChoise == options.length)
				currentChoise = 0;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
