package com.justin.stackTrace;

import java.io.File;
import java.io.PrintWriter;
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

     public static void message(String e){
         try{
             String fileName = "Stack"
                     fileName =  new SimpleDateFormat("MM-dd-yyyy").format(new Date());

             if(new File(fileName).exists()){
                 File file = new File(fileName);
                 PrintWriter writer = new PrintWriter(file);
                 writer.append(e);
                 writer.flush();
                 writer.close();
             }else{
                 File file = new File(fileName);
                 PrintWriter writer = new PrintWriter(file);
                 writer.print(e);
                 writer.flush();
                 writer.close();
             }

         }catch(Exception e1){
             e1.printStackTrace();
         }
     }
}
