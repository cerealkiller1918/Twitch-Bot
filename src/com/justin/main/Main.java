package com.justin.main;

import com.justin.fx.SoundClip;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

public class Main {

	private static IrcClient irc = new IrcClient(true);
	
	public static void main(String[] args){
		
		SoundClip clip = new SoundClip("/r2d2.wav");
		//String ChatterUrl ="https://tmi.twitch.tv/group/user/cerealkiller1918/chatters";
		Window window = new Window();
		Lookups lookups = new Lookups(irc,window);
		Thread thread = new Thread(lookups);
		thread.setName("ChatCountLoger");
		clip.setVolume(6f);
		thread.start();
		
		irc.joinChannel();
		
		while(true){
			String defaltMessage = irc.readMessage();
			String message = irc.readChat(defaltMessage);
			if(message.compareTo("!hello")==0){
				irc.sendChatMessage("hey");
			}
			if(message.compareTo("hello")==0){
				if(clip.isRunning())break;
				clip.play();
			}
			
		}
	}
		
	
	
}
