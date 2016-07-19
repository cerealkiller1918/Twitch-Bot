package com.justin.main;

import com.justin.thread.Chat_Thread;
import com.justin.thread.Web_Thread;
import com.justin.window.Window;

public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		try{
		Thread chatThread = new Thread(new Chat_Thread());
		chatThread.setName("Chat");
		//Thread webThread = new Thread(new Web_Thread(window));
		//webThread.setName("Web");
		chatThread.start();
		//webThread.start();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
