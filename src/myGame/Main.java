package myGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Main {
    
	public static void main(String[] args) throws IOException {
		

		JFrame frame = new JFrame("Tzvi Mints Game");
		BlockBreakerPanel panel = new BlockBreakerPanel();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1070, 800);
		panel.setBackground(Color.CYAN);
		frame.setVisible(true);

		
	}
}
