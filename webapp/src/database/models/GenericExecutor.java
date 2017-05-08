package com.sharebooks.database.models;

import com.sharebooks.database.interfaces.Executor;
import java.util.*;
import java.sql.*;



public abstract class GenericExecutor implements Executor {
	private String tableName;
	//private HashMap<String , Object> fieldValueMap;

	private Connection connection;
	private List<String> fields;
	private List<String> fieldTypes;
	private List<Object> fieldValues;
	


	public GenericExecutor(){
		connection = Connector.getConnection();
	}

	public GenericExecutor(String tableName , List<String> fieldTypes , List<Object> fieldValues , List<String> fields){
		this();
		this.tableName = tableName;
		//this.fieldValueMap = fieldValueMap;
		this.fieldTypes = fieldTypes;
		this.fieldValues = fieldValues;
		this.fields = fields;
	}


	public ResultSet executeQuery(PreparedStatement stmt) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery();
			return rs;
		}
		catch(Exception ex){
			System.out.println("Exception in executeQuery in GenericExecutor class ");
			throw ex;
		}
	}

	public int executeUpdate(PreparedStatement stmt) throws Exception {
		try {
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected;
		}
		catch(Exception ex){
			System.out.println("Exception in executeQuery in GenericExecutor class ");
			throw ex;
		}
	}


	//this method will take the sqlquery and will get a prepared statement for it by inserting the parameters
	public PreparedStatement getPreparedStatement(String sqlQuery) throws Exception {

		PreparedStatement stmt = null;

		try{
			stmt = connection.prepareStatement(sqlQuery);
			String fieldType = "";
			Object fieldValue = null;


			if(fieldTypes != null){
				int size = fieldTypes.size();

				for(int i=1 ; i<=size ; i++){
					fieldType = fieldTypes.get(i-1);
					fieldValue = fieldValues.get(i-1);
					setFieldValue(stmt , fieldType , fieldValue , i);
				}
			}

			System.out.println("SQL statement - " + stmt.toString());

			return stmt;
		}
		catch(Exception ex){
			stmt = null;
			System.out.println("Exception in getPreparedStatement in GenericExecutor class ");
			throw ex;
		}
		
	}


	//this method will be used to set the parameter values in the prepared statement based on whether there is int , string etc.
	public void setFieldValue(PreparedStatement stmt , String fieldType , Object fieldValue , int fieldNumber) throws Exception {
		System.out.println("fieldType : " + fieldType);
		System.out.println("fieldValue : " + fieldValue.toString());
		System.out.println("fieldNumber : " + fieldNumber);
		try{
			switch(fieldType){
				case "int" :
					stmt.setInt(fieldNumber , (Integer)fieldValue);
					break;
				case "string" :
					stmt.setString(fieldNumber , (String)fieldValue);
					break;
				case "dateTime" :
					stmt.setDate(fieldNumber , new java.sql.Date(((java.util.Date)fieldValue).getTime()) );
					break;
				default :
					break;
			}
		}
		catch(Exception ex){
			System.out.println("Exception in setFielValue in GenericExecutor class " );
			throw ex;
		}
	}





	//gettter methods
	public String getTableName(){
		return tableName;
	}


	public List<String>  getFieldTypes(){
		return fieldTypes;
	}

	public List<Object>  getFieldValues(){
		return fieldValues;
	}


	public List<String>  getFields(){
		return fields;
	}


}