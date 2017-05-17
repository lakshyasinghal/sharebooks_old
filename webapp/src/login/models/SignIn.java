package com.sharebooks.login.models;

import javax.servlet.http.HttpServletRequest;        //interface
import com.sharebooks.books.models.BooksHandler;
import com.sharebooks.books.entities.Book;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import com.sharebooks.database.models.*;
import com.sharebooks.tables.Tables;



public class SignIn {

	//private User user;
	//private final int requsetType = 0;
	private final String tableName = "user";
	//private HashMap<String , Object> fieldValueMap;
	private List<String> fields;
	private List<String> fieldTypes;
	private List<Object> fieldValues;
	private String username;
	private String password;


	//not declaring no arg contructor so if it is accessed from somewhere I get an exception as I don't want it to be used


	public SignIn(String username , String password) throws Exception {
		this.username = username;
		this.password = password;
		//user = new User(username , password);
		//tableMetaData = User.getTableMetaData();
		//paramStr = getParamStr();
	}


	public void init() throws Exception {
		try{
			setFieldValueMap();
			setFields();
		}
		catch(Exception ex){
			System.out.println("Exception in init method in SignIn class");
			throw ex;
		}
	}


	//set the pairs of field type and value
	private void setFieldValueMap() throws Exception {
		try{
			fieldTypes = new ArrayList<String>();

			fieldTypes.add("string");
			fieldTypes.add("string");

			fieldValues = new ArrayList<Object>();

			fieldValues.add(username);
			fieldValues.add(password);

			//System.out.println("fieldValueMap in checker : " + fieldValueMap.toString());
		}
		catch(Exception ex){
			System.out.println("Exception in setFieldValueMap method in SignIn class");
			throw ex;
		}
	}


	private void setFields() throws Exception {
		try{
			fields = new ArrayList<String>();

			fields.add("username");
			fields.add("password");
		}
		catch(Exception ex){
			System.out.println("Exception in setFields method in SignIn class");
			throw ex;
		}
	}


	public int validateUser() throws Exception {
		try{
			init();
			Fetcher fetcher = new Fetcher(Tables.USER , fieldTypes , fieldValues , fields);
			ResultSet rs = (ResultSet)fetcher.fetch(1);

			rs.next();

			int userId = getUserIdFromResultSet(rs);

			return userId;
		}
		catch(Exception ex){
			System.out.println("Exception in validateUser method in SignIn class");
			throw ex;
		}
	}


	public int getUserIdFromResultSet(ResultSet rs) throws Exception {
		try{
			int userId = rs.getInt("id");
			
			return userId;
		}
		catch(Exception ex){
			System.out.println("Exception in getUserIdFromResultSet method in SignIn class");
			throw ex;
		}
	}


	public void setInitialResources(HttpServletRequest req , ServletContext servletContext) throws Exception {
		try{
			//getting some books and passing them with request parameter
			BooksHandler booksHandler = new BooksHandler();
			int booksCount = Integer.parseInt(servletContext.getInitParameter("BOOKSCOUNT"));
			List<Book> someBooks = booksHandler.fetchBooksByNumber(booksCount);
			req.setAttribute("books" , someBooks);

			//setting bookCategories
			List<String> bookCategories = booksHandler.fetchBookCategories();
			req.setAttribute("bookCategories" , bookCategories);
		}
		catch(Exception ex){
			System.out.println("Exception in getInitialResources method in SignIn class");
			throw ex;
		}
	}



}