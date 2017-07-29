package com.sharebooks.transactions.entities;


import com.sharebooks.commonEntity.entities.Entity;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


//immutable class
public final class Transaction extends Entity implements Comparable<Transaction> {

	private int id;
	private int transactionType;
	private int status;
	private int requesterId;
	private int supplierId;
	private int bookId;
	private java.util.Date startDate = new java.util.Date();
	private java.util.Date endDate = new java.util.Date();
	private double amount;


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "transactionType" , "status" , "requesterId" , "supplierId" , "bookId" ,"startDate" , "endDate" , "amount"};
		fieldTypes = new String[]{"int" , "int" , "int" , "int" , "int" , "int" , "datetime" , "datetime" , "double"};
	}



	public Transaction(){
		//no arg constructor
	}

	//constructor to be used when data comes from database
	public Transaction(int id , int transactionType , int status , int requesterId , int supplierId , int bookId , java.util.Date startDate , java.util.Date endDate , double amount){
		this.id = id;
		this.transactionType = transactionType;
		this.status = status;
		this.requesterId = requesterId;
		this.supplierId = supplierId;
		this.bookId = bookId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
	}



	//constructor to be used when data comes from request
	public Transaction(int id , int transactionType , int status , int requesterId , int supplierId , int bookId){
		this.id = id;
		this.transactionType = transactionType;
		this.status = status;
		this.requesterId = requesterId;
		this.supplierId = supplierId;
		this.bookId = bookId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
	}







	//compareTo method to be used for sorting
	public int compareTo(Transaction transaction){
		return id - transaction.getId();
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

		if(!(o instanceof Transaction)){
			return false;
		}

		if(o == this){
			return true;
		}
		
		Transaction transaction = (Transaction)o;
		return id == transaction.getId();
	}








	public int getId(){
		return id;
	}

	public int getTransactionType(){
		return transactionType;
	}

	public int getStatus(){
		return status;
	}

	public int getRequesterId(){
		return requesterId;
	}

	public int getSupplierId(){
		return supplierId;
	}

	public int getBookId(){
		return bookId;
	}

	public java.util.Date getStartDate(){
		return startDate;
	}

	public java.util.Date getEndDate(){
		return endDate;
	}

	public double getAmount(){
		return amount;
	}

	






	//static methods

	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){

		Object[] fieldValues = {this.id , this.transactionType , this.status , this.requesterId , this.supplierId , this.bookId , this.startDate ,
								 this.endDate , this.amount};

		return fieldValues;
	}



	




	//method for getting a Transaction object from the result set
	public static Transaction getTransactionFromResultSet(ResultSet rs) throws Exception {
		try{
			int id = rs.getInt("id");
			int transactionType = rs.getInt("transactionType");
			int status = rs.getInt("status");
			int requesterId = rs.getInt("requesterId");
			int supplierId = rs.getInt("supplierId");
			int bookId = rs.getInt("bookId");
			
			java.sql.Timestamp timestamp = null;

			java.util.Date startDate = null;
			timestamp = rs.getTimestamp("startDate");
			if(timestamp != null){
				startDate = new java.util.Date(timestamp.getTime());
			}

			java.util.Date endDate = null;
			timestamp = rs.getTimestamp("endDate");
			if(timestamp != null){
				endDate = new java.util.Date(timestamp.getTime());
			}
			
			double amount = rs.getDouble("amount");

			Transaction transaction = new Transaction(id , transactionType , status , requesterId , supplierId , bookId , startDate , endDate , amount);

			return transaction;
		}
		catch(Exception ex){
			System.out.println("Exception in getTransactionFromResultSet method in Transaction class");
			throw ex;
		}
	}


	//getting Transaction object from httpServletRequest object
	public static Transaction getTransactionObjectFromRequest(HttpServletRequest req) throws Exception{
		try{
			//id will be required as there will be different scenarios
			String id = req.getParameter("id");
			String transactionType = req.getParameter("transactionType");
			String status = req.getParameter("status");
			String requesterId = req.getParameter("requesterId");
			String supplierId = req.getParameter("supplierId");
			String bookId = req.getParameter("bookId");
			

			//creating new book object from values retrieved
			//id value will be passed as -1
			Transaction transaction = new Transaction(Integer.parseInt(id) , Integer.parseInt(id) , Integer.parseInt(status) , Integer.parseInt(requesterId) , 
										Integer.parseInt(supplierId) , Integer.parseInt(bookId));
	
			return transaction;
		}
		catch(Exception ex){
			System.out.println("Error in getTransactionObjectFromRequest in Transaction");
			throw ex;
		}
	}


} 