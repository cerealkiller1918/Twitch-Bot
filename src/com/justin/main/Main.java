package com.justin.main;

import com.justin.fx.SoundClip;
import com.justin.twitch.irc.IrcClient;
import com.justin.twitch.web.api.TwitchStreamAPI;
import com.justin.window.Window;

public class Main {

	private static IrcClient irc = new IrcClient();

	public static void main(String[] args) {
		TwitchStreamAPI streamAPI = new TwitchStreamAPI();
		SoundClip clip = new SoundClip("/r2d2.wav");
		Window window = new Window();
		Lookups lookups = new Lookups(irc, window);
		Thread thread = new Thread(lookups);
		thread.setName("ChatCountLoger");
		clip.setVolume(1.0f);
		thread.start();

		while (true) {
			if (!irc.isConnected()) {
				irc.closeConnection();
				irc.restartConnection();
			}
			String defaltMessage = irc.readMessage();
			String message = irc.readChat(defaltMessage);
			String user = "";
			if (irc.getUserNameFromChat(defaltMessage) != null) {
				user = irc.getUserNameFromChat(defaltMessage);
			}

			if (message.compareTo("!hello") == 0) {
				irc.sendChatMessage("hey " + user);
			}
			if (message.compareTo("!play") == 0) {
				if (clip.isRunning())
					break;
				clip.play();
			}
			if (message.compareTo("!test") == 0) {
				System.out.println("The Stream is "+ streamAPI.isStreamOnline());
				

				
			}

		}
	}

}
