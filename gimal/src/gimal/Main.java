package gimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        showIntro();
    }

    // 인트로 화면 표시
    private static void showIntro() {
        JWindow introWindow = createIntroWindow();

        Timer introTimer = new Timer(5000, e -> {
            introWindow.dispose();
            showGameFrame();
        });

        introTimer.setRepeats(false);
        introTimer.start();
    }

    // 인트로 창 생성
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

    // 게임 화면 표시
    private static void showGameFrame() {
        JFrame frame = createGameFrame();

        addBackground(frame);
        addButtons(frame);

        frame.setVisible(true);
    }

    // 게임 화면 창 생성
    private static JFrame createGameFrame() {
        JFrame frame = new JFrame("Maple Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(null);
        return frame;
    }

    // 배경 이미지 추가
    private static void addBackground(JFrame frame) {
        ImageIcon backgroundImage = new ImageIcon("image/배경화면.jpg");
        Image img = backgroundImage.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.setContentPane(backgroundLabel);
    }

    // 버튼 추가
    private static void addButtons(JFrame frame) {
        String[] buttonImages = {"image/버튼.PNG", "image/버튼2.PNG"};
        for (int i = 0; i < buttonImages.length; i++) {
            JLabel button = createButton(buttonImages[i], i, frame);
            frame.add(button);
        }
    }

    // 버튼 생성
    private static JLabel createButton(String imagePath, int index, JFrame frame) {
        JLabel buttonLabel = new JLabel(new ImageIcon(imagePath));
        buttonLabel.setBounds(100 + (index * 600), 600, 300, 60);
        buttonLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (index == 0) {
                    frame.dispose();
                    showCharacterChoiceFrame();
                } else if (index == 1) {
                    System.exit(0);
                }
            }
        });
        return buttonLabel;
    }

    // 캐릭터 선택 화면 표시
    private static void showCharacterChoiceFrame() {
        JFrame choiceFrame = new JFrame("Cchoise");
        choiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choiceFrame.setSize(1500, 1000);

        Cchoise.setupCchoiseFrame(choiceFrame);

        choiceFrame.setVisible(true);
    }
}