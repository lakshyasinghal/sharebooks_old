package com.sharebooks.cache.abstractClasses;


import com.sharebooks.cache.interfaces.Cache;
import static com.sharebooks.staticClasses.ExceptionMessages.*;
import java.util.*;



//this class will only be accessible inside this package
class LRUCache implements Cache {

	private Map<Integer , CacheNode> cacheMap;
	private CacheList cacheList;
	private int size;
	private int capacity;



	protected LRUCache() throws Exception{
		try{
			cacheMap = new HashMap<Integer , CacheNode>();
			cacheList = new CacheList();
		}
		catch(Exception ex){
			System.out.println("Error in constructor in LRUCache");
			throw ex;
		}
	}


	public void init(int capacity) throws Exception {
		try{
			this.capacity = capacity;
		}
		catch(Exception ex){
			System.out.println("Error in init in LRUCache");
			throw ex;
		}
	}


	public List<Object> getAll() throws Exception {
		return null;
	}



	public Object get(int key) throws Exception{
		try{
			Object obj = null;
			CacheNode node = null;

			if(cacheMap == null){
				throw new Exception(CACHE_NOT_INITIALIZED);
			}

			if(cacheMap.containsKey(key)){
				node = cacheMap.get(key);
				obj = node.getValue();
				cacheList.moveToLast(node);
			}

			return obj;
		}
		catch(Exception ex){
			System.out.println("Error in get method in LRUCache");
			throw ex;
		}
	}



	public synchronized void insert(int key , Object value) throws Exception {
		try{
			if(cacheMap.containsKey(key)){
				return;
			}

			if(size == capacity){
				delete();
				size--;
			}

			CacheNode node = cacheList.insert(key , value);
			cacheMap.put(key , node);
			size++;
		}
		catch(Exception ex){
			System.out.println("Error in insert method in LRUCache");
			throw ex;
		}
	}



	public void delete() throws Exception {
		try{
			CacheNode startNode = cacheList.getStartNode();
			int key = startNode.getKey();

			cacheMap.remove(key);
			cacheList.remove(startNode);
		}
		catch(Exception ex){
			System.out.println("Error in delete method in LRUCache");
			throw ex;
		}
	}


	public int getCapacity(){
		return capacity;
	}


	public int getSize(){
		return size;
	}


}
