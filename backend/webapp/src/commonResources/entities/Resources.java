package com.sharebooks.commonResources.entities;


import com.sharebooks.books.models.BooksHandler;
import com.sharebooks.user.models.UserHandler;
import com.sharebooks.bookRequests.models.BookRequestHandler;
//import com.sharebooks.json.models.JSONBuilder;
import com.sharebooks.response.models.ResponseHandler;


public class Resources {

	private static BooksHandler booksHandler;
	private static UserHandler userHandler;
	private static BookRequestHandler bookRequestHandler;
	// private static JSONBuilder jsonBuilder;
	private static ResponseHandler responseHandler;


	//no-arg constructor
	public Resources(){

	}


	public static void init() throws Exception {
		try{
			System.out.println("initializing resources");

			System.out.println("initializing BooksHandler");
			booksHandler = new BooksHandler();

			System.out.println("initializing UserHandler");
			userHandler = new UserHandler();

			System.out.println("initializing BookRequestHandler");
			bookRequestHandler = new BookRequestHandler();

			// System.out.println("initializing JSONBuilder");
			// jsonBuilder = new JSONBuilder();

			System.out.println("initializing ResponseHandler");
			responseHandler = new ResponseHandler();
		}
		catch(Exception ex){
			System.out.println("Exception in init method in Resources class");
			throw ex;
		}
	}


	public static BooksHandler getBooksHandler(){
		return booksHandler;
	}

	public static UserHandler getUserHandler(){
		return userHandler;
	}

	public static BookRequestHandler getBookRequestHandler(){
		return bookRequestHandler;
	}

	// public static JSONBuilder getJSONBuilder(){
	// 	return jsonBuilder;
	// }

	public static ResponseHandler getResponseHandler(){
		return responseHandler;
	}

}