package com.sharebooks.exception.entities;




public final class CacheException extends Exception {

	private static final String message = "Exception occurred in cache";

	public CacheException(String message){
		super(CacheException.message + "\n" + message);
	}
} 