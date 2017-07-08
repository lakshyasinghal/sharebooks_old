// package com.sharebooks.viewBook.controllers;

// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.io.*;

// import com.sharebooks.viewBook.models.ViewBookHandler;
// import com.sharebooks.commonResources.entities.Resources;
// import com.sharebooks.response.entities.Response;
// import com.sharebooks.response.models.ResponseHandler;
// import static com.sharebooks.staticClasses.Requests.*;
// import static com.sharebooks.staticClasses.ResponseTypes.*;
// import static com.sharebooks.staticClasses.JspPages.*;





// public class ViewBookController extends HttpServlet {


// 	public void init(){
// 		//nothing here
// 	}


// 	public void doGet(HttpServletRequest request , HttpServletResponse response){
// 		try{

// 		}
// 		catch(Exception ex){
// 			System.out.println("Error in doGet in ViewBookController : " + ex);
// 		}
// 	}


// 	public void doPost(HttpServletRequest request , HttpServletResponse response){
// 		try{
// 			System.out.println("Inside doPost method in ViewBookController");

// 			ResponseHandler responseHandler = Resources.getResponseHandler();
// 			Response response = null;
// 			ViewBookHandler viewBookHandler = new ViewBookHandler();
// 			String requestedURL = req.getRequestURL().toString();

// 			requestedURL = requestedURL.split("sharebooks")[1];

// 			System.out.println("Requested URL : " + requestedURL);

// 			//if seesion expires take to the session timeout jsp page
// 			if(isSessionTimedOut(req)){
// 				System.out.println();
// 				System.out.println();
// 				System.out.println("Session has timed out");
// 				response = getSessionTimeOutResponse(req , res);
// 			}
// 			else{
// 				switch(requestedURL){
// 					case HOME :
// 						response = homePageHandler.getHomePage(req , res);
// 						break;
// 					case GET_USER :
// 						response = homePageHandler.getUser(req , res);
// 						break;
// 					case ADD_BOOK :
// 						response = homePageHandler.addBook(req , res);
// 						break;
// 					case GET_ALL_BOOKS :
// 						response = homePageHandler.getAllBooks(req , res);
// 						break;
// 					case UPDATE_USER :
// 						response = homePageHandler.updateUser(req , res);
// 						break;
// 					case VIEW_BOOK :
// 						response = homePageHandler.viewBook(req , res);
// 						break;
// 					case GET_NOTIFICATIONS :
// 						response = homePageHandler.getNotifications(req , res);
// 						break;
// 					default :
// 						break;
// 				}
// 			}

// 			responseHandler.sendResponse(response);
// 		}
// 		catch(Exception ex){
// 			System.out.println("Error in doPost in ViewBookController : " + ex);
// 		}
// 	}


// 	public boolean isSessionTimedOut(HttpServletRequest req) throws Exception {
// 		try{
// 			//req.getSession(false) will return null if there isn't an active session already
// 			HttpSession session = req.getSession(false);

// 			if(session == null){
// 				return true;
// 			}
// 			else{
// 				return false;
// 			}
// 		}
// 		catch(Exception ex){
// 			System.out.println("Error in isSeesionTimedOut in ViewBookController");
// 			throw ex;
// 		}
// 	}




// 	public Response getSessionTimeOutResponse(HttpServletRequest req , HttpServletResponse res) throws Exception {
// 		try {
// 			Response response = null;
			
// 			//response = new Response(JSP , req , res , SESSION_TIMEOUT);
// 			response = new Response(JSP , req , res , SESSIONEXPIRED_JSP);

// 			return response;
// 		}
// 		catch(Exception ex){
// 			System.out.println("Error in getSessionTimeOutResponse in ViewBookController");
// 			throw ex;
// 		}
// 	}

// } 