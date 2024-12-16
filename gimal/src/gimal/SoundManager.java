package gimal;

import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class SoundManager {
    private Player player;

    public void playSound(String filePath) {
        new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(filePath);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {
                player = new Player(bis);
                player.play();
            } catch (Exception e) {
                System.out.println("Error playing sound: " + e.getMessage());
            }
        }).start();
    }

    public void stopSound() {
        if (player != null) {
            player.close();
        }
    }
}
