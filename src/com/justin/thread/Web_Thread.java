package com.justin.thread;

import com.justin.fx.SoundClip;
import com.justin.twitch.web.api.TwitchChatAPI;
import com.justin.window.Window;

public class Web_Thread implements Runnable {

    private Window window;
    private int sleepTime = 30000;
    private SoundClip clip = new SoundClip("/r2d2.wav");


    public Web_Thread(Window windows) {

        this.window = windows;
        clip.setVolume(-30.0f);
    }


    public void run() {
        TwitchChatAPI twitchChat = new TwitchChatAPI();
        int last = 0, now = 0;
        while (true) {
            String[] list = twitchChat.getAllChatters();
            now = list.length;
            if (now > last) {
                if (clip.isRunning())
                    break;
                clip.play();

            }
            if (list != null) {
                window.ChatLogSetText("");
                for (int i = 0; i < list.length; i++) {
                    window.updateChatLog(list[i] + '\n');
                }

            }
            last = now;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
