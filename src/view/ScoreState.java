package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import gamestate.GameState;
import gamestate.GameStateManager;
import main.GamePanel;
import model.Background;
import utils.IO;

public class ScoreState extends GameState{
	
    private Background bg;
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private Font escFont;
	
	private static Map<Integer, String> scoreTable;
	private NavigableSet<Integer> nset;
	
	private static IO ioUtil;
	
	public ScoreState(GameStateManager gsm, int flag) {
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) {
		// bg draw
		g.drawImage(bg.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("BEST SCORE", 220, 100);
		
		//draw scores
		g.setFont(font);
		int i = 0;
		Iterator<Integer> itr = nset.iterator();
		while(itr.hasNext()) {
			i++;
			Integer currValue = itr.next();
			g.drawString(scoreTable.get(currValue), 220, 200 + i * 40);
			g.drawString(currValue.toString(), 650, 200 + i * 40);
			if(i == 5)
				break;
		}
		//draw exit option
		g.setColor(Color.WHITE);
		g.setFont(escFont);
		g.drawString("press 'ESC' to EXIT", 385, 475);
	}
	
	@Override
	public void select() {
		gsm.setState(GameStateManager.MENUSTATE);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_ESCAPE) {
			select();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void init() {
		try {
			bg = new Background("/background/menuBackground.jpg");
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Monospaced Bold", Font.BOLD, 80);
			font = new Font("Monospaced Bold", Font.BOLD, 45);
			escFont = new Font("Monospaced Bold", Font.BOLD, 20);
			ioUtil = new IO();
			
			scoreTable = new TreeMap<Integer, String>();
			loadFromTextFile();
			nset = ((TreeMap<Integer, String>) scoreTable).descendingKeySet();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "can't load background in Score");
		}
	}
	
	public void loadFromTextFile() {
		try {
			ioUtil.loadFromTextFile(scoreTable);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public static void saveToTextFile() {
		try {
			ioUtil.saveToTextFile(scoreTable);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		} catch (IOException e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void addScore(Integer val, String name) {
		scoreTable.put(val,name);
	}
	
}
