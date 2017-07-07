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

	public void init(){
		//nothing here
	}



	//return response containing the homePage.jsp
	public Response getHomePage(HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			Response response = null;

			response = new Response(JSP , req , res , HOMEPAGE_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getHomePage in HomePageHandler");
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
			//User user = req.getSession().getAttribute("user");

			System.out.println();
			System.out.println();
			System.out.println("Inside getAllBooks controller");
			System.out.println("books list - " + books.toString());
			System.out.println();
			System.out.println();

			Collections.sort(books);

			List<Entity> entities = new ArrayList<Entity>();

			for(Book book : books){
				entities.add(book);
			}
			
			response = new Response(JSON , res , true , GET_BOOKS_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getAllBooks in HomePageHandler");
			throw ex;
		}
	}

}