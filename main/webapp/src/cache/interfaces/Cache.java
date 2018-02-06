package com.sharebooks.cache.interfaces;


import java.util.*;


public interface Cache {

	public abstract void init(Object obj) throws Exception;

	public abstract List<Object> getAll() throws Exception; 

	public abstract Object get(int key) throws Exception;

	public abstract void insert(int key , Object obj) throws Exception;

	public abstract void delete() throws Exception;

}