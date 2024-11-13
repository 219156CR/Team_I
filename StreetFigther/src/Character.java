import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character implements KeyListener {
	private BufferedImage sprite;
	private State []states;
	private int state = 0;
	private int x = 50;
	private int y = 450;
	public Character() {
		loadImage();
		states = new State[5];
		State state = new State();
		states[0] = state;
			
		state.width = 90;
		state.height = 105;
		state.index_x = 0;
		state.index_y = 0;
		state.start_x = 0;
		state.start_y = 0;
		state.frame_size = 5;
		
		state = new State();
		states[1] = state;
		state.width = 75;
		state.height = 105;
		state.index_x = 0;
		state.index_y = 0;
		state.start_x = 480;
		state.start_y = 0;
		state.frame_size = 3;
		state.stop = true;
		
		state = new State();
		states[2] = state;
		state.width = 82;
		state.height = 105;
		state.index_x = 0;
		state.index_y = 0;
		state.start_x = 0;
		state.start_y = 120;
		state.frame_size = 6;
		
		state = new State();
		states[3] = state;
		state.width = 82;
		state.height = 105;
		state.index_x = 0;
		state.index_y = 0;
		state.start_x = 540;
		state.start_y = 120;
		state.frame_size = 6;
		
		state = new State();
		states[4] = state;
		state.width = 100;
		state.height = 105;
		state.index_x = 0;
		state.index_y = 0;
		state.start_x = 0;
		state.start_y = 260;
		state.frame_size = 2;
		state.stop = true;
	}
	
	private void loadImage() {
		try {
			this.sprite = ImageIO.read(new File("resources/ryu.png"));
			this.sprite = TransformColorToTransparency(this.sprite, new Color(70, 112, 104));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected BufferedImage TransformColorToTransparency(BufferedImage image, Color c1) {
	  final int r1 = c1.getRed();
	  final int g1 = c1.getGreen();
	  final int b1 = c1.getBlue();
	 
	  ImageFilter filter = new RGBImageFilter() {
			public int filterRGB(int x, int y, int rgb) {
				int r = ( rgb & 0xFF0000 ) >> 16;
				int g = ( rgb & 0xFF00 ) >> 8;
				int b = ( rgb & 0xFF );
				if( r == r1 && g == g1 && b == b1) {
					return rgb & 0xFFFFFF;
				}
				return rgb;
			}
		};
	 
		ImageProducer ip = new FilteredImageSource( image.getSource(), filter );
		Image img = Toolkit.getDefaultToolkit().createImage(ip);
		BufferedImage dest = new BufferedImage(img.getWidth(null), 
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dest.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return dest;
	}
	private State getState() {
		return states[state];
	}
	public void draw(Graphics g, Screen screen) {
		//g.drawImage(sprite, 0, 0, screen);
		drawCharacter(getState(), g, screen);
	}
	
	private void drawCharacter(State state, Graphics g, Screen screen) {
		g.drawImage(sprite, 
				x, y,  //위치 
				x + state.width, y + state.height, //크기 
				state.width*state.index_x + state.start_x, 
				state.height*state.index_y + state.start_y, 
				state.width*state.index_x + state.start_x + state.width, 
				state.height*state.index_y + state.start_y + state.height, 
				screen);
		
		if(screen.getCount() % 100 == 0)
		{
			if(state.index_x < state.frame_size-1)
			{
				state.index_x++;
			}
			else
			{
				if(!state.stop)
					state.index_x = 0;
				else
					state.index_x = state.frame_size-1;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			this.state = 3;
			x -= 2;
			break;
		case KeyEvent.VK_RIGHT:
			this.state = 2;
			x += 2;
			break;
		case KeyEvent.VK_A:
			this.state = 4;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.state = 0;
	}
}
