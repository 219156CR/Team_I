package gimal;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Monster3 {
    private BufferedImage sprite;
    private Map1[] Monster_states1;
    private int stateIndex = 0;
    private int hp = 100;
    private final int MAX_HP = 100;
    private boolean isAlive = true;
    private int x = 1300;
    private Timer timer;
    
    private boolean invincible = false; // 무적 상태 플래그 추가 
    private Timer invincibilityTimer = new Timer();
    private boolean isAttacking = false;
    private Timer attackTimer = new Timer();

    private boolean movingLeft = true;  // true면 왼쪽으로, false면 오른쪽으로 이동

    private int leftBound = 0;
    private int rightBound = 1400;
    private int monsterY = 680;  // 기본 Y 위치

    private int startX = 1300;  // 초기 X 위치

    public Monster3() {
        loadImage();
        Monster_states1 = new Map1[3];
        
        // 대기 모션
        Map1 state1 = new Map1();
        Monster_states1[0] = state1;
        state1.width = 250;  // Monster3의 기본 크기
        state1.height = 215;
        state1.index_x = 0;
        state1.index_y = 0;
        state1.start_x = 0;
        state1.start_y = 0;
        state1.frame_size = 8;
        
        // 공격 모션
        state1 = new Map1();
        Monster_states1[1] = state1;
        state1.width = 250;  // Monster3의 공격 모션 크기
        state1.height = 215;
        state1.index_x = 0;
        state1.index_y = 0;
        state1.start_x = 0;
        state1.start_y = 580;
        state1.frame_size = 5;
        
        // 이동 타이머 설정
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updatePosition();
            }
        }, 0, 50);

        x = startX;
    }
    
    private void loadImage() {
        try {
            this.sprite = ImageIO.read(new File("image/몬스터3.png"));
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
                int r = (rgb & 0xFF0000) >> 16;
                int g = (rgb & 0xFF00) >> 8;
                int b = (rgb & 0xFF);
                if (r == r1 && g == g1 && b == b1) {
                    return rgb & 0xFFFFFF;
                }
                return rgb;
            }
        };
        
        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        Image img = Toolkit.getDefaultToolkit().createImage(ip);
        BufferedImage dest = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dest.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return dest;
    }

    private void drawMonster(Map1 state, Graphics g, Screen screen, int x, int y) {
        int ix = state.width * state.index_x + state.start_x;
        int iy = state.height * state.index_y + state.start_y;
        
        g.drawImage(sprite, x, y, 
                x + state.width, y + state.height,
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

    public void updatePosition() {
        if (isAlive && !isAttacking) {
            if (movingLeft) {
                x -= 1;
                if (x <= leftBound) {
                    x = leftBound;
                    movingLeft = false;
                }
            } else {
                x += 1;
                if (x >= rightBound - Monster_states1[stateIndex].width) {  // 몬스터 크기를 고려하여 조정
                    x = rightBound - Monster_states1[stateIndex].width;
                    movingLeft = true;
                }
            }
        }
    }

    public void draw(Graphics g, Screen screen) {
        if (!isAlive) return;
        
        drawMonster(Monster_states1[stateIndex], g, screen, x, monsterY);
        
        // 체력바 그리기
        g.setColor(Color.RED);
        g.fillRect(x, monsterY - 20, (int)(hp / (float)MAX_HP * Monster_states1[stateIndex].width), 10);
        g.setColor(Color.BLACK);
        g.drawRect(x, monsterY - 20, Monster_states1[stateIndex].width, 10);
    }

    public Map1 getState() {
        return Monster_states1[stateIndex];
    }

    public int getHp() {
        return hp;
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, monsterY, 100, 100);
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
    
    public void takeDamage(int damage) {
        if (!invincible && isAlive) {
            hp -= damage;
            if (hp <= 0) {
                hp = 0;
                isAlive = false;
                timer.cancel();
                invincibilityTimer.cancel();
                
                Screen.getInstance().checkGameClear();
            } else {
                System.out.println("몬스터 HP 감소: " + hp);
                invincible = true;
                invincibilityTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        invincible = false;
                    }
                }, 1000);
            }
        }
    }
    public void checkCollision(Rectangle characterHitbox) {
        if (this.getHitbox().intersects(characterHitbox)) {
            startAttack();  // 충돌 시 공격 시작
        }
    }

    public void startAttack() {
        if (!isAttacking && isAlive) {
            isAttacking = true;
            stateIndex = 1;  // 공격 모션으로 변경
            
            // 3초 후 공격 상태 해제
            attackTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isAttacking = false;
                    stateIndex = 0;  // 기본 모션으로 복귀
                }
            }, 3000);  // 3초 동안 공격 모션 유지
        }
    }

    public void setPlatformBounds(int left, int right) {
        this.leftBound = left;
        this.rightBound = right;
        this.x = left + 50;  // 발판의 시작 지점���서 약간 떨어진 부분에서 시작
    }

    public void setY(int y) {
        this.monsterY = y;
    }
}