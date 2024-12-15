package gimal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Monster1 {
    private BufferedImage spriteSheet;
    private int[][] frameData;
    private Rectangle hitbox;
    private int maxHP;
    private int currentHP;
    private boolean isAlive;
    private int x;
    private int y;
    private int speed;
    private Random random;

    public Monster1(String spriteSheetPath) {
        this(spriteSheetPath, 0, 0, 146, 115, 100);
    }

    public Monster1(String spriteSheetPath, int x, int y, int width, int height, int maxHP) {
        try {
            spriteSheet = ImageIO.read(new File(spriteSheetPath));
            spriteSheet = TransformColorToTransparency(spriteSheet, new Color(70, 112, 104));
            parseFrameData();

            this.hitbox = new Rectangle(x, y, width, height);
            this.maxHP = maxHP;
            this.currentHP = maxHP;
            this.isAlive = true;
            this.x = x;
            this.y = y;
            this.speed = 5;
            this.random = new Random();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseFrameData() {
        frameData = new int[][] {
            {146, 115, 0, 0, 5},
            {146, 115, 480, 0, 3}
        };
    }

    public void draw(Graphics g, int x, int y, String animationState) {
        hitbox.x = x;
        hitbox.y = y;
        this.x = x;
        this.y = y;

        int[] frameInfo = getFrameInfo(animationState);
        int frameIndex = (int)(System.currentTimeMillis() / 100) % frameInfo[4];

        int sx = frameInfo[2] + frameIndex * frameInfo[0];
        int sy = frameInfo[3];

        g.drawImage(spriteSheet, x, y, x + frameInfo[0], y + frameInfo[1],
                sx, sy, sx + frameInfo[0], sy + frameInfo[1], null);
    }

    private int[] getFrameInfo(String animationState) {
        for (int[] frame : frameData) {
            if (animationState.equals("idle")) {
                return frame;
            } else if (animationState.equals("walk")) {
                return frame;
            }
        }
        return frameData[0];
    }

    public boolean checkCollision(Monster1 otherMonster) {
        return this.hitbox.intersects(otherMonster.hitbox);
    }

    public boolean checkCollision(Rectangle otherObject) {
        return this.hitbox.intersects(otherObject);
    }

    public void move(int dx, int dy) {
        if (isAlive) {
            x += dx * speed;
            y += dy * speed;
            hitbox.x = x;
            hitbox.y = y;
        }
    }

    public void randomMove() {
        if (isAlive) {
            int dx = random.nextInt(3) - 1; // -1, 0, 1 중 하나
            int dy = random.nextInt(3) - 1; // -1, 0, 1 중 하나
            move(dx, dy);
        }
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private BufferedImage TransformColorToTransparency(BufferedImage image, Color color) {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        for (int i = 0; i < pixels.length; i++) {
            if ((pixels[i] >> 16 & 0xFF) == color.getRed()
                && (pixels[i] >> 8 & 0xFF) == color.getGreen()
                && (pixels[i] & 0xFF) == color.getBlue()) {
                pixels[i] &= 0xFFFFFF;
            }
        }

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        newImage.setRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        return newImage;
    }
}
