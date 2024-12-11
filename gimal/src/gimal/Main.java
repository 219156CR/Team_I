package gimal;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;

public class Main {
    public static void main(String[] args) {
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
}
