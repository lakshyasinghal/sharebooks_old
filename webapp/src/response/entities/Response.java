package com.sharebooks.response.entities;



import com.sharebooks.commonEntity.entities.Entity;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


//Immutable class
public class Response {

	private String responseType;
	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private String jspPath;
	private boolean success;
	private String message;
	private int statusCode = -1;
	// private int sessionActive = 1;
	private List<Entity> entities;
	//private String resultType;





	
	public Response(){
		//no code
	}



	//constructor for json response
	// public Response(String responseType , HttpServletResponse httpServletResponse , boolean success , String message){
	// 	this.responseType = responseType;
	// 	this.httpServletResponse = httpServletResponse;
	// 	this.success = success;
	// 	this.message = message;
	// }


	//constructor for json response with status code
	public Response(String responseType , HttpServletResponse httpServletResponse , boolean success , int statusCode){
		this.responseType = responseType;
		this.httpServletResponse = httpServletResponse;
		this.success = success;
		//this.message = message;
		this.statusCode = statusCode;
	}


	//constructor for json response with status code and entities
	public Response(String responseType , HttpServletResponse httpServletResponse , boolean success , int statusCode , List<Entity> entities){
		this.responseType = responseType;
		this.httpServletResponse = httpServletResponse;
		this.success = success;
		//this.message = message;
		this.statusCode = statusCode;
		this.entities = entities;
	}




	//constructor for jsp response
	public Response(String responseType , HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse , String jspPath){
		this.responseType = responseType;
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
		this.jspPath = jspPath;
	}





	//Getter methods


	public String getResponseType(){
		return responseType;
	}

	public HttpServletResponse getHttpServletResponse(){
		return httpServletResponse;
	}

	public boolean getSuccess(){
		return success;	
	}

	public String getMessage(){
		return message;
	}

	public int getStatusCode(){
		return statusCode;
	}


	public List<Entity> getEntities(){
		return entities;
	}


	public HttpServletRequest getHttpServletRequest(){
		return httpServletRequest;
	}

	public String getJspPath(){
		return jspPath;
	}

 
}