package com.justin.thread;

import com.justin.twitch.irc.ChatCommand;
import com.justin.twitch.irc.IrcClient;
import com.justin.window.Window;

import javax.swing.*;

public class Chat_Thread implements Runnable {

    private IrcClient irc;
    private ChatCommand chatCommand;

    public Chat_Thread(Window window, boolean testing) {
        irc = new IrcClient(window,testing);
        chatCommand = new ChatCommand();
    }


    public void run() {
        int count = 0;
        while (true) {

            if (count >=3){
                JOptionPane.showMessageDialog(null,"Tried to reconnect not reconnecting", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                break;
            }
            if (!irc.isConnected()) {
                irc.closeConnection();
                irc.restartConnection();
                count ++;
            }else{
                count = 0;
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

}
