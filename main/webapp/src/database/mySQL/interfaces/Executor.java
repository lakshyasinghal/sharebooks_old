package com.sharebooks.database.mySQL.interfaces;


import java.util.*;
import java.sql.*;



public interface Executor {
	
	public abstract ResultSet executeQuery(String sqlQuery , List<String> fieldTypes , List<Object> fieldValues) throws Exception;

	public abstract int executeUpdate(String sqlQuery , List<String> fieldTypes , List<Object> fieldValues) throws Exception;
}