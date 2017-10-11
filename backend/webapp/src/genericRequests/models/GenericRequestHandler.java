package com.sharebooks.genericRequests.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.genericRequests.interfaces.GenericRequestInterface;
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




public class GenericRequestHandler implements GenericRequestInterface {


	//return response containing cover page jsp
	public Response getCoverPage(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = new Response(JSP , req , res , COVERPAGE_JSP);
			
			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getCoverPage in GenericRequestHandler");
			throw ex;
		}
	}

	public Response signIn(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = null;
			ResponseHandler responseHandler = Resources.getResponseHandler();
			UserHandler userHandler = Resources.getUserHandler();

			String username = req.getParameter("username");
			String password = req.getParameter("password");

			System.out.println("username - " + username);
			System.out.println("password - " + password);

			List<String> fields = Arrays.asList("username" , "password");
			List<String> fieldTypes = Arrays.asList("string" , "string"); 
			List<Object> fieldValues = Arrays.asList(username , password); 

			
			User user = userHandler.fetchUser(fields , fieldTypes , fieldValues);

			//when there is no user for the given login credentials
			if(user == null){
				response = new Response(JSON , res , false , LOGIN_FAILED);
			}
			//when there is a user for the given login credentials
			else{
				//start the session 
				HttpSession session = req.getSession();
				//add user object to the session
				session.setAttribute("user" , user);

				response = new Response(JSON , res , true , LOGIN_SUCCESSFUL);
			}

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in signIn in GenericRequestHandler");
			throw ex;
		}
	}

	

	public Response signUp(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{	
			Response response = null;
			ResponseHandler responseHandler = Resources.getResponseHandler();
			UserHandler userHandler = Resources.getUserHandler();

			User user = User.getUserObjectFromRequest(req);

			//the value should be 0 or 1 depending on whether the user has been registered or not
			int userAdded = userHandler.addUser(user);

			int statusCode = -1;
			boolean success = false;

			switch(userAdded){
				case 0:
					statusCode = USERINFO_INCOMPLETE;
					success = false;
					break;
				case 1:
					statusCode = ADD_USER_SUCCESSFUL;
					success = true;
					break;
				case 2 :
				 	statusCode = USERNAME_ALREADY_EXISTS;
				 	success = false;
				 	break;
				default :
					break;
			}

			response = new Response(JSON , res , success , statusCode);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in signUp in GenericRequestHandler");
			throw ex;
		}
	}



	public Response signOut(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = null;
			HttpSession session = req.getSession(false);

			//invalidate the session
			session.invalidate();
			response = new Response(JSP , req , res , SIGNOUT_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in signOut in GenericRequestHandler");
			throw ex;
		}
	}



	//return response containing the homePage.jsp
	public Response getHomePage(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = new Response(JSP , req , res , HOMEPAGE_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getHomePage in GenericRequestHandler");
			throw ex;
		}
	}



	//get user from the session object and return a response which will contain json containing the user object
	public Response getUser(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = null;
			UserHandler userHandler = Resources.getUserHandler();

			int userId = Integer.parseInt(req.getParameter("userId"));

			User user = userHandler.fetchUserById(userId);

			List<Entity> entities = new ArrayList<Entity>();

			entities.add(user);
			
			response = new Response(JSON , res , true , GET_USER_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getUser in GenericRequestHandler");
			throw ex;
		}
	}



	//get user from the session object and return a response which will contain json containing the user object
	public Response getBook(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = null;
			BooksHandler booksHandler = Resources.getBooksHandler();

			int bookId = Integer.parseInt(req.getParameter("bookId"));

			Book book = booksHandler.fetchBookById(bookId);

			List<Entity> entities = new ArrayList<Entity>();

			entities.add(book);
			
			response = new Response(JSON , res , true , GET_USER_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getBook in GenericRequestHandler");
			throw ex;
		}
	}



	

	public Response getAllBooks(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;
			BooksHandler booksHandler = Resources.getBooksHandler();
			List<Book> books = booksHandler.fetchAllBooks();
			//User user = req.2getSession().getAttribute("user");

			Collections.sort(books);

			List<Entity> entities = new ArrayList<Entity>();

			for(Book book : books){
				entities.add(book);
			}
			
			response = new Response(JSON , res , true , GET_BOOKS_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getAllBooks in GenericRequestHandler");
			throw ex;
		}
	}




	//viewBook method will send a jsp response containing the viewBook.jsp page
	public Response viewBook(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = new Response(JSP , req , res , VIEW_BOOK_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in viewBook in HomePageHandler");
			throw ex;
		}
	}


	//checkout method will send a jsp response containing the checkout.jsp page
	public Response checkout(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = new Response(JSP , req , res , CHECKOUT_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in viewBook in HomePageHandler");
			throw ex;
		}
	}

}