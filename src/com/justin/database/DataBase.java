package com.justin.database;

import java.sql.Connection;

import com.gmt2001.DataStore;

public class DataBase extends DataStore {

	private static Connection connection = null;
	private static final DataBase instance = new DataBase();

	private String db = "";
	private String user = "";
	private String pass = "";
	
	

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public static DataBase getInstance() {
		return instance;
	}

}
