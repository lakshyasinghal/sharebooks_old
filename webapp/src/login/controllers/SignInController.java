package com.sharebooks.login.controllers;


import javax.servlet.Servlet;                        //interface
import javax.servlet.GenericServlet;                 //abstract class
import javax.servlet.http.HttpServlet;               //abstract class
import javax.servlet.http.HttpServletRequest;        //interface
import javax.servlet.http.HttpServletResponse;       //interface
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import static com.sharebooks.util.URLConstants.*;
import com.sharebooks.login.models.SignIn;
import com.sharebooks.login.entities.User;



public class SignInController extends HttpServlet {

	public void init(){
		//nothing
	}



	public void doGet(HttpServletRequest req , HttpServletResponse res){
		doPost(req , res);
	}


	public void doPost(HttpServletRequest req , HttpServletResponse res){
		try{

			String username = req.getParameter("email");
			String password = req.getParameter("password");

			//Use SignIn class to validate user
			SignIn signIn = new SignIn(username , password);
			//User user = nes User(username , password);

			//the value of validUser will be either 0 or 1
			int userId = signIn.validateUser();

			System.out.println("User Id - " + userId);

			moveForward(req , res , userId , username , password , signIn);
		}
		catch(Exception ex){
			System.out.println("Error in SignInController : " + ex);
		}
	}



	private void moveForward(HttpServletRequest req , HttpServletResponse res , int userId , String username , String password , SignIn signIn) throws Exception{
		try{
			RequestDispatcher view = null;

			//if login credentials are correct
			if(userId >= 1){
				//add username and password to session object
				HttpSession session = req.getSession();
				User user = new User(userId , username , password);
				// session.setAttribute("username" , username);
				// session.setAttribute("password" , password);
				// session.setAttribute("userId" , userId);
				session.setAttribute("user" , user);
				session.setAttribute("profileImage" , "lakshya.jpg");

				//setting initial resources in request object such as books list , books categories etc.
				signIn.setInitialResources(req , getServletContext());

				//render home page
				view = req.getRequestDispatcher(HOMEPAGE_JSP);
				view.forward(req , res);
			}
			else{
				//redirect to login jsp page with error message
				req.setAttribute("loginStatusCode" , 2);
				view = req.getRequestDispatcher(LOGIN_JSP);
				view.forward(req , res);
			}
		}
		catch(Exception ex){
			System.out.println("Error in moveForward method in SignInController class");
			throw ex;
		}
	}

}