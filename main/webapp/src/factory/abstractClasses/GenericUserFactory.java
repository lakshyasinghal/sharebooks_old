package com.sharebooks.factory.abstractClasses;


import com.sharebooks.factory.interfaces.*;
import com.sharebooks.staticClasses.ExceptionMessages;
import com.sharebooks.exception.entities.*;
import com.sharebooks.appEntities.entities.*;
import static com.sharebooks.appEntities.enums.UserFields.*;
import static com.sharebooks.staticClasses.Tables.*;
import static com.sharebooks.util.HttpRequestParser.*;



public abstract class GenericUserFactory implements Factory {


	public GenericUserFactory(){
		//nothing
	}


	public abstract List<User> getAllUsers() throws Exception;

	public abstract User getUserById(int id) throws Exception;

	public abstract int addUser(User user) throws Exception;

	public abstract int updateUser(User user) throws Exception;

	public abstract int deleteUser(int id) throws Exception;




	//getting user from generic request

	public User getUserFromHttpRequest(HttpServletRequest req) throws Exception{
		try{
			int id = getInt(req , ID);
			String username = getString(req , USERNAME);
			String password = getString(req , PASSWORD);
			String name = getString(req , NAME);
			String birthday = getString(req , BIRTHDAY);
			String address = getString(req , ADDRESS);
			String city = getString(req , CITY);
			String state = getString(req , STATE);
			String pincode = getString(req , PINCODE);
			String mobileNo = getString(req , MOBILE_NO);
			String bookIds = getString(req , BOOK_IDS);
			int active = getInt(req , ACTIVE);
			
			User user = new User(id , username , password , name , birthday , address , city , state , pincode , mobileNo , bookIds , active);

			return user;
		}
		catch(Exception ex){
			System.out.println("Error in getUserFromHttpRequest in UserFactory");
			throw ex;
		}
	}

	
	
}