package com.sharebooks.commonEntity.models;

import com.sharebooks.database.models.*;
import com.sharebooks.commonEntity.interfaces.EntityGetterInterface;
import com.sharebooks.commonEntity.entities.Entity;
import com.sharebooks.books.entities.Book;
import com.sharebooks.user.entities.User;
import com.sharebooks.bookRequests.entities.BookRequest;
import com.sharebooks.transactions.entities.Transaction;
import static com.sharebooks.staticClasses.Tables.*;
import java.sql.*;
import java.util.*;



public class EntityGetter implements EntityGetterInterface {


	
	public List<Entity> getEntitiesFromResultSet(String table , ResultSet rs) throws Exception {
		try{
			List<Entity> entities = new ArrayList<Entity>();

			while(rs.next()){
				entities.add(getEntity(table , rs));
			} 

			return entities;
		}
		catch(Exception ex){
			System.out.println("Exception in getEntitiesFromResultSet method in EntityGetter class");
			throw ex;
		}
	}



	public Entity getEntity(String table , ResultSet rs) throws Exception{
		try{
			Entity entity = null;
			switch(table){
				case BOOKS :
					entity = Book.getBookFromResultSet(rs);
					break;
				case USER :
					entity = User.getUserFromResultSet(rs);
					break;
				case BOOK_REQUESTS :
					entity = BookRequest.getBookRequestFromResultSet(rs);
					break;
				case TRANSACTIONS :
					entity = Transaction.getTransactionFromResultSet(rs);
					break;
				default :
					System.out.println("Default case in getEntity method");
					throw new Exception("no table matches with entity type");
			}

			return entity;
		}
		catch(Exception ex){
			System.out.println("Exception in getEntity method in EntityGetter class");
			throw ex;
		}
	}


}