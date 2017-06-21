package com.sharebooks.login.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.commonResources.entities.Resources;
import com.sharebooks.login.models.SignUp;
import com.sharebooks.response.entities.Response;
import com.sharebooks.response.models.ResponseHandler;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;
import static com.sharebooks.staticClasses.JspPages.*;
import static com.sharebooks.staticClasses.StatusCodes.*;
import com.sharebooks.user.entities.User;
import com.sharebooks.user.models.UserHandler;

import com.sharebooks.util.StringParser;




public class SignUpController extends HttpServlet {

	public void init(){
		//nothing here
		System.out.println("Inside init method in SignUpController");
	}


	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {

	}


	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
		try{
			SignUp signUp = new SignUp(req);
			int userAdded = signUp.registerUser();

			sendResponse(res , userAdded);
		}
		catch(Exception ex){
			System.out.println("Error in SignUpController");
			System.out.println(ex);
		}
	}


	public void sendResponse(HttpServletResponse res , int userAdded) throws Exception{
		try{
			int statusCode = -1;
			boolean success = false;

			switch(userAdded){
				case 0:
					statusCode = USERINFO_INCOMPLETE;
					success = false;
					break;
				case 1:
					statusCode = ADD_USER_SUCCESSFUL;
					success = true;
					break;
				case 2 :
				 	statusCode = USERNAME_ALREADY_EXISTS;
				 	success = false;
				 	break;
				default :
					break;
			}

			Response response = new Response(JSON , res , success , statusCode);
			ResponseHandler resHandler = Resources.getResponseHandler();
			resHandler.sendResponse(response);
		}
		catch(Exception ex){
			System.out.println("Error in sendResponse in SignUpController class");
			throw ex;
		}
	}


}