package com.sharebooks.factory.abstractClasses;


import com.sharebooks.factory.interfaces.*;
import com.sharebooks.staticClasses.ExceptionMessages;
import com.sharebooks.exception.entities.*;
import com.sharebooks.appEntities.entities.*;
import static com.sharebooks.appEntities.enums.BookFields.*;
import static com.sharebooks.staticClasses.Tables.*;
import static com.sharebooks.util.HttpRequestParser.*;



public abstract class GenericBookFactory implements Factory {


	public GenericBookFactory(){
		//nothing
	}


	public abstract List<Book> getAllBooks() throws Exception;

	public abstract Book getBookById(int id) throws Exception;

	public abstract int addBook(Book book) throws Exception;

	public abstract int updateBook(Book book) throws Exception;

	public abstract int deleteBook(int id) throws Exception;



	//get book from http return request

	public Book getBookFromHttpRequest(HttpServletRequest req) throws Exception{
		try{
			int id = getInt(req , ID);
			int userId = getInt(req , USER_ID);
			String name = getString(req , NAME);
			String authorName = getString(req , AUTHOR_NAME);
			String category = getString(req , CATEGORY);
			String subcategory = getString(req , SUBCATEGORY);
			int pages = getInt(req , PAGES);
			String image = getString(req , IMAGE);
			int available = getInt(req , AVAILABLE);
			int buy = getInt(req , BUY);
			int rent = getInt(req , RENT);
			double buyAmount = getDouble(req , BUY_AMOUNT);
			double rentAmount = getDouble(req , RENT_AMOUNT);
			
			Book book = new Book(id , userId , name , authorName , category , subcategory , pages , image , available , buy , rent , buyAmount , rentAmount);

			return book;	
		}
		catch(Exception ex){
			System.out.println("Error in getBookFromHttpRequest in HttpRequestFactory");
			throw ex;
		}
	}

	
	
}