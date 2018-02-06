package com.sharebooks.appEntities.models;


import com.sharebooks.database.models.*;
import com.sharebooks.appEntities.interfaces.EntityGetterInterface;
import com.sharebooks.appEntities.entities.Entity;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.factory.entities.DatabaseFactory;
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
			Factory factory = DatabaseFactory.databaseFactory();
			switch(table){
				case BOOKS :
					entity = factory.book(rs);
					break;
				case USER :
					entity = factory.user(rs);
					break;
				case BOOK_REQUESTS :
					entity = factory.bookRequest(rs);
					break;
				case TRANSACTIONS :
					entity = factory.transaction(rs);
					break;
				case CITIES : 
					entity = factory.city(rs);
					break;
				case STATES : 
					entity = factory.state(rs);
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