package com.sharebooks.webRequests.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.webRequests.interfaces.HomePageHandlerInterface;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.appEntities.entities.*;
import com.sharebooks.appEntities.models.*;
import com.sharebooks.response.models.ResponseManager;
import com.sharebooks.response.entities.Response;
import com.sharebooks.factory.interfaces.Factory;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.util.StringHandler.*;
//import static com.sharebooks.staticClasses.JSONResultTypes.*;




public class HomePageHandler {

	private static boolean debugging = true;

	public void init(){
		//nothing here
	}



	//json response type Response will be sent back stating whether book was added or not
	public Response addBook(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try{
			Response response = null;
			BookManager bookManager = Resources.getBookManager();
			Book book = Resources.httpRequestFactory().book(req);

			int added = bookManager.addBook(book);

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


	



	//will return the json type response containing the books for the required category and subcategory
	public Response filterByCategory(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			if(debugging){
				System.out.println("\n\nInside filterByCategory in HomePageHandler\n\n");
			}

			Response response = null;

			String category = req.getParameter("category");
			String subcategory = req.getParameter("subcategory");

			if(category == null){
				response = new Response(JSON , res , false , INCORRECT_REQUEST);
				return response;
			}

			if(debugging){
				System.out.println("\n\ncategory --- " + category);
				System.out.println("\n\nsubcategory --- " + category);
			}

			List<String> fields = new ArrayList<String>();
			List<String> fieldTypes = new ArrayList<String>();
			List<Object> fieldValues = new ArrayList<Object>();

			fields.add("category");
			fieldTypes.add("string");
			fieldValues.add(category);

			if(subcategory != null){
				fields.add("subcategory");
				fieldTypes.add("string");
				fieldValues.add(subcategory);
			}

			List<Entity> entities = new ArrayList<Entity>();
			BookManager bookManager = Resources.getBookManager();

			List<Book> booksList = bookManager.fetchBooks(fields , fieldTypes , fieldValues);

			if(debugging){
				System.out.println("\n\nbooksList --- " + booksList.toString());
			}

			Collections.sort(booksList);

			if(debugging){
				System.out.println("\n\nsorted booksList --- " + booksList.toString());
			}

			for(Book book : booksList){
				entities.add(book);
			}
			
			response = new Response(JSON , res , true , GET_BOOKS_BY_CATEGORY_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in filterByCategory in HomePageHandler");
			throw ex;
		}
	}




	//will return the json type response containing the ids of the books in the array
	public Response filterBySearch(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			if(debugging){
				System.out.println("\n\nInside filterBySearch in HomePageHandler\n\n");
			}

			Response response = null;
			
			String searchString = req.getParameter("searchString");

			if(searchString == null){
				response = new Response(JSON , res , false , INCORRECT_REQUEST);
				return response;
			}

			if(debugging){
				System.out.println("\n\nsearchString --- " + searchString);
			}

			List<Entity> entities = new ArrayList<Entity>();
			BookManager bookManager = Resources.getBookManager();

			List<Book> booksList = bookManager.fetchAllBooks();
			List<Book> requiredBooksList = new ArrayList<Book>();

			String bookString = null;
			boolean required = false;

			//will iterate through all the books and filter out the required books
			for(Book book : booksList){
				bookString = book.getBookString();
				
				if(debugging){
					System.out.println("\n\nlower case bookString --- " + bookString.toLowerCase());
					System.out.println("\n\nlower case searchString --- " + searchString.toLowerCase());
				}

				//check if the search string is a subsequence of the book string
				required = isSubSequence(bookString.toLowerCase() , searchString.toLowerCase());
				if(required){
					requiredBooksList.add(book);
				}
			}

			if(debugging){
				System.out.println("\n\nrequiredBooksList --- " + requiredBooksList);
			}

			for(Book book : requiredBooksList){
				entities.add(book);
			}
			
			response = new Response(JSON , res , true , GET_BOOKS_BY_SEARCH_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in filterByCategory in HomePageHandler");
			throw ex;
		}
	}




	// //viewBook method will send a jsp response containing the viewBook.jsp page
	// public Response viewBook(HttpServletRequest req , HttpServletResponse res) throws Exception {
	// 	try {
	// 		Response response = new Response(JSP , req , res , VIEW_BOOK_JSP);

	// 		return response;
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in viewBook in HomePageHandler");
	// 		throw ex;
	// 	}
	// }


	//viewBook method will send a jsp response containing the viewBook.jsp page
	public Response getNotifications(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;

			//response = new Response(JSP , req , res , VIEW_BOOK_JSP);

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

			UserManager userManager = Resources.getUserManager();
			User user = Resources.httpRequestFactory().user(req);

			int updated = userManager.updateUser(user);

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