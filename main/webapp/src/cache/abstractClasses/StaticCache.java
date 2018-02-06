package com.sharebooks.cache.abstractClasses;


import com.sharebooks.cache.interfaces.Cache;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import com.sharebooks.util.TreeUtility;
import java.util.*;



//this class will only be accessible inside this package
class StaticCache implements Cache {

	private TreeSet<Object> set;


	protected StaticCache() {
		set = new TreeSet<Object>();
	}


	public void init(List<Object> list) throws Exception {
		try{
			int size = list.size();
			for(int i=0; i<size; i++){
				insert(0 , list.get(i));
			}
		}
		catch(Exception ex){
			System.out.println("Error in init in LRUCache");
			throw ex;
		}
	}


	public List<Object> getAll() throws Exception {
		try{
			List<Object>
		}
		catch(){

		}
	}



	public Object get(int key) throws Exception{
		try{
			
		}
		catch(Exception ex){
			System.out.println("Error in get method in LRUCache");
			throw ex;
		}
	}



	public void insert(int key , Object obj) throws Exception {
		try{
			set.add(obj);
		}
		catch(Exception ex){
			System.out.println("Error in insert method in StaticCache");
			throw ex;
		}
	}


}
