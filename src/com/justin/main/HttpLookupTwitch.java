package com.justin.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.parser.JSONParser;

public class HttpLookupTwitch {
	
	// look up who's chatting 
	private String ChatterUrl ="https://tmi.twitch.tv/group/user/cerealkiller1918/chatters";
	private JSONParser parser; 
	
	public ArrayList<String> getViewers(){
		try{
			ArrayList<String> viewers = new ArrayList<String>();
			StringBuilder urlResult = new StringBuilder();
			URL url = new URL(ChatterUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line= rd.readLine())!=null){
				urlResult.append(line);
			}
			rd.close();
			System.out.println(urlResult.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
