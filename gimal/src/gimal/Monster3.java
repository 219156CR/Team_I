package gimal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;

import javax.imageio.ImageIO;

public class Monster3 {
	private BufferedImage sprite;
	private Map1 [] states1;
	private int stateIndex = 0;

	public Monster3() {
		loadImage();
		states1 = new Map1[3];
		
		//대기모션
		Map1 state1 = new Map1();
		states1[0] = state1;
		state1.width = 115;
		state1.height = 140;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 0;
		state1.frame_size = 6;
		
		//공격키1
		state1 = new Map1();
		states1[1] = state1;
		state1.width = 83;
		state1.height = 95;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 80;
		state1.frame_size = 3;
	}
	
	private void loadImage() {
		try {
			this.sprite = ImageIO.read(new File("image/몬스터10.png"));
			this.sprite = TransformColorToTransparency(sprite, new Color(70, 112, 104));
		} catch (IOException e) {
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

	private void drawMonster(Map1 state, Graphics g, Screen screen, int x, int y) {
		int ix = state.width*state.index_x + state.start_x;
		int iy = state.height*state.index_y + state.start_y;
		
		g.drawImage(sprite, x, y, 
				x + state.width, y + state.height,
				ix, iy,
				ix + state.width, 
				iy + state.height, screen);
		
		if(screen.getCount() % 100 == 0) {
			if(state.index_x < state.frame_size - 1) {
				state.index_x++;
			}
			else {
				if(!state.stop)
					state.index_x = 0;
				else
					state.index_x = state.frame_size-1;
			}
		}
	}

	public void draw(Graphics g, int x, int y, Screen screen) {
		drawMonster(states1[stateIndex], g, screen, x, y);
	}

	public Map1 getState() {
		return states1[stateIndex];
	}
}
