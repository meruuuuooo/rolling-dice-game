import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    static void playRolledDiceClip() {
        try {
            File soundFile = new File("src/resources/rollingDiceSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
