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
	private Monster1 monster1;  // Monster1 객체 추가
	private Character1 ryu1 = new Character1();
    private Character2 ryu2 = new Character2();
    private Character3 ryu3 = new Character3();
	private int countNumber = 0;
	
	public Screen() {
		addComponentListener(this);
		
		// Monster1 객체 초기화 (이미지 경로 전달)
		monster1 = new Monster1("image/몬스터10.png");  // 실제 경로에 맞게 수정 필요
		
		// 배경 이미지 로드
		switch (Mchoise.getSelectedMap()) {
			case 1:
				backgroundImage = new ImageIcon("IMAGE/필드 배경2.jpg").getImage();
				break;
			case 2:
				backgroundImage = new ImageIcon("IMAGE/필드 배경3.jpg").getImage();
				break;
			case 3:
				backgroundImage = new ImageIcon("IMAGE/필드 배경1.jpg").getImage();
				break;
			default:
				backgroundImage = new ImageIcon("IMAGE/필드 배경1.jpg").getImage();
				break;
		}
		
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
		
		// Monster1 애니메이션 그리기 (예: "idle" 상태로 그리기)
		monster1.draw(bg, 100, 100, "idle");  // (100, 100) 위치에 "idle" 애니메이션 그리기
		
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
