 package com.sharebooks.commonEntity.interfaces;


import com.sharebooks.commonEntity.entities.Entity;
import java.util.*;


public interface EntityHandlerInterface {


	public abstract List<Entity> fetchAllEntities(String table) throws Exception;

	public abstract List<Entity> fetchEntitiesByNumber(String table , int number) throws Exception;

	public abstract List<Entity> fetchEntities(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception;

	public abstract Entity fetchEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception;

	public abstract int addEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception;

	public abstract int updateEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues , int propertyCounter) throws Exception;

	public abstract int deleteEntity(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception;
}