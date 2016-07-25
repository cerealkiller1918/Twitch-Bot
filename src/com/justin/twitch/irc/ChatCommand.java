package com.justin.twitch.irc;

import com.justin.fx.SoundClip;
import com.justin.stackTrace.StackTrace;

public class ChatCommand {

    SoundClip clip = new SoundClip("/coin pickup.wav");

    public void command(String message, String user, IrcClient irc) {
        try {

            if (message.compareTo("hello") == 0) {
                irc.sendChatMessage("hey " + user);
            } else if (message.compareTo("!test") == 0) {
                clip.play();
            } else if (message.compareTo("!names") == 0) {
                irc.getNames();
            } else if (message.compareTo("!restart") == 0) {
                irc.closeConnection();
                irc.restartConnection();
            } else if (message.compareTo("!file") == 0) {

            }

        }catch(Exception e){
            StackTrace.message(e);
        }

    }

}
