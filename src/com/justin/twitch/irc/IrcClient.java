package com.justin.twitch.irc;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class IrcClient {

	private Socket socket;
	private PrintWriter writer;
	private Scanner scanner;
	private String address = "irc.chat.twitch.tv";
	private int port = 6667;
	private String userName = "k_m_g_bot";
	private String oAuth = "oauth:bzuchfpauv15sjl0sy172xokrsovx9";
	private String channel = "cerealkiller1918";

	public IrcClient() {

		try {
			socket = new Socket(address, port);
			writer = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		startMessage();
		joinChannel();

	}

	private void startMessage() {
		try{
		writer.println("PASS " + oAuth);
		writer.println("NICK " + userName);
		writer.println("USER " + userName + " 8 * :" + userName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void sendIrcMessage(String message) {
		try{
		writer.println(message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void joinChannel() {
		try{
			sendIrcMessage("JOIN #" + channel);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void sendChatMessage(String message) {
		try{
		sendIrcMessage(":" + userName + "!" + userName + "@" + userName + ".tmi.twitch.tv PRIVMSG #" + channel + " :"
				+ message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String readMessage() {
		try{
		String message = "";
		if (scanner.hasNext()) {
			message = scanner.nextLine();
		}
		return message;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}

	public String readChat(String message) {
		try{
		String[] temp = message.split(" :");
		if (temp.length == 2) {
			return temp[1].trim();
		}
		return "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	public String getUserNameFromChat(String message) {
		try{
		String[] temp = message.split("!");
		StringBuilder name = new StringBuilder(temp[0]);
		if (name.length() == 0) {
			return null;
		}
		if (name.toString().charAt(0) == ':') {
			name.deleteCharAt(0);
			String sender = name.toString();
			return sender;
		} else {
			return null;
		}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean isConnected() {
		try{
		return socket.isConnected();
		}catch(Exception e){
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	public void restartConnection() {

		try {
			socket = new Socket(address, port);
			writer = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		startMessage();
		joinChannel();
	}

}
