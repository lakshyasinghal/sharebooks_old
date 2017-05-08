package com.sharebooks.user.entities;

import java.util.*;


public final class User {
	private String name;
	private String username;
	private String password;
	private String mobileNo;
	private int age;
	private String address;
	private String city;
	private String state;
	private String bookIds = "";
	private Date creationTime;


	public User(){
		creationTime = new Date();
	}

	public User(String name , String username , String password , String mobileNo , int age , String address , String city , String state){
		this();
		this.name = name;
		this.username = username;
		this.password = password;
		this.mobileNo = mobileNo;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
	}



	public String getName(){
		return name;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public String getMobileNo(){
		return mobileNo;
	}


	public int getAge(){
		return age;
	}

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public String getState(){
		return state;
	} 

	public String getBookIds(){
		return bookIds;
	}

	public Date getCreationTime(){
		return creationTime;
	}
}



// Timestamp timestamp = rs.getTimestamp(columnName);
// java.util.Date date = null;
// if (timestamp != null){
// 	date = new java.util.Date(timestamp.getTime());
// }
// obj = date;
// break;