package com.sharebooks.login.controllers;


import javax.servlet.*;
import javax.servlet.http.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseHandler;
import com.sharebooks.commonResources.entities.Resources;


//this controller class will be used to redirect to log
public class LoginPageController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try{
			//req.setAttribute("statusCode" , 1);
			// RequestDispatcher view = req.getRequestDispatcher(LOGINPAGE_JSP);
			// view.forward(req , res);

			Response response = new Response(JSP , req , res , LOGINPAGE_JSP);
			Resources.getResponseHandler().sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Exception in doGet in LoginPageController : " + ex);
		}
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try{
			//do nothing
		}
		catch(Exception ex){
			System.out.println("Exception in doPost in LoginPageController : " + ex);
		}
	}
}