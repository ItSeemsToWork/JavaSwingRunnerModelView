package gamestate;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import view.MenuState;
import view.PlayState;
import view.ScoreState;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int PLAY2STATE = 2;
	public static final int SCORESTATE = 3;
	
	
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this, 0));
		gameStates.add(new PlayState(this, 1));
		gameStates.add(new PlayState(this, 2));
		gameStates.add(new ScoreState(this, 0));
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw (Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(KeyEvent e) {
		gameStates.get(currentState).keyPressed(e);
		
	}
	
	public void keyReleased(KeyEvent e) {
		gameStates.get(currentState).keyReleased(e);
	}
}
