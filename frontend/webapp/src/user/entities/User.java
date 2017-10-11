package com.sharebooks.user.entities;


import com.sharebooks.commonEntity.entities.Entity;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

//immutable class
public final class User extends Entity implements Comparable<User> {

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String name;
	private String birthday;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String mobileNo;
	private String bookIds = "";
	private int active = 1;
	private java.util.Date creationTime = new java.util.Date();
	private java.util.Date lastVisited = new java.util.Date();


	private static final String[] fields;
	private static final String[] fieldTypes;


	static {
		fields = new String[]{"id" , "username" , "password" , "name" , "birthday" , "address" ,"city" , "state" , "pincode" , "mobileNo" , "bookIds" ,
				 "active" , "creationTime" , "lastVisited"};
		fieldTypes = new String[]{"int" , "string" , "string" , "string" , "string" , "string" , "string" , "string" , "string" , "string" ,
					 "string" , "int" , "datetime" , "datetime"};
	}


	public User(){
		//creationTime = new Date();
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



	//this is being used to create new user request 
	public User(int id , String username , String password , String name , String birthday , String address , String city , String state , String pincode , 
		String mobileNo){
		
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







	//method for getting a book object from the result set
	public static User getUserFromResultSet(ResultSet rs) throws Exception {
		try{
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String birthday = rs.getString("birthday");
			String address = rs.getString("address");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String pincode = rs.getString("pincode");
			String mobileNo = rs.getString("mobileNo");
			String bookIds = rs.getString("bookIds");
			int active = rs.getInt("active");

			java.util.Date creationTime = null;
			java.util.Date lastVisited = null;

			java.sql.Timestamp timestamp = null;

			timestamp = rs.getTimestamp("creationTime");
			if(timestamp != null){
				creationTime = new java.util.Date(timestamp.getTime());
			}

			timestamp = rs.getTimestamp("lastVisited");
			if(timestamp != null){
				lastVisited = new java.util.Date(timestamp.getTime());
			}


			//create user object from values
			User user = new User(id , username , password , name , birthday , address , city , state , pincode , mobileNo , bookIds , 
								active , creationTime , lastVisited);
			return user;
		}
		catch(Exception ex){
			System.out.println("Exception in getUserFromResultSet method in User class");
			throw ex;
		}
	}



	//getting user object from httpServletRequest object
	//getting user object from httpServletRequest object
	public static User getUserObjectFromRequest(HttpServletRequest req) throws Exception{
		try{
			String id = req.getParameter("id");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String birthday = req.getParameter("birthday");
			String address = req.getParameter("address");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String mobileNo = req.getParameter("mobileNo");
			String bookIds = req.getParameter("bookIds");
			String active = req.getParameter("active");
			//String creationTime = req.getParameter("creationTime");

			//creating new user object from values retrieved
			User user = new User(Integer.parseInt(id) , username , password , name , birthday , address , city , 
						state , pincode , mobileNo);

			return user;
		}
		catch(Exception ex){
			System.out.println("Error in getUserObjectFromRequest in User");
			throw ex;
		}
	}



	
}



