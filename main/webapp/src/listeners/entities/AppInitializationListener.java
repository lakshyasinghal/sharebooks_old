package com.sharebooks.listeners.entities;


import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.sharebooks.init.entities.AppInitializer;



public class AppInitializationListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent event){
		try{
			System.out.println("\n\nInside contextInitialized in AppInitializationListener\n\n");
			AppInitializer appInitializer = new AppInitializer();
			appInitializer.initApp();
		}
		catch(Exception ex){
			System.out.println("Exception occurred in contextInitialied method in AppInitializationListener");
			System.out.println(ex);
		}
	}


	public void contextDestroyed(ServletContextEvent event){
		try{
			//do nothing
			System.out.println("Inside contextDestroyed in AppInitializationListener");
		}
		catch(Exception ex){
			System.out.println("Exception occurred in contextDestroyed method in AppInitializationListener");
			System.out.println(ex);
		}
	}
}