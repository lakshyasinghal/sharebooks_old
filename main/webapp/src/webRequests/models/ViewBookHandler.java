package com.sharebooks.webRequests.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.webRequests.interfaces.ViewBookHandlerInterface;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.appEntities.entities.*;
import com.sharebooks.appEntities.models.*;
import com.sharebooks.response.models.ResponseManager;
import com.sharebooks.response.entities.Response;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.util.StringHandler.*;
//import static com.sharebooks.staticClasses.JSONResultTypes.*;




public class ViewBookHandler {

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
			
			response = new Response(JSON , res , true , GET_SIMILAR_BOOKS_SUCCESSFUL , entities);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getSimilarResults in ViewBookHandler");
			throw ex;
		}
	}

	

}