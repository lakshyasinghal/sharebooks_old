package com.sharebooks.database.models;

import com.sharebooks.database.interfaces.Executor;
import java.util.*;
import java.sql.*;



public abstract class GenericExecutor implements Executor {

	private Connection connection;


	

	public GenericExecutor(){
		connection = Connector.getConnection();
	}



	public ResultSet executeQuery(PreparedStatement stmt) throws Exception {
		try {
			ResultSet rs = null;

			System.out.println("Thread " + Thread.currentThread().getName() + " inside executeQuery");

			synchronized(connection){
				rs = stmt.executeQuery();
			}
			return rs;
		}
		catch(Exception ex){
			System.out.println("Exception in executeQuery in GenericExecutor class ");
			throw ex;
		}
	}



	public int executeUpdate(PreparedStatement stmt) throws Exception {
		try {
			int rowsAffected = -1;

			System.out.println("Thread " + Thread.currentThread().getName() + " inside executeUpdate");
			
			synchronized(connection){
				rowsAffected = stmt.executeUpdate();
			}
			return rowsAffected;
		}
		catch(Exception ex){
			System.out.println("Exception in executeQuery in GenericExecutor class ");
			throw ex;
		}
	}



	//this method will take the sqlquery and will get a prepared statement for it by inserting the parameters
	public PreparedStatement getPreparedStatement(String sqlQuery , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		PreparedStatement stmt = null;

		try{
			//System.out.println("Inside getPreparedStatement");

			stmt = connection.prepareStatement(sqlQuery);
			String fieldType = "";
			Object fieldValue = null;


			//System.out.println("Point 0");

			if(fieldTypes != null){
				int size = fieldTypes.size();

				//System.out.println("Point 1");

				for(int i=1 ; i<=size ; i++){
					fieldType = fieldTypes.get(i-1);
					fieldValue = fieldValues.get(i-1);
					setFieldValue(stmt , fieldType , fieldValue , i);
				}

				//System.out.println("Point 2");
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
		
		try{
			//System.out.println("fieldType : " + fieldType);
			//System.out.println("fieldValue : " + fieldValue.toString());
			//System.out.println("fieldNumber : " + fieldNumber);
			switch(fieldType){
				case "int" :
					stmt.setInt(fieldNumber , (Integer)fieldValue);
					break;
				case "float" :
					stmt.setFloat(fieldNumber , (Float)fieldValue);
					break;
				case "double" :
					stmt.setDouble(fieldNumber , (Double)fieldValue);
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


}