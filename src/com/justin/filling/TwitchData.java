package com.justin.filling;

import com.justin.stackTrace.StackTrace;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TwitchData {

    private static String fileName = "TwitchLogin.txt";
    public TwitchData() {
        // TODO Auto-generated constructor stub
    }


    public static ArrayList<String> getLoginData() {

        if (new File(fileName).exists()) {
            ArrayList<String> strings = new ArrayList<String>();
            File file = new File(fileName);
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine().split(":", 2)[1]);
            }
            return strings;
        } else {
            try {
                PrintWriter writer = new PrintWriter(fileName);
                String userName = JOptionPane.showInputDialog("Enter bot's Twitch User Name.");
                String oAuth = JOptionPane.showInputDialog("Enter the bot's Twitch OAuth Password.");
                String channel = JOptionPane.showInputDialog("Enter the Channel ");
                writer.println("User Name :" + userName);
                writer.println("oAuth :" + oAuth);
                writer.println("Channel :" + channel);
                writer.flush();
                writer.close();
                ArrayList<String> strings = new ArrayList<String>();
                strings.add(userName);
                strings.add(oAuth);
                strings.add(channel);

                return strings;
            } catch (FileNotFoundException e) {
                StackTrace.message(e);
                return null;
            }
        }
    }

    public static void deleteFile(){
        File file = new File(fileName);
        file.delete();
    }

}
