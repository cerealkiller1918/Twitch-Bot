package com.justin.twitch.web.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.justin.webInterface.WebInterface;

public class TwitchStreamAPI {

	private String url = "https://api.twitch.tv/kraken/streams/cerealkiller1918";
	private String testUrl = "http://192.168.1.5:9092/test.json";
	private WebInterface web = new WebInterface();

	public boolean isStreamOnline() {
		try{
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTPS(url));
			if(jsonObject.get("stream")!= null){
				return true;
			
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
