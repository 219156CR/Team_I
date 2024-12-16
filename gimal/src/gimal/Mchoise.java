package gimal;

import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Mchoise {
    private static int selectedMap = 0; // 선택된 맵 저장
    private static int selectedMonster = 0; // 선택된 몬스터 저장

    // 선택된 맵 반환
    public static int getSelectedMap() {
        return selectedMap;
    }
    
    public static int getSelectedMonster() {
        return selectedMonster;
    }

    // 맵 선택 프레임을 설정하는 메서드
    public static void setupMchoiseFrame(JFrame frame) {
        frame.setSize(1500, 1000); // 프레임 크기 설정

        // 배경 패널 생성
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("IMAGE/배경_맵 선택.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 프레임 크기에 맞춰 배경 이미지 그리기
                g.drawImage(backgroundImage, 0, 0, frame.getWidth(), frame.getHeight(), null);
            }
        };
        backgroundPanel.setLayout(null);

        // 맵 이미지 불러오기 (이미지 크기 조정)
        ImageIcon image1 = new ImageIcon(new ImageIcon("image/선택1.PNG").getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH));
        ImageIcon image2 = new ImageIcon(new ImageIcon("image/선택2.PNG").getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH));
        ImageIcon image3 = new ImageIcon(new ImageIcon("image/선택3.PNG").getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH));

        // 각 이미지에 대한 JLabel 생성
        JLabel imageLabel1 = new JLabel(image1);
        imageLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // 굵은 검은 테두리 추가
        JLabel imageLabel2 = new JLabel(image2);
        imageLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // 굵은 검은 테두리 추가
        JLabel imageLabel3 = new JLabel(image3);
        imageLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // 굵은 검은 테두리 추가

        // 이미지 라벨 위치 설정
        imageLabel1.setBounds(150, 600, 200, 60);
        imageLabel2.setBounds(650, 600, 200, 60);
        imageLabel3.setBounds(1150, 600, 200, 60);

        // 첫 번째 맵 선택 이벤트
        imageLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedMap = 1; // 첫 번째 맵 선택
                selectedMonster = 1; // 첫 번째 몬스터 선택
                frame.dispose(); // 현재 프레임 닫기

                // 게임 시작 프레임 생성
                JFrame gameFrame = new JFrame("Game Screen");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(1500, 1000);  // 게임 화면 크기 설정

                Screen screen = new Screen();
                gameFrame.add(screen);

                screen.setMonster1(new Monster1()); // Monster1 객체를 Screen에 설정

                gameFrame.setVisible(true);
            }
        });

        // 두 번째 맵 선택 이벤트
        imageLabel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedMap = 2; // 두 번째 맵 선택
                selectedMonster = 2; // 두 번째 몬스터 선택
                frame.dispose(); // 현재 프레임 닫기

                JFrame gameFrame = new JFrame("Game Screen");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(1500, 1000);  // 게임 화면 크기 설정

                Screen screen = new Screen();
                gameFrame.add(screen);

                screen.setMonster2(new Monster2()); // Monster2 객체를 Screen에 설정

                gameFrame.setVisible(true);
            }
        });

        // 세 번째 맵 선택 이벤트
        imageLabel3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedMap = 3; // 세 번째 맵 선택
                selectedMonster = 3; // 세 번째 몬스터 선택
                frame.dispose(); // 현재 프레임 닫기

                JFrame gameFrame = new JFrame("Game Screen");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(1500, 1000);  // 게임 화면 크기 설정

                Screen screen = new Screen();
                gameFrame.add(screen);

                screen.setMonster3(new Monster3()); // Monster3 객체를 Screen에 설정

                gameFrame.setVisible(true);
            }
        });

        // 배경 패널에 컴포넌트 추가
        backgroundPanel.add(imageLabel1);
        backgroundPanel.add(imageLabel2);
        backgroundPanel.add(imageLabel3);

        // 프레임 설정
        frame.setContentPane(backgroundPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
