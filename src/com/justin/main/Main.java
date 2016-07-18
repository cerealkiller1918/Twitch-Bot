package com.justin.main;

import com.justin.fx.SoundClip;
import com.justin.twitch.chat.TwitchChatAPI;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

public class Main {

	private static IrcClient irc = new IrcClient(true);
	
	public static void main(String[] args){
		
		TwitchChatAPI chatAPI = new TwitchChatAPI();
		
		SoundClip clip = new SoundClip("/r2d2.wav");
		//String ChatterUrl ="https://tmi.twitch.tv/group/user/cerealkiller1918/chatters";
		Window window = new Window();
		Lookups lookups = new Lookups(irc,window);
		Thread thread = new Thread(lookups);
		thread.setName("ChatCountLoger");
		clip.setVolume(1.0f);
		thread.start();
		
		irc.joinChannel();
		
		while(true){
			String defaltMessage = irc.readMessage();
			String message = irc.readChat(defaltMessage);
			String user ="";
			if(irc.getUserNameFromChat(defaltMessage)!=null){
				user = irc.getUserNameFromChat(defaltMessage);
			}
			
			if(message.compareTo("!hello")==0){
				irc.sendChatMessage("hey "+user);
			}
			if(message.compareTo("!play")==0){
				if(clip.isRunning())break;
				clip.play();
			}
			if(message.compareTo("!test")==0){
				String[] list = chatAPI.getAllChatters();
				if(list==null)System.out.println("Did not work");
				if(list!=null){
					window.ChatLogSetText("");
					for(int i =0; i < list.length; i++){
						window.updateChatLog(list[i]+'\n');
					}
					
				}
			}
			
		}
	}
		
	
	
}
