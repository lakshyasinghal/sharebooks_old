package com.sharebooks.cache.entities;


import com.sharebooks.cache.abstractClasses.LRUCache;
import com.sharebooks.appEntities.entities.User;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import java.util.*;




//LRUUserCache will be a singleton class
public class LRUUserCache extends LRUCache {
	private static LRUUserCache lruUserCache;

	/*
	static {
		booksCache = new BooksCache();
	}
	*/

	private LRUUserCache() throws Exception{
		//nothing
	}


	public static LRUUserCache getLRUUserCache() throws Exception{
		try{
			//lazy instantiation
			if(lruUserCache == null){
				synchronized(LRUUserCache.class){
					if(lruUserCache == null){
						lruUserCache = new LRUUserCache();
					}
				}
			}

			return lruUserCache;
		}
		catch(Exception ex){
			System.out.println("Error in getLRUUserCache in LRUUserCache");
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
			System.out.println("Error in init in LRUUserCache");
			throw ex;
		}
	}



	public User get(int key) throws Exception{
		try{
			Object obj = super.get(key);

			return User(obj);
		}
		catch(Exception ex){
			System.out.println("Error in get method in LRUUserCache");
			throw ex;
		}
	}



	public void insert(int key , User user) throws Exception {
		try{
			super.insert(key , user);
		}
		catch(Exception ex){
			System.out.println("Error in insert method in LRUUserCache");
			throw ex;
		}
	}

}