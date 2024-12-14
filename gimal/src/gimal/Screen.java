package gimal;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Screen extends Canvas implements ComponentListener {
	
	private Graphics bg;
	private Image offScreen;
	private Image backgroundImage;
	private Dimension dim;
	private Character1 ryu1 = new Character1();
    private Character2 ryu2 = new Character2();
    private Character3 ryu3 = new Character3();
	private int countNumber = 0;
	
	public Screen() {
		addComponentListener(this);
		// 배경 이미지 로드
		backgroundImage = new ImageIcon("IMAGE/필드 배경1.JPG").getImage();
		
		// 선택된 캐릭터에 따라 키 리스너 추가
		switch (Cchoise.getSelectedCharacter()) {
			case 1:
				addKeyListener(ryu1);
				break;
			case 2:
				addKeyListener(ryu2);
				break;
			case 3:
				addKeyListener(ryu3);
				break;
			default:
				// 선택된 캐릭터가 없을 경우 처리
				break;
		}
		setFocusable(true);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				repaint();
				counting();
			}
		}, 0, 1);
	}
	public void counting() {
		this.countNumber++;
	}
	
	public int getCount() {
		return this.countNumber;
	}
	
	private void initBuffer() {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.bg = this.offScreen.getGraphics();
	}
	
	@Override
	public void paint(Graphics g) {
		bg.clearRect(0, 0, dim.width, dim.height);
		// 배경 이미지 그리기
		bg.drawImage(backgroundImage, 0, 0, dim.width, dim.height, this);
		
		// 캐릭터 선택에 따라 그리기
		switch (Cchoise.getSelectedCharacter()) {
			case 1:
				ryu1.draw(bg, this);
				break;
			case 2:
				ryu2.draw(bg, this);
				break;
			case 3:
				ryu3.draw(bg, this);
				break;
			default:
				// 선택된 캐릭터가 없을 경우 처리
				break;
		}
		g.drawImage(offScreen, 0, 0, this);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		initBuffer();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}