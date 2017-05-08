package com.sharebooks.books.entities;


//immutable class
public final class Book {

	private int id;
	private final String name;
	private final String authorName;
	private final String category;
	private final int pages;
	private final String imagePath;


	public Book(){
		//no arg constructor
		id = 0;
		name = "";
		authorName = "";
		category = "";
		pages = 0;
		imagePath = "";
	}


	public Book(int id , String name , String authorName , String category , int pages , String imagePath){
		this.id = id;
		this.name = name;
		this.authorName = authorName;
		this.category = category;
		this.pages = pages;
		this.imagePath = imagePath;
	}

	public Book(String name , String authorName , String category , int pages , String imagePath){
		//this.id = id;
		this.name = name;
		this.authorName = authorName;
		this.category = category;
		this.pages = pages;
		this.imagePath = imagePath;
	}

	public int getId(){
		return id;
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

	public String getImagePath(){
		return imagePath;
	}
} 