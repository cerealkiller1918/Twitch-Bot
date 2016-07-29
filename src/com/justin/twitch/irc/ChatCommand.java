package com.justin.twitch.irc;

import com.justin.fx.SoundClip;
import com.justin.stackTrace.StackTrace;
import com.justin.weather.Weather;

public class ChatCommand {

    private SoundClip clip = new SoundClip("/coin pickup.wav");

    public void command(String message, String user, IrcClient irc) {
        try {

            if (message.contains("hello")) {
                irc.sendChatMessage("hey " + user);
            } else if (message.compareTo("!test") == 0) {
                clip.play();
            } else if (message.compareTo("!restart") == 0) {
                irc.closeConnection();
                irc.restartConnection();
            }  else if (message.contains("!weather")){
                String[] temp = message.trim().split(" ");
                if(temp.length<=2){
                    irc.sendChatMessage("!weather (city) (state abbreviation)");
                    return;
                }
                if(temp[2].length()>2){
                    irc.sendChatMessage("!weather (city) (state abbreviation)");
                    return;
                }
                irc.sendChatMessage(Weather.getForecast(temp[1].trim(),temp[2].trim()));
            }

        }catch(Exception e){
            StackTrace.message(e);
        }

    }

}
