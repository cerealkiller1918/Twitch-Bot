package com.justin.stackTrace;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Justin on 07/24/16.
 */
public class StackTrace {

    private static DateFormat dateFormat;

     public StackTrace(){
         dateFormat = new SimpleDateFormat("MM-dd-yy");
     }

     public static void message(Exception e){
         try{
             String fileName;
             fileName = new StringBuilder().append("StackTrace ").append(new SimpleDateFormat("MM-dd-yyyy-HH-mm").format(new Date())).append(".txt").toString();
             if(new File(fileName).exists()){
                 PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                 e.printStackTrace(writer);
                 writer.flush();
                 writer.close();
             }else{
                 PrintWriter writer = new PrintWriter(new BufferedWriter( new FileWriter(fileName)));
                 e.printStackTrace(writer);
                 writer.flush();
                 writer.close();
             }

         }catch(Exception e1){
             e1.printStackTrace();
         }
     }
}
