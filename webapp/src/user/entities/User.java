package com.sharebooks.user.entities;


import com.sharebooks.commonEntity.entities.Entity;
import java.util.*;
import java.sql.*;


public final class User extends Entity implements Comparable<User> {

	private static final long serialVersionUID = 1L;

	private int id = -1;
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


	private static final String[] fields;
	private static final String[] fieldTypes;


	static {
		fields = new String[]{"id" , "username" , "password" , "name" , "birthday" , "address" ,"city" , "state" , "pincode" , "mobileNo" , "bookIds" ,
				 "active" , "creationTime"};
		fieldTypes = new String[]{"int" , "string" , "string" , "string" , "string" , "string" , "string" , "string" , "string" , "string" ,
					 "string" , "int" , "datetime"};
	}


	public User(){
		//creationTime = new Date();
	}

	public User(int id , String username , String password , String name , String birthday , String address , String city , String state , String pincode , 
		String mobileNo , String bookIds , int active , java.util.Date creationTime){
		
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






	//setter methods

	public void setId(int id){
		this.id = id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setCity(String city){
		this.city = city;
	}

	public void setState(String state){
		this.state = state;
	} 

	public void setPincode(String pincode){
		this.pincode = pincode;
	} 

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public void setBookIds(String bookIds){
		this.bookIds = bookIds;
	}

	public void setActive(int active){
		this.active = active;
	}

	public void setCreationTime(java.util.Date creationTime){
		this.creationTime = creationTime;
	}





	public String[] getFields(){
		return fields; 
	}

	public String[] getFieldTypes(){
		return fields; 
	}


	public Object[] getFieldValues(){
		Object[] fieldValues = {this.id , this.username , this.password , this.name , this.birthday , this.address , this.city , 
								this.state , this.pincode , this.mobileNo , this.bookIds , this.active , this.creationTime};

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
			java.sql.Timestamp timestamp = rs.getTimestamp("creationTime");
			if(timestamp != null){
				creationTime = new java.util.Date(timestamp.getTime());
			}


			//create user object from values
			User user = new User(id , username , password , name , birthday , address , city , state , pincode , mobileNo , bookIds , 
								active , creationTime);
			return user;
		}
		catch(Exception ex){
			System.out.println("Exception in getUserFromResultSet method in User class");
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