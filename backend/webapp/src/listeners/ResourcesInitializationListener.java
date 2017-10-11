package com.sharebooks.listeners;


import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.sharebooks.commonResources.entities.Resources;



public class ResourcesInitializationListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent event){
		try{
			System.out.println("Inside contextInitialized in ResourcesInitializationListener");
			Resources.init();
		}
		catch(Exception ex){
			System.out.println("Exception occurred in contextInitialied method in ResourcesInitializationListener");
			System.out.println(ex);
		}
	}


	public void contextDestroyed(ServletContextEvent event){
		try{
			//do nothing
			System.out.println("Inside contextDestroyed in ResourcesInitializationListener");
		}
		catch(Exception ex){
			System.out.println("Exception occurred in contextDestroyed method in ResourcesInitializationListener");
			System.out.println(ex);
		}
	}
}