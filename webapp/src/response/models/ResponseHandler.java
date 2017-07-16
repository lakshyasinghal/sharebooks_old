package com.sharebooks.response.models;


import com.sharebooks.commonEntity.entities.Entity;
import com.sharebooks.response.entities.Response;
import com.sharebooks.json.models.JSONBuilder;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import static com.sharebooks.staticClasses.ResponseTypes.*;




public class ResponseHandler {

	public ResponseHandler(){
		//no code
	}


	public void sendResponse(Response response) throws Exception{
		try{

			//System.out.println("\nInside sendResponseHandler\n");

			//System.out.println("Point 0");

			String responseType = response.getResponseType();

			//System.out.println("Point 1");

			switch(responseType){
				case JSON :
					sendJson(response);
					break;
				case JSP :
					sendJsp(response);
					break;
				default :
					break;
			}

			//System.out.println("Point 2");

		}
		catch(Exception ex){
			System.out.println("Error in sendResponse in ResponseHandler");
			throw ex;
		}
	}


	public void sendJson(Response response) throws Exception{
		try{
			HttpServletResponse res = response.getHttpServletResponse();
			boolean success = response.getSuccess();
			String message = response.getMessage();
			int statusCode = response.getStatusCode();
			//int sessionActive = response.getSessionActive();
			List<Entity> entities = response.getEntities();
			//String resultType = response.getResultType();

			String json = null;

			//get json by using JSONBuilder object
			json = new JSONBuilder().getJSON(success , statusCode , entities);
			
			
			System.out.println("Json - " + json);

			res.setContentType("application/json");
			PrintWriter out = res.getWriter();

			out.println(json);
			out.flush();
		}
		catch(Exception ex){
			System.out.println("Error in sendJson in ResponseHandler");
			throw ex;
		}
	}


	public void sendJsp(Response response) throws Exception{
		try{
			//System.out.println("Inside sendJsp");

			//System.out.println("\nPoint 0");

			HttpServletRequest req = response.getHttpServletRequest();
			HttpServletResponse res = response.getHttpServletResponse();
			String jspPath = response.getJspPath();

			//System.out.println("jspPath --- " + jspPath);

			//System.out.println("\nPoint 1");

			RequestDispatcher view = req.getRequestDispatcher(jspPath);
			view.forward(req , res);

			//System.out.println("\nPoint 2");
		}
		catch(Exception ex){
			System.out.println("Error in sendJsp in ResponseHandler");
			throw ex;
		}
	}
}