package com.sharebook.cache.entities;


import com.sharebooks.cache.abstractClasses.LRUCache;
import com.sharebook.appEntities.entities.Book;
import static com.sharebook.staticClasses.ExceptionMessages.*;
import java.util.*;



//LRUBookCache will be a singleton class
public abstract class LRUBookCache extends LRUCache {
	
	private static LRUBookCache lruBookCache;

	/*
	static {
		lruBookCache = new LRUBookCache();
	}
	*/

	private LRUBookCache() throws Exception{
		//nothing
	}


	public static LRUBookCache getLRUBookCache() throws Exception{
		try{
			//lazy instantiation
			if(lruBookCache == null){
				synchronized(LRUBookCache.class){
					if(lruBookCache == null){
						lruBookCache = new LRUBookCache();
					}
				}
			}

			return lruBookCache;
		}
		catch(Exception ex){
			System.out.println("Error in getLRUBookCache in LRUBookCache");
			throw ex;
		}
	}


	public void init(int capacity) throws Exception{
		try{
			if(capacity < 1){
				throw new Exception(CACHE_SIZE_CANNOT_BE_ZERO);
			}
			else{
				super.init(capacity);
			}
		}
		catch(Exception ex){
			System.out.println("Error in init in LRUBookCache");
			throw ex;
		}
	}



	public Book get(int key) throws Exception{
		try{
			Object obj = super.get(key);

			return Book(obj);
		}
		catch(Exception ex){
			System.out.println("Error in get method in LRUBookCache");
			throw ex;
		}
	}




	public void insert(int key , Book book) throws Exception {
		try{
			super.insert(key , book);
		}
		catch(Exception ex){
			System.out.println("Error in insert method in LRUBookCache");
			throw ex;
		}
	}

}