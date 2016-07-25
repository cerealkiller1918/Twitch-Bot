package com.justin.fx;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundClip {

    private Clip clip;
    private FloatControl gainControl;
    private int frame = 0;
    private boolean ifPaused = false;

    public SoundClip(String path) {

        try {
            InputStream audioSrc = getClass().getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(dais);
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN))
                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        if (clip == null) return;

        stop();
        clip.setFramePosition(0);
        while (!clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        while (!clip.isRunning()) {
            clip.start();
        }
    }


    public void setVolume(float value) {
        gainControl.setValue(value);
    }

    public void pause() {
        if (clip.isRunning() && !ifPaused) {
            frame = clip.getFramePosition();
            clip.stop();
            ifPaused = true;
        } else if (!clip.isRunning() && ifPaused) {
            clip.setFramePosition(frame);
            clip.start();
            ifPaused = false;
        }
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

}
