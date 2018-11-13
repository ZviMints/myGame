package myGame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BlockBreakerPanel extends JPanel implements KeyListener{
	Block paddle;
	Block back;
	Thread thread;
	Animate animate;
	int size = 25; //size of the ball
	ArrayList<Block> blocks = new ArrayList<Block>(); 
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();

	BlockBreakerPanel() throws IOException
	{	
		paddle = new Block(384,650,300,36,"paddle.png");
		blocks = new ArrayList<Block>();
		back = new Block(0,0,1060,800,"background.jpg");
		
		for(int i=0; i<8; i++) // Create the blocks
			// Block Constructor :  a,  b,  w, h, String ( name of the image ) 
		{
			blocks.add(new Block((i*131+2),5,131,55,"green.png"));
		}
		for(int i=0; i<8; i++)
		{
			blocks.add(new Block((i*131+2),60,131,55,"blue.png"));
		}
		for(int i=0; i<8; i++)
		{
			blocks.add(new Block((i*131+2),115,131,55,"green.png"));
		}
		for(int i=0; i<8; i++)
		{
			blocks.add(new Block((i*131+2),170,131,55,"orange.png"));
		}		

		Random random = new Random();
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;

		
		ball.add(new Block(520,500,33,33,"ball.png"));
		addKeyListener(this); // for read the keys
		setFocusable(true);

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // reprint
		
		back.draw(g, this);
		
		for(Block b : blocks)
		{
			b.draw(g,this);
		}
		for(Block p : powerup)
		{
			p.draw(g,this);
		}

		for(Block b : ball)
		{
			b.draw(g,this);
		}
		paddle.draw(g, this);
	}
	public void update() {

		for(Block p : powerup)
		{
			p.y+=1;
			if(p.intersects(paddle) && !p.destroyed) {
				p.destroyed=true;
				ball.add(new Block(paddle.x+100,437,33,33,"ball.png"));
			}
		}
		for(Block ba : ball)
		{
			if(ba.y > getHeight()) thread.stop();
			
			if(ba.x > (getWidth() - size) || ba.x < 0)
				ba.dx*=-1;
			if(ba.y < 0 || ba.intersects(paddle))
				ba.dy*=-1;
			ba.x+=ba.dx;
			ba.y+=ba.dy;
			for(Block b : blocks)
			{
				if((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed)
				{
					ba.dx*=-1;
					b.destroyed = true;
					if(b.powerup)
					{
						powerup.add(new Block(b.x,b.y,33,33,"extra.png"));
					}
				}
				else if (ba.intersects(b) && !b.destroyed)
				{
					b.destroyed = true;
					ba.dy*=-1;
					if(b.powerup)
					{
						powerup.add(new Block(b.x,b.y,25,25,"extra.png"));
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
					
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0)
		{
			paddle.x-=25;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width))
		{
			paddle.x+=25;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
		{
			thread.stop();
		}
	}
}
