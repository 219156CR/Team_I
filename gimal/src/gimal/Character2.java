package gimal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;

import javax.imageio.ImageIO;

public class Character2 implements KeyListener {
	private BufferedImage sprite;
	private int x = 0;
	private int y = 850;
	private Map1 [] states1;
	private Map2 [] states2;
	private Map3 [] states3;
	private int stateIndex = 0;
	private boolean isJumping = false;
	private int jumpHeight = 150;
	private int jumpCount = 0;
	private int jumpSpeed = 1;
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private float moveSpeed = 10;
	
	public Character2() {
		loadImage();

		states1 = new Map1[5];
		Map1 state1 = new Map1();
		states1[0] = state1;
		state1.width = 90;
		state1.height = 105;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 0;
		state1.frame_size = 5;
		
		state1 = new Map1();
		states1[1] = state1;
		state1.width = 75;
		state1.height = 105;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 480;
		state1.start_y = 0;
		state1.frame_size = 3;
		state1.stop = true;
		
		state1 = new Map1();
		states1[2] = state1;
		state1.width = 82;
		state1.height = 105;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 540;
		state1.start_y = 120;
		state1.frame_size = 6;
		
		state1 = new Map1();
		states1[3] = state1;
		state1.width = 82;
		state1.height = 105;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 540;
		state1.start_y = 120;
		state1.frame_size = 6;
		
		state1 = new Map1();
		states1[4] = state1;
		state1.width = 100;
		state1.height = 105;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 260;
		state1.frame_size = 2;
		state1.stop = true;


		states2 = new Map2[5];
		Map2 state2 = new Map2();
		states2[0] = state2;
		state2.width = 90;
		state2.height = 105;
		state2.index_x = 0;
		state2.index_y = 0;
		state2.start_x = 0;
		state2.start_y = 0;
		state2.frame_size = 5;
		
		state2 = new Map2();
		states2[1] = state2;
		state2.width = 75;
		state2.height = 105;
		state2.index_x = 0;
		state2.index_y = 0;
		state2.start_x = 480;
		state2.start_y = 0;
		state2.frame_size = 3;
		state2.stop = true;
		
		state2 = new Map2();
		states2[2] = state2;
		state2.width = 82;
		state2.height = 105;
		state2.index_x = 0;
		state2.index_y = 0;
		state2.start_x = 540;
		state2.start_y = 120;
		state2.frame_size = 6;
		
		state2 = new Map2();
		states2[3] = state2;
		state2.width = 82;
		state2.height = 105;
		state2.index_x = 0;
		state2.index_y = 0;
		state2.start_x = 540;
		state2.start_y = 120;
		state2.frame_size = 6;
		
		state2 = new Map2();
		states2[4] = state2;
		state2.width = 100;
		state2.height = 105;
		state2.index_x = 0;
		state2.index_y = 0;
		state2.start_x = 0;
		state2.start_y = 260;
		state2.frame_size = 2;
		state2.stop = true;


		states3 = new Map3[5];
		Map3 state3 = new Map3();
		states3[0] = state3;
		state3.width = 90;
		state3.height = 105;
		state3.index_x = 0;
		state3.index_y = 0;
		state3.start_x = 0;
		state3.start_y = 0;
		state3.frame_size = 5;
		
		state3 = new Map3();
		states3[1] = state3;
		state3.width = 75;
		state3.height = 105;
		state3.index_x = 0;
		state3.index_y = 0;
		state3.start_x = 480;
		state3.start_y = 0;
		state3.frame_size = 3;
		state3.stop = true;
		
		state3 = new Map3();
		states3[2] = state3;
		state3.width = 82;
		state3.height = 105;
		state3.index_x = 0;
		state3.index_y = 0;
		state3.start_x = 540;
		state3.start_y = 120;
		state3.frame_size = 6;
		
		state3 = new Map3();
		states3[3] = state3;
		state3.width = 82;
		state3.height = 105;
		state3.index_x = 0;
		state3.index_y = 0;
		state3.start_x = 540;
		state3.start_y = 120;
		state3.frame_size = 6;
		
		state3 = new Map3();
		states3[4] = state3;
		state3.width = 100;
		state3.height = 105;
		state3.index_x = 0;
		state3.index_y = 0;
		state3.start_x = 0;
		state3.start_y = 260;
		state3.frame_size = 2;
		state3.stop = true;
	}
	
	private Map1 getState() {
		return states1[stateIndex];
	}
	
	private void loadImage() {
		try {
			this.sprite = ImageIO.read(new File("res/ryu.png"));
			this.sprite = TransformColorToTransparency(sprite, new Color(70, 112, 104));
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
	public void draw(Graphics g, Screen screen) {
		updateJump();
		drawCharacter(getState(), g, screen);
	}
	
	private void drawCharacter(Map1 state, Graphics g, Screen screen) {
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isMovingLeft = true;
				this.stateIndex = 3;
				break;
			case KeyEvent.VK_RIGHT:
				isMovingRight = true;
				this.stateIndex = 2;
				break;
			case KeyEvent.VK_A:
				this.stateIndex = 4;
				break;
			case KeyEvent.VK_SPACE:
				if (!isJumping) {
					isJumping = true;
					jumpCount = 0;
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isMovingLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				isMovingRight = false;
				break;
		}
		this.stateIndex = 0;
	}

	public void updateJump() {
		if (isJumping) {
			if (jumpCount < jumpHeight) {
				y -= jumpSpeed;
				jumpCount += jumpSpeed;
			} else {
				isJumping = false;
			}
		//점프 후 바닥 착지 부분
		} else if (y < 850) {
			y += jumpSpeed;
		}
		//화면 넘어가지 않게 조절해주는 부분
		if (isMovingLeft) {
			x -= moveSpeed;
			if (x < 0) {
				x = 0;
			}
		}
		if (isMovingRight) {
			x += moveSpeed;
			if (x > 1100) {
				x = 1100;
			}
		}
	}
}
