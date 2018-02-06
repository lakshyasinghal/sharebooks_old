package com.sharebooks.database.mySQL.interfaces;


import java.util.*;

public interface QueryBuilder {
	
	public abstract String buildQuery(String table , List<String> fields , int generic) throws Exception;
}