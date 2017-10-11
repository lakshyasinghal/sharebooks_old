package com.sharebooks.database.interfaces;


import java.util.*;
import java.sql.*;



public interface Executor {
	
	public abstract ResultSet executeQuery(PreparedStatement stmt) throws Exception;

	public abstract int executeUpdate(PreparedStatement stmt) throws Exception;
}