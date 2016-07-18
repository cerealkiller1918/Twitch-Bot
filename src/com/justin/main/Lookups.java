package com.justin.main;

import com.justin.fx.SoundClip;
import com.justin.twitch.chat.TwitchChatAPI;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

public class Lookups implements Runnable {

	private IrcClient irc;
	private Window window;
	private int sleepTime = 30000;
	private SoundClip clip = new SoundClip("/r2d2.wav");

	public Lookups(IrcClient irc, Window windows) {
		this.irc = irc;
		this.window = windows;
	}

	@Override
	public void run() {
		// System.out.println("Run Has been called");
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
