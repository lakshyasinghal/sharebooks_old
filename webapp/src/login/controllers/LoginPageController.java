package com.sharebooks.login.controllers;


import javax.servlet.*;
import javax.servlet.http.*;

import static com.sharebooks.util.URLConstants.*;


//this controller class will be used to redirect to log
public class LoginPageController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try{
			req.setAttribute("statusCode" , 1);
			RequestDispatcher view = req.getRequestDispatcher(LOGIN_JSP);
			view.forward(req , res);
		}
		catch(Exception ex){
			System.out.println("Exception in LoginPageController : " + ex);
		}
	}
}