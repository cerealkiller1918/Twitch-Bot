package com.justin.twitter;

import com.justin.stackTrace.StackTrace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Justin on 07/26/16.
 */
public class GetLoginInfo {

    public static ArrayList<String> info() {
        String fileName = "TwitterLogin.txt";
        if (new File(fileName).exists()) {
            ArrayList<String> strings = new ArrayList<String>();
            File file = new File(fileName);
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                StackTrace.message(e);
            }
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine().split(": ", 2)[1]);
            }
            return strings;
        }else{
            return null;
        }
    }
}
