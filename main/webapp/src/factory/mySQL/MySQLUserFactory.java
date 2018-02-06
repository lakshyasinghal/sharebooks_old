package com.shareusers.factory.mySQL;


import com.shareusers.factory.abstractClasses.GenericUserFactory;
import com.shareusers.staticClasses.ExceptionMessages;
import com.shareusers.exception.entities.*;
import com.shareusers.appEntities.entities.*;
import static com.shareusers.appEntities.enums.UserFields.*;
import static com.shareusers.staticClasses.Tables.*;



public class MySQLUserFactory extends GenericUserFactory {


	public UserFactory(){
		//nothing
	}


	public List<User> getAllUsers() throws Exception {
		try{
			List<User> users = new Arraylist<User>();
			//fieldTypes , fieldValues & fields will be null so that we can get all the entitys
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(USERS , null , null , null);

			users = getUsersFromResultSet(rs);

			return users;
		}
		catch(Exception ex){
			System.out.println("Exception in getAllUsers method in MySQLUserFactory class");
			throw ex;
		}
	}


	public User getUserById(int id) throws NoResultsFoundException , Exception {
		try{
			List<User> users = new Arraylist<User>();

			List<String> fields = Arrays.asList(ID);
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(USERS , fields , fieldTypes , fieldValues);

			users = getUsersFromResultSet(rs);

			if(users.size() == 0){
				throw new NoResultsFoundException("User Id : " + id);
			}

			return users.get(0);
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in getUserById method in MySQLUserFactory class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in getUserById method in MySQLUserFactory class");
			throw ex;
		}
	}



	public int addUser(User user) throws Exception {
		try{
			List<String> fields = Arrays.asList(USERNAME , PASSOWRD , NAME , BIRTHDAY , ADDRESS , CITY , STATE , PINCODE , MOBILE_NO
							 , BOOK_IDS , ACTIVE , CREATION_TIME , LAST_VISITED);
			List<String> fieldTypes = Arrays.asList("string", "string", "string", "birthday", "address", "string", "string", 
									  "string", "string", "string", "int", "datetime", "datetime");
			List<Object> fieldValues = Arrays.asList(user.username(), user.password(), user.name(), user.birthday(), user.address(),
										user.city(), user.state(), user.pincode(), user.mobileNo(), user.bookIds(), user.active(),
										user.creationTime(), user.lastVisited());

			Insertor insertor = new Insertor();
			int added = insertor.insert(USERS , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addUser method in MySQLUserFactory class");
			throw ex;
		}
	}



	public int updateUser(User user) throws Exception {
		try{
			List<String> fields = Arrays.asList(USERNAME , PASSOWRD , NAME , BIRTHDAY , ADDRESS , CITY , STATE , PINCODE , MOBILE_NO
							 , BOOK_IDS , ACTIVE);
			List<String> fieldTypes = Arrays.asList("string", "string", "string", "birthday", "address", "string", "string", 
									  "string", "string", "string", "int", "int");
			List<Object> fieldValues = Arrays.asList(user.username(), user.password(), user.name(), user.birthday(), user.address(),
										user.city(), user.state(), user.pincode(), user.mobileNo(), user.bookIds(), user.active(),
										user.id());

			Updator updator = new Updator();
			int updated = updator.update(USERS ,  fields , fieldTypes , fieldValues);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateUser in MySQLUserFactory class");
			throw ex;
		}
	}

	public int deleteUser(int id) throws Exception {
		try{
			List<String> fields = Arrays.asList(ID);
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Deletor deletor = new Deletor();
			int deleted = deletor.delete(USERS , fields , fieldTypes , fieldValues);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in deleteUser in MySQLUserFactory class");
			throw ex;
		}
	}














	//getting users from resultset 


	private User getUserFromResultSet(ResultSet rs) throws Exception{
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
			System.out.println("Error in getUserFromResultSet in MySQLUserFactory class");
			throw ex;
		}
	}


	public List<User> getUsersFromResultSet(ResultSet rs) throws Exception {
		try{
			List<User> users = new ArrayList<User>();

			while(rs.next()){
				users.add(getUserFromResultSet(rs));
			} 

			return users;
		}
		catch(Exception ex){
			System.out.println("Exception in getUsersFromResultSet method in MySQLUserFactory class");
			throw ex;
		}
	}
	
}