package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class Player {
	
	private List<Image>runImg;
	private List<Image>jumpImg;
	private Image crushImg;

	private static int currentSpeed;

	public int anim;

	private int pathScore;

	private int x;
	private int y;
	
	private boolean jumpVector;

	private boolean jump;
	private boolean jumpUp;
	private boolean jumpDown;
	private int jumpTime;
	private int jumpSpeed;
	
	
	private boolean dJump;
	private boolean dJumpUp;
	private boolean dJumpDown;
	private int dJumpTime;
	private int dJumpSpeed;
	  
	public Player(){
		init();
			for(int i = 0; i < 3; i++) 
				runImg.add(new ImageIcon(getClass().getResource("/playerAnimation/run/frame-1.png")).getImage());
		    for(int i = 0; i < 3; i++) 
		    	runImg.add(new ImageIcon(getClass().getResource("/playerAnimation/run/frame-2.png")).getImage());
		    for(int i = 0; i < 3; i++) 
		    	runImg.add(new ImageIcon(getClass().getResource("/playerAnimation/run/frame-3.png")).getImage());
		    for(int i = 0; i < 3; i++) 
		    	runImg.add(new ImageIcon(getClass().getResource("/playerAnimation/run/frame-4.png")).getImage());
		    for(int i = 0; i < 3; i++) 
		    	runImg.add(new ImageIcon(getClass().getResource("/playerAnimation/run/frame-5.png")).getImage());
		    for(int i = 0; i < 3; i++) 
		    	runImg.add(new ImageIcon(getClass().getResource("/playerAnimation/run/frame-6.png")).getImage());
		    
		    jumpImg.add(new ImageIcon(getClass().getResource("/playerAnimation/jump/jump_up.png")).getImage());
		    jumpImg.add(new ImageIcon(getClass().getResource("/playerAnimation/jump/jump_fall.png")).getImage());
		    
		    crushImg = new ImageIcon(getClass().getResource("/playerAnimation/hit/frame.png")).getImage();
	}
	
	public void init() {
		pathScore = 0;
		runImg = new ArrayList<Image>();
		jumpImg = new ArrayList<Image>();
		jump = false;
		jumpUp = false;
		jumpDown = false;
		jumpTime = 10;
	}

	public void updateMove() {
		pathScore += (currentSpeed / 2) ;
	}
    public void updateRun() {
		anim++;
		if(anim > runImg.size()-1)
			anim = 0;
	}
	public void updateJump() {
		jumpTime++;
		if(jump == true) {
			if(y > 100 && jumpUp == false) {
				y -= (jumpTime - currentSpeed + jumpSpeed);
				jumpVector = false;
			}
			else if(jumpDown == false) {
				jumpUp = true;
				jumpVector = true;
				y += (jumpTime - currentSpeed - jumpSpeed);
				
				if(y > 270) {
					jump = false;
					jumpTime=10;
				}
			}
			
			if(jump == false) {
				jumpUp = false;
				jumpDown = false;
				dJump = false;
			}
		}
	}
	
	public void updateDoubleJump() {
	
	}
	
    public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
    public int getAnimationIndex() {
		return anim;
	}
	public Image getRunImg(int index) {
		return runImg.get(index);
	}
	public Image getJumpImg(int index) {
		return jumpImg.get(index);	
	}
	public Image getCrashImg() {
		return crushImg;
	}
	
	public boolean getJumpVector() {
		return jumpVector;
	}
	
	public int getPath() {
		return pathScore;
	}
	public void setSpeed(int speed) {
		currentSpeed = speed;
	}
	public static int getSpeed() {
		return currentSpeed;
	}
	public void setJumpSpeed(int speed) {
		jumpSpeed = speed;
	}
	public int getJumpSpeed() {
		return jumpSpeed;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
    public void setJump(boolean flag) {
		jump = flag;
	}
	public boolean getJump() {
		return jump;
	}
	
    public void setDoubleJump(boolean flag) {
		dJump = flag;
	}
	public boolean getDoubleJump() {
		return dJump;
	}
	
	public Rectangle getRect() {
		return new Rectangle(this.x+10, this.y+10, 46, 90);
	}
}

