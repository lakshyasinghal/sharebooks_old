package com.sharebooks.viewBook.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.viewBook.interfaces.ViewBookHandlerInterface;
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
import static com.sharebooks.util.StringHandler.*;
//import static com.sharebooks.staticClasses.JSONResultTypes.*;




public class ViewBookHandler implements ViewBookHandlerInterface {

	private static boolean debugging = true;

	public void init(){
		//nothing here
	}


	public Response getSimilarBooks(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try{
			if(debugging){
				System.out.println("\n\nInside getSimilarResults in ViewBookHandler\n\n");
			}

			Response response = null;

			String name = req.getParameter("bookName");

			if(name == null){
				response = new Response(JSON , res , false , INCORRECT_REQUEST);
				return response;
			}

			if(debugging){
				System.out.println("\nbookName --- " + name);
			}

			List<String> fields = new ArrayList<String>();
			List<String> fieldTypes = new ArrayList<String>();
			List<Object> fieldValues = new ArrayList<Object>();

			fields.add("name");
			fieldTypes.add("string");
			fieldValues.add(name);


			List<Entity> entities = new ArrayList<Entity>();
			BooksHandler booksHandler = Resources.getBooksHandler();

			List<Book> booksList = booksHandler.fetchBooks(fields , fieldTypes , fieldValues);

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
			
			response = new Response(JSON , res , true , GET_SIMILAR_BOOKS_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getSimilarResults in ViewBookHandler");
			throw ex;
		}
	}

	

}