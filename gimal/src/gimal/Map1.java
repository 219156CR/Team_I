package gimal;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Map1 {
	public int width = 0;
	public int height = 0;
	public int index_x = 0;
	public int index_y = 0;
	public int start_x = 0;
	public int start_y = 0;
	public int frame_size = 0;
	public boolean stop = false;
	public boolean fristCheck = false;
	private Image image;
	
	public Map1() {
		this.image = new ImageIcon("A.jpg").getImage();
	}
	
	public void clear() {
		this.fristCheck = false;
	}
}
