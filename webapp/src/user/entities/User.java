package com.sharebooks.user.entities;

import java.util.*;
import com.sharebooks.common.interfaces.JsonObjectBuilder;


public final class User implements JsonObjectBuilder {
	private int id = -1;
	private String name;
	private String username;
	private String password;
	private String mobileNo;
	private int age;
	private String address;
	private String city;
	private String state;
	private String bookIds = "";
	private Date creationTime = new Date();


	public User(){
		//creationTime = new Date();
	}

	public User(int id , String name , String username , String password , String mobileNo , int age , String address , String city , String state){
		this();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.mobileNo = mobileNo;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
	}


	public String getId(){
		return id;
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





	public String getJsonObject(User user){
		StringBuilder jsonObj = new StringBuilder();

		try{
			jsonObj.append("{");
			jsonObj.append("id" + ":" + user.getId());
			jsonObj.append(",");
			jsonObj.append("name" + ":" + "'" + user.getUserId() + "'");
			jsonObj.append(",");
			jsonObj.append("userName" + ":" + "'" + user.getUsername() + "'");
			jsonObj.append(",");
			jsonObj.append("password" + ":" + "'" + user.getPassword() + "'");
			jsonObj.append(",");
			jsonObj.append("mobileNo" + ":" + "'" + user.getMobileNo() + "'");
			jsonObj.append(",");
			jsonObj.append("age" + ":" + user.getAge());
			jsonObj.append(",");
			jsonObj.append("address" + ":" + "'" + user.getAddress() + "'");
			jsonObj.append(",");
			jsonObj.append("city" + ":" + "'" + user.getCity() + "'");
			jsonObj.append(",");
			jsonObj.append("state" + ":" + "'" + user.getState() + "'");
			jsonObj.append(",");
			jsonObj.append("bookIds" + ":" + "'" + user.getBookIds() + "'");
			jsonObj.append(",");
			jsonObj.append("creationTime" + ":" + "'" + (user.getCreationTime()).toString() + "'");
			jsonObj.append("}");

			return jsonObj.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getJsonObject in User");
			throw ex;
		}
	}
}



// Timestamp timestamp = rs.getTimestamp(columnName);
// java.util.Date date = null;
// if (timestamp != null){
// 	date = new java.util.Date(timestamp.getTime());
// }
// obj = date;
// break;