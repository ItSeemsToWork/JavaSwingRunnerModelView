package objects;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Enemy;
import model.Player;

public class TestEnemy {

	@Test()
	public void testEnemyConstructor() {
		int testX = 100;
		Enemy tEnemy = new Enemy(testX);
		assertEquals(testX, tEnemy.getX());
	}
	
	@Test()
	public void testEnemyMove() {
		int testX = 100;
		Enemy tEnemy = new Enemy(testX);
		tEnemy.updateMove();
		assertEquals(testX -= Player.getSpeed() + 2, tEnemy.getX());
	}
	
	@Test
	public void testEnemyCollisionTRUE() {
		Enemy tEnemy1 = new Enemy(300);
		Enemy tEnemy2 = new Enemy(303);
		assertEquals(true, tEnemy1.getRect().intersects(tEnemy2.getRect()));
	}
	
	@Test
	public void testEnemyCollisionFALSE() {
		Enemy tEnemy1 = new Enemy(300);
		Enemy tEnemy2 = new Enemy(400);
		assertEquals(false, tEnemy1.getRect().intersects(tEnemy2.getRect()));
	}
	
	

}
