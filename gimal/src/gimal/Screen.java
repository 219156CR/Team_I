package gimal;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Screen extends Canvas implements ComponentListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5203228742370882076L;
	private Graphics bufferGraphics;
	private Image offScreen;
	private Dimension dim;
	private player player1 = new player();
	private monster monster1 = new monster();
	private int countNumber = 0;
	private int stage = 0;
	public void counting() {
		this.countNumber++;
	}
	
	public int getCount() {
		return this.countNumber;
	}
	
	public Screen(Main mainFrame) {
		monster.setPosition(450, 450, true);
		addComponentListener(this);
		this.addKeyListener(player1);
		this.addKeyListener(monster1);
		this.addKeyListener(this);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				repaint();
				counting();
			}
		}, 0, 1);
	}
	
	private void addKeyListener(monster monster1) {
		// TODO Auto-generated method stub
		
	}

	private void addKeyListener(player player1) {
		// TODO Auto-generated method stub
		
	}

	private void initBuffer() {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.bufferGraphics = this.offScreen.getGraphics();
	}
	
	@Override
	public void paint(Graphics g) {
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		if(stage == 0) {
		}
		else if(stage == 1) {
			player1.draw(bufferGraphics, this);
			monster1.draw(bufferGraphics, this);
		}
		g.drawImage(this.offScreen, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		initBuffer();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(stage == 0) {
				stage = 1;
		}
		else if(stage == 1) {
			stage = 1;
			if(e.getKeyCode() == KeyEvent.VK_A)
			{
				if(checkCollision()) {
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkCollision() {
		Rectangle rect1 = this.player1.getRect();
		Rectangle rect2 = this.monster1.getRect();
		return rect1.intersects(rect2);
	}
}
