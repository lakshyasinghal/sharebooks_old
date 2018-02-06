package com.sharebooks.appEntities.entities;


import java.io.Serializable;


public abstract class Entity implements Serializable {

	public Entity(){
		//no arg constructor
	}


	public abstract String[] getFields();
	public abstract String[] getFieldTypes();
	public abstract Object[] getFieldValues();

}