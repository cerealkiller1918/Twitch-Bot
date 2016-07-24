package com.justin.twitch.web.api;

import com.justin.webInterface.WebInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class TwitchChatAPI {

    private String url = "https://tmi.twitch.tv/group/user/cerealkiller1918/chatters";

    public Long getChatterCount() {
        try {
            WebInterface web = new WebInterface();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(web.getHTTPS(url));
            JSONObject jsonObject = (JSONObject) obj;
            return (long) jsonObject.get("chatter_count");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getViewsList() {
        try {
            WebInterface web = new WebInterface();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTPS(url));
            JSONObject inter = (JSONObject) jsonObject.get("chatters");
            JSONArray jsonArray = (JSONArray) inter.get("viewers");
            String[] strings = new String[jsonArray.size()];
            for (int index = 0; index < jsonArray.size(); index++) {
                strings[index] = (String) jsonArray.get(index);
            }
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getModeratorsList() {
        try {
            WebInterface web = new WebInterface();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTPS(url));
            JSONObject inter = (JSONObject) jsonObject.get("chatters");
            JSONArray jsonArray = (JSONArray) inter.get("moderators");
            String[] strings = new String[jsonArray.size()];
            for (int index = 0; index < jsonArray.size(); index++) {
                strings[index] = (String) jsonArray.get(index);
            }
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getAllChatters() {
        try {
            WebInterface web = new WebInterface();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(web.getHTTPS(url));
            JSONObject inter = (JSONObject) jsonObject.get("chatters");
            JSONArray moderatorsArray = (JSONArray) inter.get("moderators");
            JSONArray staffArray = (JSONArray) inter.get("staff");
            JSONArray adminArray = (JSONArray) inter.get("admins");
            JSONArray global_modsArray = (JSONArray) inter.get("global_mods");
            JSONArray viewersArray = (JSONArray) inter.get("viewers");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.addAll(convertJsonArrayToArrayList(moderatorsArray));
            arrayList.addAll(convertJsonArrayToArrayList(staffArray));
            arrayList.addAll(convertJsonArrayToArrayList(adminArray));
            arrayList.addAll(convertJsonArrayToArrayList(global_modsArray));
            arrayList.addAll(convertJsonArrayToArrayList(viewersArray));
            String[] list = new String[arrayList.size()];
            list = arrayList.toArray(list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<String> convertJsonArrayToArrayList(JSONArray json) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < json.size(); i++) {
            list.add((String) json.get(i));
        }
        return list;
    }
}
