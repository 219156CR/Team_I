package gimal;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    private static Timer gameTimer;
    private static int timeLeft = 60; // 게임 시간 (초)
    private static int score = 0;    // 점수 기본값
    private static SoundManager soundManager = new SoundManager(); // 사운드 매니저 추가

    public static void main(String[] args) {
        showIntro();
    }

    private static void showIntro() {
        JWindow introWindow = createIntroWindow();

        Timer introTimer = new Timer(5000, e -> {
            introWindow.dispose();
            soundManager.playSound("sound/메이플스토리 BGM - 신버전 로그인.wav"); // 인트로 종료 후 소리 재생
            showGameFrame();
        });

        introTimer.setRepeats(false);
        introTimer.start();
    }

    private static JWindow createIntroWindow() {
        JWindow introWindow = new JWindow();
        introWindow.setSize(800, 600);
        introWindow.setLayout(new BorderLayout());
        introWindow.setLocationRelativeTo(null);

        JLabel introLabel = new JLabel(new ImageIcon("image/gamestart.gif"));
        introWindow.add(introLabel, BorderLayout.CENTER);
        introWindow.setVisible(true);

        return introWindow;
    }

    private static void showGameFrame() {
        JFrame frame = createGameFrame();

        addBackground(frame);
        addButtons(frame);

        frame.setVisible(true);
    }

    private static JFrame createGameFrame() {
        JFrame frame = new JFrame("Maple Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(null);
        return frame;
    }

    private static void addBackground(JFrame frame) {
        ImageIcon backgroundImage = new ImageIcon("image/배경화면.jpg");
        Image img = backgroundImage.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.setContentPane(backgroundLabel);
    }

    private static void addButtons(JFrame frame) {
        String[] buttonImages = {"image/버튼.PNG", "image/버튼2.PNG"};
        for (int i = 0; i < buttonImages.length; i++) {
            JLabel button = createButton(buttonImages[i], i, frame);
            frame.add(button);
        }
    }

    private static JLabel createButton(String imagePath, int index, JFrame frame) {
        JLabel buttonLabel = new JLabel(new ImageIcon(imagePath));
        buttonLabel.setBounds(100 + (index * 600), 600, 300, 60);
        
        // 검은 테두리 추가
        buttonLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 두께 2의 검은 테두리

        buttonLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (index == 0) {
                    frame.dispose();
                    showCharacterChoiceFrame();
                } else if (index == 1) {
                    endGame(frame, false); // 게임 오버 처리
                }
            }
        });
        return buttonLabel;
    }

    private static void showCharacterChoiceFrame() {
        JFrame choiceFrame = new JFrame("Cchoise");
        choiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choiceFrame.setSize(1500, 1000);

        Cchoise.setupCchoiseFrame(choiceFrame);

        choiceFrame.setVisible(true);
    }

    public static void endGame(JFrame frame, boolean isClear) {
        if (isClear) {
            score = timeLeft * 10; // 클리어 시 남은 시간 × 10
            JOptionPane.showMessageDialog(frame, "게임 클리어! 점수: " + score);
        } else {
            score = 0; // 게임 오버 시 점수 0점
            JOptionPane.showMessageDialog(frame, 
                "게임 오버!\n캐릭터 사망...\n최종 점수: " + score);
        }

        frame.dispose(); // 프레임 닫기
        System.exit(0);  // 애플리케이션 종료
    }

    public static void clearGame(JFrame frame) {
        int remainingTime = Screen.getTimeLeft(); // Screen에서 남은 시간 가져오기
        int finalScore = remainingTime * 100; // 남은 시간 * 100으로 점수 계산
        JOptionPane.showMessageDialog(frame, 
            "게임 클리어!\n남은 시간: " + remainingTime + "초\n최종 점수: " + finalScore);
        frame.dispose();
        System.exit(0);
    }
}