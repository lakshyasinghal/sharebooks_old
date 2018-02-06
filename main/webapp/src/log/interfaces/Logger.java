package com.sharebooks.logging.interfaces;

import com.sharebooks.exception.entities.*;



public interface Logger {

	public abstract void init(Object obj1 , Object obj2) throws Exception;

	public abstract void log(Object obj) throws Exception; 

	public abstract void error(Object obj) throws Exception;

	public abstract void debug(Object obj) throws Exception;
}