package com.justin.twitch.irc;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class IrcClient {
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private OutputStream outputStream;
	private Scanner scanner;
	private InputStream inputStream;
	private String address = "irc.chat.twitch.tv";
	private int port = 6667;
	private String userName= "k_m_g_bot";
	private String oAuth = "oauth:bzuchfpauv15sjl0sy172xokrsovx9";
	private String channel = "cerealkiller1918";
	
	public IrcClient(boolean start){
		if(start){
			try {
				socket = new Socket(address, port);
				writer = new PrintWriter(socket.getOutputStream(), true);
				scanner = new Scanner(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			} 
			writer.println("PASS " + oAuth);
			writer.println("NICK " + userName);;
			writer.println("USER " + userName + " 8 * :" + userName);
		}
	}
	
	private void sendIrcMessage(String message) {
		writer.println(message);
		System.out.println(message);
		
	}
	
	public void joinChannel(){
		sendIrcMessage("JOIN #" +channel);;
	}
	
	public void sendChatMessage(String message){
		sendIrcMessage(":" + userName + "!" + userName + "@" + userName + ".tmi.twitch.tv PRIVMSG #" + channel + " :" + message);
	}
	
	public String readMessage(){
		String message= "";
		if(scanner.hasNext()){
			message = scanner.nextLine();
			//System.out.println(message);
		}
		return message;
	}
	
	public String readChat(String message){
		String[] temp = message.split(" :");
		if(temp.length == 2){
			//System.out.println(temp[1]);
			return  temp[1].trim();
		}
		return "";
		
	}
	public String getUserNameFromChat(String message){
		String[] temp = message.split("!");
		StringBuilder name = new StringBuilder(temp[0]);
		name.deleteCharAt(0);
		String sender = name.toString();
		System.out.println(sender);
		return sender;
	}

}
