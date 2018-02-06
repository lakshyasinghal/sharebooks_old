package com.sharebooks.appEntities.entities;


import java.sql.*;
import java.util.*;


//immutable class
public final class State extends Entity implements Comparable<State> {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String name;


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "name"};
		fieldTypes = new String[]{"int" , "string"};
	}


	public State(int id , String name){
		this.id = id;
		this.name = name;
	}


	//compareTo method to be used for sorting
	public int compareTo(State state){
		return name.compareTo(state.name());
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

		if(!(o instanceof State)){
			return false;
		}

		if(o == this){
			return true;
		}
		
		State state = (State)o;
		return name.equals(state.name());
	}





	//getter methods
	public int id(){
		return id;
	}

	public String name(){
		return name;
	}
	




	//static methods
	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){
		Object[] fieldValues = {this.id , this.name};

		return fieldValues;
	}


	public String toString(){
		return name;
	}


} 