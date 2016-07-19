package com.justin.twitch.web.api;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.justin.webInterface.WebInterface;

public class TwitchFollowsAPI {

	private String url = "https://api.twitch.tv/kraken/channels/cerealkiller1918/follows";

	public String[] listOfFollows() {
		try {
			WebInterface web = new WebInterface();
			ArrayList<String> strings = new ArrayList<String>();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject1 = (JSONObject) parser.parse(web.getHTTPS(url));
			JSONArray jsonFollows = (JSONArray) jsonObject1.get("follows");
			for (int i = 0; i < jsonFollows.size(); i++) {
				JSONObject object = (JSONObject) jsonFollows.get(i);
				JSONObject object2 = (JSONObject) object.get("user");
				strings.add((String) object2.get("display_name"));
			}
			String[] temp = new String[strings.size()];
			temp = strings.toArray(temp);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
