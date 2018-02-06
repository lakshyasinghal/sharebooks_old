package com.sharebooks.webRequests.models;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.sharebooks.webRequests.interfaces.CheckoutHandlerInterface;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.appEntities.entities.*;
import com.sharebooks.appEntities.models.BookRequestManager;
import com.sharebooks.response.models.ResponseManager;
import com.sharebooks.response.entities.Response;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.util.StringHandler.*;




public class CheckoutHandler {

	private static boolean debugging = true;

	public void init(){
		//nothing here
	}


	public Response addBookRequest(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try{
			System.out.println("Inside addBookRequest in CheckoutHandler");
			Response response = null;

			BookRequest bookRequest = Resources.httpRequestFactory().bookRequest(req);

			System.out.println("BookRequest : " + bookRequest);

			BookRequestManager bookRequestManager = Resources.getBookRequestManager();

			int added = bookRequestManager.addBookRequest(bookRequest);

			switch(added){
				case 0 :
					response = new Response(JSON , res , false , ADD_BOOK_REQUEST_FAILED);
					break;
				case 1 :
					response = new Response(JSON , res , true , ADD_BOOK_REQUEST_SUCCESSFUL);
					break;
				default :
					break;
			}

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in addBookRequest in CheckoutHandler");
			throw ex;
		}
	}

	

}