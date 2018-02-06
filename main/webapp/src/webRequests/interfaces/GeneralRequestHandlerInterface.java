package com.sharebooks.webRequests.interfaces;


import javax.servlet.http.*;
import com.sharebooks.response.entities.Response;


public interface GeneralRequestHandlerInterface {

	public abstract Response getCoverPage(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response signIn(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response signUp(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response signOut(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getHomePage(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getUser(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getBook(HttpServletRequest req , HttpServletResponse res) throws Exception;

	public abstract Response getAllBooks(HttpServletRequest req , HttpServletResponse res) throws Exception;
}