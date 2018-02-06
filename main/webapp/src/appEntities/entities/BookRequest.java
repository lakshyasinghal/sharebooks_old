package com.sharebooks.appEntities.entities;


import javax.servlet.http.*;
import static com.sharebooks.appEntities.enums.BookRequestType.*;
import static com.sharebooks.appEntities.enums.BookRequestStatus.*;
import java.sql.*;
import java.util.*;


//immutable class
public final class BookRequest extends Entity implements Comparable<BookRequest> {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final int requestType;
	private final int requestStatus;
	private final int requesterId;
	private final int bookId;
	private final int targetId;
	private final int estimatedDays;
	private final java.util.Date creationTime;
	private final java.util.Date lastModified;


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "requestType" , "requestStatus" , "requesterId" , "bookId" , "targetId" , "estimatedDays" , "creationTime" , "lastModified"};
		fieldTypes = new String[]{"int" , "int" , "int" , "int" , "int" , "int" , "int" , "datetime" , "datetime"};
	}


	//constructor for getting object from database
	public BookRequest(int id , int requestType , int requestStatus , int requesterId , int bookId , int targetId , int estimatedDays , java.util.Date creationTime , java.util.Date lastModified){
		this.id = id;
		this.requestType = requestType;
		this.requestStatus = requestStatus;
		this.requesterId = requesterId;
		this.bookId = bookId;
		this.targetId = targetId;
		this.estimatedDays = estimatedDays;
		this.creationTime = creationTime;
		this.lastModified = lastModified;
	}


	//getting object from client request
	public BookRequest(int requestType , int requesterId , int bookId , int targetId , int estimatedDays){
		this.id = -1;
		this.requestType = requestType;
		this.requestStatus = PENDING;
		this.requesterId = requesterId;
		this.bookId = bookId;
		this.targetId = targetId;
		this.estimatedDays = estimatedDays;
		creationTime = new java.util.Date();
		lastModified = new java.util.Date();
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

	public int getEstimatedDays(){
		return estimatedDays;
	}

	public java.util.Date getCreationTime(){
		return creationTime;
	}

	public java.util.Date getLastModified(){
		return lastModified;
	}


	




	//static methods

	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){

		Object[] fieldValues = {this.id , this.requestType , this.requestStatus , this.requesterId , this.bookId , this.targetId , this.estimatedDays , this.creationTime , this.lastModified};

		return fieldValues;
	}



	public String getBookRequestString() throws Exception {
		try{
			StringBuilder bookRequestString = new StringBuilder();
			bookRequestString.append("\nid - " + this.id);
			bookRequestString.append("\nrequestType - " + this.requestType);
			bookRequestString.append("\nrequestStatus - " + this.requestStatus);
			bookRequestString.append("\nrequesterId - " + this.requesterId);
			bookRequestString.append("\nbookId - " + this.bookId);
			bookRequestString.append("\ntargetId - " + this.targetId);
			bookRequestString.append("\nestimatedDays - " + this.estimatedDays);
			bookRequestString.append("\ncreationTime - " + this.creationTime);
			bookRequestString.append("\nlastModified - " + this.lastModified);

			return bookRequestString.toString();

		}
		catch(Exception ex){
			System.out.println("Exception in toString method in BookRequests class");
			throw ex;
		}
	}


}