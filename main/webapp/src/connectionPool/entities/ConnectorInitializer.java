package com.sharebooks.connectionPool.entities;


import static com.sharebooks.staticClasses.LogMessages.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import com.sharebooks.staticClasses.Properties;


//this class is created so that connector class can be kept private
//will help in initializing the connector class
public class ConnectorInitializer {

	private static boolean connectorInitialized = false; 

	public static void initializeConnector(String server , String portNum , String username , String password , String dbName) throws Exception {
		try{
			if(!connectorInitialized){
				Connector.init(server , portNum , username , password , dbName);
			}
			else{
				throw new Exception(SOME_OUTER_CLASS_INITIALIZING_CONNECTOR);
			}
		}
		catch(Exception ex){
			System.out.println("Exception occurred in init in Connector class");
			System.out.println(ex);
		}
	}

}

