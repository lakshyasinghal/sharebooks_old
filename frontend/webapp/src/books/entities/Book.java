package com.sharebooks.books.entities;


import com.sharebooks.commonEntity.entities.Entity;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


//immutable class
public final class Book extends Entity implements Comparable<Book> {

	private static final long serialVersionUID = 1L;

	private int id = -1;
	private int userId = -1;
	private String name = "";
	private String authorName = "";
	private String category = "";
	private String subcategory = "";
	private int pages = 0;
	private String image = "";
	private int available = 1;
	private int buy = 0;
	private int rent = 0;
	private double buyAmount = 0.0;
	private double rentAmount = 0.0;


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "userId" , "name" , "authorName" , "category" , "subcategory" ,"pages" , "image" , "available"
							 ,"buy" , "rent" , "buyAmount" , "rentAmount"};
		fieldTypes = new String[]{"int" , "int" , "string" , "string" , "string" , "string" , "int" , "string" , "int" , "int"
							  , "int" , "double" , "double"};
	}

	public Book(){
		//do nothing 
	}


	public Book(int id , int userId , String name , String authorName , String category , String subcategory , int pages , String image ,
	 int available , int buy , int rent , double buyAmount , double rentAmount){
		//this();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.authorName = authorName;
		this.category = category;
		this.subcategory = subcategory;
		this.pages = pages;
		this.image = image;
		this.available = available;
		this.buy = buy;
		this.rent = rent;
		this.buyAmount = buyAmount;
		this.rentAmount = rentAmount;
	}



	public Book(int userId , String name , String authorName , String category , String subcategory , int pages , String image ,
	 int available , int buy , int rent , double buyAmount , double rentAmount){
		//this();
		//this.id = id;
		this.userId = userId;
		this.name = name;
		this.authorName = authorName;
		this.category = category;
		this.subcategory = subcategory;
		this.pages = pages;
		this.image = image;
		this.available = available;
		this.buy = buy;
		this.rent = rent;
		this.buyAmount = buyAmount;
		this.rentAmount = rentAmount;
	}



	//compareTo method to be used for sorting
	public int compareTo(Book book){
		return name.compareTo(book.getName());
	}


	//overriding hashCode method
	public int hashCode(){
		return name.hashCode();
	}


	//overriding equals method
	public boolean equals(Object o){
		if(o == null){
			return false;
		}

		if(!(o instanceof Book)){
			return false;
		}

		if(o == this){
			return true;
		}
		
		Book book = (Book)o;
		return name.equals(book.getName());
	}







	//getter methods

	public int getId(){
		return id;
	}

	public int getUserId(){
		return userId;
	}

	public String getName(){
		return name;
	}

	public String getAuthorName(){
		return authorName;
	}

	public String getCategory(){
		return category;
	}

	public String getSubcategory(){
		return subcategory;
	}

	public int getPages(){
		return pages;
	}

	public String getImage(){
		return image;
	}

	public int getAvailable(){
		return available;
	}

	public int getBuy(){
		return buy;
	}

	public int getRent(){
		return rent;
	}

	public double getBuyAmount(){
		return buyAmount;
	}

	public double getRentAmount(){
		return rentAmount;
	}




	//setter methods

	public void setId(int id){
		this.id = id;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public void setSubCategory(String subcategory){
		this.subcategory = subcategory;
	}

	public void setPages(int pages){
		this.pages = pages;
	}

	public void setImage(String image){
		this.image = image;
	}

	public void setAvailable(int available){
		this.available = available;
	}

	public void setBuy(int buy){
		this.buy = buy;
	}

	public void setRent(int rent){
		this.rent = rent;
	}

	public void setBuyAmount(double buyAmount){
		this.buyAmount = buyAmount;
	}

	public void setRentAmount(double rentAmount){
		this.rentAmount = rentAmount;
	}




	//static methods

	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){

		Object[] fieldValues = {this.id , this.userId , this.name , this.authorName , this.category , this.subcategory , this.pages ,
								 this.image , this.available , this.buy , this.rent , this.buyAmount , this.rentAmount};

		return fieldValues;
	}



	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Name : " + this.name);
		stringBuilder.append(" ");
		stringBuilder.append("Author name : " + this.authorName);
		stringBuilder.append(" ");
		stringBuilder.append("Category : " + this.category);
		stringBuilder.append(" ");
		stringBuilder.append("Subcategory : " + this.subcategory);
		stringBuilder.append(" ");
		stringBuilder.append("Pages : " + this.pages);

		return stringBuilder.toString();
	}




	//method for getting a book object from the result set
	public static Book getBookFromResultSet(ResultSet rs) throws Exception {
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
			System.out.println("Exception in getBookFromResultSet method in Books class");
			throw ex;
		}
	}


	//getting book object from httpServletRequest object
	public static Book getBookObjectFromRequest(HttpServletRequest req) throws Exception{
		try{
			String id = req.getParameter("id");
			String userId = req.getParameter("userId");
			String name = req.getParameter("name");
			String authorName = req.getParameter("authorName");
			String category = req.getParameter("category");
			String subcategory = req.getParameter("subcategory");
			String pages = req.getParameter("pages");
			String image = req.getParameter("image");
			System.out.println("image ------- " + image);
			String available = req.getParameter("available");
			String buy = req.getParameter("buy");
			String rent = req.getParameter("rent");
			String buyAmount = req.getParameter("buyAmount");
			
			String rentAmount = req.getParameter("rentAmount");
			

			//creating new book object from values retrieved
			//id value will be passed as -1
			Book book = new Book(Integer.parseInt(userId) , name , authorName , category , subcategory , Integer.parseInt(pages) , image
						, Integer.parseInt(available) , Integer.parseInt(buy) , Integer.parseInt(rent) , Double.parseDouble(buyAmount) ,
						Double.parseDouble(rentAmount));
			System.out.println("buyAmount ------- " + book.getBuyAmount());
			System.out.println("rentAmount ------- " + book.getRentAmount());

			return book;
		}
		catch(Exception ex){
			System.out.println("Error in getBookObjectFromRequest in BooksHandler");
			throw ex;
		}
	}


	//getting string representation of book for fields name , authorname , category , subcategory
	public String getBookString(){
		try{
			StringBuilder bookString = new StringBuilder();

			bookString.append(this.name);
			bookString.append(this.authorName);
			bookString.append(this.category);
			bookString.append(this.subcategory);

			return bookString.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getBookString in Books");
			throw ex;
		}
	}


} 