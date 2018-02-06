package com.sharebooks.appEntities.entities;


import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

//immutable class
public final class User extends Entity implements Comparable<User> {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String username;
	private final String password;
	private final String name;
	private final String birthday;
	private final String address;
	private final String city;
	private final String state;
	private final String pincode;
	private final String mobileNo;
	private final String bookIds;
	private final int active;
	private final java.util.Date creationTime;
	private final java.util.Date lastVisited;


	private static final String[] fields;
	private static final String[] fieldTypes;


	static {
		fields = new String[]{"id" , "username" , "password" , "name" , "birthday" , "address" ,"city" , "state" , "pincode" , "mobileNo" , "bookIds" ,
				 "active" , "creationTime" , "lastVisited"};
		fieldTypes = new String[]{"int" , "string" , "string" , "string" , "string" , "string" , "string" , "string" , "string" , "string" ,
					 "string" , "int" , "datetime" , "datetime"};
	}



	//this constructor will be used when fetching user from datatbase
	public User(int id , String username , String password , String name , String birthday , String address , String city , String state , String pincode , 
		String mobileNo , String bookIds , int active , java.util.Date creationTime , java.util.Date lastVisited){
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.mobileNo = mobileNo;
		this.bookIds = bookIds;
		this.active = active;
		this.creationTime = creationTime;
		this.lastVisited = lastVisited;
	}



	//this will be used to create user from http request 
	public User(int id , String username , String password , String name , String birthday , String address , String city , String state , String pincode , 
		String mobileNo , String bookIds , int active){
		try{
			this.id = id;
			this.username = username;
			this.password = password;
			this.name = name;
			this.birthday = birthday;
			this.address = address;
			this.city = city;
			this.state = state;
			this.pincode = pincode;
			this.mobileNo = mobileNo;
			this.bookIds = bookIds;
			this.active = active;
			this.creationTime = new java.util.Date();
			this.lastVisited = new java.util.Date();
		}
		catch(Exception ex){
			System.out.println("Exception in User constructor 2 method in User class");
			throw ex;
		}
	}





	//compareTo method to be used for sorting
	public int compareTo(User user){
		return username.compareTo(user.getUsername());
	}
	
	//overriding hashCode method
	public int hashCode(){
		return username.hashCode();
	}

	//overriding equals method
	public boolean equals(Object o){
		if(o == null){
			return false;
		}

		if(!(o instanceof User)){
			return false;
		}

		if(o == this){
			return true;
		}

		User user = (User)o;
		return username.equals(user.getUsername());
	}



	//getter methods

	public int getId(){
		return id;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public String getName(){
		return name;
	}

	public String getBirthday(){
		return birthday;
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

	public String getPincode(){
		return pincode;
	} 

	public String getMobileNo(){
		return mobileNo;
	}

	public String getBookIds(){
		return bookIds;
	}

	public int getActive(){
		return active;
	}

	public java.util.Date getCreationTime(){
		return creationTime;
	}

	public java.util.Date getLastVisited(){
		return lastVisited;
	}









	public String[] getFields(){
		return fields; 
	}

	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){
		Object[] fieldValues = {this.id , this.username , this.password , this.name , this.birthday , this.address , this.city , 
								this.state , this.pincode , this.mobileNo , this.bookIds , this.active , this.creationTime , this.lastVisited};

		return fieldValues;
	}

	
}