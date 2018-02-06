package com.sharebooks.json.interfaces;



import com.sharebooks.appEntities.entities.Entity;
import java.util.*;


public interface JSONBuilderInterface {

	public abstract String getJSON(boolean success , int statusCode , List<Entity> entities) throws Exception;

	// public abstract String getJSON(boolean success , int statusCode , List<Integer> intValues) throws Exception;

	// public abstract String getJSON(boolean success , int statusCode , List<String> stringValues) throws Exception;

}