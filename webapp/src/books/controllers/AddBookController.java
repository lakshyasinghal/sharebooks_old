package com.sharebooks.books.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.books.entities.Book;
import com.sharebooks.books.models.BooksHandler;



public class AddBookController extends HttpServlet {

	public void init(){
		//nothing here
	}


	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {

	}


	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
		try{
			String userId = req.getParameter("userId");
			String name = req.getParameter("name");
			String authorName = req.getParameter("authorName");
			String category = req.getParameter("category");
			String pages = req.getParameter("pages");
			String image = req.getParameter("image");

			Book book = new Book(Integer.parseInt(userId) , name , authorName , category , Integer.parseInt(pages) , image);

			BooksHandler booksHandler = new BooksHandler();
			int inserted = booksHandler.addBook(book);

			String jsonResponse = getJsonResponse(inserted);

			sendJsonResponse(res , jsonResponse);
		}
		catch(Exception ex){
			System.out.println("Error in AddBookController : " + ex);
		}
	}

	public void sendJsonResponse(HttpServletResponse response , String jsonResponse) throws Exception {
		try{
			response.setContentType("application/json");
			    
			PrintWriter out = response.getWriter();
			
			out.print(jsonResponse);
			out.flush();
		}
		catch(Exception ex){
			System.out.println("Error in sendJsonResponse in AddBookController");
			throw ex;
		}
	}




	public String getJsonResponse(int inserted) throws Exception {
		try{
			String jsonResponse = "{success:";

			switch(inserted){
				case 0 :
					jsonResponse += "false , " + "message:\"The book was not added\"";
					break;
				case 1 :
					jsonResponse += "true , " + "message:\"The book has been added\"";
					break;
				default :
					break;
			}

			jsonResponse += "}";

			return jsonResponse;
		}
		catch(Exception ex){
			System.out.println("Error in getJsonResponse in AddBookController");
			throw ex;
		}
	}

}