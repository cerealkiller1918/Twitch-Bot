package com.justin.thread;

import com.justin.twitch.irc.ChatCommand;
import com.justin.twitch.irc.IrcClient;

public class Chat_Thread implements Runnable {

	private IrcClient irc = new IrcClient();
	private ChatCommand chatCommand = new ChatCommand();

	public Chat_Thread() {

	}

	@Override
	public void run() {
		while (true) {
			if (!irc.isConnected()) {
				irc.closeConnection();
				irc.restartConnection();
				System.out.println("We have a problem");
			}
			String defaltMessage = irc.readMessage();
			String chatMessage = irc.readChat(defaltMessage);
			String user = "";
			if (irc.getUserNameFromChat(defaltMessage) != null) {
				user = irc.getUserNameFromChat(defaltMessage);
			}
			chatCommand.command(chatMessage, user, irc);
			if (chatMessage.compareTo("hello") == 0) {
				irc.sendChatMessage("hey " + user);
			}
		}
	}

}
