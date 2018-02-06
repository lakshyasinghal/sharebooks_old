package com.sharebooks.database.mySQL.entities;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.mySQL.interfaces.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;



public class Deletor extends GenericExecutor implements QueryBuilder {


	public Deletor(){
		//nothing goes here
	}


	//it will delete a row and will return a status value of 0 or 1
	public int delete(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception {
		try{
			String query = buildQuery(table , fields , 0);

			//PreparedStatement stmt = getPreparedStatement(query , fieldTypes , fieldValues);

			int result = executeUpdate(query , fieldTypes , fieldValues);

			int deleted = processResult(result);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in delete in Deletor class");
			throw ex;
		}
	}		


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery(String table , List<String> fields , int generic) throws Exception {

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("Delete");
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
						sqlQuery.append(",");
						sqlQuery.append(" ");
					}
				}

			}

			sqlQuery.append(";");

			System.out.println("SQL Query - " + sqlQuery);

			return sqlQuery.toString();
		}
		catch(Exception ex){
			System.out.println("Exception in buildQuery in Deletor class");
			throw ex;
		}
	}


	public int processResult(Object result) throws Exception{
		int count = (Integer)result;

		if(count > 0){
			return 1;
		}

		return 0;
	}


}