package com.sharebooks.factory.mySQL;


import com.sharebooks.factory.abstractClasses.GenericBookFactory;
import com.sharebooks.staticClasses.ExceptionMessages;
import com.sharebooks.exception.entities.*;
import com.sharebooks.appEntities.entities.*;
import static com.sharebooks.appEntities.enums.BookFields.*;
import static com.sharebooks.staticClasses.Tables.*;



public class MySQLBookFactory extends GenericBookFactory {


	public BookFactory(){
		//nothing
	}


	public List<Book> getAllBooks() throws Exception {
		try{
			List<Book> books = new Arraylist<Book>();
			//fieldTypes , fieldValues & fields will be null so that we can get all the entitys
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(BOOKS , null , null , null);

			books = getBooksFromResultSet(rs);

			return books;
		}
		catch(Exception ex){
			System.out.println("Exception in getAllBooks method in MySQLBookFactory class");
			throw ex;
		}
	}


	public Book getBookById(int id) throws NoResultsFoundException , Exception {
		try{
			List<Book> books = new Arraylist<Book>();

			List<String> fields = Arrays.asList("id");
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(BOOKS , fields , fieldTypes , fieldValues);

			books = getBooksFromResultSet(rs);

			if(books.size() == 0){
				throw new NoResultsFoundException("Book Id : " + id);
			}

			return books.get(0);
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in getBookById method in MySQLBookFactory class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in getBookById method in MySQLBookFactory class");
			throw ex;
		}
	}



	public int addBook(Book book) throws Exception {
		try{
			List<String> fields = Arrays.asList("userId" , "name" , "authorName" , "category" , "subcategory" ,"pages" , "image" , "available"
							 ,"buy" , "rent" , "buyAmount" , "rentAmount");
			List<String> fieldTypes = Arrays.asList("int" , "string" , "string" , "string" , "string" , "int" , "string" , "int" , "int"
							  , "int" , "double" , "double");
			List<Object> fieldValues = Arrays.asList(book.userId() , book.name() , book.authorName() , book.category() , 
										book.subcategory() , book.pages() , book.image() , book.available() , book.buy()
										, book.rent() , book.buyAmount() , book.rentAmount());

			Insertor insertor = new Insertor();
			int added = insertor.insert(BOOKS , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addBook method in MySQLBookFactory class");
			throw ex;
		}
	}



	public int updateBook(Book book) throws Exception {
		try{
			List<String> fields = Arrays.asList("userId" , "name" , "authorName" , "category" , "subcategory" ,"pages" , "image" , "available"
							 ,"buy" , "rent" , "buyAmount" , "rentAmount");
			List<String> fieldTypes = Arrays.asList("int" , "string" , "string" , "string" , "string" , "int" , "string" , "int" , "int"
							  , "int" , "double" , "double" , "int");
			List<Object> fieldValues = Arrays.asList(book.userId(), book.name(), book.authorName(), book.category(), book.subcategory(),
									   book.pages(), book.image(), book.available(), book.buy(), book.rent(), book.buyAmount(), 
									   book.rentAmount(), book.id());

			Updator updator = new Updator();
			int updated = updator.update(BOOKS ,  fields , fieldTypes , fieldValues , 12);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateBook in MySQLBookFactory class");
			throw ex;
		}
	}

	public int deleteBook(int id) throws Exception {
		try{
			List<String> fields = Arrays.asList("id");
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Deletor deletor = new Deletor();
			int deleted = deletor.delete(BOOKS , fields , fieldTypes , fieldValues);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in deleteBook in MySQLBookFactory class");
			throw ex;
		}
	}














	//getting books from resultset 


	private Book getBookFromResultSet(ResultSet rs) throws Exception{
		try{
			int id = rs.getInt("id");
			int userId = rs.getInt("userId");
			String name = rs.getString("name");
			String authorName = rs.getString("authorName");
			String category = rs.getString("category");
			String subcategory = rs.getString("subcategory");
			int pages = rs.getInt("pages");
			String image = rs.getString("image");
			int available = rs.getInt("available");
			int buy = rs.getInt("buy");
			int rent = rs.getInt("rent");
			double buyAmount = rs.getDouble("buyAmount");
			double rentAmount = rs.getDouble("rentAmount");

			Book book = new Book(id , userId , name , authorName , category , subcategory , pages , image , available , buy , rent , buyAmount , rentAmount);

			return book;
		}
		catch(Exception ex){
			System.out.println("Error in getBookFromResultSet in MySQLBookFactory class");
			throw ex;
		}
	}


	public List<Book> getBooksFromResultSet(ResultSet rs) throws Exception {
		try{
			List<Book> books = new ArrayList<Book>();

			while(rs.next()){
				books.add(getBookFromResultSet(rs));
			} 

			return books;
		}
		catch(Exception ex){
			System.out.println("Exception in getBooksFromResultSet method in MySQLBookFactory class");
			throw ex;
		}
	}
	
}