package com.sharebooks.appEntities.models;


//import com.sharebooks.database.models.*;
import javax.servlet.http.*;
import com.sharebooks.appEntities.entities.State;
import com.sharebooks.appEntities.entities.Entity;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.exception.entities.*;
import static com.sharebooks.staticClasses.Tables.*;
import java.util.*;
import java.sql.*;




//singleton class
public class StateManager extends EntityManager {
	
	private static StateManager stateManager = new StateManager(); 
	private List<State> states = new ArrayList<State>(); 

	private StateManager(){
		//nothing
	}


	public void init(Factory factory) throws Exception{
		try{
			super.init(factory);
		}
		catch(IllegalInitializationException ex){
			System.out.println("init method in StateManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in init method in StateManager");
			throw ex;
		}
	}



	public static StateManager stateManager() throws Exception{
		try{
			return stateManager;
		}
		catch(Exception ex){
			System.out.println("Exception in stateManager method in StateManager class");
			throw ex;
		}
	}


	public void init() throws Exception{
		try{
			states = fetchAllStates();
			System.out.println("States : \n" + states.toString() + "\n\n");
		}
		catch(Exception ex){
			System.out.println("Exception in init method in StateManager class");
			throw ex;
		}
	}


	//this method will fetch all the books in the database
	public List<State> fetchAllStates() throws Exception{
		try{
			List<Entity> entities = fetchAllEntities(STATES);
			
			List<State> states = convertEntitiesToStates(entities);
			
			return states;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllStates method in StateManager class");
			throw ex;
		}
	}


	private List<State> convertEntitiesToStates(List<Entity> entities) throws Exception{
		try{
			List<State> states = new ArrayList<State>();
			State state = null;
			int size = entities.size();

			for(int i=0 ; i<size ; i++){
				state = (State)entities.get(i);
				states.add(state);
			}

			return states;
		}
		catch(Exception ex){
			System.out.println("Exception in convertEntitiesToStates method in StateManager class");
			throw ex;
		}
	}

}