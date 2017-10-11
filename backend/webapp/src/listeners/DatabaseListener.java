package com.sharebooks.listeners;


import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.sharebooks.database.models.Connector;



public class DatabaseListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent event){
		try{
			//System.out .println();
			System.out .println("\n\nInitializing database connection\n\n");
			//System.out .println();

			ServletContext servContext = event.getServletContext();

			String dbURL = servContext.getInitParameter("DB_URL");
			String username = servContext.getInitParameter("DB_USERNAME");
			String password = servContext.getInitParameter("DB_PASSWORD");

			Connector.init();

			Connector.setProperties(dbURL , username , password);
			Connector.createConnection();

			testConnection();
		}
		catch(Exception ex){
			System.out.println("Exception occurred in contextInitialied method in DatabaseListener class");
			System.out.println(ex);
		}
	}


	private void testConnection() throws Exception{
		try{
			System.out .println("\n\nTesting database connection\n\n");
			if(Connector.getConnection() == null){
				throw new Exception("Connection not initialized");
			}
		}
		catch(Exception ex){
			throw ex;
		}
	}

	public void contextDestroyed(ServletContextEvent event){
		try{
			System.out.println("Inside contextDestroyed in DatabaseListener");
			if(Connector.getConnection() != null){
				Connector.closeConnection();
				System.out.println("Database connection closed");
			}
		}
		catch(Exception ex){
			System.out.println("Exception occurred in contextDestroyed method in DatabaseListener class");
			System.out.println(ex);
		}
	}
}