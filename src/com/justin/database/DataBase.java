package com.justin.database;

import com.justin.stackTrace.StackTrace;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class DataBase {

    private static Connection connection =null;
    private static String dbName = "jdbc:sqlite:TwitchBot.db";
    private static int queryTimeout = 30;
    private static Statement statement;

    public DataBase() {


        try {
            // Connection to the database or makes the database if one does not exist
            if(!new File("TwitchBot.db").exists()) {
                connection = DriverManager.getConnection(dbName);
                statement = connection.createStatement();
                statement.setQueryTimeout(queryTimeout);
                statement.executeUpdate("create table logger ('date' DATE ,'time' DATE,'name' CHAR ,'message' CHAR );");
                cleanup();
            }
        } catch (Exception e) {

            StackTrace.message(e);

        }
    }

    public static void cleanup(){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            StackTrace.message(e);

        }
    }

    public static void getQuery(String table, int columnIndex){
        try {
            ResultSet rs = statement.executeQuery("select * from " + table);
            while(rs.next()){
                System.out.println(rs.getString(columnIndex));
            }

        }catch(Exception e){
            StackTrace.message(e);
        }
    }


    public static void insert(String table, String user, String message){
        try {
            DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            connection = DriverManager.getConnection(dbName);
            statement = connection.createStatement();
            statement.setQueryTimeout(queryTimeout);
            statement.executeUpdate("INSERT INTO "+ table +" VALUES ('"+format.format(new Date())+"','"+new SimpleDateFormat("HH:mm:ss").format(new Date())+"','"+user+"','"+message+"');");
            cleanup();
        } catch (SQLException e) {
            StackTrace.message(e);
        }
    }


}

