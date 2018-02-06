package com.sharebookRequests.factory.mySQL;


import com.sharebookRequests.factory.abstractClasses.GenericBookRequestFactory;
import com.sharebookRequests.staticClasses.ExceptionMessages;
import com.sharebookRequests.exception.entities.*;
import com.sharebookRequests.appEntities.entities.*;
import static com.sharebookRequests.appEntities.enums.BookRequestFields.*;
import static com.sharebookRequests.staticClasses.Tables.*;



public class MySQLBookRequestFactory extends GenericBookRequestFactory {


	public BookRequestFactory(){
		//nothing
	}


	public List<BookRequest> getAllBookRequests() throws Exception {
		try{
			List<BookRequest> bookRequests = new Arraylist<BookRequest>();
			//fieldTypes , fieldValues & fields will be null so that we can get all the entitys
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(BOOKREQUESTS , null , null , null);

			bookRequests = getBookRequestsFromResultSet(rs);

			return bookRequests;
		}
		catch(Exception ex){
			System.out.println("Exception in getAllBookRequests method in MySQLBookRequestFactory class");
			throw ex;
		}
	}


	public BookRequest getBookRequestById(int id) throws NoResultsFoundException , Exception {
		try{
			List<BookRequest> bookRequests = new Arraylist<BookRequest>();

			List<String> fields = Arrays.asList(ID);
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(BOOKREQUESTS , fields , fieldTypes , fieldValues);

			bookRequests = getBookRequestsFromResultSet(rs);

			if(bookRequests.size() == 0){
				throw new NoResultsFoundException("BookRequest Id : " + id);
			}

			return bookRequests.get(0);
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in getBookRequestById method in MySQLBookRequestFactory class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in getBookRequestById method in MySQLBookRequestFactory class");
			throw ex;
		}
	}



	public int addBookRequest(BookRequest bookRequest) throws Exception {
		try{
			List<String> fields = Arrays.asList(REQUEST_TYPE, REQUEST_STATUS, REQUESTER_ID, BOOK_ID, TARGET_ID, ESTIMATED_DAYS, CREATION_TIME,
			 					  LAST_MODIFIED);
			List<String> fieldTypes = Arrays.asList("int", "int", "int", "int", "int", "int", "datetime", "datetime");
			List<Object> fieldValues = Arrays.asList(bookRequest.requestType(), bookRequest.requestStatus(), bookRequest.requestorId(), 
									   bookRequest.bookId(), bookRequest.targetId(), bookRequest.estimatedDays(), 
									   bookRequest.creationTime(), bookRequest.lastModified());

			Insertor insertor = new Insertor();
			int added = insertor.insert(BOOKREQUESTS , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addBookRequest method in MySQLBookRequestFactory class");
			throw ex;
		}
	}



	public int updateBookRequest(BookRequest bookRequest) throws Exception {
		try{
			List<String> fields = Arrays.asList(REQUEST_TYPE, REQUEST_STATUS, REQUESTER_ID, BOOK_ID, TARGET_ID, ESTIMATED_DAYS);
			List<String> fieldTypes = Arrays.asList("int", "int", "int", "int", "int", "int", "int");
			List<Object> fieldValues = Arrays.asList(bookRequest.requestType(), bookRequest.requestStatus(), bookRequest.requestorId(), 
									   bookRequest.bookId(), bookRequest.targetId(), bookRequest.estimatedDays(), bookRequest.id());

			Updator updator = new Updator();
			int updated = updator.update(BOOKREQUESTS ,  fields , fieldTypes , fieldValues);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateBookRequest in MySQLBookRequestFactory class");
			throw ex;
		}
	}

	public int deleteBookRequest(int id) throws Exception {
		try{
			List<String> fields = Arrays.asList(ID);
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Deletor deletor = new Deletor();
			int deleted = deletor.delete(BOOKREQUESTS , fields , fieldTypes , fieldValues);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in deleteBookRequest in MySQLBookRequestFactory class");
			throw ex;
		}
	}














	//getting bookRequests from resultset 


	private BookRequest getBookRequestFromResultSet(ResultSet rs) throws Exception{
		try{
			int id = rs.getInt(ID);
			int requestType = rs.getString(REQUEST_TYPE);
			int requestStatus = rs.getString(REQUEST_STATUS);
			int requesterId = rs.getString(REQUESTER_ID);
			int bookId = rs.getString(BOOK_ID);
			int targetId = rs.getString(TARGET_ID);
			int estimatedDays = rs.getString(ESTIMATED_DAYS);			

			java.util.Date creationTime = null;
			java.util.Date lastModified = null;

			java.sql.Timestamp timestamp = null;

			timestamp = rs.getTimestamp(CREATION_TIME);
			if(timestamp != null){
				creationTime = new java.util.Date(timestamp.getTime());
			}

			timestamp = rs.getTimestamp(LAST_MODIFIED);
			if(timestamp != null){
				lastModified = new java.util.Date(timestamp.getTime());
			}


			//create bookRequest object from values
			BookRequest bookRequest = new BookRequest(id, requestType, requestStatus, requesterId, bookId, targetId, estimatedDays, 
								     creationTime, lastModified);
			return bookRequest;
		}
		catch(Exception ex){
			System.out.println("Error in getBookRequestFromResultSet in MySQLBookRequestFactory class");
			throw ex;
		}
	}


	public List<BookRequest> getBookRequestsFromResultSet(ResultSet rs) throws Exception {
		try{
			List<BookRequest> bookRequests = new ArrayList<BookRequest>();

			while(rs.next()){
				bookRequests.add(getBookRequestFromResultSet(rs));
			} 

			return bookRequests;
		}
		catch(Exception ex){
			System.out.println("Exception in getBookRequestsFromResultSet method in MySQLBookRequestFactory class");
			throw ex;
		}
	}
	
}