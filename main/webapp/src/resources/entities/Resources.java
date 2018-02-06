package com.sharebooks.resources.entities;


import com.sharebooks.interfaces.Manager;
import com.sharebooks.appEntities.models.*;
//import com.sharebooks.json.models.JSONBuilder;
import com.sharebooks.response.models.ResponseManager;
//import com.sharebooks.mail.models.MailManager;
import com.sharebooks.cache.entities.*;
import com.sharebooks.cache.interfaces.Cache;
import com.sharebooks.factory.entities.*;
import com.sharebooks.factory.interfaces.Factory;


public class Resources {

	private static BookManager bookManager;
	private static UserManager userManager;
	private static BookRequestManager bookRequestManager;
	//private static JSONBuilder jsonBuilder;
	private static ResponseManager responseManager;
	private static CityManager cityManager;
	private static StateManager stateManager;
	//private static ConnectionPoolManager connectionPoolManager;
	private static Cache booksCache;
	private static Cache usersCache;
	private static Factory databaseFactory;
	private static Factory httpRequestFactory;
	//private static MailManager mailManager;


	//no-arg constructor
	private Resources(){

	}


	public static void init() throws Exception {
		try{
			System.out.println("Getting Resources\n\n");

			System.out.println("Getting BookManager");
			bookManager = new BookManager();

			System.out.println("Getting UserManager");
			userManager = new UserManager();

			System.out.println("Getting BookRequestManager");
			bookRequestManager = new BookRequestManager();

			System.out.println("Getting CityManager");
			cityManager = CityManager.cityManager();

			System.out.println("Getting StateManager");
			stateManager = StateManager.stateManager();

			System.out.println("Getting ResponseManager");
			responseManager = new ResponseManager();

			// System.out.println("Getting ConnectionPoolManager");
			// connectionPoolManager = new ConnectionPoolManager();

			System.out.println("Getting BooksCache");
			booksCache = BooksCache.getBooksCache();

			System.out.println("Getting UsersCache");
			usersCache = UsersCache.getUsersCache();

			System.out.println("Getting databaseFactory");
			databaseFactory = DatabaseFactory.databaseFactory();

			System.out.println("Getting httpRequestFactory");
			httpRequestFactory = HttpRequestFactory.httpRequestFactory();
			// System.out.println("Getting MailManager");
			// mailManager = new MailManager();
		}
		catch(Exception ex){
			System.out.println("Exception in init method in Resources class");
			throw ex;
		}
	}


	public static BookManager getBookManager(){
		return bookManager;
	}

	public static UserManager getUserManager(){
		return userManager;
	}

	public static BookRequestManager getBookRequestManager(){
		return bookRequestManager;
	}

	public static ResponseManager getResponseManager(){
		return responseManager;
	}

	// public static ConnectionPoolManager getConnectionPoolManager(){
	// 	return userManager;
	// }

	public static Cache booksCache(){
		return booksCache;
	}

	public static Cache usersCache(){
		return usersCache;
	}

	public static CityManager cityManager(){
		return cityManager;
	}

	public static StateManager stateManager(){
		return stateManager;
	}

	public static Factory databaseFactory(){
		return databaseFactory;
	}

	public static Factory httpRequestFactory(){
		return httpRequestFactory;
	}


	// public static MailManager getMailManager(){
	// 	return mailManager;
	// }

}