package gimal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

public class Character1 implements KeyListener {
    private ImageIcon sprite;
    private int x = 0;
    private int y = 850;
    private Map1[] states1;
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

    public Character1() {
        loadImage();

<<<<<<< HEAD
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
		drawHealthBars(g);
		drawCooldownBars(g);
	}
	
	private void drawCharacter(Map1 state, Graphics g, Screen screen) {
		int ix = state.width * state.index_x + state.start_x;
		int iy = state.height * state.index_y + state.start_y;
=======
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
    }
    
    private Map1 getState() {
        return states1[stateIndex];
    }
    
    private void loadImage() {
        this.sprite = new ImageIcon("image/모래시계 기본.gif"); // GIF 파일로 변경
    }
    
    public void draw(Graphics g, Screen screen) {
        updateJump();
        drawCharacter(getState(), g, screen);
        drawHealthBars(g);
        drawCooldownBars(g);
    }
    
    private void drawCharacter(Map1 state, Graphics g, Screen screen) {
        int characterY = (state.index_y == 0) ? 600 : y;

        g.drawImage(sprite.getImage(), x, characterY, 
                x + state.width, characterY + state.height,
                state.start_x, state.start_y,
                state.start_x + state.width, 
                state.start_y + state.height, screen);

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
        g.fillRect(10, 10, (int) (hp / (float) MAX_HP * 200), 20); // HP 바
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 200, 20); // HP 바 테두리

        // MP 바 그리기
        g.setColor(Color.BLUE);
        g.fillRect(10, 40, (int) (mp / (float) MAX_MP * 200), 20); // MP 바
        g.setColor(Color.BLACK);
        g.drawRect(10, 40, 200, 20); // MP 바 테두리
    }
>>>>>>> branch 'main' of https://github.com/219156CR/Team_I.git

<<<<<<< HEAD
		int characterY = (state.index_y == 0) ? 700 : y;
=======
    private void drawCooldownBars(Graphics g) {
        int barWidth = 35; // 대기시간 바의 너비
        int barHeight = 35; // 대기시간 바의 높이
        int xPosition = 10; // 시작 x 좌표
        int yPosition = 70; // 대기시간 바의 y 좌표
>>>>>>> branch 'main' of https://github.com/219156CR/Team_I.git

        // A키 대기시간 바
        long remainingA = cooldownTimeA - (System.currentTimeMillis() - lastActionTimeA);
        g.setColor(Color.WHITE);
        g.fillRect(xPosition, yPosition, (int) Math.max(0, (remainingA / (float) cooldownTimeA) * barWidth), barHeight);
        g.setColor(Color.BLACK);
        g.drawRect(xPosition, yPosition, barWidth, barHeight); // A키 대기시간 바 테두리

        // S키 대기시간 바
        long remainingS = cooldownTimeS - (System.currentTimeMillis() - lastActionTimeS);
        g.setColor(Color.WHITE);
        g.fillRect(xPosition + barWidth + 10, yPosition, (int) Math.max(0, (remainingS / (float) cooldownTimeS) * barWidth), barHeight);
        g.setColor(Color.BLACK);
        g.drawRect(xPosition + barWidth + 10, yPosition, barWidth, barHeight); // S키 대기시간 바 테두리

        // D키 대기시간 바
        long remainingD = cooldownTimeD - (System.currentTimeMillis() - lastActionTimeD);
        g.setColor(Color.WHITE);
        g.fillRect(xPosition + (barWidth + 10) * 2, yPosition, (int) Math.max(0, (remainingD / (float) cooldownTimeD) * barWidth), barHeight);
        g.setColor(Color.BLACK);
        g.drawRect(xPosition + (barWidth + 10) * 2, yPosition, barWidth, barHeight); // D키 대기시간 바 테두리

        // F키 대기시간 바
        long remainingF = cooldownTimeF - (System.currentTimeMillis() - lastActionTimeF);
        g.setColor(Color.WHITE);
        g.fillRect(xPosition + (barWidth + 10) * 3, yPosition, (int) Math.max(0, (remainingF / (float) cooldownTimeF) * barWidth), barHeight);
        g.setColor(Color.BLACK);
        g.drawRect(xPosition + (barWidth + 10) * 3, yPosition, barWidth, barHeight); // F키 대기시간 바 테두리
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
                if (mp > 0 && System.currentTimeMillis() - lastActionTimeA > cooldownTimeA) {
                    this.stateIndex = 4;
                    mp -= MAX_MP * 0.01;
                    if (mp < 0) {
                        mp = 0;
                    }
                    lastActionTimeA = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
                }
                break;
            case KeyEvent.VK_S:
                if (mp > 0 && System.currentTimeMillis() - lastActionTimeS > cooldownTimeS) {
                    this.stateIndex = 4;
                    mp -= MAX_MP * 0.05;
                    if (mp < 0) {
                        mp = 0;
                    }
                    lastActionTimeS = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
                }
                break;
            case KeyEvent.VK_D:
                if (mp > 0 && System.currentTimeMillis() - lastActionTimeD > cooldownTimeD) {
                    this.stateIndex = 4;
                    mp -= MAX_MP * 0.1;
                    if (mp < 0) {
                        mp = 0;
                    }
                    lastActionTimeD = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
                }
                break;
            case KeyEvent.VK_F:
                if (mp > 0 && System.currentTimeMillis() - lastActionTimeF > cooldownTimeF) {
                    this.stateIndex = 4;
                    mp -= MAX_MP * 0.5;
                    if (mp < 0) {
                        mp = 0;
                    }
                    lastActionTimeF = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
                }
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

<<<<<<< HEAD
		// D키 대기시간 바
		long remainingD = cooldownTimeD - (System.currentTimeMillis() - lastActionTimeD);
		g.setColor(Color.WHITE);
		g.fillRect(xPosition + (barWidth + 10) * 2, yPosition, (int) Math.max(0, (remainingD / (float) cooldownTimeD) * barWidth), barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xPosition + (barWidth + 10) * 2, yPosition, barWidth, barHeight); // D키 대기시간 바 테두리

		// F키 대기시간 바
		long remainingF = cooldownTimeF - (System.currentTimeMillis() - lastActionTimeF);
		g.setColor(Color.WHITE);
		g.fillRect(xPosition + (barWidth + 10) * 3, yPosition, (int) Math.max(0, (remainingF / (float) cooldownTimeF) * barWidth), barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(xPosition + (barWidth + 10) * 3, yPosition, barWidth, barHeight); // F키 대기시간 바 테두리
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
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeA > cooldownTimeA) {
					this.stateIndex = 4;
					mp -= MAX_MP * 0.01;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeA = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
				break;
			case KeyEvent.VK_S:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeS > cooldownTimeS) {
					this.stateIndex = 4;
					mp -= MAX_MP * 0.05;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeS = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
				break;
			case KeyEvent.VK_D:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeD > cooldownTimeD) {
					this.stateIndex = 4;
					mp -= MAX_MP * 0.1;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeD = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
				break;
			case KeyEvent.VK_F:
				if (mp > 0 && System.currentTimeMillis() - lastActionTimeF > cooldownTimeF) {
					this.stateIndex = 4;
					mp -= MAX_MP * 0.5;
					if (mp < 0) {
						mp = 0;
					}
					lastActionTimeF = System.currentTimeMillis(); // 마지막 사용 시간 업데이트
				}
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
		} else if (y < 700) {
			y += jumpSpeed;
		}
		if (isMovingLeft) {
			x -= moveSpeed;
			if (x < 0) {
				x = 0;
			}
		}
		if (isMovingRight) {
			x += moveSpeed;
			if (x > 1400) {
				x = 1400;
			}
		}
	}
}
=======
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
        //화면 어가지 않게 조절해주는 부분
        if (isMovingLeft) {
            x -= moveSpeed;
            if (x < 0) {
                x = 0;
            }
        }
        if (isMovingRight) {
            x += moveSpeed;
            if (x > 1400) {
                x = 1400;
            }
        }
    }
}
>>>>>>> branch 'main' of https://github.com/219156CR/Team_I.git
