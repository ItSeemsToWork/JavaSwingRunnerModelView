package main;

import java.awt.Toolkit;

import javax.swing.*;

public class MainGame {

public static void main(String[]args) {
		
	    int WIDTH = 960;
	    int HEIGHT = 480;
	
	    JFrame window = new JFrame("MyRunner v1.0");
		window.setContentPane(new GamePanel());
		window.setSize(WIDTH, HEIGHT);
		window.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - window.getSize().width) / 2, 
			    (Toolkit.getDefaultToolkit().getScreenSize().height - window.getSize().height) / 2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
