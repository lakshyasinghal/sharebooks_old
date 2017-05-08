package com.sharebooks.commonresources;


import com.sharebooks.books.models.BooksHandler;


public class Resources {

	public static BooksHandler booksHandler;


	//no-arg constructor
	public Resources(){

	}


	public static void init() throws Exception {
		try{
			booksHandler = new BooksHandler();
		}
		catch(Exception ex){
			System.out.println("Exception in init method in Resources class : " + ex);
			throw ex;
		}
	}


	public static BooksHandler getBooksHandler(){
		return booksHandler;
	}



}