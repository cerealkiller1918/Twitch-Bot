package com.justin.logger;

import com.justin.stackTrace.StackTrace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Justin Frasier on 07/26/16.
 */
public class Logger {

    public static void console(String message){
        logFile(message);
        System.out.println(message);
    }

    public static void log(String message){
        logFile(message);
    }

    private static void logFile(String message){
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String fileName = "Logger " + dateFormat.format(new Date()) + ".txt";
            if (new File(fileName).exists()) {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                writer.write(message+'\n');
                writer.flush();
                writer.close();
            }else{
                PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter(fileName))));
                writer.write(message+"\n");
                writer.flush();
                writer.close();
            }
        }catch (Exception e){
            StackTrace.message(e);
        }
    }
}
