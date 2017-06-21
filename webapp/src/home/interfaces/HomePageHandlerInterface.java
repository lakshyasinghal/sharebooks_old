package com.sharebooks.home.interfaces;


import javax.servlet.http.*;
import com.sharebooks.response.entities.Response;


public interface HomePageHandlerInterface {

	public abstract Response addBook(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getAllBooks(HttpServletRequest req , HttpServletResponse res) throws Exception;
	
}