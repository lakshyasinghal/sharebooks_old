package com.sharebooks.util;



public class ObjectPair {

	private Object object1;
	private Object object2;

	public ObjectPair(){
		//empty
	}

	public ObjectPair(Object obj1 , Object obj2){
		object1 = obj1;
		object2 = obj2;
	}

	public Object getObject1(){
		return object1;
	}

	public Object getObject2(){
		return object2;
	}

	public void setObject1(Object obj){
		object1 = obj;
	}

	public void setObject2(Object obj){
		object2 = obj;
	}
}