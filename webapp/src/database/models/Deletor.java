package com.sharebooks.database.models;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.interfaces.*;
import static com.sharebooks.util.URLConstants.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;



public class Deletor extends GenericExecutor implements QueryBuilder {


	public Deletor(){
		//nothing goes here
	}

	//constructor
	public Deletor(String tableName , List<String> fieldTypes , List<Object> fieldValues , List<String> fields){
		super(tableName , fieldTypes , fieldValues , fields);
	}


	//this method will be the entry method for this class
	//it will delete a row and will return a status value of 0 or 1
	public int delete() throws Exception {
		try{
			String query = buildQuery();

			PreparedStatement stmt = getPreparedStatement(query);

			int result = executeUpdate(stmt);

			int deleted = processResult(result);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in delete in Deletor class");
			throw ex;
		}
	}		


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery() throws Exception {
		List<String> fields = getFields();
		String tableName = getTableName();

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("Delete");
			sqlQuery.append(SPACE);
			sqlQuery.append("FROM");
			sqlQuery.append(SPACE);
			sqlQuery.append(tableName);

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


	public int processResult(Object result){
		return 0;
	}


}