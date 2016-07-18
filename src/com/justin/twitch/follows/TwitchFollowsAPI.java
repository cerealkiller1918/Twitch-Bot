package com.justin.twitch.follows;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.justin.json.JSON;

public class TwitchFollowsAPI {

	private String url = "https://api.twitch.tv/kraken/channels/cerealkiller1918/follows";
	private JSON web = new JSON();

	public String[] listOfFollows() {
		try {
			ArrayList<String> strings = new ArrayList<String>();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject1 = (JSONObject) parser.parse(web.getHTTPS(url));
			JSONArray jsonFollows = (JSONArray) jsonObject1.get("follows");
			for (int i = 0; i < jsonFollows.size(); i++) {
				JSONObject object = (JSONObject) jsonFollows.get(i);
				strings.add((String) object.get("display_name"));
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
