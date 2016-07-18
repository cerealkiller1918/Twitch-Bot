package com.justin.main;

import com.justin.fx.SoundClip;
import com.justin.twitch.chat.TwitchChatAPI;
import com.justin.twitch.follows.TwitchFollowsAPI;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

public class Main {

	private static IrcClient irc = new IrcClient();

	public static void main(String[] args) {

		TwitchChatAPI chatAPI = new TwitchChatAPI();
		TwitchFollowsAPI followsAPI = new TwitchFollowsAPI();
		SoundClip clip = new SoundClip("/r2d2.wav");
		// String ChatterUrl
		// ="https://tmi.twitch.tv/group/user/cerealkiller1918/chatters";
		Window window = new Window();
		Lookups lookups = new Lookups(irc, window);
		Thread thread = new Thread(lookups);
		thread.setName("ChatCountLoger");
		clip.setVolume(1.0f);
		thread.start();

		while(true){
		irc.joinChannel();

<<<<<<< .mine

=======
		while (true) {
>>>>>>> .theirs
			if(!irc.isConnected()){
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
				String[] list = followsAPI.listOfFollows();
				if (list == null)
					System.out.println("Did not work");
				if (list != null) {
					for (int i = 0; i < list.length; i++) {
						System.out.println(list[i]);
					}

				}
			}

		}
	}

}
