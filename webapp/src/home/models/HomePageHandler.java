package com.sharebooks.home.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.home.interfaces.HomePageHandlerInterface;
import com.sharebooks.commonResources.entities.Resources;
import com.sharebooks.commonEntity.entities.Entity;
import com.sharebooks.books.entities.Book;
import com.sharebooks.books.models.BooksHandler;
import com.sharebooks.user.entities.User;
import com.sharebooks.user.models.UserHandler;
import com.sharebooks.response.models.ResponseHandler;
import com.sharebooks.response.entities.Response;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import static com.sharebooks.staticClasses.JspPages.*;
//import static com.sharebooks.staticClasses.JSONResultTypes.*;




public class HomePageHandler implements HomePageHandlerInterface {

	public void init(){
		//nothing here
	}



	// //return response containing the homePage.jsp
	// public Response getHomePage(HttpServletRequest req , HttpServletResponse res) throws Exception{
	// 	try{
	// 		Response response = null;

	// 		response = new Response(JSP , req , res , HOMEPAGE_JSP);

	// 		return response;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in getHomePage in HomePageHandler");
	// 		throw ex;
	// 	}
	// }



	//get user from the session object and return a response which will contain json containing the user object
	// public Response getUser(HttpServletRequest req , HttpServletResponse res) throws Exception{
	// 	try{
	// 		Response response = null;
	// 		User user = (User)req.getSession().getAttribute("user");

	// 		List<Entity> entities = new ArrayList<Entity>();
	// 		entities.add(user);

	// 		response = new Response(JSON , res , true , GET_USER_SUCCESSFUL , entities);

	// 		return response;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in getUser in HomePageHandler");
	// 		throw ex;
	// 	}
	// }


	//json response type Response will be sent back stating whether book was added or not
	public Response addBook(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try{
			Response response = null;
			BooksHandler booksHandler = Resources.getBooksHandler();
			Book book = booksHandler.getBookObjectFromRequest(req);

			int added = booksHandler.addBook(book);

			switch(added){
				case 0 :
					response = new Response(JSON , res , false , ADD_BOOK_FAILED);
					break;
				case 1 :
					response = new Response(JSON , res , true , ADD_BOOK_SUCCESSFUL);
					break;
				default :
					break;
			}

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in addBook in HomePageHandler");
			throw ex;
		}
	}


	

	// public Response getAllBooks(HttpServletRequest req , HttpServletResponse res) throws Exception {
	// 	try {
	// 		Response response = null;
	// 		BooksHandler booksHandler = Resources.getBooksHandler();
	// 		List<Book> books = booksHandler.fetchAllBooks();
	// 		//User user = req.getSession().getAttribute("user");

		
	// 		System.out.println("\n\nInside getAllBooks controller");
	// 		System.out.println("books list - " + books.toString() + "\n\n");
		
	// 		Collections.sort(books);

	// 		List<Entity> entities = new ArrayList<Entity>();

	// 		for(Book book : books){
	// 			entities.add(book);
	// 		}
			
	// 		response = new Response(JSON , res , true , GET_BOOKS_SUCCESSFUL , entities);

	// 		return response;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in getAllBooks in HomePageHandler");
	// 		throw ex;
	// 	}
	// }



	//will return the json type response containing the ids of the books in the array
	// public Response filterByCategory(HttpServletRequest req , HttpServletResponse res) throws Exception {
	// 	try {
	// 		Response response = null;
	// 		List<Entity> entities = new ArrayList<Entity>();
	// 		BooksHandler booksHandler = Resources.getBooksHandler();

	// 		List<Book> booksList = booksHandler.fetchBooksByCategory(req);
	// 		Collections.sort(booksList);
	// 		for(Book book : booksList){
	// 			entities.add(book);
	// 		}
			
	// 		response = new Response(JSON , res , true , GET_BOOKS_BY_CATEGORY_SUCCESSFUL , entities);

	// 		return response;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in filterByCategory in HomePageHandler");
	// 		throw ex;
	// 	}
	// }




	//will return the json type response containing the ids of the books in the array
	// public Response filterBySearch(HttpServletRequest req , HttpServletResponse res) throws Exception {
	// 	try {
	// 		Response response = null;
	// 		List<Entity> entities = new ArrayList<Entity>();
	// 		BooksHandler booksHandler = Resources.getBooksHandler();

	// 		List<Book> booksList = booksHandler.fetchBooksBySearch(req);

	// 		Collections.sort(booksList);

	// 		for(Book book : booksList){
	// 			entities.add(book);
	// 		}
			
	// 		response = new Response(JSON , res , true , GET_BOOKS_BY_SEARCH_SUCCESSFUL , entities);

	// 		return response;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in filterByCategory in HomePageHandler");
	// 		throw ex;
	// 	}
	// }




	//viewBook method will send a jsp response containing the viewBook.jsp page
	public Response viewBook(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;

			response = new Response(JSP , req , res , VIEW_BOOK_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in viewBook in HomePageHandler");
			throw ex;
		}
	}


	//viewBook method will send a jsp response containing the viewBook.jsp page
	public Response getNotifications(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;

			response = new Response(JSP , req , res , VIEW_BOOK_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in viewBook in HomePageHandler");
			throw ex;
		}
	}



	public Response updateUser(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;

			UserHandler userHandler = Resources.getUserHandler();
			User user = userHandler.getUserObjectFromRequest(req);

			int updated = userHandler.updateUser(user);

			if(updated == 1){
				response = new Response(JSON , res , true , USER_UPDATE_SUCCESSFUL);
			}
			else{
				response = new Response(JSON , res , false , USER_UPDATE_FAILED);
			}

			//response = new Response(JSP , req , res , VIEW_BOOK_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in modifyUser in HomePageHandler");
			throw ex;
		}
	}



	
	

}