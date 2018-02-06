package com.sharebooks.appEntities.models;


import com.sharebooks.database.models.*;
import com.sharebooks.appEntities.entities.Book;
import com.sharebooks.appEntities.entities.Entity;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.factory.abstractClasses.GenericBookFactory;
import com.sharebooks.exception.entities.*;
import java.util.*;
import java.sql.*;
import static com.sharebooks.staticClasses.Tables.*;
import javax.servlet.http.*;



public class BookManager {

	private GenericBookFactory bookFactory;
	private Cache bookCache;


	public BookManager(){

	}


	public void init(GenericBookFactory bookFactory , Cache bookCache) throws Exception{
		try{
			if(this.bookFactory == null){
				this.bookFactory = bookFactory;
			}
			else{
				throw new IllegalInitializationException();
			}

			if(this.bookCache == null){
				this.bookCache = bookCache;
			}
			else{
				throw new IllegalInitializationException();
			}
		}
		catch(IllegalInitializationException ex){
			System.out.println("init method in BookManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in init method in BookManager");
			throw ex;
		}
	}



	//this method will fetch all the books in the database
	public List<Book> getAllBooks() throws Exception{
		try{
			List<Book> books = bookFactory.getAllBooks();
			
			return books;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllBooks method in BookManager class");
			throw ex;
		}
	}




	//this method will fetch a book for a given id 
	public Book getBookById(int bookId) throws NoResultsFoundException , Exception{
		try{
			Book book = bookFactory.getBookById();

			return book;
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in fetchBookById method in BookManager class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchBookById method in BookManager class");
			throw ex;
		}
	}




	public int addBook(HttpServletRequest req) throws Exception{
		try{
			Book book = bookFactory.getBookFromHttpRequest(req);

			int added = bookFactory.addBook(book);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addBook method in BookManager class");
			throw ex;
		}
	}


	
	public int updateBook(HttpServletRequest req) throws Exception{
		try{
			Book book = bookFactory.getBookFromHttpRequest(req);
			
			int updated = bookFactory.updateBook(book);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateBook method in BookManager class");
			throw ex;
		}
	}

}