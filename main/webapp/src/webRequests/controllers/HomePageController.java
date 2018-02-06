package com.sharebooks.webRequests.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.webRequests.models.HomePageHandler;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseManager;
import static com.sharebooks.staticClasses.Requests.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.staticClasses.StatusCodes.*;


public class HomePageController extends HttpServlet {


	public void init(){
		//nothing here
	}


	public void doGet(HttpServletRequest req , HttpServletResponse res) {
		try{
			System.out.println("Inside doGet method in HomePageController");
			doPost(req , res);
		}
		catch(Exception ex){
			System.out.println("Error in doGET in HomePageController : " + ex);
		}
	}


	public void doPost(HttpServletRequest req , HttpServletResponse res) {
		try{
			System.out.println("\nThread " + Thread.currentThread().getName() + " inside doPost method in HomePageController");

			ResponseManager responseManager = Resources.getResponseManager();
			Response response = null;
			HomePageHandler homePageHandler = new HomePageHandler();
			String requestedURL = req.getRequestURL().toString();

			requestedURL = requestedURL.split("sharebooks")[1];

			System.out.println("Requested URL : " + requestedURL);

			//if seesion expires take to the session timeout jsp page
			if(isSessionTimedOut(req)){
				System.out.println("Session has timed out\n\n");
				response = getSessionTimeOutResponse(req , res);
			}
			else{
				switch(requestedURL){
					case ADD_BOOK :
						response = homePageHandler.addBook(req , res);
						break;
					case UPDATE_USER :
						response = homePageHandler.updateUser(req , res);
						break;
					case FILTER_BOOKS_BY_CATEGORY :
						response = homePageHandler.filterByCategory(req , res);
						break;
					case FILTER_BOOKS_BY_SEARCH :
						response = homePageHandler.filterBySearch(req , res);
						break;
					// case VIEW_BOOK :
					// 	response = homePageHandler.viewBook(req , res);
					// 	break;
					case GET_NOTIFICATIONS :
						response = homePageHandler.getNotifications(req , res);
						break;
					default :
						break;
				}
			}

			responseManager.sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Error in doPost in HomePageController : " + ex);
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
			System.out.println("Error in isSeesionTimedOut in HomePageController");
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
			System.out.println("Error in getSessionTimeOutResponse in HomePageController");
			throw ex;
		}
	}

	
}