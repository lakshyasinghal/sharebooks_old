package com.sharebook.cache.entities;


import com.sharebooks.cache.abstractClasses.StaticCache;
import com.sharebook.appEntities.entities.City;
import static com.sharebook.staticClasses.ExceptionMessages.*;
import java.util.*;



//StaticCityCache will be a singleton class
public abstract class StaticCityCache extends LRUCache {
	
	private static StaticCityCache staticCityCache;

	/*
	static {
		staticCityCache = new StaticCityCache();
	}
	*/

	private StaticCityCache() throws Exception{
		//nothing
	}


	public static StaticCityCache getStaticCityCache() throws Exception{
		try{
			//lazy instantiation
			if(staticCityCache == null){
				synchronized(StaticCityCache.class){
					if(staticCityCache == null){
						staticCityCache = new StaticCityCache();
					}
				}
			}

			return staticCityCache;
		}
		catch(Exception ex){
			System.out.println("Error in getStaticCityCache in StaticCityCache");
			throw ex;
		}
	}


	public void init(List<City> cities) throws Exception{
		try{
			super.init(cities);
		}
		catch(Exception ex){
			System.out.println("Error in init in StaticCityCache");
			throw ex;
		}
	}



	public Book get(int key) throws Exception{
		try{
			Object obj = super.get(key);

			return Book(obj);
		}
		catch(Exception ex){
			System.out.println("Error in get method in StaticCityCache");
			throw ex;
		}
	}




	public void insert(int key , Book book) throws Exception {
		try{
			super.insert(key , book);
		}
		catch(Exception ex){
			System.out.println("Error in insert method in StaticCityCache");
			throw ex;
		}
	}

}