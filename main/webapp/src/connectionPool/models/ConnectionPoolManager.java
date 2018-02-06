package com.sharebooks.connectionPool.models;


import com.sharebooks.interfaces.Manager;
import com.sharebooks.connectionPool.entities.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import static com.sharebooks.staticClasses.LogMessages.*;
import java.sql.*;




public class ConnectionPoolManager implements Manager {

	private static ConnectionPool connectionPool;



	public ConnectionPoolManager(ConnectionPool connectionPool) throws Exception {
		//nothing
	}


	public static void init(ConnectionPool connectionPool) throws Exception {
		try{
			if(connectionPool == null){
				throw new Exception(CONNECTION_POOL_NOT_INITIALIZED);
			}
			ConnectionPoolManager.connectionPool = connectionPool;
		}
		catch(Exception ex){
			System.out.println("Error in init in ConnectionPoolManager");
			throw ex;
		}
	}




	//this method will return available connection from pool of connections
	//if no connection is available, it will create new connection if pool size is not at it's max capacity, otherwise it will wait
	//for a connection to be released by some other thread  
	public static Connection getConnection() throws Exception{
		try{
			Connection conn = null;
			synchronized(connectionPool){

				int size = connectionPool.size();
				int capacity = connectionPool.capacity();
				int[] connStatusArr = connectionPool.connStatusArray();
				Connection[] connections = connectionPool.connections();

				conn = getAvailableConnection();

				if(conn==null){
					//if connection pool is full, wait for some connection to be released
					if(connectionPool.full()){
						//wait until notified by release connection method
						System.out.println("Waiting for connection to be released");
						connectionPool.wait();
					}
					else{  //add new connection to the connection pool
						connectionPool.addNewConnection();	
					}
					conn = getAvailableConnection();
					if(conn == null){
						throw new Exception(CONNECTION_POOL_NOT_WORKING_PROPERLY);
					}
				}
			}

			return conn;
		}
		catch(Exception ex){
			System.out.println("Error in getConnection in ConnectionPoolManager");
			throw ex;
		}
	}



	private static Connection getAvailableConnection() throws Exception{
		try{
			int selected = -1;
			int size = connectionPool.size();
			int[] connStatusArr = connectionPool.connStatusArray();
			Connection[] connections = connectionPool.connections();

			for(int i=0 ; i<size ; i++){
				if(connStatusArr[i] == 1){
					selected = i;
					connStatusArr[i] = 0;
					break;
				}
			}

			if(selected == -1){
				return null;
			}

			return connections[selected];
		}
		catch(Exception ex){
			System.out.println("Error in getAvailableConnection in ConnectionPoolManager");
			throw ex;
		}
	}




	public static void releaseConnection(Connection connection) throws Exception{
		try{
			synchronized(connectionPool){
				int size = connectionPool.size();
				int[] connStatusArr = connectionPool.connStatusArray();
				Connection[] connections = connectionPool.connections();

				for(int i=0 ; i<size ; i++){
					if(connection.equals(connections[i])){
						connStatusArr[i] = 1;
						break;
					}
				}

				//will notify only the first thread as only one connection will be released
				connectionPool.notify();
				System.out.println("\n\n" + DATABASE_CONNECTION_RELEASED + "\n\n");
			}
		}
		catch(Exception ex){
			System.out.println("Error in releaseConnection in ConnectionPoolManager");
			throw ex;
		}
	}


}