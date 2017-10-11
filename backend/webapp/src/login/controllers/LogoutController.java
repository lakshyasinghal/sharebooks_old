package com.sharebooks.login.controllers;


import javax.servlet.*;
import javax.servlet.http.*;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseHandler;
import com.sharebooks.commonResources.entities.Resources;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;
//import static com.sharebooks.staticClasses.Messages.*;
import static com.sharebooks.staticClasses.StatusCodes.*;




//this controller class will be used to redirect to log
public class LogoutController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try{
			Response response = null;
			HttpSession session = req.getSession(false);

			//if session has already timed out
			if(session == null){
				response = new Response(JSON , res , true , SESSION_ALREADY_EXPIRED);
			}
			else{      //invalidate the session
				session.invalidate();
				response = new Response(JSON , res , true , LOGOUT_SUCCESSFUL);
			}

			Resources.getResponseHandler().sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Exception in LoginPageController : " + ex);
		}
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try{
			//do nothing
		}
		catch(Exception ex){
			System.out.println("Exception in doPost in LogoutController : " + ex);
		}
	}
}