package com.sharebooks.home.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.home.models.HomePageHandler;
import com.sharebooks.commonResources.entities.Resources;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseHandler;
import static com.sharebooks.staticClasses.Requests.*;


public class HomePageController extends HttpServlet {

	public void init(){
		//nothing here
	}


	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {
		try{
			doPost(req , res);
		}
		catch(Exception ex){
			System.out.println("Error in doGET in HomePageController : " + ex);
		}
	}


	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
		try{
			ResponseHandler responseHandler = Resources.getResponseHandler();
			Response response = null;
			HomePageHandler homePageHandler = new HomePageHandler();
			String servletPath = req.getServletPath();

			System.out.println("Servlet path : " + servletPath);

			//if seesion expires take to the session timeout jsp page
			if(isSessionTimedOut(req)){
				response = homePageHandler.sessionTimeOut(req , res);
				return;
			}


			switch(servletPath){
				case GET_USER :
					response = homePageHandler.getUser(req , res);
					break;
				case ADD_BOOK :
					response = homePageHandler.addBook(req , res);
					break;
				// case FILTER_BY_CATEGORY :
				// 	response = homePageHandler.filterByCategory(req , res);
				// 	break;
				// case FILTER_BY_SEARCH :
				// 	response = homePageHandler.filterBySearch(req , res);
				// 	break;
				case GET_ALL_BOOKS :
					response = homePageHandler.getAllBooks(req , res);
					break;
				case UPDATE_USER :
					response = homePageHandler.updateUser(req , res);
					break;
				case VIEW_BOOK :
					response = homePageHandler.viewBook(req , res);
					break;
				default :
					break;
			}

			responseHandler.sendResponse(response);
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

	
}