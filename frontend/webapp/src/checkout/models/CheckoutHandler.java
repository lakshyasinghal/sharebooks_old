package com.sharebooks.checkout.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.checkout.interfaces.CheckoutHandlerInterface;
import com.sharebooks.commonResources.entities.Resources;
import com.sharebooks.commonEntity.entities.Entity;
// import com.sharebooks.books.entities.Book;
// import com.sharebooks.books.models.BooksHandler;
// import com.sharebooks.user.entities.User;
// import com.sharebooks.user.models.UserHandler;
import com.sharebooks.bookRequests.entities.BookRequest;
import com.sharebooks.bookRequests.models.BookRequestHandler;
import com.sharebooks.response.models.ResponseHandler;
import com.sharebooks.response.entities.Response;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.util.StringHandler.*;
//import static com.sharebooks.staticClasses.JSONResultTypes.*;




public class CheckoutHandler implements CheckoutHandlerInterface {

	private static boolean debugging = true;

	public void init(){
		//nothing here
	}


	public Response sendRequest(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try{
			if(debugging){
				System.out.println("\n\nInside sendRequest in CheckoutHandler\n\n");
			}

			Response response = null;

			BookRequest bookRequest = BookRequest.getBookRequestObjectFromRequest(req);

			BookRequestHandler bookRequestHandler = Resources.getBookRequestHandler();

			int added = bookRequestHandler.addBookRequest(bookRequest);

			switch(added){
				case 0 :
					response = new Response(JSON , res , false , SEND_REQUEST_FAILED);
					break;
				case 1 :
					response = new Response(JSON , res , true , SEND_REQUEST_SUCCESSFUL);
					break;
				default :
					break;
			}

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in sendRequest in CheckoutHandler");
			throw ex;
		}
	}

	

}