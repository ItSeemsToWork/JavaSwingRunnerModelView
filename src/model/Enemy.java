package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	private int x;
	private int y;
	private Image img;
	
	
	public Enemy(int x) {
		img = new ImageIcon(getClass().getResource("/enemy/enemy.png")).getImage();
		this.x=x;
		this.y=320;
	}
	
	public void updateMove() {
		this.x -= Player.getSpeed() + 2;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Image getEnemyImg() {
		return img;
	}
	
	public Rectangle getRect() {
		return new Rectangle(this.x+10, this.y+20, 28, 50);
	}
}
