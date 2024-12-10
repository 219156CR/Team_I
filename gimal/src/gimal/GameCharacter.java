package gimal;

import java.awt.Graphics;
import java.awt.event.KeyListener;

public abstract class GameCharacter implements KeyListener {
    public abstract void draw(Graphics g, Screen screen);
} 