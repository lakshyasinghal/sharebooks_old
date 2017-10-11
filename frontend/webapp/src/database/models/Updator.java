package com.sharebooks.database.models;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.interfaces.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;



public class Updator extends GenericExecutor implements QueryBuilder {
	private int setPropertyCounter;

	public Updator(){
		//nothing
	}


	//this method will be the entry method for this class
	//it will update the rows and return the number of rows updated
	public int update(String table , List<String> fields , List<String> fieldTypes , List<Object> fieldValues , int propertyCounter) throws Exception {
		try{
			String query = buildQuery(table , fields , propertyCounter);

			PreparedStatement stmt = getPreparedStatement(query , fieldTypes , fieldValues);

			int result = executeUpdate(stmt);

			int updated = processResult(result);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in update in Updator class");
			throw ex;
		}
	}	


	//this method will return a query string in the form of        update table set field1 = ? , field2 = ? where targetField = ? ;
	//propertyCounter will count the properties to be updated from the front
	public String buildQuery(String table , List<String> fields , int propertyCounter) throws Exception {

		try{
			StringBuilder sqlQuery = new StringBuilder();

			sqlQuery.append("UPDATE");
			sqlQuery.append(SPACE);
			sqlQuery.append(table);

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