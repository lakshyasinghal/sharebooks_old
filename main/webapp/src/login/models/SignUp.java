package com.sharebooks.login.models;



import com.sharebooks.user.models.UserManager;
import com.sharebooks.user.entities.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.sharebooks.resources.entities.Resources;
import com.sharebooks.factory.interfaces.Factory;





public class SignUp {

	private HttpServletRequest request;

	public SignUp(){
		//no code
	}


	public SignUp(HttpServletRequest request){
		this.request = request;
	}


	public int registerUser() throws Exception{
		try{

			UserManager userManager = Resources.getUserManager();
			User user = Resources.httpRequestFactory().user(req);

			//the value should be 0 or 1 depending on whether the user has been registered or not
			int userAdded = userManager.addUser(user);

			return userAdded;
		}
		catch(Exception ex){
			System.out.println("Error in registerUser in SignUp");
			throw ex;
		}
	}
}