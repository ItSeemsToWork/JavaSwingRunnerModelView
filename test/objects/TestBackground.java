package objects;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Background;

public class TestBackground {

	@Test()
	public void testBackgroundConstructor() {
		try {
			Background tBack = new Background("/background/layer1_1920x1080.png");
		}catch(NullPointerException e) {
			fail("Cant load some image from resourse");
		}
	}

}
