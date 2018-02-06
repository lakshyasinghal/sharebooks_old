package com.sharebooks.staticClasses;



public class Requests {

	//JSP Requests
	public static final String IN = "/in";
	public static final String HOME = "/home";
	public static final String VIEW_BOOK = "/viewBook";
	public static final String CHECKOUT = "/checkout";

	//Resource requests
	public static final String SIGN_IN = "/signIn";
	public static final String SIGN_UP = "/signUp";
	public static final String SIGN_OUT = "/signOut";
	public static final String GET_ALL_BOOKS = "/getAllBooks";
	public static final String GET_BOOK = "/getBook";
	public static final String GET_USER = "/getUser";
	public static final String FILTER_BOOKS_BY_CATEGORY = "/home/filterBooksByCategory";
	public static final String FILTER_BOOKS_BY_SEARCH = "/home/filterBooksBySearch";
	public static final String ADD_BOOK = "/home/addBook";
	public static final String GET_NOTIFICATIONS = "/home/getNotifications";
	public static final String UPDATE_USER = "/home/updateUser";
	public static final String VIEW_HISTORY = "/home/viewHistory";
	public static final String GET_SIMILAR_BOOKS = "/viewBook/getSimilarBooks";
	public static final String ADD_BOOKREQUEST = "/checkout/addBookRequest";
	public static final String BOOK_RESPONSE = "/respondToBookRequest";
}