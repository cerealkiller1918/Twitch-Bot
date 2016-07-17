package com.justin.twitch.chat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.justin.json.JSON;

public class TwitchChatPull {
	
	private JSON web = new JSON();
	
	public Long getChatterCount(){
	
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(web.getJSON("https://tmi.twitch.tv/group/user/cerealkiller1918/chatters"));
			JSONObject jsonObject = (JSONObject) obj;
			return (long) jsonObject.get("chatter_count");
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
