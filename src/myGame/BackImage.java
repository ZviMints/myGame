package myGame;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class BackImage extends Canvas {
	Image img;
	public BackImage()
	{
		img = Toolkit.getDefaultToolkit().getImage("background.jpg");
	}
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, 0, 0 ,1070, 600, this);
	}
}
