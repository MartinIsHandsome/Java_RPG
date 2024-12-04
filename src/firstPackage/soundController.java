package firstPackage;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class soundController {
	Clip clip;
	URL soundURL[] = new URL[30];

	soundController() {
		soundURL[0] = getClass().getResource("/sounds/Background_Music.wav");
		soundURL[1] = getClass().getResource("/sounds/Sound.wav");
		soundURL[2] = getClass().getResource("/sounds/DoorSound.wav");
		soundURL[3] = getClass().getResource("/sounds/soundOfGettingKeys.wav");
	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {

		}
	}

	public void playFile() {

		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}
}
