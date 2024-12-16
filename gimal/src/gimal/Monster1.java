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
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class Monster1 {
	private BufferedImage sprite;
	private Map1 [] Monster_states1;
	private int stateIndex = 0;
	private int hp = 100;
	private final int MAX_HP = 100;
	private boolean isAlive = true;
	private int x = 1300; // 몬스터의 초기 x 좌표
	private Timer timer;

	public Monster1() {
		loadImage();
		Monster_states1 = new Map1[3];
		
		//대기모션
		Map1 state1 = new Map1();
		Monster_states1[0] = state1;
		state1.width = 146;
		state1.height = 120;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 0;
		state1.frame_size = 6;
		
		//공격키1
		state1 = new Map1();
		Monster_states1[1] = state1;
		state1.width = 83;
		state1.height = 95;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 80;
		state1.frame_size = 3;
		
		// 이동 타이머 설정
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updatePosition(); // 주기적으로 위치 업데이트
			}
		}, 0, 50); // 50ms마다 이동 (0.5초)
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

	public void updatePosition() {
		if (isAlive) {
			x -= 1; // 좌측으로 1픽셀 이동
			if (x < 0) {
				x = 0; // 화면 밖으로 나가지 않도록 제한
			}
		}
	}

	public void draw(Graphics g, Screen screen) {
		if (!isAlive) return;
		
		// y 좌표는 고정
		int y = 680;  // 기본 y 좌표
		
		drawMonster(Monster_states1[stateIndex], g, screen, x, y);
		
		// 체력바 그리기
		g.setColor(Color.RED);
		g.fillRect(x, y - 20, (int)(hp / (float)MAX_HP * Monster_states1[stateIndex].width), 10);
		g.setColor(Color.BLACK);
		g.drawRect(x, y - 20, Monster_states1[stateIndex].width, 10);
		
		// 히트박스 그리기 (디버그용)
		Rectangle hitbox = getHitbox();
		g.setColor(new Color(255, 0, 0, 128)); // 반투명 빨간색
		g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height); // 히트박스 그리기
		g.setColor(Color.BLACK);
		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height); // 히트박스 테두리 그리기
	}

	public Map1 getState() {
		return Monster_states1[stateIndex];
	}

	public void takeDamage(int damage) {
		hp -= damage;
		if (hp < 0) {
			hp = 0;
			isAlive = false;
		}
	}

	public int getHp() {
		return hp;
	}

	public Rectangle getHitbox() {
		// 현재 x 좌표를 기준으로 100x100 크기의 히트박스 생성
		int width = 100; // 히트박스 너비
		int height = 100; // 히트박스 높이
		return new Rectangle(x, 680, width, height); // y 좌표는 고정
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getMaxHp() {
		return MAX_HP;
	}

	public void setX(int x) {
		this.x = x; // x 좌표 설정
	}

	public int getX() {
		return x; // x 좌표 반환
	}
}
