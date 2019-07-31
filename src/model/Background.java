package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Background {

	private Image img;

    public Background(String s) {
    	img = new ImageIcon(getClass().getResource(s)).getImage();
    }

    public Image getImage() {
	    return img;
    }

}

