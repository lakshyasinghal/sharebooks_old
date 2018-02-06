package com.sharebooks.appEntities.entities;


import static com.sharebooks.appEntities.enums.BookAvailabilityStatus.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


//immutable class
public final class Book extends Entity implements Comparable<Book> {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final int userId;
	private final String name;
	private final String authorName;
	private final String category;
	private final String subcategory;
	private final int pages;
	private final String image;
	private final int available;
	private final int buy;
	private final int rent;
	private final double buyAmount;
	private final double rentAmount;


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "userId" , "name" , "authorName" , "category" , "subcategory" ,"pages" , "image" , "available"
							 ,"buy" , "rent" , "buyAmount" , "rentAmount"};
		fieldTypes = new String[]{"int" , "int" , "string" , "string" , "string" , "string" , "int" , "string" , "int" , "int"
							  , "int" , "double" , "double"};
	}


	public Book(int id , int userId , String name , String authorName , String category , String subcategory , int pages , String image ,
	 int available , int buy , int rent , double buyAmount , double rentAmount){
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



	//compareTo method to be used for sorting
	public int compareTo(Book book){
		return name.compareTo(book.name());
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
		return name.equals(book.name());
	}







	//getter methods

	public int id(){
		return id;
	}

	public int userId(){
		return userId;
	}

	public String name(){
		return name;
	}

	public String authorName(){
		return authorName;
	}

	public String category(){
		return category;
	}

	public String subcategory(){
		return subcategory;
	}

	public int pages(){
		return pages;
	}

	public String image(){
		return image;
	}

	public int available(){
		return available;
	}

	public int buy(){
		return buy;
	}

	public int rent(){
		return rent;
	}

	public double buyAmount(){
		return buyAmount;
	}

	public double rentAmount(){
		return rentAmount;
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

	


	//getting string representation of book for fields name , authorname , category , subcategory
	//will help in debugging
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