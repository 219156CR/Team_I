package gimal;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends Canvas implements ComponentListener {

	private Graphics bg;
	private Image offScreen;
	private Image backgroundImage;
	private Dimension dim;
	private Monster1 monster1;
	private Monster1 platformMonster;
	private Monster2 monster2;
	private Monster3 monster3;
	private Character1 character1 = new Character1();
    private Character2 character2 = new Character2();
    private Character3 character3 = new Character3();
	private int countNumber = 0;
	private static int timeLeft = 60; // 60초
	private Timer gameTimer;
	private static Screen instance;
	private Rectangle platform;
	private Rectangle platform2;
	private Rectangle platform3;
	private Rectangle platform4;
	private Image platformImage;

	public Screen() {
		instance = this;
		addComponentListener(this);

		// 배경 이미지 로드
		switch (Mchoise.getSelectedMap()) {
			case 1:
				backgroundImage = new ImageIcon("IMAGE/필드 배경1.jpg").getImage();
				break;
			case 2:
				backgroundImage = new ImageIcon("IMAGE/필드 배경2.jpg").getImage();
				break;
			case 3:
				backgroundImage = new ImageIcon("IMAGE/필드 배경3.jpg").getImage();
				break;
			default:
				backgroundImage = new ImageIcon("IMAGE/필드 배경1.jpg").getImage();
				break;
		}

		// 선택된 캐릭터에 따라 키 리스너 추가
		switch (Cchoise.getSelectedCharacter()) {
			case 1:
				addKeyListener(character1);
				break;
			case 2:
				addKeyListener(character2);
				break;
			case 3:
				addKeyListener(character3);
				break;
			default:
				break;
		}

		setFocusable(true);

		// 게임 타이머 시작
		startGameTimer();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				repaint();
				counting();
			}
		}, 0, 1);

		// 발판 이미지 로드
		try {
			platformImage = new ImageIcon("image/발판.PNG").getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 발판 생성
		platform = new Rectangle(500, 600, 300, 30);
		platform2 = new Rectangle(1100, 600, 300, 30);
		platform3 = new Rectangle(0, 600, 300, 30);
		platform4 = new Rectangle(50, 400, 900, 30);
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

	private void checkCollisions() {
		Object activeCharacter = null;
		switch (Cchoise.getSelectedCharacter()) {
			case 1:
				activeCharacter = character1;
				break;
			case 2:
				activeCharacter = character2;
				break;
			case 3:
				activeCharacter = character3;
				break;
		}
		
		// 캐릭터 타입 체크 및 처리
		if (activeCharacter instanceof Character1) {
			handleCharacterCollisions((Character1) activeCharacter);
		} else if (activeCharacter instanceof Character2) {
			handleCharacterCollisions((Character2) activeCharacter);
		} else if (activeCharacter instanceof Character3) {
			handleCharacterCollisions((Character3) activeCharacter);
		}
	}

	private void handleCharacterCollisions(Character1 character) {
	    // monster1 또는 monster2 또는 monster3와의 충돌 체크
	    if (Mchoise.getSelectedMap() == 1 && monster1 != null && monster1.isAlive()) {
	        checkMonsterCollision(character, monster1);
	    } else if (Mchoise.getSelectedMap() == 2 && monster2 != null) {
	        Rectangle characterHitbox = character.getHitbox();
	        Rectangle monsterHitbox = monster2.getHitbox();

	        if (characterHitbox.intersects(monsterHitbox)) {
	            monster2.checkCollision(characterHitbox);
	            character.takeDamage(20);
	        }

	        if (character.isAttacking()) {
	            Rectangle attackHitbox = character.getAttackHitbox();
	            if (attackHitbox != null && attackHitbox.intersects(monsterHitbox)) {
	                monster2.takeDamage(character.getAttackDamage());
	            }
	        }
	    } else if (Mchoise.getSelectedMap() == 3 && monster3 != null) { // monster3 추가
	        Rectangle characterHitbox = character.getHitbox();
	        Rectangle monsterHitbox = monster3.getHitbox();

	        if (characterHitbox.intersects(monsterHitbox)) {
	            monster3.checkCollision(characterHitbox);
	            character.takeDamage(20);
	        }

	        if (character.isAttacking()) {
	            Rectangle attackHitbox = character.getAttackHitbox();
	            if (attackHitbox != null && attackHitbox.intersects(monsterHitbox)) {
	                monster3.takeDamage(character.getAttackDamage());
	            }
	        }
	    }

		// platformMonster 충돌 체크
		if (platformMonster != null && platformMonster.isAlive()) {
			checkMonsterCollision(character, platformMonster);
		}
	}

	// Character2를 위한 오버로드된 메소드
	private void handleCharacterCollisions(Character2 character) {
		// monster1 또는 monster2와의 충돌 체크
		if (Mchoise.getSelectedMap() == 1 && monster1 != null && monster1.isAlive()) {
			checkMonsterCollision(character, monster1);
		} else if (Mchoise.getSelectedMap() == 2 && monster2 != null) {
			Rectangle characterHitbox = character.getHitbox();
			Rectangle monsterHitbox = monster2.getHitbox();
			
			if (characterHitbox.intersects(monsterHitbox)) {
				monster2.checkCollision(characterHitbox);
				character.takeDamage(20);
			}
			
			if (character.isAttacking()) {
				Rectangle attackHitbox = character.getAttackHitbox();
				if (attackHitbox != null && attackHitbox.intersects(monsterHitbox)) {
					monster2.takeDamage(character.getAttackDamage());
				}
			}
		}

		// platformMonster 충돌 체크
		if (platformMonster != null && platformMonster.isAlive()) {
			checkMonsterCollision(character, platformMonster);
		}
	}

	// Character3를 위한 오버로드된 메소드도 동일하게 추가
	private void handleCharacterCollisions(Character3 character) {
		// 위와 동일한 코드
	}

	// Monster와의 충돌 체크를 위한 헬퍼 메소드
	private void checkMonsterCollision(Character1 character, Monster1 monster) {
		Rectangle characterHitbox = character.getHitbox();
		Rectangle monsterHitbox = monster.getHitbox();
		
		if (characterHitbox.intersects(monsterHitbox)) {
			monster.checkCollision(characterHitbox);
			character.takeDamage(20);
		}
		
		if (character.isAttacking()) {
			Rectangle attackHitbox = character.getAttackHitbox();
			if (attackHitbox != null && attackHitbox.intersects(monsterHitbox)) {
				monster.takeDamage(character.getAttackDamage());
			}
		}
	}

	// Character2를 위한 checkMonsterCollision 오버로드
	private void checkMonsterCollision(Character2 character, Monster1 monster) {
		Rectangle characterHitbox = character.getHitbox();
		Rectangle monsterHitbox = monster.getHitbox();
		
		if (characterHitbox.intersects(monsterHitbox)) {
			monster.checkCollision(characterHitbox);
			character.takeDamage(20);
		}
		
		if (character.isAttacking()) {
			Rectangle attackHitbox = character.getAttackHitbox();
			if (attackHitbox != null && attackHitbox.intersects(monsterHitbox)) {
				monster.takeDamage(character.getAttackDamage());
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		bg.clearRect(0, 0, dim.width, dim.height);
		// 배경 이미지 그리기
		bg.drawImage(backgroundImage, 0, 0, dim.width, dim.height, this);
		
		// 발판 그리기
		bg.drawImage(platformImage, platform.x, platform.y, platform.width, platform.height, this);
		bg.drawImage(platformImage, platform2.x, platform2.y, platform2.width, platform2.height, this);
		bg.drawImage(platformImage, platform3.x, platform3.y, platform3.width, platform3.height, this);
		bg.drawImage(platformImage, platform4.x, platform4.y, platform4.width, platform4.height, this);
		
		// 타이머 그리기
		bg.setFont(new Font("Arial", Font.BOLD, 40));
		bg.setColor(Color.WHITE);
		bg.drawString("Time: " + timeLeft, dim.width/2 - 100, 50);
		
		checkCollisions();
		// 캐릭터 선택에 따라 그리기
		switch (Cchoise.getSelectedCharacter()) {
			case 1:
				character1.draw(bg, this);
				break;
			case 2:
				character2.draw(bg, this);
				break;
			case 3:
				character3.draw(bg, this);
				break;
			default:
				break;
		}

		// 몬스터 선택에 따라 그리기
		if (monster1 != null) {
			monster1.draw(bg, this);
		}
		if (platformMonster != null) {
			platformMonster.draw(bg, this);
		}
		if (monster2 != null) {
			monster2.draw(bg, this);
		}
		if (monster3 != null) {
			monster3.draw(bg, this);
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

	// Monster1 설정 메서드
	public void setMonster1(Monster1 monster) {
		this.monster1 = monster;
		// 두 번째 몬스터 생성 및 설정
		this.platformMonster = new Monster1();
		this.platformMonster.setPlatformBounds(platform4.x, platform4.x + platform4.width);
		this.platformMonster.setY(platform4.y - 100);  // 발판 위에 위치하도록 설정
		
		// Character1에 몬스터들 설정
		if (character1 != null) {
			character1.setMonster1(monster);
			character1.setPlatformMonster(platformMonster);
		} else if (character2 != null) {
			character2.setMonster1(monster);
			character2.setPlatformMonster(platformMonster);
		} else if (character3 != null) {
			character3.setMonster1(monster);
			character3.setPlatformMonster(platformMonster);
		}
	}

	// Monster2 설정 메서드 수정
	public void setMonster2(Monster2 monster) {
		this.monster2 = monster;
		// 발판 위 몬스터 설정
		this.platformMonster = new Monster1() {
			@Override
			public void loadImage() {
				try {
					this.sprite = ImageIO.read(new File("image/몬스터2.png"));
					this.sprite = TransformColorToTransparency(sprite, new Color(70, 112, 104));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		this.platformMonster.setPlatformBounds(platform4.x, platform4.x + platform4.width);
		this.platformMonster.setY(platform4.y - 100);
		
		// Character에 platformMonster 설정
		if (character1 != null) {
			character1.setPlatformMonster(platformMonster);
		} else if (character2 != null) {
			character2.setPlatformMonster(platformMonster);
		} else if (character3 != null) {
			character3.setPlatformMonster(platformMonster);
		}
	}

	// Monster3 설정 메서드 수정
	public void setMonster3(Monster3 monster) {
		this.monster3 = monster;
		// 발판 위 몬스터 설정
		this.platformMonster = new Monster1() {
			@Override
			public void loadImage() {
				try {
					this.sprite = ImageIO.read(new File("image/몬스터3.png"));
					this.sprite = TransformColorToTransparency(sprite, new Color(70, 112, 104));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		this.platformMonster.setPlatformBounds(platform4.x, platform4.x + platform4.width);
		this.platformMonster.setY(platform4.y - 100);
		
		// Character에 platformMonster 설정
		if (character1 != null) {
			character1.setPlatformMonster(platformMonster);
		} else if (character2 != null) {
			character2.setPlatformMonster(platformMonster);
		} else if (character3 != null) {
			character3.setPlatformMonster(platformMonster);
		}
	}

	// 타이머 시작 메소드 추가
	private void startGameTimer() {
		gameTimer = new Timer();
		
		gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				timeLeft--;
				if (timeLeft >= 0) {
					repaint(); // 화면 갱신
				}
				if (timeLeft <= 0) {
					gameTimer.cancel();
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Screen.this);
					Main.endGame(frame, false);
				}
			}
		}, 1000, 1000);
	}

	// 인스턴스 getter 추가
	public static Screen getInstance() {
		return instance;
	}

	// timeLeft getter 추가
	public static int getTimeLeft() {
		return timeLeft;
	}

	// 발판 getter 메소드 추가
	public Rectangle[] getPlatforms() {
		return new Rectangle[] { platform, platform2, platform3, platform4 };
	}

	// Screen 클래스에 메소드 추가
	private boolean checkAllMonstersDefeated() {
		switch (Mchoise.getSelectedMap()) {
			case 1:
				return (monster1 != null && !monster1.isAlive() && 
						platformMonster != null && !platformMonster.isAlive());
			case 2:
				return (monster2 != null && !monster2.isAlive() && 
						platformMonster != null && !platformMonster.isAlive());
			case 3:
				return (monster3 != null && !monster3.isAlive() && 
						platformMonster != null && !platformMonster.isAlive());
			default:
				return false;
		}
	}

	// 게임 클리어 체크 메소드 추가
	public void checkGameClear() {
		if (checkAllMonstersDefeated()) {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				Main.clearGame(frame);
			}
		}
	}
}