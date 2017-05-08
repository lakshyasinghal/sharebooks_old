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
			String name = req.getParameter("name");
			String authorName = req.getParameter("authorName");
			String category = req.getParameter("category");
			String pages = req.getParameter("pages");
			String imagePath = req.getParameter("imagePath");


			Book book = new Book(name , authorName , category , Integer.parseInt(pages) , imagePath);
			//the value of validUser will be either 0 or 1

			BooksHandler booksHandler = new BooksHandler();
			int inserted = booksHandler.addBook(book);
		}
		catch(Exception ex){
			System.out.println("Error in AddBookController");
		}
	}

}