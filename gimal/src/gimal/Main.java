package gimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        // 인트로 화면 표시
        ImageIcon introImage = new ImageIcon("image/intro.gif");
        JLabel introLabel = new JLabel(introImage);
        introLabel.setBounds(0, 0, 1500, 1000);

        // 인트로 영상 창 설정
        JWindow introWindow = new JWindow();
        introWindow.setSize(1500, 1000);
        introWindow.setLayout(new BorderLayout());
        introWindow.add(introLabel, BorderLayout.CENTER);
        introWindow.setLocationRelativeTo(null); // 화면 중앙에 위치
        introWindow.setVisible(true);

        // 일정 시간 후 인트로 화면 종료 및 게임 화면으로 전환
        Timer introTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 인트로 화면을 닫고 게임 화면으로 전환
                introWindow.dispose();

                // 게임 프레임 설정
                JFrame frame = new JFrame("Maple Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1500, 1000);
                frame.setLayout(null);

                // 배경 이미지 설정 및 크기 조정
                ImageIcon backgroundImage = new ImageIcon("image/배경화면.jpg");
                Image img = backgroundImage.getImage(); // ImageIcon에서 Image 객체 얻기
                Image scaledImg = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH); // 크기 조정
                ImageIcon scaledBackgroundImage = new ImageIcon(scaledImg); // 크기가 조정된 이미지로 ImageIcon 생성

                JLabel backgroundLabel = new JLabel(scaledBackgroundImage);
                backgroundLabel.setBounds(0, 0, 1500, 1000); // 배경 이미지를 JLabel에 설정
                frame.setContentPane(backgroundLabel);

                // 이미지 아이콘들 설정
                ImageIcon image = new ImageIcon("image/1.PNG");
                ImageIcon image2 = new ImageIcon("image/2.PNG");

                JLabel imageLabel = new JLabel(image);
                JLabel imageLabel2 = new JLabel(image2);

                imageLabel.setBounds(100, 600, 200, 60); // 첫 번째 버튼의 위치
                imageLabel2.setBounds(700, 600, 200, 60); // 두 번째 버튼의 위치

                // 첫 번째 버튼 클릭 시 캐릭터 선택 화면으로 이동
                imageLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        frame.dispose(); // 현재 프레임을 닫음

                        // 캐릭터 선택 프레임 생성
                        JFrame cchoiseFrame = new JFrame("Cchoise");
                        cchoiseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        cchoiseFrame.setSize(1500, 1000);

                        Cchoise.setupCchoiseFrame(cchoiseFrame); // 캐릭터 선택 화면 설정

                        cchoiseFrame.setVisible(true); // 캐릭터 선택 화면 표시
                    }
                });

                // 두 번째 버튼 클릭 시 프로그램 종료
                imageLabel2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.exit(0); // 프로그램 종료
                    }
                });

                // 이미지 레이블들을 프레임에 추가
                frame.add(imageLabel);
                frame.add(imageLabel2);
                frame.setVisible(true); // 프레임 표시
            }
        });

        // 타이머 설정: 5초 후에 인트로 영상 종료
        introTimer.setRepeats(false); // 한 번만 실행
        introTimer.start(); // 타이머 시작
    }
}

