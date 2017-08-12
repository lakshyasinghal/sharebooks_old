package com.sharebooks.viewBook.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.viewBook.models.ViewBookHandler;
import com.sharebooks.commonResources.entities.Resources;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseHandler;
import static com.sharebooks.staticClasses.Requests.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.staticClasses.StatusCodes.*;





public class ViewBookController extends HttpServlet {

	private boolean debugging = true;

	public void init(){
		//nothing here
	}


	public void doGet(HttpServletRequest request , HttpServletResponse response){
		try{
			if(debugging){
				System.out.println("Inside doGet method in ViewBookController");
			}
			doPost(request , response);
		}
		catch(Exception ex){
			System.out.println("Error in doGet in ViewBookController : " + ex);
		}
	}


	public void doPost(HttpServletRequest req , HttpServletResponse res){
		try{
			if(debugging){
				System.out.println("Inside doPost method in ViewBookController");
			}

			ResponseHandler responseHandler = Resources.getResponseHandler();
			Response response = null;
			ViewBookHandler viewBookHandler = new ViewBookHandler();
			String requestedURL = req.getRequestURL().toString();

			requestedURL = requestedURL.split("sharebooks")[1];

			System.out.println("Requested URL : " + requestedURL);

			//if seesion expires take to the session timeout jsp page
			if(isSessionTimedOut(req)){
				System.out.println("\nSession has timed out\n");
				response = getSessionTimeOutResponse(req , res);
			}
			else{
				switch(requestedURL){
					case GET_SIMILAR_BOOKS :
						response = viewBookHandler.getSimilarBooks(req , res);
						break;
					default :
						break;
				}
			}

			responseHandler.sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Error in doPost in ViewBookController : " + ex);
		}
	}


	public boolean isSessionTimedOut(HttpServletRequest req) throws Exception {
		try{
			//req.getSession(false) will return null if there isn't an active session already
			HttpSession session = req.getSession(false);

			if(session == null){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception ex){
			System.out.println("Error in isSeesionTimedOut in ViewBookController");
			throw ex;
		}
	}




	public Response getSessionTimeOutResponse(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;
			
			//response = new Response(JSP , req , res , SESSION_TIMEOUT);
			response = new Response(JSON , res , false , SESSION_DOES_NOT_EXIST);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getSessionTimeOutResponse in ViewBookController");
			throw ex;
		}
	}

} 