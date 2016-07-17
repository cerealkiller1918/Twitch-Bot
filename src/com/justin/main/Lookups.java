package com.justin.main;

import com.justin.twitch.chat.TwitchChatPull;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

public class Lookups implements Runnable {
	
	private IrcClient irc;
	private Window window;
	 public Lookups(IrcClient irc, Window windows) {
		this.irc = irc;
		this.window = windows;
	}

	@Override
	public void run() {
		//System.out.println("Run Has been called");
		TwitchChatPull twitchChat = new TwitchChatPull();
		long chatters = twitchChat.getChatterCount();
		while(true){
			long checkedChatters = twitchChat.getChatterCount();
			window.ChatLogSetText(Long.toString(chatters));
			if(checkedChatters >chatters){
				
				chatters = checkedChatters;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(checkedChatters < chatters){
				irc.sendChatMessage("Bye");
				chatters = checkedChatters;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

	
	


}
