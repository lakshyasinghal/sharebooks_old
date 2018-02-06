package com.sharebooks.appEntities.entities;


import java.sql.*;
import java.util.*;


//immutable class
public final class City extends Entity implements Comparable<City> {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String name;
	private final int stateId;
	


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "name" , "stateId"};
		fieldTypes = new String[]{"int" , "string" , "int"};
	}


	public City(int id , String name , int stateId){
		this.id = id;
		this.name = name;
		this.stateId = stateId;
	}


	//compareTo method to be used for sorting
	public int compareTo(City city){
		return name.compareTo(city.getName());
	}


	//overriding hashCode method
	public int hashCode(){
		return name.hashCode();
	}


	//overriding equals method
	public boolean equals(Object o){
		if(o == null){
			return false;
		}

		if(!(o instanceof City)){
			return false;
		}

		if(o == this){
			return true;
		}
		
		City city = (City)o;
		return name.equals(city.getName());
	}





	//getter methods
	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public int getStateId(){
		return stateId;
	}
	




	//static methods

	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){
		Object[] fieldValues = {this.id , this.name , this.stateId};

		return fieldValues;
	}



	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Name : " + this.name);

		return stringBuilder.toString();
	}


} 