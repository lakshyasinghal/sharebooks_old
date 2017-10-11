package com.sharebooks.database.interfaces;


import java.util.*;

public interface QueryBuilder {
	
	public abstract String buildQuery(String table , List<String> fields , int generic) throws Exception;
}