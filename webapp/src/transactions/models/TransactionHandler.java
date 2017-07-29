package com.sharebooks.transactions.models;

import com.sharebooks.database.models.*;
import com.sharebooks.transactions.entities.Transaction;
import com.sharebooks.user.entities.User;
import com.sharebooks.commonEntity.entities.Entity;
import com.sharebooks.commonEntity.models.EntityHandler;
import java.util.*;
import java.sql.*;
import static com.sharebooks.staticClasses.Tables.*;
import javax.servlet.http.*;



public class TransactionHandler extends EntityHandler {
	

	public TransactionHandler(){

	}


	//this method will fetch all the transactions in the database
	public List<Transaction> fetchAllTransactions() throws Exception{
		try{
			List<Entity> entities = fetchAllEntities(TRANSACTIONS);
			
			List<Transaction> transactions = convertEntitiesToTransactions(entities);
			
			return transactions;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllTransactions method in TransactionHandler class");
			throw ex;
		}
	}





	//this method will fetch all transactions for given parameters 
	public List<Transaction> fetchTransactions(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			List<Entity> entities = fetchEntities(TRANSACTIONS , fields , fieldTypes , fieldValues);
			
			List<Transaction> transactions = convertEntitiesToTransactions(entities);
			
			return transactions;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchTransactions method in TransactionHandler class");
			throw ex;
		}
	}


	//this method will fetch a book for a given id 
	public Transaction fetchTransactionById(int id) throws Exception{
		try{
			return null;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchTransactionById method in TransactionHandler class");
			throw ex;
		}
	}



	



	public int addBorrowTransaction(Transaction transaction) throws Exception{
		try{
			List<String> fields = Arrays.asList("transactionType" , "status" , "requesterId" , "supplierId" , "bookId" ,"startDate");
			List<String> fieldTypes = Arrays.asList("int" , "int" , "int" , "int" , "int" , "datetime");
			List<Object> fieldValues = Arrays.asList(transaction.getTransactionType() , transaction.getStatus() , transaction.getRequesterId() , 
										transaction.getSupplierId() , transaction.getBookId() , transaction.getStartDate());

			int added = addEntity(TRANSACTIONS , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addTransaction method in TransactionHandler class");
			throw ex;
		}
	}


	public int addBuyTransaction(Transaction transaction) throws Exception{
		try{
			List<String> fields = Arrays.asList("transactionType" , "status" , "requesterId" , "supplierId" , "bookId" ,"startDate" , "endDate");
			List<String> fieldTypes = Arrays.asList("int" , "int" , "int" , "int" , "int" , "datetime" , "datetime");
			List<Object> fieldValues = Arrays.asList(transaction.getTransactionType() , transaction.getStatus() , transaction.getRequesterId() , 
										transaction.getSupplierId() , transaction.getBookId() , transaction.getStartDate() , transaction.getEndDate());

			int added = addEntity(TRANSACTIONS , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addTransaction method in TransactionHandler class");
			throw ex;
		}
	}


	
	public int updateTransaction(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			int updated = updateEntity(TRANSACTIONS ,  fields , fieldTypes , fieldValues , fields.size() - 1);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateTransaction method in TransactionHandler class");
			throw ex;
		}
	}






	//method for converting entities list to transactions list
	public List<Transaction> convertEntitiesToTransactions(List<Entity> entities) throws Exception {
		try{
			List<Transaction> transactions = new ArrayList<Transaction>();
			Transaction transaction;
			int size = entities.size();

			for(int i=0 ; i<size ; i++){
				transaction = (Transaction)entities.get(i);
				transactions.add(transaction);
			}

			return transactions;
		}
		catch(Exception ex){
			System.out.println("Exception in convertEntitiesToTransactions method in TransactionHandler class");
			throw ex;
		}
	}


}