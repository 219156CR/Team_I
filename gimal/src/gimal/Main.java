package gimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        // 인트로 영상 크기 설정
        int introWidth = 800;  // 영상의 가로 크기
        int introHeight = 600; // 영상의 세로 크기

        // 인트로 영상 창 생성
        JWindow introWindow = new JWindow();
        introWindow.setSize(introWidth, introHeight);
        introWindow.setLayout(new BorderLayout());

        // 인트로 영상 설정
        ImageIcon introImage = new ImageIcon("image/gamestart.gif");
        JLabel introLabel = new JLabel(introImage);
        introWindow.add(introLabel, BorderLayout.CENTER);

        // 화면 중앙에 배치
        introWindow.setLocationRelativeTo(null);
        introWindow.setVisible(true);

        // 일정 시간 후 인트로 종료 및 게임 화면 전환
        Timer introTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introWindow.dispose(); // 인트로 창 닫기

                // 게임 화면 생성
                JFrame frame = new JFrame("Maple Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1500, 1000);
                frame.setLayout(null);

                // 배경 이미지 설정 및 크기 조정
                ImageIcon backgroundImage = new ImageIcon("image/배경화면.jpg");
                Image img = backgroundImage.getImage();
                Image scaledImg = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledBackgroundImage = new ImageIcon(scaledImg);

                JLabel backgroundLabel = new JLabel(scaledBackgroundImage);
                backgroundLabel.setBounds(0, 0, 1500, 1000);
                frame.setContentPane(backgroundLabel);

                // 맵을 표시할 Map1 객체 생성
                Map1 map = new Map1();
                map.width = 1200; // 맵의 가로 크기
                map.height = 800; // 맵의 세로 크기
                map.start_x = 150; // 맵 시작 X 좌표
                map.start_y = 100; // 맵 시작 Y 좌표

                // 맵 이미지 설정
                ImageIcon mapImage = new ImageIcon("image/필드배경1.jpg"); // 맵 이미지 파일 경로
                JLabel mapLabel = new JLabel(mapImage);
                mapLabel.setBounds(map.start_x, map.start_y, map.width, map.height);

                // 이미지 아이콘들 설정
                String[] buttonImages = {"image/버튼.PNG", "image/버튼2.PNG"};
                JLabel[] buttonLabels = new JLabel[buttonImages.length];

                for (int i = 0; i < buttonImages.length; i++) {
                    ImageIcon buttonImage = new ImageIcon(buttonImages[i]);
                    buttonLabels[i] = new JLabel(buttonImage);
                    buttonLabels[i].setBounds(100 + (i * 600), 600, 300, 60); // 버튼 위치 조정
                    final int index = i;

                    buttonLabels[i].addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            if (index == 0) {
                                // 첫 번째 버튼 클릭 시: 다른 화면으로 전환
                                frame.dispose();
                                JFrame choiceFrame = new JFrame("Cchoise");
                                choiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                choiceFrame.setSize(1500, 1000);
                                Cchoise.setupCchoiseFrame(choiceFrame);
                                choiceFrame.setVisible(true);
                            } else if (index == 1) {
                                // 두 번째 버튼 클릭 시: 게임 종료
                                System.exit(0);
                            }
                        }
                    });
                }

                // 프레임에 버튼과 맵 추가
                frame.add(mapLabel); // 맵 추가
                for (JLabel buttonLabel : buttonLabels) {
                    frame.add(buttonLabel); // 버튼 추가
                }

                // 프레임 표시
                frame.setVisible(true); // 게임 화면 표시
            }
        });

        // 타이머 실행
        introTimer.setRepeats(false); // 한 번만 실행
        introTimer.start();
    }
}






