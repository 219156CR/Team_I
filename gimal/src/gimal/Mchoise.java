package gimal;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.*;

public class Mchoise {
    private static int selectedMap = 0; // 선택된 맵 저장
    private static int selectedMonster = 0; // 선택된 몬스터 저장

    // 선택된 맵 ID를 반환하는 메서드
    public static int getSelectedMap() {
        return selectedMap;
    }
    
    public static int getSelectedMonster() {
        return selectedMonster;
    }

    // 맵 선택 프레임을 설정하는 메서드
    public static void setupMchoiseFrame(JFrame frame) {
        // 배경 이미지 설정 (프레임 크기와 맞추기 위해 리사이즈)
        ImageIcon backgroundIcon = new ImageIcon("IMAGE/배경_맵 선택.jpg");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setSize(frame.getWidth(), frame.getHeight()); // 프레임 크기에 맞게 배경 이미지 크기 설정
        backgroundLabel.setLocation(0, 0);

        // 맵 이미지 불러오기 (버튼 크기는 그대로 유지)
        ImageIcon image1 = new ImageIcon("image/버튼.PNG");
        ImageIcon image2 = new ImageIcon("image/버튼2.PNG");
        ImageIcon image3 = new ImageIcon("image/버튼.PNG");

        // 각 이미지에 대한 JLabel 생성
        JLabel imageLabel1 = new JLabel(image1);
        JLabel imageLabel2 = new JLabel(image2);
        JLabel imageLabel3 = new JLabel(image3);

        // 이미지 라벨 위치 설정
        imageLabel1.setBounds(100, 600, 200, 60);
        imageLabel2.setBounds(600, 600, 200, 60);
        imageLabel3.setBounds(1100, 600, 200, 60);

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

                // Monster1 객체 생성 및 추가
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

                // 게임 시작 프레임 생성
                JFrame gameFrame = new JFrame("Game Screen");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(1500, 1000);  // 게임 화면 크기 설정

                Screen screen = new Screen();
                gameFrame.add(screen);

                // Monster2 객체 생성 및 추가
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

                // 게임 시작 프레임 생성
                JFrame gameFrame = new JFrame("Game Screen");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(1500, 1000);  // 게임 화면 크기 설정

                Screen screen = new Screen();
                gameFrame.add(screen);

                // Monster3 객체 생성 및 추가
                screen.setMonster3(new Monster3()); // Monster3 객체를 Screen에 설정

                gameFrame.setVisible(true);
            }
        });

        // 프레임 레이아웃 설정 및 컴포넌트 추가
        frame.setLayout(null);
        frame.setContentPane(new JLayeredPane());
        frame.getContentPane().add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        frame.getContentPane().add(imageLabel1, JLayeredPane.PALETTE_LAYER);
        frame.getContentPane().add(imageLabel2, JLayeredPane.PALETTE_LAYER);
        frame.getContentPane().add(imageLabel3, JLayeredPane.PALETTE_LAYER);
    }
}

