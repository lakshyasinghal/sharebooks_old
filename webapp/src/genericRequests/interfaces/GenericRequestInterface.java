package com.sharebooks.genericRequests.interfaces;


import javax.servlet.http.*;
import com.sharebooks.response.entities.Response;


public interface GenericRequestInterface {

	public abstract Response getUser(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getBook(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getAllBooks(HttpServletRequest req , HttpServletResponse res) throws Exception;
	
}