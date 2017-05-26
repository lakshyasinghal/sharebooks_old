package com.sharebooks.books.entities;


import com.sharebooks.common.interfaces.JsonObjectBuilder;



//immutable class
public final class Book implements JsonObjectBuilder {

	private int id;
	private int userId;
	private final String name;
	private final String authorName;
	private final String category;
	private final int pages;
	private final String image;


	public Book(){
		//no arg constructor
		id = -1;
		userId = -1;
		name = "";
		authorName = "";
		category = "";
		pages = 0;
		image = "";
	}


	public Book(int id , int userId , String name , String authorName , String category , int pages , String image){
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.authorName = authorName;
		this.category = category;
		this.pages = pages;
		this.image = image;
	}

	// public Book(int userId , String name , String authorName , String category , int pages , String image){
	// 	this.userId = userId;
	// 	this.name = name;
	// 	this.authorName = authorName;
	// 	this.category = category;
	// 	this.pages = pages;
	// 	this.image = image;
	// }

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

	public int getPages(){
		return pages;
	}

	public String getImage(){
		return image;
	}





	public String getJsonObject(Book book){
		StringBuilder jsonObj = new StringBuilder();

		try{
			jsonObj.append("{");
			jsonObj.append("id" + ":" + book.getId());
			jsonObj.append(",");
			jsonObj.append("userId" + ":" + book.getUserId());
			jsonObj.append(",");
			jsonObj.append("name" + ":" + "'" + book.getName() + "'");
			jsonObj.append(",");
			jsonObj.append("authorName" + ":" + "'" + book.getAuthorName() + "'");
			jsonObj.append(",");
			jsonObj.append("category" + ":" + "'" + book.getCategory() + "'");
			jsonObj.append(",");
			jsonObj.append("pages" + ":" + book.getPages());
			jsonObj.append(",");
			jsonObj.append("image" + ":" + "'" + book.getImage() + "'");
			jsonObj.append("}");

			return jsonObj.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getJsonObject in Book");
			throw ex;
		}
	}
} 