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
			JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTP(testUrl));
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
	
	public String getGame(){
		if(!isStreamOnline()) return null;
		try{
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTP(testUrl));
			JSONObject streamObject = (JSONObject) jsonObject.get("stream");
			return (String) streamObject.get("game");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public long getVideoSize(){
		if(!isStreamOnline()) return -1;
		try{
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTP(testUrl));
			JSONObject streamObject = (JSONObject) jsonObject.get("stream");
			return (long) streamObject.get("video_height");
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public long getDelay(){
		if(!isStreamOnline()) return -1;
		try{
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTP(testUrl));
			JSONObject streamObject = (JSONObject) jsonObject.get("stream");
			return (long) streamObject.get("delay");
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public long getViewers(){
		if(!isStreamOnline()) return -1;
		try{
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTP(testUrl));
			JSONObject streamObject = (JSONObject) jsonObject.get("stream");
			return (long) streamObject.get("viewers");
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	

}
