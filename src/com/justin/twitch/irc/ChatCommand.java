package com.justin.twitch.irc;

import com.justin.fx.SoundClip;

public class ChatCommand {
	
	SoundClip clip = new SoundClip("/coin pickup.wav");
	
	public void command(String message, String user){
		
		
		if(message.compareTo("!test")==0){
			clip.play();
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
