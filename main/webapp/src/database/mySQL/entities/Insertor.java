package com.sharebooks.database.mySQL.entities;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.mySQL.interfaces.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;



public class Insertor extends GenericExecutor implements QueryBuilder {


	public Insertor(){
		//nothing
	}


	//this method will be the entry method for this class
	//it will insert the rows and return the number of rows insertd
	public int insert(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			String query = buildQuery(table , fields , 0);

			//PreparedStatement stmt = getPreparedStatement(query , fieldTypes , fieldValues);

			int result = executeUpdate(query , fieldTypes , fieldValues);

			int inserted = processResult(result);

			return inserted;
		}
		catch(Exception ex){
			System.out.println("Exception in insert in Insertor class");
			throw ex;
		}
	}


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery(String table , List<String> fields , int generic) throws Exception {

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("Insert");
			sqlQuery.append(SPACE);
			sqlQuery.append("into");
			sqlQuery.append(SPACE);
			sqlQuery.append(table);

			if(fields != null){

				sqlQuery.append(SPACE);
				sqlQuery.append("(");
				sqlQuery.append(SPACE);
				// sqlQuery.append("SELECT");
				// sqlQuery.append(SPACE);

				int size = fields.size();
				int i=0;
				//adding column names
				for(; i<size ; i++){
					String field = fields.get(i);

					sqlQuery.append(field);
					sqlQuery.append(SPACE);
					if(i != size-1){
						sqlQuery.append(",");
						sqlQuery.append(SPACE);
					}
				}

				sqlQuery.append(")");

				sqlQuery.append(SPACE);
				sqlQuery.append("values");
				sqlQuery.append(SPACE);
				sqlQuery.append("(");
				sqlQuery.append(SPACE);

				for(i=0 ; i<size ; i++){
					//String field = fields.get(i);

					sqlQuery.append("?");
					sqlQuery.append(SPACE);
					if(i != size-1){
						sqlQuery.append(",");
						sqlQuery.append(SPACE);
					}
				}

				sqlQuery.append(")");
			}

			sqlQuery.append(";");

			System.out.println("SQL Query - " + sqlQuery);

			return sqlQuery.toString();
		}
		catch(Exception ex){
			System.out.println("Exception in buildQuery in Insertor class");
			throw ex;
		}
	}

	public int processResult(Object result) throws Exception {
		try{
			int n = (Integer)result;
			if(n > 0){
				return 1;
			}
			else
				return 0;
		}
		catch(Exception ex){
			System.out.println("Exception in processResult in Insertor class");
			throw ex;
		}
	}


}