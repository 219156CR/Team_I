package gimal;

import javax.swing.*;
import java.awt.event.*;

public class Cchoise {
    private static int selectedCharacter = 0; // 선택된 캐릭터 저장

    // 선택된 캐릭터 ID를 반환하는 메서드
    public static int getSelectedCharacter() {
        return selectedCharacter;
    }

    // 캐릭터 선택 프레임을 설정하는 메서드
    public static void setupCchoiseFrame(JFrame frame) {
        // 캐릭터 이미지 불러오기
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
        
        // 첫 번째 캐릭터 선택 이벤트
        imageLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedCharacter = 1; // 첫 번째 캐릭터 선택
                frame.dispose(); // 현재 프레임 닫기
                
                // 몬스터 선택 프레임 생성
                JFrame mchoiseFrame = new JFrame("Monster Choice");
                mchoiseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mchoiseFrame.setSize(1500, 1000);
                
                // 몬스터 선택 프레임 설정
                Mchoise.setupMchoiseFrame(mchoiseFrame);
                
                // 프레임 표시
                mchoiseFrame.setVisible(true);
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
                mchoiseFrame.setSize(1500, 1000);
                
                // 몬스터 선택 프레임 설정
                Mchoise.setupMchoiseFrame(mchoiseFrame);
                
                // 프레임 표시
                mchoiseFrame.setVisible(true);
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
                mchoiseFrame.setSize(1500, 1000);
                
                // 몬스터 선택 프레임 설정
                Mchoise.setupMchoiseFrame(mchoiseFrame);
                
                // 프레임 표시
                mchoiseFrame.setVisible(true);
            }
        });
        
        // 프레임 레이아웃 및 컴포넌트 추가
        frame.setLayout(null);
        frame.add(imageLabel1);
        frame.add(imageLabel2);
        frame.add(imageLabel3);
    }
}
