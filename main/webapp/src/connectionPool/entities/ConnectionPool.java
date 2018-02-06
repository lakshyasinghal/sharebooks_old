package com.sharebooks.connectionPool.entities;


import static com.sharebooks.staticClasses.ExceptionMessages.*;
import static com.sharebooks.staticClasses.LogMessages.*;
import java.sql.*;


//Singleton class
//creating instance by eager instantiation as the connection pool will be required for sure
public class ConnectionPool {

	private static ConnectionPool connectionPool = new ConnectionPool();
	private Connection[] connections;
	private int[] connStatusArray;
	private int capacity;
	private int size;


	private ConnectionPool(){
		//nothing
	}


	public void init(int capacity) throws Exception{
		try{
			if(capacity < 0){
				throw new Exception(CONNECTION_POOL_SIZE_CANNOT_BE_ZERO);
			}
			else if(capacity == 0){
				throw new Exception(CONNECTION_POOL_SIZE_CANNOT_BE_NEGATIVE);
			}
			else{
				this.capacity = capacity;
				connections = new Connection[capacity];
				connStatusArray = new int[capacity];
			}
		}
		catch(Exception ex){
			System.out.println("Error in init in ConnectionPool");
			throw ex;
		}
	}




	public static ConnectionPool getConnectionPool(){
		return connectionPool;
	}



	public void addNewConnection() throws Exception{
		try{
			if(full()){
				throw new Exception(CONNECTION_POOL_ALREADY_FULL);
			}
			else{
				connections[size] = Connector.createConnection(); 
				connStatusArray[size] = 1;
				size++;
				System.out.println("\n\n" + DATABASE_CONNECTION_CREATED + "\n\n");
				System.out.println("Size of connection pool : " + size());
			}
		}
		catch(Exception ex){
			System.out.println("Error in addNewConnection method in ConnectionPool");
			throw ex;
		}
	}



	//getter methods

	public Connection[] connections(){
		return connections;
	}

	public int[] connStatusArray(){
		return connStatusArray;
	}

	public int capacity(){
		return capacity;
	}

	public int size(){
		return size;
	}

	public boolean full(){
		return (size == capacity);
	}


	//setter methods

	public void size(int size){
		this.size = size;
	}
}