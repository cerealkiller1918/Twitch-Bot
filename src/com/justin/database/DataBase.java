package com.justin.database;

import java.sql.*;



public class DataBase {

    private Connection connection =null;
    private String dbName = "jdbc:sqlite:TwitchBot.db";
    private int queryTimeout = 30;
    private Statement statement;

    public DataBase() {


        try {
            // Connection to the database or makes the database if one does not exist

            connection = DriverManager.getConnection(dbName);
            statement = connection.createStatement();
            statement.setQueryTimeout(queryTimeout);

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            insert("person",1,"Justin");
            insert("person",2,"Bob");

            getQuery("person", 2);

        } catch (SQLException e) {

            System.err.println(e.getMessage());

        } finally {
            cleanup();
        }
    }

    public void cleanup(){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e);
        }
    }

    public void getQuery(String table, int columnIndex){
        try {
            ResultSet rs = statement.executeQuery("select * from " + table);
            while(rs.next()){
                System.out.println(rs.getString(columnIndex));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insert(String table, int index , String name){
        try {
            statement.executeUpdate("INSERT INTO "+ table +" VALUES ("+index+", '"+name+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

