package com.sharebooks.database.models;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.interfaces.*;
import static com.sharebooks.util.URLConstants.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;


public class Fetcher extends GenericExecutor implements QueryBuilder {



	public Fetcher(){
		//nothing goes here
	}

	//constructor
	public Fetcher(String tableName , List<String> fieldTypes , List<Object> fieldValues , List<String> fields){
		super(tableName , fieldTypes , fieldValues , fields);
	}

	//this method will be the entry method for this class
	//resultType 1 will mean we want resultset as it is
	public Object fetch(int resultType) throws Exception{
		try{
			String query = buildQuery();

			PreparedStatement stmt = getPreparedStatement(query);

			ResultSet rs = executeQuery(stmt);

			Object processedResult = processResult(rs , resultType);


			//return value to be decided from resultType
			return processedResult;
		}
		catch(Exception ex){
			System.out.println("Exception in fetch in fetcher class ");
			throw ex;
		}
	}	


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery() throws Exception{
		List<String> fields = getFields();
		String tableName = getTableName();

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("SELECT");
			sqlQuery.append(SPACE);
			sqlQuery.append("*");
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