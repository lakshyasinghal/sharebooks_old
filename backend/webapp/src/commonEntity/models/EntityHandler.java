package com.sharebooks.commonEntity.models;

import com.sharebooks.database.models.*;
import com.sharebooks.commonEntity.interfaces.EntityHandlerInterface;
import com.sharebooks.commonEntity.entities.Entity;
import java.util.*;
import java.sql.*;


public class EntityHandler implements EntityHandlerInterface {


	public EntityHandler(){
		//no code
	}


	public List<Entity> fetchAllEntities(String table) throws Exception{
		try{
			//fieldTypes , fieldValues & fields will be null so that we can get all the entitys
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(table , null , null , null);

			List<Entity> entities = new EntityGetter().getEntitiesFromResultSet(table , rs);

			return entities;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchEntities method in EntityHandler class");
			throw ex;
		}
	}


	public List<Entity> fetchEntitiesByNumber(String table , int number) throws Exception {
		try{
			List<Entity> allEntities = fetchAllEntities(table);

			int size = allEntities.size();

			if(number > size){
				number = size;
			}

			List<Entity> requiredEntities = new ArrayList<Entity>();

			for(int i=0 ; i<number ; i++){
				requiredEntities.add(allEntities.get(i));
			}

			return requiredEntities;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchEntitiesByNumber method in EntityHandler class");
			throw ex;
		}
	}


	public List<Entity> fetchEntities(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(table , fields , fieldTypes , fieldValues);

			List<Entity> entities = new EntityGetter().getEntitiesFromResultSet(table , rs);

			return entities;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchEntities method in EntityHandler class");
			throw ex;
		}
	}



	public Entity fetchEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			List<Entity> entities = fetchEntities(table , fields , fieldTypes , fieldValues);

			if(entities.size() == 0){
				return null;
			}

			return entities.get(0);
		}
		catch(Exception ex){
			System.out.println("Exception in fetchEntities method in EntityHandler class");
			throw ex;
		}
	}


	public boolean alreadyExists(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(table , fields , fieldTypes , fieldValues);

			while(rs.next()){
				return true;
			}

			return false;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchEntities method in EntityHandler class");
			throw ex;
		}
	}



	public int addEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			Insertor insertor = new Insertor();
			int inserted = insertor.insert(table , fields , fieldTypes , fieldValues);

			return inserted;
		}
		catch(Exception ex){
			System.out.println("Exception in addEntity method in EntityHandler class");
			throw ex;
		}
	}


	public int updateEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues , int propertyCounter) throws Exception {
		try{

			Updator updator = new Updator();
			int updated = updator.update(table , fields , fieldTypes , fieldValues , propertyCounter);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateEntity in EntityHandler class");
			throw ex;
		}
	}



	public int deleteEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			//do nothing
			return 0;
		}
		catch(Exception ex){
			System.out.println("Exception in deleteEntity in EntityHandler class");
			throw ex;
		}
	}


}