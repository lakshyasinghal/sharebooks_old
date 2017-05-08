package com.sharebooks.database.models;


import java.util.*;
import java.sql.*;
import com.sharebooks.database.interfaces.*;
import static com.sharebooks.util.URLConstants.*;
import static com.sharebooks.util.StringConstants.*;
import static com.sharebooks.util.ObjectPair.*;



public class Checker extends GenericExecutor implements QueryBuilder {



	public Checker(){
		//nothing
	}

	//constructor
	public Checker(String tableName , List<String> fieldTypes , List<Object> fieldValues , List<String> fields){
		super(tableName , fieldTypes , fieldValues , fields);
	}

	//this method will be the entry method for this class
	//it will check whether the row with given values exists in the table or not and will return a value of 0 or 1 accordingly
	//value 1 if row exists and 0 otherwise
	public int check() throws Exception {
		try{
			String query = buildQuery();

			PreparedStatement stmt = getPreparedStatement(query);

			ResultSet rs = executeQuery(stmt);

			int exists = processResult(rs);

			return exists;
		}
		catch(Exception ex){
			System.out.println("Exception in check in Checker class ");
			throw ex;
		}
	}	


	//this method will return a query string in the form of        select * from tableName where field1=? , field2=? , field3=? ;
	public String buildQuery() throws Exception {
		List<String> fields = getFields();
		String tableName = getTableName();

		try {
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
						sqlQuery.append("and");
						sqlQuery.append(" ");
					}
				}

			}

			sqlQuery.append(";");

			System.out.println("SQL Query - " + sqlQuery);

			return sqlQuery.toString();
		}
		catch(Exception ex){
			System.out.println("Exception in buildQuery in Checker class ");
			throw ex;
		}
	}


	

	public int processResult(Object result) throws Exception {
		try{
			ResultSet rs = (ResultSet)result;

			int count = 0;

			while(rs.next()){
				++count;
				break;
			}

			return count;
		}
		catch(Exception ex){
			System.out.println("Exception in processResult in Checker class ");
			throw ex;
		}
	}

}







