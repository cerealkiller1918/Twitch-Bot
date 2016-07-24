package com.justin.twitch.irc;

import com.justin.fx.SoundClip;

public class ChatCommand {

    SoundClip clip = new SoundClip("/coin pickup.wav");

    public void command(String message, String user, IrcClient irc) {

        if (message.compareTo("!test") == 0) {
            clip.play();
        }
        if (message.compareTo("!names") == 0) {
            irc.getNames();
        }
        if (message.compareTo("!restart") == 0) {
            irc.closeConnection();
            irc.restartConnection();
        }
        if (message.compareTo("!file") == 0) {

        }

    }

}
