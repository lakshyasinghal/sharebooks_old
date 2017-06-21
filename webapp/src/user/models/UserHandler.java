package com.sharebooks.user.models;


import javax.servlet.http.*;
import com.sharebooks.database.models.*;
import com.sharebooks.user.entities.User;
import com.sharebooks.commonEntity.entities.Entity;
import com.sharebooks.commonEntity.models.EntityHandler;
import static com.sharebooks.staticClasses.Tables.*;
import java.util.*;
import java.sql.*;



public class UserHandler extends EntityHandler {



	public UserHandler(){
		//no arg constructor
	}



	public List<User> fetchUsers(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			List<Entity> entities = fetchEntities(USER , fields , fieldTypes , fieldValues);
			
			List<User> users = convertEntitiesToUsers(entities);
			
			return users;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchUsers method in UserHandler class");
			throw ex;
		}
	}


	public User fetchUser(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			Entity entity = fetchEntity(USER , fields , fieldTypes , fieldValues);
			User user = null;

			if(entity != null){
				user = (User)entity;
			}
			
			return user;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchUser method in UserHandler class");
			throw ex;
		}
	}



	public User fetchUserById(int id) throws Exception {
		try{
			List<String> fields = Arrays.asList("id");
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Entity entity = fetchEntity(USER , fields , fieldTypes , fieldValues);
			User user = (User)entity;

			return user;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchUserById method in UserHandler class");
			throw ex;
		}
	}



	//will return status 2 if user already exists and 1 if successfully added
	public int addUser(User user) throws Exception{
		try{
			List<String> fields = null;
			List<String> fieldTypes = null;
			List<Object> fieldValues = null;

			int added = 0;

			//if username already exists in database , return value of 2
			if(usernameAlreadyExists(user.getUsername())){
				return 2;
			}

			fields = Arrays.asList("username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobilNo" , "bookIds" , "active" , "creationTime");
			fieldTypes = Arrays.asList("string" , "string" , "string" , "string" , "string" , "string" , "string" , "string"  , "String" , "string" , "int" , "dateTime");
			fieldValues = Arrays.asList(user.getUsername() , user.getPassword() , user.getName() , user.getBirthday() , user.getAddress() , user.getCity()
					, user.getState() , user.getPincode() , user.getMobileNo() , user.getBookIds() , user.getActive() , user.getCreationTime());
			
			added = addEntity(USER , fields , fieldTypes , fieldValues);	
			
			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addUser method in UserHandler class");
			throw ex;
		}
	}




	//will update user using the id of the user
	public int updateUser(User user) throws Exception{
		try{
			List<String> fields = null;
			List<String> fieldTypes = null;
			List<Object> fieldValues = null;

			fields = Arrays.asList("username" , "password" , "name" , "birthday" , "address" , "city" , "state" , "pincode" , "mobilNo" , "id");
			fieldTypes = Arrays.asList("string" , "string" , "string" , "string" , "string" , "string" , "string" , "string"  , "string" , "int");
			fieldValues = Arrays.asList(user.getUsername() , user.getPassword() , user.getName() , user.getBirthday() , user.getAddress() , user.getCity()
					, user.getState() , user.getPincode() , user.getMobileNo() , user.getId());
			
			int updated = updateEntity(USER , fields , fieldTypes , fieldValues , 1);	
			
			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateUser method in UserHandler");
			throw ex;
		}
	}




	//checks if the username already exists in the database and returns true or false
	public boolean usernameAlreadyExists(String username) throws Exception{
		try{
			List<String> fields = null;
			List<String> fieldTypes = null;
			List<Object> fieldValues = null;

			boolean alreadyExists = false; 
			
			fields = Arrays.asList("username");
			fieldTypes = Arrays.asList("string");
			fieldValues = Arrays.asList(username);


			alreadyExists = alreadyExists(USER , fields , fieldTypes , fieldValues);

			return alreadyExists;
		}
		catch(Exception ex){
			System.out.println("Exception in usernameAlreadyExists method in UserHandler class");
			throw ex;
		}
	}



	//method for converting entities list to books list
	public List<User> convertEntitiesToUsers(List<Entity> entities) throws Exception {
		try{
			List<User> users = new ArrayList<User>();
			User user;
			int size = entities.size();

			for(int i=0 ; i<size ; i++){
				user = (User)entities.get(i);
				users.add(user);
			}

			return users;
		}
		catch(Exception ex){
			System.out.println("Exception in convertEntitiesToUsers method in UserHandler class");
			throw ex;
		}
	}





	//getting user object from httpServletRequest object
	public User getUserObjectFromRequest(HttpServletRequest req) throws Exception{
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
			String creationTime = req.getParameter("creationTime");

			//creating new user object from values retrieved
			User user = new User(Integer.parseInt(id) , username , password , name , birthday , address , city , 
						state , pincode , mobileNo , bookIds , Integer.parseInt(active) , new java.util.Date());

			return user;
		}
		catch(Exception ex){
			System.out.println("Error in getUserObjectFromRequest in UserHandler");
			throw ex;
		}
	}

}