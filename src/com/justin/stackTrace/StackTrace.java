package com.justin.stackTrace;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Justin on 07/24/16.
 */
public class StackTrace {



     public StackTrace(){

     }

     public static void message(Exception e){
         try{
             String fileName;
             fileName = new StringBuilder().append("StackTrace ").append(new SimpleDateFormat("MM-dd-yyyy-HH-mm").format(new Date())).append(".txt").toString();
             if(new File(fileName).exists()){
                 PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                 writer.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                 e.printStackTrace(writer);
                 e.printStackTrace();
                 writer.flush();
                 writer.close();
             }else{
                 PrintWriter writer = new PrintWriter(new BufferedWriter( new FileWriter(fileName)));
                 writer.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                 e.printStackTrace(writer);
                 e.printStackTrace();
                 writer.flush();
                 writer.close();
             }

         }catch(Exception e1){
             e1.printStackTrace();
         }
     }
}
