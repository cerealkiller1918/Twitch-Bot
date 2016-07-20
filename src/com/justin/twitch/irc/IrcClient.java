package com.justin.twitch.irc;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.justin.fx.SoundClip;
import com.justin.window.Window;

public class IrcClient {
	private SoundClip ping;
	private Socket socket;
	private PrintWriter writer;
	private Scanner scanner;
	private Window window;

	private int port = 6667;
	// private String address = "irc.chat.twitch.tv";
	// private String userName = "k_m_g_bot";
	// private String oAuth = "oauth:bzuchfpauv15sjl0sy172xokrsovx9";
	// private String channel = "cerealkiller1918";

	// For Testing
	private String channel = "bossier";
	private String userName = "bot1918";
	private String address = "irc.freenode.net";

	public IrcClient(Window window) {
		this.window =window;
		try {
			ping = new SoundClip("/coin pickup.wav");
			ping.setVolume(-5.0f);
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
		// writer.println("PASS " + oAuth);
		writer.println("NICK " + userName);
		writer.println("USER " + userName + " 8 * :" + userName);
	}

	private void sendIrcMessage(String message) {
		writer.println(message);
		System.out.println("<<< " + message);
	}

	private void joinChannel() {
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
			String sender = name.toString();
			return sender;
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
		joinChannel();
	}

	public void getNames() {
		sendIrcMessage("NAMES #" + channel);
	}

}
