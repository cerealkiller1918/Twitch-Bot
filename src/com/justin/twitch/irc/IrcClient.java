package com.justin.twitch.irc;

import com.justin.logger.Logger;
import com.justin.twitch.data.TwitchData;
import com.justin.stackTrace.StackTrace;
import com.justin.window.Window;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class IrcClient {
    private Socket socket;
    private PrintWriter writer;
    private Scanner scanner;
    private Window window;

    private int port;
    private String address;
    private String userName;
    private String oAuth;
    private String channel;

    //For Testing
    private boolean testing = false;

    public IrcClient(Window window, boolean testing) {
        try {
            this.testing = testing;
            this.window = window;
            ArrayList<String> list;
            for (String s : list = TwitchData.getLoginData()) {
                if (s.contains("null")) {
                    JOptionPane.showMessageDialog(null, "There was a NULL", "Error", JOptionPane.ERROR_MESSAGE);
                    TwitchData.deleteFile();
                    return;
                }
            }
            if(!testing) {
                port = 6667;
                address =  "irc.chat.twitch.tv";
                userName = list.get(0).toLowerCase();
                oAuth = list.get(1);
                channel = list.get(2).toLowerCase();
            }else{
                port = 6667;
                channel = "bossier";
                userName = "bot1918";
                address = "irc.freenode.net";
            }
            socket = new Socket(address, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(socket.getInputStream());
            startMessage();
            joinChannel(channel);
            if(!testing)
                setMembership();
        } catch (Exception e) {
            StackTrace.message(e);
        }


    }

    private void startMessage() {
        if(!testing) {
            writer.println("PASS " + oAuth);
        }
        sendIrcMessage("NICK " + userName);
        sendIrcMessage("USER " + userName + " 8 * :" + userName);

    }

    private void sendIrcMessage(String message) {
        try {
            writer.println(message);
            Logger.console(" <<< "+message);
        } catch (Exception e) {
            StackTrace.message(e);
        }
    }

    private void joinChannel(String channel) {
        sendIrcMessage("JOIN #" + channel);
    }

    void sendChatMessage(String message) {
        sendIrcMessage(":" + userName + "!" + userName + "@" + userName + ".tmi.twitch.tv PRIVMSG #" + channel + " :"
                + message);
    }

    public String readMessage() {
        try {
            String message = "";
            if (scanner.hasNext()) {
                message = scanner.nextLine();
                Logger.console(" >>> "+ message);
            }
            if (message.startsWith("PING")) {
                String pingContents = message.split(" ", 2)[1];
                sendIrcMessage("PONG " + pingContents);
            }
            return message;
        }catch (Exception e){
            StackTrace.message(e);
            return null;
        }
    }

    public String readChat(String message) {
        try {
            String[] temp = message.split(" :");
            if (temp.length == 2) {
                return temp[1].trim();
            }
            return "";
        }catch(Exception e){
            StackTrace.message(e);
            return null;
        }
    }

    public String getUserNameFromChat(String message) {
       try {
           String[] temp = message.split("!");
           StringBuilder name = new StringBuilder(temp[0]);
           if (name.length() == 0) {
               return null;
           }
           if (name.toString().charAt(0) == ':') {
               name.deleteCharAt(0);
               return name.toString();
           } else {
               return null;
           }
       }catch(Exception e){
           StackTrace.message(e);
           return null;
       }
    }

    public boolean isConnected() {
        boolean x = true;
        try {
            if (!socket.isConnected()) {
                x = false;
            }
            if (writer.checkError()) {
                x = false;
            }
            return x;
        }catch(Exception e){
            StackTrace.message(e);
            return false;
        }
    }

    public void closeConnection() {
        try {
            scanner.close();
            writer.flush();
            writer.close();
            socket.close();
        } catch (Exception e) {
            StackTrace.message(e);
        }
    }

    public void restartConnection() {
        try {
            scanner.close();
            writer.flush();
            writer.close();
            socket.close();
        } catch (Exception e) {
            StackTrace.message(e);
            return;
        }
        try {
            socket = new Socket(address, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(socket.getInputStream());
        } catch (Exception e) {
           StackTrace.message(e);
            return;
        }
        startMessage();
        joinChannel(this.channel);
    }

    private void setMembership() {
        sendIrcMessage("CAP REQ :twitch.tv/membership");
    }


}
