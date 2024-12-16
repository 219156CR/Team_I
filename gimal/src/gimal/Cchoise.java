package gimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cchoise {
    private static int selectedCharacter = 0; // 선택된 캐릭터 저장

    // 선택된 캐릭터 ID를 반환하는 메서드
    public static int getSelectedCharacter() {
        return selectedCharacter;
    }

    // 캐릭터 선택 프레임을 설정하는 메서드
    public static void setupCchoiseFrame(JFrame frame) {
        // 프레임 크기 설정
        frame.setSize(1500, 1000); // 프레임 크기 설정

        // 배경 이미지 설정 (프레임 크기에 맞게 크기 조정)
        ImageIcon backgroundIcon = new ImageIcon("image/캐릭터 선택 배경화면.jpg");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH); // 배경 크기 조정
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));

        // 캐릭터 이미지 불러오기
        ImageIcon image1 = new ImageIcon("image/버튼.PNG");
        ImageIcon image2 = new ImageIcon("image/버튼2.PNG");
        ImageIcon image3 = new ImageIcon("image/버튼.PNG");

        // 각 이미지에 대한 JLabel 생성 (버튼 이미지 크기는 변경하지 않음)
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

        // 첫 번째 캐릭터 선택 이벤트
        imageLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedCharacter = 1; // 첫 번째 캐릭터 선택
                frame.dispose(); // 현재 프레임 닫기
                // 몬스터 선택 프레임 생성
                JFrame mchoiseFrame = new JFrame("Monster Choice");
                mchoiseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mchoiseFrame.setSize(1500, 1000); // 몬스터 선택 프레임 설정
                Mchoise.setupMchoiseFrame(mchoiseFrame); // 프레임 설정
                mchoiseFrame.setVisible(true); // 프레임 표시
            }
        });

        // 두 번째 캐릭터 선택 이벤트
        imageLabel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedCharacter = 2; // 두 번째 캐릭터 선택
                frame.dispose(); // 현재 프레임 닫기
                // 몬스터 선택 프레임 생성
                JFrame mchoiseFrame = new JFrame("Monster Choice");
                mchoiseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mchoiseFrame.setSize(1500, 1000); // 몬스터 선택 프레임 설정
                Mchoise.setupMchoiseFrame(mchoiseFrame); // 프레임 설정
                mchoiseFrame.setVisible(true); // 프레임 표시
            }
        });

        // 세 번째 캐릭터 선택 이벤트
        imageLabel3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedCharacter = 3; // 세 번째 캐릭터 선택
                frame.dispose(); // 현재 프레임 닫기
                // 몬스터 선택 프레임 생성
                JFrame mchoiseFrame = new JFrame("Monster Choice");
                mchoiseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mchoiseFrame.setSize(1500, 1000); // 몬스터 선택 프레임 설정
                Mchoise.setupMchoiseFrame(mchoiseFrame); // 프레임 설정
                mchoiseFrame.setVisible(true); // 프레임 표시
            }
        });

        // 프레임 레이아웃 및 컴포넌트 추가
        frame.setLayout(null); // 레이아웃을 null로 설정하여 자유롭게 위치 지정
        frame.setContentPane(backgroundLabel); // 배경 이미지를 컨텐트 팬으로 설정
        frame.add(imageLabel1);
        frame.add(imageLabel2);
        frame.add(imageLabel3);
        frame.setVisible(true); // 프레임 표시
    }
}