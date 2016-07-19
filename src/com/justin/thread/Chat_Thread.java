package com.justin.thread;

import com.justin.twitch.irc.ChatCommand;
import com.justin.twitch.irc.IrcClient;

public class Chat_Thread implements Runnable {

	private IrcClient irc;
	private ChatCommand chatCommand;

	public Chat_Thread() {
		irc = new IrcClient();
		chatCommand = new ChatCommand();
	}

	@Override
	public void run() {
		try{
		while (true) {
			if (!irc.isConnected()) {
				irc.closeConnection();
				irc.restartConnection();
			}
			String defaltMessage = irc.readMessage();
			String chatMessage = irc.readChat(defaltMessage);
			String user = "";
			if (irc.getUserNameFromChat(defaltMessage) != null) {
				user = irc.getUserNameFromChat(defaltMessage);
			}
			chatCommand.command(chatMessage, user);
			if (chatMessage.compareTo("hello") == 0) {
				irc.sendChatMessage("hey " + user);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
