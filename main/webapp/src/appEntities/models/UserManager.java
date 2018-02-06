package com.shareusers.appEntities.models;


import javax.servlet.http.*;
import com.shareusers.database.models.*;
import com.shareusers.appEntities.entities.User;
import com.shareusers.appEntities.entities.Entity;
import static com.shareusers.staticClasses.Tables.*;
import com.shareusers.factory.interfaces.Factory;
import com.shareusers.exception.entities.*;
import java.util.*;
import java.sql.*;



public class UserManager extends EntityManager {

	private GenericUserFactory userFactory;

	public UserManager(){
		//no arg constructor
	}


	public void init(GenericUserFactory userFactory) throws Exception{
		try{
			if(this.userFactory == null){
				this.userFactory = userFactory;
			}
			else{
				throw new IllegalInitializationException();
			}
		}
		catch(IllegalInitializationException ex){
			System.out.println("init method in UserManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in init method in UserManager");
			throw ex;
		}
	}



	//this method will fetch all the users in the database
	public List<User> getAllUsers() throws Exception{
		try{
			List<User> users = userFactory.getAllUsers();
			
			return users;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllUsers method in UserManager class");
			throw ex;
		}
	}




	//this method will fetch a user for a given id 
	public User getUserById(int userId) throws NoResultsFoundException , Exception{
		try{
			User user = userFactory.getUserById();

			return user;
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in fetchUserById method in UserManager class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchUserById method in UserManager class");
			throw ex;
		}
	}




	public int addUser(HttpServletRequest req) throws Exception{
		try{
			User user = userFactory.getUserFromHttpRequest(req);

			int added = userFactory.addUser(user);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addUser method in UserManager class");
			throw ex;
		}
	}


	
	public int updateUser(HttpServletRequest req) throws Exception{
		try{
			User user = userFactory.getUserFromHttpRequest(req);
			
			int updated = userFactory.updateUser(user);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateUser method in UserManager class");
			throw ex;
		}
	}


}