package com.sharebooks.user.models;

import com.sharebooks.database.models.*;
import com.sharebooks.user.entities.User;
import com.sharebooks.tables.Tables;
import java.util.*;
import java.sql.*;



public class UserHandler {

	private List<String> fieldTypes = null;
	private List<Object> fieldValues = null;
	private List<String> fields = null;
	//private int setPropertyCounter = 5;


	// public void init(List<String> fieldTypes , List<Object> fieldValues , List<String> fields){

	// }



	public UserHandler(){

	}


	public UserHandler(List<String> fieldTypes , List<Object> fieldValues , List<String> fields){
		
	}


	// public List<User> fetchUsers() throws Exception{
	// 	try{
	// 		//fieldTypes , fieldValues & fields will be null so that we can get all the users
	// 		Fetcher fetcher = new Fetcher(TABLENAME , fieldTypes , fieldValues , fields);
	// 		ResultSet rs = (ResultSet)fetcher.fetch(1);

	// 		List<User> users = new ArrayList();

	// 		while(rs.next()){
	// 			User user = getUserFromResultSet(rs);
	// 			users.add(user);
	// 		}

	// 		return users;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Exception in fetchUsers method in UserHandler class : " + ex);
	// 		throw ex;
	// 	}
	// }



	//will return status 2 if user already exists and 1 if successfully added
	public int addUser(User user) throws Exception{
		try{
			List<String> fields = null;
			List<String> fieldTypes = null;
			List<Object> fieldValues = null;
			int inserted = 0;

			if(alreadyExists(user)){
				return 2;
			}


			//adding username and password in user table
			{
				fields = Arrays.asList("username" , "password");
				//setFields(fields);

				fieldTypes = Arrays.asList("string" , "string");
				//setFieldTypes(fieldTypes);

				fieldValues = Arrays.asList(user.getUsername() , user.getPassword());
				//setFieldValues(fieldValues);

				Insertor insertor = new Insertor(Tables.USER , fieldTypes , fieldValues , fields);
				inserted = insertor.insert();

				if(inserted == 0){
					return inserted;
				}
			}


			int id = getIdOfUser(user);		


			//adding user information in user_info table
			{
				fields = Arrays.asList("userId" , "name" , "age" , "address" , "city" , "state" , "bookIds" , "creationTime");
				//setFields(fields);

				fieldTypes = Arrays.asList("int" , "string" , "int" , "string" , "string" , "string" , "string" , "dateTime");
				//setFieldTypes(fieldTypes);

				fieldValues = Arrays.asList(id , user.getName() , user.getAge() , user.getAddress() , user.getCity()
					, user.getState() , user.getBookIds() , user.getCreationTime());
				//setFieldValues(fieldValues);

				Insertor insertor = new Insertor(Tables.USERINFO , fieldTypes , fieldValues , fields);
				inserted = insertor.insert();
			}

			return inserted;
		}
		catch(Exception ex){
			System.out.println("Exception in addUser method in UserHandler class");
			throw ex;
		}
	}


	public boolean alreadyExists(User user) throws Exception{
		try{
			List<String> fields = null;
			List<String> fieldTypes = null;
			List<Object> fieldValues = null;

			int exists = 0; 
			
			fields = Arrays.asList("username");
			setFields(fields);

			fieldTypes = Arrays.asList("string");
			setFieldTypes(fieldTypes);

			fieldValues = Arrays.asList(user.getUsername());
			setFieldValues(fieldValues);

			Checker checker = new Checker(Tables.USER , fieldTypes , fieldValues , fields);
			exists = checker.check();

			if(exists == 1){
				System.out.println("User already exists");
				return true;
			}
			else{
				return false;
			}
			
		}
		catch(Exception ex){
			System.out.println("Exception in alreadyExists method in UserHandler class");
			throw ex;
		}
	}



	public int getIdOfUser(User user) throws Exception {
		try{
			List<String> fields = null;
			List<String> fieldTypes = null;
			List<Object> fieldValues = null;

			fields = Arrays.asList("username" , "password");
			setFields(fields);

			fieldTypes = Arrays.asList("string" , "string");
			setFieldTypes(fieldTypes);

			fieldValues = Arrays.asList(user.getUsername() , user.getPassword());
			setFieldValues(fieldValues);
			//fieldTypes , fieldValues & fields will be null so that we can get all the books
			Fetcher fetcher = new Fetcher(Tables.USER , Arrays.asList("string" , "string") , Arrays.asList(user.getUsername() , user.getPassword()) , fields);
			ResultSet rs = (ResultSet)fetcher.fetch(1);

			int id = getIdFromResultSet(rs);

			return id;
	    }
	    catch(Exception ex){
	    	System.out.println("Exception in getIdOfUser method in UserHandler class");
			throw ex;
	    }
	} 



	public int getIdFromResultSet(ResultSet rs) throws Exception{
		try{
			rs.next();
			int id = rs.getInt("id");
			
			return id;
		}
		catch(Exception ex){
			System.out.println("Exception in getIdFromResultSet method in UserHandler class");
			throw ex;
		}
	}




	public void setFieldTypes(List<String> fieldTypes){
		this.fieldTypes = fieldTypes;
	}

	public void setFieldValues(List<Object> fieldValues){
		this.fieldValues = fieldValues;
	}

	public void setFields(List<String> fields){
		this.fields = fields;
	}

}