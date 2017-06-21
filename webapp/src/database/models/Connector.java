package com.sharebooks.database.models;


import java.sql.*;
import java.util.*;


//the connector class instance needs to be created at the time of the application initialization and will be stored in the
//application context
public class Connector {

	private static Connection connection = null;
	private static String DB_URL;
	private static String USERNAME;
	private static String PASSWORD;

	//private constructor so no object creation is allowed from the class
	private Connector(){

	}


	//initializing driver class
	public static void init(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception ex){
			System.out.println("Exception occurred in init method in Connector class");
			System.out.println(ex);
		}
	}


	public static void setProperties(String url , String username , String password){
		try{
			System.out.println("Database url : " + url);
			System.out.println("Database username : " + username);
			System.out.println("Database password : " + password);
			DB_URL = url;
			USERNAME = username;
			PASSWORD = password;
		}
		catch(Exception ex){

		}
	}



	public static void createConnection(){
		try{
			if(connection != null){
				throw new Exception("Connection already created.");
			}
			connection = DriverManager.getConnection(DB_URL , USERNAME , PASSWORD);
		}
		catch(Exception ex){
			System.out.println("Exception occurred in createConnection method in Connector class");
			System.out.println(ex);
		}
	}

	public static void closeConnection(){
		try{
			connection.close();
		}
		catch(Exception ex){
			System.out.println("Exception occurred in closeConnection method in Connector class");
			System.out.println(ex);
		}
	}


	public static Connection getConnection(){
		return connection;
	}

}

