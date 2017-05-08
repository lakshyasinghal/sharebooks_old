package com.sharebooks.database.models;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.interfaces.*;
import static com.sharebooks.util.URLConstants.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;



public class Updator extends GenericExecutor implements QueryBuilder {
	private int setPropertyCounter;

	public Updator(){
		//nothing
	}


	//constructor
	public Updator(String tableName , List<String> fieldTypes , List<Object> fieldValues , List<String> fields , int n){
		super(tableName , fieldTypes , fieldValues , fields);
		setPropertyCounter = n;
	}


	//this method will be the entry method for this class
	//it will update the rows and return the number of rows updated
	public int update() throws Exception {
		try{
			String query = buildQuery();

			PreparedStatement stmt = getPreparedStatement(query);

			int result = executeUpdate(stmt);

			int updated = processResult(result);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in update in Updator class");
			throw ex;
		}
	}	


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery() throws Exception {
		List<String> fields = getFields();
		String tableName = getTableName();

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("UPDATE");
			sqlQuery.append(SPACE);
			sqlQuery.append(tableName);

			if(fields != null){
				int size = fields.size();

				sqlQuery.append(SPACE);
				sqlQuery.append("set");
				sqlQuery.append(SPACE);

				//adding the filed names with ? to the query
				for(int i=0; i<setPropertyCounter ; i++){
					String field = fields.get(i);

					sqlQuery.append(field);
					sqlQuery.append("=");
					sqlQuery.append("?");
					sqlQuery.append(SPACE);
					if(i != size-1){
						sqlQuery.append(",");
						sqlQuery.append(SPACE);
					}
				}

				// sqlQuery.append(SPACE);
				sqlQuery.append("WHERE");
				sqlQuery.append(SPACE);
				// sqlQuery.append("SELECT");
				// sqlQuery.append(SPACE);

				

				//adding the filed names with ? to the query
				for(int i=setPropertyCounter; i<size ; i++){
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
			System.out.println("Exception in buildQuery in Updator class");
			throw ex;
		}
	}




	public int processResult(Object result) throws Exception {
		int count = (Integer)result;

		if(count > 0){
			return 1;
		}

		return 0;
	}


}