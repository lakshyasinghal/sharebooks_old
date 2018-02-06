// package com.sharebooks.appEntities.models;


// import com.sharebooks.database.models.*;
// import com.sharebooks.appEntities.entities.Book;
// import com.sharebooks.appEntities.entities.Entity;
// import com.sharebooks.factory.interfaces.Factory;
// import com.sharebooks.exception.entities.*;
// import java.util.*;
// import java.sql.*;
// import static com.sharebooks.staticClasses.Tables.*;
// import javax.servlet.http.*;



// public class BookManager extends EntityManager {
	

// 	public BookManager(){

// 	}

	// public void init(Factory factory) throws Exception{
	// 	try{
	// 		super.init(factory);
	// 	}
	// 	catch(IllegalInitializationException ex){
	// 		System.out.println("init method in BookRequestManager");
	// 		System.out.println(ex);
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in init method in BookRequestManager");
	// 		throw ex;
	// 	}
	// }


// 	//this method will fetch all the books in the database
// 	public List<Book> fetchAllBooks() throws Exception{
// 		try{
// 			List<Entity> entities = fetchAllEntities(BOOKS);
			
// 			List<Book> books = convertEntitiesToBooks(entities);
			
// 			return books;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in fetchAllBooks method in BookManager class");
// 			throw ex;
// 		}
// 	}



// 	//this method will fetch a given number of books
// 	public List<Book> fetchBooksByNumber(int number) throws Exception {
// 		try{
// 			List<Entity> entities = fetchEntitiesByNumber(BOOKS , number);
			
// 			List<Book> books = convertEntitiesToBooks(entities);
			
// 			return books;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in fetchBooksByNumber method in BookManager class : " + ex);
// 			System.out.println("number : " + number);
// 			throw ex;
// 		}
// 	}



// 	//this method will fetch all books for given parameters 
// 	public List<Book> fetchBooks(List<String> fields , List<String> fieldTypes , List<Object> fieldValues) throws Exception{
// 		try{
// 			List<Entity> entities = fetchEntities(BOOKS , fields , fieldTypes , fieldValues);
			
// 			List<Book> books = convertEntitiesToBooks(entities);
			
// 			return books;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in fetchBooks method in BookManager class");
// 			throw ex;
// 		}
// 	}


// 	//this method will fetch a book for a given id 
// 	public Book fetchBookById(int bookId) throws Exception{
// 		try{
// 			return null;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in fetchBookById method in BookManager class");
// 			throw ex;
// 		}
// 	}



// 	//this method will fetch books for given categories and subcategories
// 	// public List<Book> fetchBooksByCategory(HttpServletRequest req) throws Exception{
// 	// 	try{

// 	// 		HashMap<String , List<String>> categoriesMap = getCategoriesHashMap(req.getParameter("categoriesJson"));

// 	// 		List<Book> books = new ArrayList<Book>();
// 	// 		Iterator<String> keySetIterator = categoriesMap.keySet().iterator();
// 	// 		String category = null;
// 	// 		List<String> subcategoriesList = null;

// 	// 		List<String> fields = Arrays.asList("category" , "subcategory"); 
// 	// 		List<String> fieldtypes = Arrays.asList("string" , "string"); 
// 	// 		List<Object> fieldValues = null;


// 	// 		//this will act as a temporary storage
// 	// 		List<Book> tempBooks = null;

// 	// 		while(keySetIterator.hasNext()){
// 	// 			category = keySetIterator.next();
// 	// 			subcategoriesList = categoriesMap.get(category);

// 	// 			for(String subcategory : subcategoriesList){
// 	// 				fieldValues = new ArrayList<Object>();
// 	// 				fieldValues.add(category);
// 	// 				fieldValues.add(subcategory);

// 	// 				tempBooks = fetchBooks(fields , fieldtypes , fieldValues);
// 	// 				books.addAll(tempBooks);
// 	// 			}
// 	// 		}

// 	// 		return books;
// 	// 	}
// 	// 	catch(Exception ex){
// 	// 		System.out.println("Exception in fetchBooksByCategory method in BookManager class");
// 	// 		throw ex;
// 	// 	}
// 	// }




// 	//this method will get the hashmap<String , List<String>> for the categories and subcategories
// 	//the categoriesJson will be in the format {Science: ["Biology" , "Chemistry"] , "Computer Science" :["C" , "C++" , "Java"]}
// 	//   ***** but right now only json of type {Science: ["Biology" , "Chemistry"]} is handled
// 	// public HashMap<String , List<String>> getCategoriesHashMap(String categoriesJson) throws Exception{
// 	// 	try{

// 	// 		HashMap<String , List<String>> categoriesMap = new HashMap<String , List<String>>();

// 	// 		//this part needs to be modified to handle the json properly
// 	// 		String[] tokens = {categoriesJson.split("{")[1].split("}")[0]};
			
// 	// 		String token = null;
// 	// 		String category = null;
// 	// 		List<String> subcategoriesList = null;

// 	// 		for(int i=0 ; i<tokens.length ; i++){
// 	// 			token = tokens[i];
// 	// 			String[] subTokens = token.split(":");
// 	// 			category = subTokens[0].trim();
// 	// 			subcategoriesList = new ArrayList<String>(Arrays.asList(subTokens[1].split("[")[1].split("]")[0].replace(" " , "").split(",")));

// 	// 			categoriesMap.put(category , subcategoriesList);
// 	// 		}
			

// 	// 		return categoriesMap;
// 	// 	}
// 	// 	catch(Exception ex){
// 	// 		System.out.println("Exception in fetchBooksByCategoryAndSubcategory method in BookManager class");
// 	// 		throw ex;
// 	// 	}
// 	// }



// 	public int addBook(Book book) throws Exception{
// 		try{
// 			List<String> fields = Arrays.asList("userId" , "name" , "authorName" , "category" , "subcategory" ,"pages" , "image" , "available"
// 							 ,"buy" , "rent" , "buyAmount" , "rentAmount");
// 			List<String> fieldTypes = Arrays.asList("int" , "string" , "string" , "string" , "string" , "int" , "string" , "int" , "int"
// 							  , "int" , "double" , "double");
// 			List<Object> fieldValues = Arrays.asList(book.getUserId() , book.getName() , book.getAuthorName() , book.getCategory() , 
// 										book.getSubcategory() , book.getPages() , book.getImage() , book.getAvailable() , book.getBuy()
// 										, book.getRent() , book.getBuyAmount() , book.getRentAmount());

// 			int added = addEntity(BOOKS , fields , fieldTypes , fieldValues);

// 			return added;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in addBook method in BookManager class");
// 			throw ex;
// 		}
// 	}


	
// 	public int updateBook(Book book) throws Exception{
// 		try{
// 			List<String> fields = Arrays.asList("userId" , "name" , "authorName" , "category" , "subcategory" ,"pages" , "image" , "available"
// 							 ,"buy" , "rent" , "buyAmount" , "rentAmount" , "id");
// 			List<String> fieldTypes = Arrays.asList("int" , "string" , "string" , "string" , "string" , "int" , "string" , "int" , "int"
// 							  , "int" , "double" , "double" , "int");
// 			List<Object> fieldValues = Arrays.asList(book.getUserId() , book.getName() , book.getAuthorName() , book.getCategory() , 
// 										book.getSubcategory() , book.getPages() , book.getImage() , book.getAvailable() , book.getBuy()
// 										, book.getRent() , book.getBuyAmount() , book.getRentAmount() , book.getId());
			
// 			int updated = updateEntity(BOOKS ,  fields , fieldTypes , fieldValues , 12);

// 			return updated;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in updateBook method in BookManager class");
// 			throw ex;
// 		}
// 	}






// 	//method for converting entities list to books list
// 	public List<Book> convertEntitiesToBooks(List<Entity> entities) throws Exception {
// 		try{
// 			List<Book> books = new ArrayList<Book>();
// 			Book book;
// 			int size = entities.size();

// 			for(int i=0 ; i<size ; i++){
// 				book = (Book)entities.get(i);
// 				books.add(book);
// 			}

// 			return books;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Exception in convertEntitiesToBooks method in BookManager class");
// 			throw ex;
// 		}
// 	}

// }