package gimal;

import java.awt.Color;
import java.awt.Font;
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
import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Character3 implements KeyListener {
	private BufferedImage sprite;
	private int x = 0;
	private int y = 650;
	private Map1 [] states1;
	private int stateIndex = 0;
	private boolean isJumping = false;
	private int jumpHeight = 150;
	private int jumpCount = 0;
	private int jumpSpeed = 1;
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private float moveSpeed = 1;
	private int mp = 100;
	private int hp = 100;
	private final int MAX_MP = 100;
	private final int MAX_HP = 100;
	private long lastActionTimeA = 0;
	private long lastActionTimeS = 0;
	private long lastActionTimeD = 0;
	private long lastActionTimeF = 0;
	private final long cooldownTimeA = 500; // A키 대기시간 0.5초
	private final long cooldownTimeS = 3000; // S키 대기시간 3초
	private final long cooldownTimeD = 10000; // D키 대기시간 10초
	private final long cooldownTimeF = 60000; // F키 대기시간 60초
	private int healCount = 0;
	private int qKeyUses = 5; // Q키 사용 횟수
	private int wKeyUses = 5; // W키 사용 횟수
	private Rectangle attackHitbox;
	private boolean isAttacking = false;
	private int attackDamage = 10;
	private Monster1 monster1;

	
	public Character3() {
		loadImage();
		states1 = new Map1[8];
		
		//대기모션
		Map1 state1 = new Map1();
		states1[0] = state1;
		state1.width = 82;
		state1.height = 74;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 0;
		state1.frame_size = 2;
		
		//좌측키
		state1 = new Map1();
		states1[1] = state1;
		state1.width = 85;
		state1.height = 75;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 195;
		state1.frame_size = 4;
		
		//우측키
		state1 = new Map1();
		states1[2] = state1;
		state1.width = 85;
		state1.height = 75;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 195;
		state1.frame_size = 4;
		
		//점프키
		state1 = new Map1();
		states1[3] = state1;
		state1.width = 50;
		state1.height = 100;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 565;
		state1.start_y = 165;
		state1.frame_size = 1;
		state1.stop = true;
		
		//공격키1
		state1 = new Map1();
		states1[4] = state1;
		state1.width = 83;
		state1.height = 95;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 80;
		state1.frame_size = 3;
		
		//공격키2
		state1 = new Map1();
		states1[5] = state1;
		state1.width = 125;
		state1.height = 64;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 290;
		state1.frame_size = 3;
		
		//공격키3
		state1 = new Map1();
		states1[6] = state1;
		state1.width = 85;
		state1.height = 110;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 80;
		state1.frame_size = 6;
		
		//공격키4
		state1 = new Map1();
		states1[7] = state1;
		state1.width = 141;
		state1.height = 91;
		state1.index_x = 0;
		state1.index_y = 0;
		state1.start_x = 0;
		state1.start_y = 570;
		state1.frame_size = 4;
	}
	
	private Map1 getState() {
		return states1[stateIndex];
	}
	
	private void loadImage() {
		try {
			this.sprite = ImageIO.read(new File("image/전사1.png"));
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
		drawHealthBars(g);
		drawCooldownBars(g);
		/*
		// 히트박스 표시 (디버그용)
		if (isAttacking && attackHitbox != null) {
			g.setColor(new Color(255, 0, 0, 128));
			g.fillRect(attackHitbox.x, attackHitbox.y, attackHitbox.width, attackHitbox.height);
		}
		*/
	}
	
	private void drawCharacter(Map1 state, Graphics g, Screen screen) {
		int ix = state.width * state.index_x + state.start_x;
		int iy = state.height * state.index_y + state.start_y;

		int characterY = y;
		
		if (stateIndex == 4) { // A키
	        characterY -= 20; // Y 좌표를 20만큼 위로 이동
	    }
		if (stateIndex == 5) { // S키
	        characterY += 10; // Y 좌표를 10만큼 아래로 이동
	    }
		if (stateIndex == 6) { // D키 상태
	        characterY -= 20; // Y 좌표를 20만큼 위로 이동
	    }
		if (stateIndex == 7) { // F키 상태
	        characterY -= 18; // Y 좌표를 18만큼 위로 이동
	    }
		

		// 좌우 반전 처리
		int drawX = (stateIndex == 0 || stateIndex == 2 || stateIndex == 3 || stateIndex == 4 || stateIndex == 5 || stateIndex == 6 || stateIndex == 7) ? x + state.width : x; // 대���, 우측, 점프 상태일 때 반전
		int drawWidth = (stateIndex == 0 || stateIndex == 2 || stateIndex == 3 || stateIndex == 4 || stateIndex == 5 || stateIndex == 6 || stateIndex == 7) ? -state.width : state.width; // 대기, 우측, 점프 상태일 때 반전

		g.drawImage(sprite, drawX, characterY, 
				drawX + drawWidth, characterY + state.height,
				ix, iy,
				ix + state.width, 
				iy + state.height, screen);

		if (screen.getCount() % 100 == 0) {
			if (state.index_x < state.frame_size - 1) {
				state.index_x++;
			} else {
				if (!state.stop)
					state.index_x = 0;
				else
					state.index_x = state.frame_size - 1;
			}
		}
	}

	private void drawHealthBars(Graphics g) {
		// HP 바 그리기
		g.setColor(Color.RED);
		g.fillRect(10, 10, (int) (hp / (float) MAX_HP * 200), 20); // HP 
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 200, 20); // HP 바 테두리

		// MP 바 그리기
		g.setColor(Color.BLUE);
		g.fillRect(10, 40, (int) (mp / (float) MAX_MP * 200), 20); // MP 바
		g.setColor(Color.BLACK);
		g.drawRect(10, 40, 200, 20); // MP 바 테두리
	}

	private void drawCooldownBars(Graphics g) {
		int barWidth = 35; // 대기시간 바의 너비
		int barHeight = 35; // 대기시간 바의 높이
		int xPosition = 10; // 시작 x 좌표
		int yPosition = 70; // 대기시간 바의 y 좌표

		// A키 대기시간 바
		long remainingA = cooldownTimeA - (System.currentTimeMillis() - lastActionTimeA);
		g.setColor(Color.WHITE);
		g.fillRect(xPosition, yPosition, (int) Math.max(0, (remainingA / (float) cooldownTimeA) * barWidth), barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xPosition, yPosition, barWidth, barHeight); // A키 대기시간 바 테두리
		g.setFont(new Font("Arial", Font.BOLD, 30)); // 폰트 크기를 30으로 설정
		int aX = (int)(xPosition + barWidth / 2 - g.getFontMetrics().stringWidth("A") / 2);
		int aY = (int)(yPosition + barHeight / 2 + 10);
		g.drawString("A", aX, aY); // A 문자 추가

		// S키 대기시간 바
		long remainingS = cooldownTimeS - (System.currentTimeMillis() - lastActionTimeS);
		g.setColor(Color.WHITE);
		g.fillRect(xPosition + barWidth + 10, yPosition, (int) Math.max(0, (remainingS / (float) cooldownTimeS) * barWidth), barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xPosition + barWidth + 10, yPosition, barWidth, barHeight); // S키 대기시간 바 테두리
		int sX = (int)(xPosition + barWidth + 10 + barWidth / 2 - g.getFontMetrics().stringWidth("S") / 2);
		int sY = (int)(yPosition + barHeight / 2 + 10);
		g.drawString("S", sX, sY); // S 문자 추가

		// D키 대기시간 바
		long remainingD = cooldownTimeD - (System.currentTimeMillis() - lastActionTimeD);
		g.setColor(Color.WHITE);
		g.fillRect(xPosition + (barWidth + 10) * 2, yPosition, (int) Math.max(0, (remainingD / (float) cooldownTimeD) * barWidth), barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xPosition + (barWidth + 10) * 2, yPosition, barWidth, barHeight); // D키 대기시간 바 테두리
		int dX = (int)(xPosition + (barWidth + 10) * 2 + barWidth / 2 - g.getFontMetrics().stringWidth("D") / 2);
		int dY = (int)(yPosition + barHeight / 2 + 10);
		g.drawString("D", dX, dY); // D 문자 추가

		// F키 대기시간 바
		long remainingF = cooldownTimeF - (System.currentTimeMillis() - lastActionTimeF);
		g.setColor(Color.WHITE);
		g.fillRect(xPosition + (barWidth + 10) * 3, yPosition, (int) Math.max(0, (remainingF / (float) cooldownTimeF) * barWidth), barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xPosition + (barWidth + 10) * 3, yPosition, barWidth, barHeight); // F키 대기시간 바 테두리
		int fX = (int)(xPosition + (barWidth + 10) * 3 + barWidth / 2 - g.getFontMetrics().stringWidth("F") / 2);
		int fY = (int)(yPosition + barHeight / 2 + 10);
		g.drawString("F", fX, fY); // F 문자 추가

		// Q키 사용 횟수 표시
		g.setColor(Color.BLACK);
		g.drawString("HP posion Count: " + qKeyUses, 10, 150); // Q키 사용 횟수 표시

		// W키 사용 횟수 표시
		g.setColor(Color.BLACK);
		g.drawString("Mp posion Count: " + wKeyUses, 10, 180); // W키 사용 횟수 표시
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
				this.stateIndex = 1;
				break;
			case KeyEvent.VK_RIGHT:
				isMovingRight = true;
				this.stateIndex = 2;
				break;
				
			case KeyEvent.VK_A:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeA > cooldownTimeA) {
					this.stateIndex = 4;
					isAttacking = true;
					
					// 공격 방향에 따른 히트박스 생성
					int hitboxWidth = 40;
					int hitboxHeight = 40;
					int hitboxX = x + (stateIndex == 1 ? -hitboxWidth : states1[4].width);
					attackHitbox = new Rectangle(hitboxX, y, hitboxWidth, hitboxHeight);
					
					mp -= MAX_MP * 0.01;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeA = System.currentTimeMillis();
				}
				break;
				
			case KeyEvent.VK_S:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeS > cooldownTimeS) {
					this.stateIndex = 5;
					isAttacking = true;
					
					// 공격 방향에 따른 히트박스 생성
					int hitboxWidth = 100;
					int hitboxHeight = 20;
					int hitboxX = x + (stateIndex == 1 ? -hitboxWidth : states1[4].width);
					attackHitbox = new Rectangle(hitboxX, y, hitboxWidth, hitboxHeight);
					
					mp -= MAX_MP * 0.05;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeS = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
				break;
				
			case KeyEvent.VK_D:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeD > cooldownTimeD) {
					this.stateIndex = 6;
					isAttacking = true;
					
					// 공격 방향에 따른 히트박스 생성
					int hitboxWidth = 300;
					int hitboxHeight = 100;
					int hitboxX = x + (stateIndex == 1 ? -hitboxWidth : states1[4].width);
					attackHitbox = new Rectangle(hitboxX, y, hitboxWidth, hitboxHeight);
					
					mp -= MAX_MP * 0.1;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeD = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
				break;
				
			case KeyEvent.VK_F:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeF > cooldownTimeF) {
					this.stateIndex = 7;
					isAttacking = true;
					
					// 공격 방향에 따른 히트박스 생성
					int hitboxWidth = 500;
					int hitboxHeight = 100;
					int hitboxX = x + (stateIndex == 1 ? -hitboxWidth : states1[4].width);
					attackHitbox = new Rectangle(hitboxX, y, hitboxWidth, hitboxHeight);
					
					mp -= MAX_MP * 0.5;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeF = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
				break;
				
			case KeyEvent.VK_SPACE:
				this.stateIndex = 3;
				if (!isJumping) {
					isJumping = true;
					jumpCount = 0; // 점프 카운트 초기화
				}
				break;
				
			case KeyEvent.VK_Q:
				if (qKeyUses > 0) { // 남은 사용 횟수 체크
					hp += MAX_HP * 0.2; // HP 20% 회복
					if (hp > MAX_HP) {
						hp = MAX_HP; // 최대 HP 초과 방지
					}
					qKeyUses--; // 사용 횟수 감소
				}
				break;
				
			case KeyEvent.VK_W:
				if (wKeyUses > 0) { // 남은 사용 횟수 체크
					mp += MAX_MP * 0.2; // MP 20% 회복
					if (mp > MAX_MP) {
						mp = MAX_MP; // 최대 MP 초과 방지
					}
					wKeyUses--; // 사용 횟수 감소
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
			case KeyEvent.VK_A:
				isAttacking = false;
				attackHitbox = null;
				break;
			case KeyEvent.VK_S:
				isAttacking = false;
				attackHitbox = null;
				break;
			case KeyEvent.VK_D:
				isAttacking = false;
				attackHitbox = null;
				break;
			case KeyEvent.VK_F:
				isAttacking = false;
				attackHitbox = null;
				break;
		}
		this.stateIndex = 0;
	}

	private void updateJump() {
	    if (isJumping) {
	        if (jumpCount < jumpHeight) {
	            y -= jumpSpeed; // 상승
	            jumpCount += jumpSpeed;
	        } else {
	            // 하강 로직을 부드럽게 처리
	            if (y < 725) {
	                y += jumpSpeed; // 하강
	            } else {
	                isJumping = false; // 점프 완료
	                y = 725; // 초기 위치로 복귀
	                jumpCount = 0; // 점프 카운트 초기화
	            }
	        }
	    } else {
	        // 점프가 끝난 후 y를 초기 위치로 설정
	        if (y < 725) {
	            y += jumpSpeed; // 하강
	        }
	    }
		// 캐릭터 이동 로직 추가
		if (isMovingLeft) {
			x -= moveSpeed;
			if (x < 0) {
				x = 0; // 화면 왼쪽 경계
			}
		}
		if (isMovingRight) {
			x += moveSpeed;
			if (x > 1400) {
				x = 1400; // 화면 오른쪽 경계
			}
		}
	}
	
	// 공격 상태 확인 메서드 추가
    public boolean isAttacking() {
        return isAttacking;
    }

    // 공격 히트박스 반환 메서드 추가
    public Rectangle getAttackHitbox() {
        return attackHitbox;
    }
    
 // 현재 공격 데미지 반환 메서드 추가
    public int getAttackDamage() {
        // 각 공격 키에 따른 데미지 차등 적용
        switch(stateIndex) {
            case 4: return attackDamage; // A키
            case 5: return attackDamage * 2; // S키
            case 6: return attackDamage * 3; // D키
            case 7: return attackDamage * 4; // F키
            default: return 0;
        }
    }

}