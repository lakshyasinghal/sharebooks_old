package com.sharebooks.database.mySQL.entities;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.mySQL.interfaces.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;


public class Fetcher extends GenericExecutor implements QueryBuilder {



	public Fetcher(){
		//nothing goes here
	}


	//this method will fetch all the entries 
	public ResultSet fetch(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
		try{
			String query = buildQuery(table , fields , 0);

			//PreparedStatement stmt = getPreparedStatement(query , fieldTypes , fieldValues);

			ResultSet rs = executeQuery(query , fieldTypes , fieldValues);

			//Object processedResult = processResult(rs , resultType);


			//return value to be decided from resultType
			return rs;
		}
		catch(Exception ex){
			System.out.println("Exception in fetch in fetcher class ");
			throw ex;
		}
	}	


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery(String table , List<String> fields , int generic) throws Exception{
		//List<String> fields = e.getFields();
		//String tableName = getTableName();

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("SELECT");
			sqlQuery.append(SPACE);
			sqlQuery.append("*");
			sqlQuery.append(SPACE);
			sqlQuery.append("FROM");
			sqlQuery.append(SPACE);
			sqlQuery.append(table);

			if(fields != null){

				sqlQuery.append(SPACE);
				sqlQuery.append("WHERE");
				sqlQuery.append(SPACE);
				// sqlQuery.append("SELECT");
				// sqlQuery.append(SPACE);

				int size = fields.size();

				//adding the filed names with ? to the query
				for(int i=0; i<size ; i++){
					String field = fields.get(i);

					sqlQuery.append(field);
					sqlQuery.append("=");
					sqlQuery.append("?");
					sqlQuery.append(" ");
					if(i != size-1){
						sqlQuery.append("AND");
						sqlQuery.append(" ");
					}
				}

			}

			sqlQuery.append(";");

			System.out.println("SQL Query - " + sqlQuery);

			return sqlQuery.toString();
		}
		catch(Exception ex){
			System.out.println("Exception in buildQuery in Fetcher class");
			throw ex;
		}

	}


	//not in use currently
	//this method can return different types of results depending on the resultType value
	//it can either be a resultSet or a json containing the results
	public Object processResult(Object result , int resultType){
		Object obj = null;
		if(resultType == 1){
			// do nothing
			obj = result;
		}

		return obj;
	} 

}