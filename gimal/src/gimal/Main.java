package gimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("maple game");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		ImageIcon image = new ImageIcon("C:\\Users\\305\\eclipse-workspace\\gimal\\image\\1.PNG");
		ImageIcon image2 = new ImageIcon("C:\\Users\\305\\eclipse-workspace\\gimal\\image\\2.PNG");
		JLabel imageLabel = new JLabel(image);
		JLabel imageLabel2 = new JLabel(image2);
		imageLabel.setBounds(100, 600, 200, 60);
		imageLabel2.setBounds(700, 600, 200, 60);
		
		imageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		imageLabel2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		frame.setLayout(null);
		frame.add(imageLabel);
		frame.add(imageLabel2);
		frame.setVisible(true);
	}
}