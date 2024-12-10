package gimal;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mchoise {
	public static void setupMchoiseFrame(JFrame frame) {
		ImageIcon image1 = new ImageIcon("image/1.PNG");
		ImageIcon image2 = new ImageIcon("image/2.PNG");
		ImageIcon image3 = new ImageIcon("image/2.PNG");
		
		JLabel imageLabel1 = new JLabel(image1);
		JLabel imageLabel2 = new JLabel(image2);
		JLabel imageLabel3 = new JLabel(image3);
		
		imageLabel1.setBounds(0, 600, 200, 60);
		imageLabel2.setBounds(400, 600, 200, 60);
		imageLabel3.setBounds(800, 600, 200, 60);
		
		frame.setLayout(null);
		frame.add(imageLabel1);
		frame.add(imageLabel2);
		frame.add(imageLabel3);
		
		imageLabel1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				JFrame gameFrame = new JFrame("Game Screen");
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setSize(1500, 1000);
				
				Screen screen = new Screen();
				gameFrame.add(screen);
				
				gameFrame.setVisible(true);
			}
		});

		imageLabel2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				JFrame gameFrame = new JFrame("Game Screen");
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setSize(1500, 1000);
				
				Screen screen = new Screen();
				gameFrame.add(screen);
				
				gameFrame.setVisible(true);
			}
		});

		imageLabel3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				JFrame gameFrame = new JFrame("Game Screen");
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setSize(1500, 1000);
				
				Screen screen = new Screen();
				gameFrame.add(screen);
				
				gameFrame.setVisible(true);
			}
		});
	}
}