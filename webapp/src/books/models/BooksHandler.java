package com.sharebooks.books.models;

import com.sharebooks.database.models.*;
import com.sharebooks.books.entities.Book;
import java.util.*;
import java.sql.*;
import com.sharebooks.tables.Tables;



public class BooksHandler {
	//private String tableName = "Books";
	private List<String> fieldTypes = null;
	private List<Object> fieldValues = null;
	private List<String> fields = null;
	private int setPropertyCounter = 5;


	// public void init(List<String> fieldTypes , List<Object> fieldValues , List<String> fields){

	// }



	public BooksHandler(){

	}


	public List<Book> fetchBooks() throws Exception{
		try{
			//fieldTypes , fieldValues & fields will be null so that we can get all the books
			Fetcher fetcher = new Fetcher(Tables.BOOKS , null , null , null);
			ResultSet rs = (ResultSet)fetcher.fetch(1);

			List<Book> books = new ArrayList();

			while(rs.next()){
				Book book = getBookFromResultSet(rs);
				books.add(book);
			}

			return books;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchBooks method in BooksHandler class : " + ex);
			throw ex;
		}
	}


	public List<Book> fetchBooksByNumber(int number) throws Exception {
		try{
			List<Book> tempBooks = fetchBooks();

			int size = tempBooks.size();

			if(number > size){
				number = size;
			}

			List<Book> books = new ArrayList<Book>();

			for(int i=0 ; i<number ; i++){
				books.add(tempBooks.get(i));
			}

			return books;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchBooksByNumber method in BooksHandler class : " + ex);
			System.out.println("number : " + number);
			throw ex;
		}
	}


	public int addBook(Book book) throws Exception{
		try{
			List<String> fields = Arrays.asList("userId" , "name" , "authorName" , "category" , "pages" , "imagePath");
			//setFields(fields);

			List<String> fieldTypes = Arrays.asList("int" , "string" , "string" , "string" , "int" , "string");
			//setFieldTypes(fieldTypes);

			List<Object> fieldValues = Arrays.asList(book.getUserId() , book.getName() , book.getAuthorName() , book.getCategory() , book.getPages() , book.getImagePath());
			//setFieldValues(fieldValues);

			Insertor insertor = new Insertor(Tables.BOOKS , fieldTypes , fieldValues , fields);
			int inserted = insertor.insert();

			return inserted;
		}
		catch(Exception ex){
			System.out.println("Exception in addBook method in BooksHandler class : " + ex);
			throw ex;
		}
	}


	public int updateBook(Book book) throws Exception{
		try{
			List<String> fields = Arrays.asList("name" , "authorName" , "category" , "pages" , "imagePath" , "id");
			setFields(fields);

			List<String> fieldTypes = Arrays.asList("string" , "string" , "string" , "int" , "string" , "int");
			setFieldTypes(fieldTypes);

			List<Object> fieldValues = Arrays.asList(book.getName() , book.getAuthorName() , book.getCategory() , String.valueOf(book.getPages()) , book.getImagePath() , String.valueOf(book.getId()));
			setFieldValues(fieldValues);

			Updator updator = new Updator(Tables.BOOKS , this.fieldTypes , this.fieldValues , this.fields , this.setPropertyCounter);
			int updated = updator.update();

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateBook method in BooksHandler class : " + ex);
			throw ex;
		}
	}





	public Book getBookFromResultSet(ResultSet rs) throws Exception {
		try{
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String authorName = rs.getString("authorName");
			String category = rs.getString("category");
			int pages = rs.getInt("pages");
			String imagePath = rs.getString("imagePath");

			Book book = new Book(id , name , authorName , category , pages , imagePath);

			return book;
		}
		catch(Exception ex){
			System.out.println("Exception in getBookFromResultSet method in BooksHandler class : " + ex);
			throw ex;
		}
	}


	public List<String> fetchBookCategories() throws Exception {
		try{
			//fieldTypes , fieldValues & fields will be null so that we can get all the books
			Fetcher fetcher = new Fetcher(Tables.BOOKCATEGORIES , null , null , null);
			ResultSet rs = (ResultSet)fetcher.fetch(1);

			List<String> bookCategories = new ArrayList();

			while(rs.next()){
				String category = getCategoryFromResultSet(rs);
				bookCategories.add(category);
			}

			System.out.println("Book Categories : " + bookCategories.toString());

			return bookCategories;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchBookCategories method in BooksHandler class");
			throw ex;
		}
	}

	public String getCategoryFromResultSet(ResultSet rs) throws Exception {
		try{
			String category = rs.getString("category");
			
			return category;
		}
		catch(Exception ex){
			System.out.println("Exception in getCategoryFromResultSet method in BooksHandler class");
			throw ex;
		}
	}



	public void setFieldTypes(List<String> fieldTypes){
		this.fieldTypes = fieldTypes;
	}

	public void setFieldValues(List<Object> fieldValues){
		this.fieldValues = fieldValues;
	}

	public void setFields(List<String> fields){
		this.fields = fields;
	}

}