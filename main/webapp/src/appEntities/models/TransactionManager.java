package com.sharebooks.appEntities.models;


import com.sharebooks.database.models.*;
import com.sharebooks.appEntities.entities.Transaction;
import com.sharebooks.appEntities.entities.User;
import com.sharebooks.appEntities.entities.Entity;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.exception.entities.*;
import java.util.*;
import java.sql.*;
import static com.sharebooks.staticClasses.Tables.*;
import javax.servlet.http.*;



public class TransactionManager extends EntityManager {
	

	public TransactionManager(){

	}


	public void init(Factory factory) throws Exception{
		try{
			super.init(factory);
		}
		catch(IllegalInitializationException ex){
			System.out.println("init method in TransactionManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in init method in TransactionManager");
			throw ex;
		}
	}


	//this method will fetch all the transactions in the database
	public List<Transaction> fetchAllTransactions() throws Exception{
		try{
			List<Entity> entities = fetchAllEntities(TRANSACTIONS);
			
			List<Transaction> transactions = convertEntitiesToTransactions(entities);
			
			return transactions;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllTransactions method in TransactionManager class");
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
			System.out.println("Exception in fetchTransactions method in TransactionManager class");
			throw ex;
		}
	}


	//this method will fetch a book for a given id 
	public Transaction fetchTransactionById(int id) throws Exception{
		try{
			return null;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchTransactionById method in TransactionManager class");
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
			System.out.println("Exception in addTransaction method in TransactionManager class");
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
			System.out.println("Exception in addTransaction method in TransactionManager class");
			throw ex;
		}
	}


	
	public int updateTransaction(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			int updated = updateEntity(TRANSACTIONS ,  fields , fieldTypes , fieldValues , fields.size() - 1);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateTransaction method in TransactionManager class");
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
			System.out.println("Exception in convertEntitiesToTransactions method in TransactionManager class");
			throw ex;
		}
	}

}