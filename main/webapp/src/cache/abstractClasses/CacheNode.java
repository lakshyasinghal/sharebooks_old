package com.sharebooks.cache.abstractClasses;




class CacheNode {
	private CacheNode prev;
	private int key;
	private Object value;
	private CacheNode next;


	public CacheNode(int key , Object value){
		this.key = key;
		this.value = value;
	}

	public Object getValue(){
		return value;
	}

	public int getKey(){
		return key;
	}

	public CacheNode getPrev(){
		return prev;
	}

	public CacheNode getNext(){
		return next;
	}



	//set methods

	public void setValue(Object val){
		value = val;
	}

	public void setPrev(CacheNode node){
		prev = node;
	}

	public void setNext(CacheNode node){
		next = node;
	}
}