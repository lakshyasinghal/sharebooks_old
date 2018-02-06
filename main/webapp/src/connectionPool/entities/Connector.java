package com.sharebooks.connectionPool.entities;


import java.sql.*;
import static com.sharebooks.staticClasses.LogMessages.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import com.sharebooks.staticClasses.Properties;


//the connector class instance needs to be created at the time of the application initialization and will be stored in the
//application context
class Connector {

	//private static Connection connection = null;
	private static String DBMS = "mysql";
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	

	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}


	//private constructor so no object creation is allowed from the class
	private Connector(){

	}


	//initializing driver class
	public static void init(String server , String portNum , String username , String password , String dbName){
		try{
			if(server.equals("") || portNum.equals("") || username.equals("") || password.equals("") || dbName.equals("")){
				throw new Exception(PROPERTIES_NOT_INITIALIZED + " : " + server + " , " + portNum + " , " + username + " , " + password + " , " + dbName);
			}

			prepareURL(server , portNum , dbName);
			USERNAME = username;
			PASSWORD = password;
		}
		catch(Exception ex){
			System.out.println("Exception occurred in init in Connector class");
			System.out.println(ex);
		}
	}


	private static void prepareURL(String server , String portNum , String dbName) throws Exception{
		try{
			URL = "jdbc" + ":" + DBMS + "://" + server + ":" + portNum + "/" + dbName;
		}
		catch(Exception ex){
			System.out.println("Error occurred in prepareURL in Connector class");
			throw ex;
		}
	}



	public static Connection createConnection() throws Exception{
		try{
			if(URL.equals("") || USERNAME.equals("") || PASSWORD.equals("")){
				throw new Exception(PROPERTIES_NOT_INITIALIZED);
			}

			Connection connection = DriverManager.getConnection(URL , USERNAME , PASSWORD);

			System.out.println(DATABASE_CONNECTION_CREATED);

			return connection;
		}
		catch(Exception ex){
			System.out.println("Error occurred in createConnection in Connector class");
			throw ex;
		}
	}


	public static void closeConnection(Connection connection){
		try{
			connection.close();
			System.out.println("Connection closed");
		}
		catch(Exception ex){
			System.out.println("Exception occurred in closeConnection method in Connector class");
			System.out.println(ex);
		}
	}

}

