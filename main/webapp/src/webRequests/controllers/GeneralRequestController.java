package com.sharebooks.webRequests.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.webRequests.models.GeneralRequestHandler;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseManager;
import static com.sharebooks.staticClasses.Requests.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.JspPages.*;


public class GeneralRequestController extends HttpServlet {

	private static boolean debugging = true;

	public void init(){
		//nothing here
	}


	public void doGet(HttpServletRequest req , HttpServletResponse res) {
		try{
			System.out.println("Inside doGet method in GenericRequestController");
			doPost(req , res);
		}
		catch(Exception ex){
			System.out.println("Error in doGET in GenericRequestController : " + ex);
		}
	}


	public void doPost(HttpServletRequest req , HttpServletResponse res) {
		try{
			if(debugging){
				System.out.println("\n\n Thread " + Thread.currentThread().getName() + " inside doPost method in GenericRequestController");
			}

			ResponseManager responseManager = Resources.getResponseManager();
			Response response = null;
			GeneralRequestHandler generalRequestHandler = new GeneralRequestHandler();
			String requestedURL = req.getRequestURL().toString();

			requestedURL = requestedURL.split("sharebooks")[1];

			if(debugging){
				System.out.println("Requested URL : " + requestedURL);
			}


			System.out.println("GeneralRequestHandler : " + generalRequestHandler);

			//!requestedURL.equals(IN) && !requestedURL.equals(SIGN_IN) && !requestedURL.equals(SIGN_UP) && !requestedURL.equals(SIGN_OUT) &&

			//if session expires take to the session timeout jsp page
			if(!requestedURL.equals(IN) && !requestedURL.equals(SIGN_IN) && !requestedURL.equals(SIGN_UP) && isSessionTimedOut(req)){
				if(debugging){
					System.out.println("\n\nSession has timed out or doesn't exist\n\n");
				}
				response = getSessionTimeOutResponse(req , res);
			}
			else{
				switch(requestedURL){
					case IN :
						response = generalRequestHandler.getCoverPage(req , res);
						break;
					case SIGN_IN :
						response = generalRequestHandler.signIn(req , res);
						break;
					case SIGN_UP :
						response = generalRequestHandler.signUp(req , res);
						break;
					case SIGN_OUT :
						response = generalRequestHandler.signOut(req , res);
						break;
					case HOME :
						response = generalRequestHandler.getHomePage(req , res);
						break;
					case GET_USER :
						response = generalRequestHandler.getUser(req , res);
						break;
					case GET_BOOK :
						response = generalRequestHandler.getBook(req , res);
						break;
					case GET_ALL_BOOKS :
						//Thread.sleep(1000);
						response = generalRequestHandler.getAllBooks(req , res);
						break;
					case VIEW_BOOK :
						response = generalRequestHandler.viewBook(req , res);
						System.out.println("Response object --- " + response);
						break;
					case CHECKOUT :
						response = generalRequestHandler.checkout(req , res);
						System.out.println("Response object --- " + response);
						break;
					default :
						break;
				}
			}

			responseManager.sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Error in doPost in GenericRequestController : " + ex);
		}
	}


	public boolean isSessionTimedOut(HttpServletRequest req) throws Exception {
		try{
			if(debugging){
				System.out.println("Inside isSessionTimedOut in GenericRequestController");
			}
			//req.getSession(false) will return null if there isn't an active session already
			HttpSession session = req.getSession(false);

			if(debugging){
				System.out.println("\n\nSession ---- " + session);
			}

			if(session == null){
				return true;
			}
			else{
				if(debugging){
					System.out.println("\n\nSession --- " + session.toString() + "\n");
				}
				return false;
			}
		}
		catch(Exception ex){
			System.out.println("Error in isSessionTimedOut in GenericRequestController");
			throw ex;
		}
	}




	public Response getSessionTimeOutResponse(HttpServletRequest req , HttpServletResponse res) throws Exception {
		try {
			Response response = null;
			
			//response = new Response(JSP , req , res , SESSION_TIMEOUT);
			response = new Response(JSP , req , res , SESSIONEXPIRED_JSP);

			return response;
		}
		catch(Exception ex){
			System.out.println("Error in getSessionTimeOutResponse in GenericRequestController");
			throw ex;
		}
	}

	
}