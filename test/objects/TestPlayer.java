package objects;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Player;

public class TestPlayer {

	@Test()
	public void testPlayerConstructor() {
		try {
			Player tPlayer = new Player();
		}catch(NullPointerException e) {
			fail("Cant load some image from resourse");
		}
	}
	
	@Test()
	public void testPlayerSetSpeed() {
		Player tPlayer = new Player();
		int test = 7;
		tPlayer.setSpeed(test);
		assertEquals(test, tPlayer.getSpeed());
	}
	
	@Test()
	public void testPlayerJunp() {
		Player tPlayer = new Player();
		tPlayer.setPosition(100, 280);
		tPlayer.setSpeed(5);
		tPlayer.setJumpSpeed(10);
		tPlayer.setJump(true);
		while(tPlayer.getJump() != false) {
			tPlayer.updateJump();
			assertTrue(tPlayer.getY() <= 280 && tPlayer.getY() >= 100);
		}
	
	}
	
	@Test
	public void testPlayerMove() {
		Player tPlayer = new Player();
		tPlayer.setPosition(100, 280);
		tPlayer.setSpeed(7);
		int count = 0;
		while(count >= 100) {
			tPlayer.updateMove();
			count+= (tPlayer.getSpeed() / 2);
		}
		assertEquals(count, tPlayer.getPath() / 2);
	}
	
	@Test
	public void testPlayerCollisionTRUE() {
		Player tPlayer1 = new Player();
		tPlayer1.setPosition(100, 280);
		
		Player tPlayer2 = new Player();
		tPlayer2.setPosition(70, 250);
		
		assertEquals(true, tPlayer1.getRect().intersects(tPlayer2.getRect()));
	}
	
	@Test
	public void testPlayerCollisionFALSE() {
		Player tPlayer1 = new Player();
		tPlayer1.setPosition(100, 280);
		
		Player tPlayer2 = new Player();
		tPlayer2.setPosition(200, 280);
		
		assertEquals(false, tPlayer1.getRect().intersects(tPlayer2.getRect()));
	}
	
	

}
