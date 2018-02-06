package com.sharebooks.factory.abstractClasses;


import com.sharebooks.factory.interfaces.*;
import com.sharebooks.staticClasses.ExceptionMessages;
import com.sharebooks.exception.entities.*;
import com.sharebooks.appEntities.entities.*;
import static com.sharebooks.appEntities.enums.BookRequestFields.*;
import static com.sharebooks.staticClasses.Tables.*;
import static com.sharebooks.util.HttpRequestParser.*;



public abstract class GenericBookRequestFactory implements Factory {


	public GenericBookRequestFactory(){
		//nothing
	}


	public abstract List<BookRequest> getAllBookRequests() throws Exception;

	public BookRequest getBookRequestById(int id) throws NoResultsFoundException, Exception;

	public abstract int addBookRequest(BookRequest bookRequest) throws Exception;

	public abstract int updateBookRequest(BookRequest bookRequest) throws Exception;

	public abstract int deleteBookRequest(int id) throws Exception;

	public BookRequest getBookRequestFromHttpRequest(HttpServletRequest req) throws Exception{
		try{
			int id = getInt(req , ID);
			int requestType = getInt(req , REQUEST_TYPE);
			int requesterId = getInt(req , REQUESTER_ID);
			int bookId = getInt(req , BOOK_ID);
			int targetId = getInt(req , TARGET_ID);
			int estimatedDays = getInt(req , ESTIMATED_DAYS);
			
			BookRequest bookRequest = new BookRequest(id , requestType , requesterId , bookId , targetId , estimatedDays);
			
			return bookRequest;
		}
		catch(Exception ex){
			System.out.println("Error in getBookRequestFromHttpRequest in GenericBookRequestFactory");
			throw ex;
		}
	}

	
	
}