package com.sharebooks.bookRequests.entities;


import com.sharebooks.commonEntity.entities.Entity;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


//immutable class
public final class BookRequest extends Entity implements Comparable<BookRequest> {

	private static final long serialVersionUID = 1L;

	private int id = -1;
	private int requestType;
	private int requestStatus;
	private int requesterId;
	private int bookId;
	private int targetId;
	private java.util.Date creationTime = new java.util.Date();


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "requestType" , "requestStatus" , "requesterId" , "bookId" , "targetId" ,"creationTime"};
		fieldTypes = new String[]{"int" , "int" , "int" , "int" , "int" , "int" , "datetime"};
	}

	public BookRequest(){
		//do nothing 
	}


	public BookRequest(int id , int requestType , int requestStatus , int requesterId , int bookId , int targetId , java.util.Date creationTime){
		this.id = id;
		this.requestType = requestType;
		this.requestStatus = requestStatus;
		this.requesterId = requesterId;
		this.bookId = bookId;
		this.targetId = targetId;
		this.creationTime = creationTime;
	}



	public BookRequest(int id , int requestType , int requestStatus , int requesterId , int bookId , int targetId){
		this.requestType = requestType;
		this.requestStatus = requestStatus;
		this.requesterId = requesterId;
		this.bookId = bookId;
		this.targetId = targetId;
	}



	//compareTo method to be used for sorting
	public int compareTo(BookRequest bookRequest){
		return id - bookRequest.getId();
	}


	//overriding hashCode method
	public int hashCode(){
		return id;
	}


	//overriding equals method
	public boolean equals(Object o){
		if(o == null){
			return false;
		}

		if(!(o instanceof BookRequest)){
			return false;
		}

		if(o == this){
			return true;
		}
		
		BookRequest bookRequest = (BookRequest)o;
		return id == bookRequest.getId();
	}






	//getter methods

	public int getId(){
		return id;
	}

	public int getRequestType(){
		return requestType;
	}

	public int getRequestStatus(){
		return requestStatus;
	}

	public int getRequesterId(){
		return requesterId;
	}

	public int getBookId(){
		return bookId;
	}

	public int getTargetId(){
		return targetId;
	}

	public java.util.Date getCreationTime(){
		return creationTime;
	}


	




	//static methods

	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){

		Object[] fieldValues = {this.id , this.requestType , this.requestStatus , this.requesterId , this.bookId , this.targetId , this.creationTime};

		return fieldValues;
	}









	//method for getting a book object from the result set
	public static BookRequest getBookRequestFromResultSet(ResultSet rs) throws Exception {
		try{
			int id = rs.getInt("id");
			int requestType = rs.getInt("requestType");
			int requestStatus = rs.getInt("requestStatus");
			int requesterId = rs.getInt("requesterId");
			int bookId = rs.getInt("bookId");
			int targetId = rs.getInt("targetId");

			java.util.Date creationTime = null;
			java.sql.Timestamp timestamp = rs.getTimestamp("creationTime");
			if(timestamp != null){
				creationTime = new java.util.Date(timestamp.getTime());
			}

			BookRequest bookRequest = new BookRequest(id , requestType , requestStatus , requesterId , bookId , targetId , creationTime);

			return bookRequest;
		}
		catch(Exception ex){
			System.out.println("Exception in getBookRequestFromResultSet method in BookRequests class");
			throw ex;
		}
	}


	//getting book object from httpServletRequest object
	public static BookRequest getBookRequestObjectFromRequest(HttpServletRequest req) throws Exception{
		try{
			String id = req.getParameter("id");
			String requestType = req.getParameter("userId");
			String requestStatus = req.getParameter("name");
			String requesterId = req.getParameter("authorName");
			String bookId = req.getParameter("category");
			String targetId = req.getParameter("subcategory");
			

			//creating new bookRequest object from values retrieved
			//id value will be -1 by default which won't be used
			BookRequest bookRequest = new BookRequest(Integer.parseInt(id) , Integer.parseInt(requestType) , Integer.parseInt(requestStatus) , Integer.parseInt(requesterId) , Integer.parseInt(bookId) , Integer.parseInt(targetId));
			
			return bookRequest;
		}
		catch(Exception ex){
			System.out.println("Error in getBookRequestObjectFromRequest in BookRequest");
			throw ex;
		}
	}


} 