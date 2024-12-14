package gimal;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster1 {
	private BufferedImage spriteSheet;
    private int[][] frameData;

    public Monster1(String spriteSheetPath) {
        try {
            spriteSheet = ImageIO.read(new File(spriteSheetPath));
            spriteSheet = TransformColorToTransparency(spriteSheet, new Color(70, 112, 104));
            parseFrameData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseFrameData() {
        frameData = new int[][] {
            {146, 115, 0, 0, 5}, // idle
            {146, 115, 480, 0, 3} // walk
        };
    }

    public void draw(Graphics g, int x, int y, String animationState) {
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
        return null;
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