package gimal;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster1 {
    private int x = 450;
    private int y = 450;
    private int width = 90;
    private int height = 105;
    
    public void draw(Graphics g, Screen screen) {
        // 몬스터 그리기 로직 구현
        g.fillRect(x, y, width, height);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
