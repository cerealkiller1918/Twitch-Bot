package com.justin.main;


import com.justin.thread.Chat_Thread;
import com.justin.thread.Web_Thread;
import com.justin.window.Clock;
import com.justin.window.Window;

public class Main {

	public static void main(String[] args) {
		
		Clock clock = new Clock();
		Window window = new Window();
		Thread chatThread = new Thread(new Chat_Thread(window));
		chatThread.setName("Chat");
		Thread webThread = new Thread(new Web_Thread(window));
		webThread.setName("Web");
		chatThread.start();
		webThread.start();

	}

}
