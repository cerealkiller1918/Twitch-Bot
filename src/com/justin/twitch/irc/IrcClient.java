package com.justin.twitch.irc;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.justin.filling.TwitchData;
import com.justin.window.Window;

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
		this.window = window;
		ArrayList<String> list = TwitchData.getLoginData();
		userName = list.get(0);
		oAuth = list.get(1);
		channel = list.get(2);
		try {
			socket = new Socket(address, port);
			writer = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		startMessage();
		joinChannel(channel);
		setMembership();

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
			e.printStackTrace();
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
		String message = "";
		if (scanner.hasNext()) {
			message = scanner.nextLine();
			System.out.println(">>> " + message);
		}
		if (message.startsWith("PING")) {
			String pingContects = message.split(" ", 2)[1];
			sendIrcMessage("PONG " + pingContects);
		}
		return message;
	}

	public String readChat(String message) {
		String[] temp = message.split(" :");
		if (temp.length == 2) {
			return temp[1].trim();
		}
		return "";
	}

	public String getUserNameFromChat(String message) {
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
	}

	public boolean isConnected() {
		boolean x = true;
		if (!socket.isConnected()) {
			x = false;
		}
		if (writer.checkError()) {
			x = false;
		}
		return x;

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
			scanner.close();
			writer.flush();
			writer.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			socket = new Socket(address, port);
			writer = new PrintWriter(socket.getOutputStream(), true);
			scanner = new Scanner(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
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
