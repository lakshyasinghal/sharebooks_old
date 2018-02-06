package com.sharebooks.factory.abstractClasses;


import com.sharebooks.factory.interfaces.*;
import com.sharebooks.staticClasses.ExceptionMessages;
import com.sharebooks.exception.entities.*;
import com.sharebooks.appEntities.entities.*;
import static com.sharebooks.appEntities.enums.StateFields.*;
import static com.sharebooks.staticClasses.Tables.*;
import static com.sharebooks.util.HttpRequestParser.*;



public abstract class GenericStateFactory implements Factory {


	public GenericStateFactory(){
		//nothing
	}


	public abstract List<State> getAllStates() throws Exception;

	public abstract State getStateById(int id) throws Exception;

	public abstract int addState(State state) throws Exception;

	public abstract int updateState(State state) throws Exception;

	public abstract int deleteState(int id) throws Exception;



	//get state from http return request

	public State getStateFromHttpRequest(HttpServletRequest req) throws Exception{
		try{
			return null;	
		}
		catch(Exception ex){
			System.out.println("Error in getStateFromHttpRequest in HttpRequestFactory");
			throw ex;
		}
	}

	
	
}