package com.justin.thread;

import com.justin.twitch.irc.ChatCommand;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

public class Chat_Thread implements Runnable {

    private IrcClient irc;
    private ChatCommand chatCommand;

    public Chat_Thread(Window window) {
        irc = new IrcClient(window);
        chatCommand = new ChatCommand();
    }

    @Override
    public void run() {
        while (true) {
            if (!irc.isConnected()) {
                irc.closeConnection();
                irc.restartConnection();
                System.out.println("We have a problem");
            }
            String defaultMessage = irc.readMessage();
            String chatMessage = irc.readChat(defaultMessage);
            String user = "";
            if (irc.getUserNameFromChat(defaultMessage) != null) {
                user = irc.getUserNameFromChat(defaultMessage);
            }
            chatCommand.command(chatMessage, user, irc);
        }
    }

}
