package com.sharebooks.appEntities.models;


import com.sharebooks.database.models.*;
import com.sharebooks.appEntities.entities.BookRequest;
import com.sharebooks.appEntities.entities.User;
import com.sharebooks.appEntities.entities.Entity;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.exception.entities.*;
import java.util.*;
import java.sql.*;
import static com.sharebooks.staticClasses.Tables.*;
import javax.servlet.http.*;



public class BookRequestManager {

	private GenericBookRequestFactory bookRequestFactory;
	

	public BookRequestManager(){

	}


	public void init(GenericBookRequestFactory bookRequestFactory) throws Exception{
		try{
			if(this.bookRequestFactory == null){
				this.bookRequestFactory = bookRequestFactory;
			}
			else{
				throw new IllegalInitializationException();
			}
		}
		catch(IllegalInitializationException ex){
			System.out.println("init method in BookRequestManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in init method in BookRequestManager");
			throw ex;
		}
	}




	//this method will fetch all the book requests in the database
	public List<BookRequest> fetchAllBookRequests() throws Exception{
		try{
			List<Entity> entities = fetchAllEntities(BOOK_REQUESTS);
			
			List<BookRequest> bookRequests = convertEntitiesToBookRequests(entities);
			
			return bookRequests;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllBookRequests method in BookRequestManager class");
			throw ex;
		}
	}




	//this method will fetch all book requests for given parameters 
	public List<BookRequest> fetchBookRequests(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			List<Entity> entities = fetchEntities(BOOK_REQUESTS , fields , fieldTypes , fieldValues);
			
			List<BookRequest> bookRequests = convertEntitiesToBookRequests(entities);
			
			return bookRequests;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchBookRequests method in BookRequestManager class");
			throw ex;
		}
	}


	//this method will fetch a BookRequest for a given id 
	public BookRequest fetchBookRequestById(int id) throws Exception{
		try{
			return null;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchBookRequestById method in BookRequestManager class");
			throw ex;
		}
	}




	public int addBookRequest(BookRequest bookRequest) throws Exception{
		try{
			if(debugging){
				System.out.println("\nInside addBookRequest in BookRequestManager\n");
				System.out.println("\nbookRequest ----- \n" + bookRequest.getBookRequestString());
			}

			List<String> fields = Arrays.asList("requestType" , "requestStatus" , "requesterId" , "bookId" , "targetId" , "estimatedDays" , "creationTime" , "lastModified");
			List<String> fieldTypes = Arrays.asList("int" , "int" , "int" , "int" , "int" , "int" , "dateTime" , "dateTime");
			List<Object> fieldValues = Arrays.asList(bookRequest.getRequestType() , bookRequest.getRequestStatus() , bookRequest.getRequesterId() , 
										bookRequest.getBookId() , bookRequest.getTargetId() , bookRequest.getEstimatedDays() , bookRequest.getCreationTime() , bookRequest.getLastModified());

			int added = addEntity(BOOK_REQUESTS , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addBookRequest method in BookRequestManager class");
			throw ex;
		}
	}


	
	public int updateBookRequest(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			int updated = updateEntity(BOOK_REQUESTS ,  fields , fieldTypes , fieldValues , fields.size() - 1);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateBookRequest method in BookRequestManager class");
			throw ex;
		}
	}




	//method for converting entities list to BookRequests list
	public List<BookRequest> convertEntitiesToBookRequests(List<Entity> entities) throws Exception {
		try{
			List<BookRequest> bookRequests = new ArrayList<BookRequest>();
			BookRequest bookRequest;
			int size = entities.size();

			for(int i=0 ; i<size ; i++){
				bookRequest = (BookRequest)entities.get(i);
				bookRequests.add(bookRequest);
			}

			return bookRequests;
		}
		catch(Exception ex){
			System.out.println("Exception in convertEntitiesToBookRequests method in BookRequestsHandler class");
			throw ex;
		}
	}

}