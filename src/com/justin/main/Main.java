package com.justin.main;

public class Main {

	
	
	public static void main(String[] args){
		Window window = new Window();
		IrcClient irc = new IrcClient(true);
		HttpLookupTwitch lookup = new HttpLookupTwitch();
		irc.joinChannel();
		while(true){
			String defaltMessage = irc.readMessage();
			String message = irc.readChat(defaltMessage);
			
			if(message.compareTo("!hello")==0){
				irc.sendChatMessage("hey");
			}
			if(message.compareTo("!user")==0){
				lookup.getViewers();
			}
		}
		
	}
}
