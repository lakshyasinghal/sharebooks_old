package com.sharebooks.login.models;

import javax.servlet.http.HttpServletRequest;        //interface
import com.sharebooks.books.models.BooksHandler;
import com.sharebooks.books.entities.Book;
import com.sharebooks.user.models.UserHandler;
import com.sharebooks.user.entities.User;
import com.sharebooks.commonResources.entities.Resources;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import com.sharebooks.database.models.*;



public class SignIn {

	private final HttpServletRequest request;


	//not declaring no arg contructor so if it is accessed from somewhere I get an exception as I don't want it to be used


	public SignIn(HttpServletRequest request) throws Exception {
		this.request = request;
	}



	public User getUser() throws Exception{
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			List<String> fields = Arrays.asList("username" , "password");
			List<String> fieldTypes = Arrays.asList("string" , "string"); 
			List<Object> fieldValues = Arrays.asList(username , password); 

			UserHandler userHandler = Resources.getUserHandler();
			User user = userHandler.fetchUser(fields , fieldTypes , fieldValues);

			return user;
		}
		catch(Exception ex){
			System.out.println("Exception in getUser method in SignIn class");
			throw ex;
		}
	}
	




	// public void setInitialResources(HttpServletRequest req , ServletContext servletContext) throws Exception {
	// 	try{
	// 		//getting some books and passing them with request parameter
	// 		BooksHandler booksHandler = new BooksHandler();
	// 		int booksCount = Integer.parseInt(servletContext.getInitParameter("BOOKSCOUNT"));
	// 		List<Book> someBooks = booksHandler.fetchBooksByNumber(booksCount);
	// 		req.setAttribute("books" , someBooks);

	// 		//setting bookCategories
	// 		List<String> bookCategories = booksHandler.fetchBookCategories();
	// 		req.setAttribute("bookCategories" , bookCategories);
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Exception in getInitialResources method in SignIn class");
	// 		throw ex;
	// 	}
	// }



}