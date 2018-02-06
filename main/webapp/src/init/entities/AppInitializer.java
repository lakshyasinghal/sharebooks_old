package com.sharebooks.init.entities;


import java.util.*;
import com.sharebooks.fileHandling.interfaces.FileManager;
import com.sharebooks.fileHandling.models.propertiesHandling.PropertyFileManager;
import com.sharebooks.cache.entities.*;
import com.sharebooks.cache.interfaces.Cache;
import com.sharebooks.connectionPool.entities.*;
import com.sharebooks.connectionPool.models.*;
import com.sharebooks.appEntities.models.CityManager;
import com.sharebooks.appEntities.models.StateManager;
//import com.sharebooks.connectionPool.interfaces.Pool;
//import com.sharebooks.mail.models.MailSender;
import static com.sharebooks.staticClasses.Properties.*;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.exception.entities.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import static com.sharebooks.staticClasses.Properties.*;


//this class will initialize all the things required for the entire application
public class AppInitializer {	

	private String PROPERTIES_FILE_PATH = "/Users/lakshya.singhal/Desktop/Development/Personal/Git/ShareBooks/main/config/Properties.txt";
	private Map<String , String> propertyMapper;
	private FileManager fileManager = new PropertyFileManager();


	public void initApp() throws IncorrectPathException , Exception{
		try{
			//get properties from properties file
			getProperties();
			showProperties();

			//instantiate resources such as cache , connection pool , logger
			initLoggers();

			initCache();

			initConnector();

			initConnectionPool();

			initConnectionPoolManager();

			initCityManager();

			initStateManager();

			//initMailSender();

			//initialize above resources by providing properties' values

			//create instances of handlers and managers
			Resources.init();

			//create other constant value containers such as city list , state list , initial books set list
		}
		catch(IncorrectPathException ex){
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Error in initApp in AppInitializer");
			System.out.println("\nError : " + ex + "\n");
		}
	}






	private void getProperties() throws Exception{
		try{
			propertyMapper = fileManager.getDataFromFile(PROPERTIES_FILE_PATH);
		}
		catch(Exception ex){
			System.out.println("Error in readPropertiesFile in AppInitializer");
			throw ex;
		}
	}


	private void showProperties() throws Exception {
		try{
			for (Map.Entry<String, String> entry : propertyMapper.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}
		}
		catch(Exception ex){
			System.out.println("Error in showProperties in AppInitializer");
			throw ex;
		}
	}



	private void initLoggers() throws Exception {
		try{
			LoggerInitializer loggerInitializer = new LoggerInitializer(propertyMapper);
			loggerInitializer.initLoggers();
		}
		catch(Exception ex){
			System.out.println("Error in initLoggers in AppInitializer");
			throw ex;
		}
	}



	private void initCache() throws Exception {
		try{
			int capacity = 0;

			Cache booksCache = BooksCache.getBooksCache();
			Cache usersCache = UsersCache.getUsersCache();

			if(propertyMapper.containsKey(BOOKS_CACHE_CAPACITY)){
				capacity = Integer.parseInt(propertyMapper.get(BOOKS_CACHE_CAPACITY));
				booksCache.init(capacity);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + BOOKS_CACHE_CAPACITY);
			}

			if(propertyMapper.containsKey(USERS_CACHE_CAPACITY)){
				capacity = Integer.parseInt(propertyMapper.get(USERS_CACHE_CAPACITY));
				usersCache.init(capacity);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + USERS_CACHE_CAPACITY);
			}
		}
		catch(Exception ex){
			System.out.println("Error in initCache in AppInitializer");
			throw ex;
		}
	}


	private void initConnector() throws Exception {
		try{
			String server = "";
			String username = "";
			String password = "";
			String portNum = "";
			String dbName = "";

			if(propertyMapper.containsKey(DATABASE_SERVER)){
				server = propertyMapper.get(DATABASE_SERVER);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + DATABASE_SERVER);
			}

			if(propertyMapper.containsKey(DATABASE_PORT)){
				portNum = propertyMapper.get(DATABASE_PORT);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + DATABASE_PORT);
			}

			if(propertyMapper.containsKey(DATABASE_USERNAME)){
		 		username = propertyMapper.get(DATABASE_USERNAME);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + DATABASE_USERNAME);
			}

			if(propertyMapper.containsKey(DATABASE_PASSWORD)){
		 		password = propertyMapper.get(DATABASE_PASSWORD);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + DATABASE_PASSWORD);
			}

			if(propertyMapper.containsKey(DATABASE_NAME)){
		 		dbName = propertyMapper.get(DATABASE_NAME);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + DATABASE_NAME);
			}

			ConnectorInitializer.initializeConnector(server , portNum , username , password , dbName);
		}
		catch(Exception ex){
			System.out.println("Error in initConnector in AppInitializer");
			throw ex;
		}
	}


	private void initConnectionPool() throws Exception {
		try{
			int capacity = 0;
			ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

			if(propertyMapper.containsKey(CONNECTION_POOL_CAPACITY)){
				capacity = Integer.parseInt(propertyMapper.get(CONNECTION_POOL_CAPACITY));
				connectionPool.init(capacity);
			}
			else{
				throw new Exception(PROPERTY_NOT_FOUND + " : " + CONNECTION_POOL_CAPACITY);
			}
		}
		catch(Exception ex){
			System.out.println("Error in initConnectionPool in AppInitializer");
			throw ex;
		}
	}


	private void initConnectionPoolManager() throws Exception {
		try{
			ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
			if(connectionPool == null){
				throw new Exception(CONNECTION_POOL_NOT_INITIALIZED);
			}
			ConnectionPoolManager.init(connectionPool);
		}
		catch(Exception ex){
			System.out.println("Error in initConnectionPoolManager in AppInitializer");
			throw ex;
		}
	}


	private void initCityManager() throws Exception{
		try{
			CityManager.cityManager().init();
		}
		catch(Exception ex){
			System.out.println("Error in initCityManager in AppInitializer");
			throw ex;
		}
	}



	private void initStateManager() throws Exception{
		try{
			StateManager.stateManager().init();
		}
		catch(Exception ex){
			System.out.println("Error in initStateManager in AppInitializer");
			throw ex;
		}
	}

	// public void initMailSender() throws Exception {
	// 	try{
	// 		String username = "";
	// 		String password = "";
	// 		String host = "";
	// 		String port = "";
	// 		if(propertyMapper.containsKey(MAIL_USERNAME) && propertyMapper.containsKey(MAIL_PASSWORD) && propertyMapper.containsKey(MAIL_HOST) && propertyMapper.containsKey(MAIL_PORT)){
	// 			username = propertyMapper.get(MAIL_USERNAME);
	// 			password = propertyMapper.get(MAIL_PASSWORD);
	// 			host = propertyMapper.get(MAIL_HOST);
	// 			port = propertyMapper.get(MAIL_PORT);

	// 			MailSender.init(username , password , host , port);
	// 		}
	// 		else{
	// 			throw new Exception(PROPERTIES_NOT_FOUND);
	// 		}
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in initMailSender in AppInitializer");
	// 		throw ex;
	// 	}
	// } 

}