package com.sharebooks.login.entities;


import java.util.*;


public class User {
	private int id;
	private String username;
	private String password;


	public User(String username , String password){
		this.username = username;
		this.password = password;
	}

	public User(int id , String username , String password){
		this.id = id;
		this.username = username;
		this.password = password;
	}


	public int getId(){
		return id;
	}

	public String getUserName(){
		return username;
	}
	

	public String getPassword(){
		return	password;
	}


	// public static String getTableMetaData() throws Exception {
	// 	try{
	// 		StringBuilder metaData = new StringBuilder();

	// 		metaData.append("id");
	// 		metaData.append(COLON);
	// 		metaData.append("int");
	// 		metaData.append(COMMA);
	// 		metaData.append("username");
	// 		metaData.append(COLON);
	// 		metaData.append("string");
	// 		metaData.append(COMMA);
	// 		metaData.append("password");
	// 		metaData.append(COLON);
	// 		metaData.append("string");
			
	// 		return metaData.toString();
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in getTableMetaData in User");
	// 		throw ex;
	// 	}
	// }
}