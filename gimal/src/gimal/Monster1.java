package gimal;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;

public class Monster1 {
    private ImageIcon monsterIdleGif;
    private ImageIcon monsterMoveGif;
    private ImageIcon[] monsterAttackGifs;  // 공격 GIF 배열
    private ImageIcon monsterDeadGif;  // 죽음 GIF
    private int x = 1400;  // 시작 지점을 오른쪽으로 설정
    private int y = 650;
    private double speed = 0.1;  // 속도를 0과 1 사이로 설정
    private final int groundY = 650;

    private String currentState = "MOVE";  // 상태를 추적하는 변수 (이동 상태 기본값)

    private boolean isDead = false;  // 몬스터가 죽었는지 여부

    public Monster1() {
        monsterIdleGif = new ImageIcon("IMAGE/모래시계 기본.gif");
        monsterMoveGif = new ImageIcon("IMAGE/모래시계 기분좋음.gif");
        // 공격 GIF 여러 개를 배열로 저장
        monsterAttackGifs = new ImageIcon[] {
            new ImageIcon("IMAGE/모래시계 공격1.gif"),
            new ImageIcon("IMAGE/모래시계 공격2.gif")
        };
        monsterDeadGif = new ImageIcon("IMAGE/모래시계 죽음.gif");  // 죽음 GIF

        this.y = groundY;
    }

    public void draw(Graphics g, Screen screen) {
        if (isDead) {
            g.drawImage(monsterDeadGif.getImage(), x, y, screen);  // 죽었을 때
        } else if (currentState.equals("IDLE")) {
            g.drawImage(monsterIdleGif.getImage(), x, y, screen);  // 정지 상태
        } else if (currentState.equals("MOVE")) {
            g.drawImage(monsterMoveGif.getImage(), x, y, screen);  // 이동 상태
        } else if (currentState.equals("ATTACK")) {
            // 공격 상태일 때 랜덤으로 공격 GIF 선택
            Random rand = new Random();
            int randomIndex = rand.nextInt(monsterAttackGifs.length);  // 0 또는 1 랜덤으로 선택
            g.drawImage(monsterAttackGifs[randomIndex].getImage(), x, y, screen);  // 랜덤 공격 GIF
        }
    }

    public void move() {
        if (currentState.equals("MOVE")) {
            x += speed;  // speed에 따라 오른쪽(양수) 또는 왼쪽(음수)으로 이동
            
            // 왼쪽 벽에 부딪히면 오른쪽으로 이동
            if (x < 0) {
                x = 0;  
                speed = Math.abs(speed);  // 오른쪽으로 이동
            } 
            // 오른쪽 벽에 부딪히면 왼쪽으로 이동
            else if (x > 1470) {
                x = 1470;
                speed = -Math.abs(speed);  // 왼쪽으로 이동
            }
        }
    }

    public void randomizeState() {
        Random rand = new Random();
        int randomState = rand.nextInt(2);  // 0 또는 1을 랜덤으로 선택
        int randomDuration = rand.nextInt(3000);  // 0초에서 3초 사이의 랜덤 시간

        if (!isDead) {  // 몬스터가 죽지 않았다면
            if (randomState == 0) {
                currentState = "IDLE";  // 정지 상태
            } else {
                currentState = "MOVE";  // 이동 상태
            }
            try {
                Thread.sleep(randomDuration);  // 랜덤 시간 동안 정지 또는 이동
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 게임 루프에서 주기적으로 호출되는 메서드
    public void gameLoop() {
        while (!isDead) {
            randomizeState();  // 상태 랜덤화
            move();  // 이동 호출
            // 화면에 몬스터 그리기 (그래픽 객체가 필요합니다)
            // 예: monster.draw(g, screen);
            try {
                Thread.sleep(100);  // 100ms 마다 상태 변경
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 캐릭터와 몬스터 간의 거리를 계산해 공격을 시작하는 메서드
    public void checkAttackCondition(int characterX, int characterY) {
        // 캐릭터와 몬스터 간의 거리를 계산
        int distance = (int) Math.sqrt(Math.pow(x - characterX, 2) + Math.pow(y - characterY, 2));

        // 일정 거리 이내에 캐릭터가 오면 공격 상태로 전환
        if (distance < 100) {  // 100은 임의의 범위
            currentState = "ATTACK";  // 공격 상태로 전환
        } else {
            currentState = "MOVE";  // 공격 범위를 벗어나면 이동 상태로 전환
        }
    }

    // 몬스터가 죽었을 때 상태 변경
    public void die() {
        isDead = true;  // 몬스터 죽음
        currentState = "IDLE";  // 죽으면 정지 상태로 설정
    }

    // 몬스터가 살아있는지 확인
    public boolean isDead() {
        return isDead;
    }

    // 몬스터가 이동 상태로 시작하는 메서드
    public void startMoving() {
        currentState = "MOVE";
    }
}
