package com.justin.twitch.irc;

import com.justin.filling.TwitchData;
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

    private int port = 6667;
    private String address = "irc.chat.twitch.tv";
    private String userName;
    private String oAuth;
    private String channel;

    // For Testing
    /*
	 * private String channel = "bossier";
	 *  private String userName = "bot1918";
	 * private String address = "irc.freenode.net";
	 * 
	 */

    public IrcClient(Window window) {
        try {
            this.window = window;
            ArrayList<String> list;
            list = TwitchData.getLoginData();
            for (int i =0; i<list.size(); i++){
                if(list.get(i).contains("null")){
                    JOptionPane.showMessageDialog(null,"There was a NULL","Error",JOptionPane.ERROR_MESSAGE);
                    TwitchData.deleteFile();
                    return;
                }
            }
            userName = list.get(0).toLowerCase();
            oAuth = list.get(1);
            channel = list.get(2).toLowerCase();

            socket = new Socket(address, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(socket.getInputStream());
            startMessage();
            joinChannel(channel);
            setMembership();
        } catch (Exception e) {
            StackTrace.message(e);
        }


    }

    private void startMessage() {

        writer.println("PASS " + oAuth);
        sendIrcMessage("NICK " + userName);
        sendIrcMessage("USER " + userName + " 8 * :" + userName);

    }

    private void sendIrcMessage(String message) {
        try {
            writer.println(message);
            System.out.println("<<< " + message);
        } catch (Exception e) {
            StackTrace.message(e);
        }
    }

    private void joinChannel(String channel) {
        sendIrcMessage("JOIN #" + channel);
    }

    public void sendChatMessage(String message) {
        sendIrcMessage(":" + userName + "!" + userName + "@" + userName + ".tmi.twitch.tv PRIVMSG #" + channel + " :"
                + message);
    }

    public String readMessage() {
        try {
            String message = "";
            if (scanner.hasNext()) {
                message = scanner.nextLine();
                System.out.println(">>> " + message);
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

    public void getNames() {
        sendIrcMessage("NAMES");
    }

}
