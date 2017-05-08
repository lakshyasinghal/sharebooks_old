package com.sharebooks.user.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.sharebooks.user.entities.User;
import com.sharebooks.user.models.UserHandler;



public class AddUserController extends HttpServlet {

	// public void init(){
	// 	//nothing here
	// 	System.out.println("Inside init method in AddUserController");
	// }


	// public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {

	// }


	// public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {
	// 	try{
	// 		String username = req.getParameter("username");
	// 		String password = req.getParameter("password");
	// 		String name = req.getParameter("name");
	// 		String age = req.getParameter("age");
	// 		String address = req.getParameter("address");
	// 		String city = req.getParameter("city");
	// 		String state = req.getParameter("state");


	// 		User user = new User(username , password , name , Integer.parseInt(age) , address , city , state);
	// 		//the value of validUser will be either 0 or 1

	// 		UserHandler userHandler = new UserHandler();
	// 		int inserted = userHandler.addUser(user);
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("Error in AddBookController");
	// 	}
	// }

}