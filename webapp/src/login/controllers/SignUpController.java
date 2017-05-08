package com.sharebooks.login.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import static com.sharebooks.util.URLConstants.*;
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
			String name = req.getParameter("name");
			String username = req.getParameter("email");
			String password = req.getParameter("password");
			String mobileNo = req.getParameter("mobileNo");
			String birthday = req.getParameter("birthday");
			String address = req.getParameter("address");
			String city = req.getParameter("city");
			String state = req.getParameter("state");


			int age = com.sharebooks.util.StringParser.getAgeFromBirthday(birthday);

			User user = new User(name , username , password , mobileNo , age , address , city , state);
			//the value of validUser will be either 0 or 1

			UserHandler userHandler = new UserHandler();
			int userAdded = userHandler.addUser(user);

			moveForward(userAdded , req , res);
		}
		catch(Exception ex){
			System.out.println("Error in SignUpController");
			System.out.println(ex);
		}
	}



	public void moveForward(int userAdded , HttpServletRequest req , HttpServletResponse res) throws Exception{
		try{
			RequestDispatcher view = null;

			//if login credentials are correct
			if(userAdded == 1){
				req.setAttribute("userAddedStatusCode" , 1);
			}
			else if(userAdded == 2){
				//redirect to login jsp page with error message
				req.setAttribute("userAddedStatusCode" , 2);
			}
			else{
				//when the details are incomplete
			}

			view = req.getRequestDispatcher(LOGIN_JSP);
			view.forward(req , res);
		}
		catch(Exception ex){
			System.out.println("Error in moveForward method in SignUpController class");
			throw ex;
		}
	}

}