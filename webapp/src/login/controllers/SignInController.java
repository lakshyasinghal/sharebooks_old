package com.sharebooks.login.controllers;


import javax.servlet.Servlet;                        //interface
import javax.servlet.GenericServlet;                 //abstract class
import javax.servlet.http.HttpServlet;               //abstract class
import javax.servlet.http.HttpServletRequest;        //interface
import javax.servlet.http.HttpServletResponse;       //interface
import com.sharebooks.login.models.SignIn;
import com.sharebooks.user.entities.User;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseHandler;
import com.sharebooks.commonResources.entities.Resources;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;




public class SignInController extends HttpServlet {

	public void init(){
		//nothing
	}



	public void doGet(HttpServletRequest req , HttpServletResponse res){
		doPost(req , res);
	}


	public void doPost(HttpServletRequest req , HttpServletResponse res){
		try{
			//Use SignIn class to validate user
			SignIn signIn = new SignIn(req);
			
			User user = signIn.getUser();

			moveForward(req , res , user);
		}
		catch(Exception ex){
			System.out.println("Error in SignInController : " + ex);
		}
	}



	private void moveForward(HttpServletRequest req , HttpServletResponse res , User user) throws Exception{
		try{
			Response response = null;
			ResponseHandler responseHandler = Resources.getResponseHandler();

			//iwhen there is no user for the given login credentials
			if(user == null){
				response = new Response(JSON , res , false , LOGIN_FAILED);
			}
			//when there is a user for the given login credentials
			else{
				//start the session 
				HttpSession session = req.getSession();
				//add user object to the session
				session.setAttribute("user" , user);

				response = new Response(JSON , res , true , LOGIN_SUCCESSFUL);
			}

			responseHandler.sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Error in moveForward method in SignInController");
			throw ex;
		}
	}

}