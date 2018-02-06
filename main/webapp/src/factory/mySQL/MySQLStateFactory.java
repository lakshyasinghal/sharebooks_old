package com.sharestates.factory.mySQL;


import com.sharestates.factory.abstractClasses.GenericStateFactory;
import com.sharestates.staticClasses.ExceptionMessages;
import com.sharestates.exception.entities.*;
import com.sharestates.appEntities.entities.*;
import static com.sharestates.appEntities.enums.StateFields.*;
import static com.sharestates.staticClasses.Tables.*;



public class MySQLStateFactory extends GenericStateFactory {


	public StateFactory(){
		//nothing
	}


	public List<State> getAllStates() throws Exception {
		try{
			List<State> states = new Arraylist<State>();
			//fieldTypes , fieldValues & fields will be null so that we can get all the entitys
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(STATES , null , null , null);

			states = getStatesFromResultSet(rs);

			return states;
		}
		catch(Exception ex){
			System.out.println("Exception in getAllStates method in MySQLStateFactory class");
			throw ex;
		}
	}


	public State getStateById(int id) throws NoResultsFoundException , Exception {
		try{
			List<State> states = new Arraylist<State>();

			List<String> fields = Arrays.asList("id");
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(STATES , fields , fieldTypes , fieldValues);

			states = getStatesFromResultSet(rs);

			if(states.size() == 0){
				throw new NoResultsFoundException("State Id : " + id);
			}

			return states.get(0);
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in getStateById method in MySQLStateFactory class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in getStateById method in MySQLStateFactory class");
			throw ex;
		}
	}



	public int addState(State state) throws Exception {
		try{
			List<String> fields = Arrays.asList(ID , NAME);
			List<String> fieldTypes = Arrays.asList("int" , "string");
			List<Object> fieldValues = Arrays.asList(state.id() , state.name());

			Insertor insertor = new Insertor();
			int added = insertor.insert(STATES , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addState method in MySQLStateFactory class");
			throw ex;
		}
	}



	public int updateState(State state) throws Exception {
		try{
			List<String> fields = Arrays.asList(NAME);
			List<String> fieldTypes = Arrays.asList("string", "int");
			List<Object> fieldValues = Arrays.asList(state.name(), state.stateId(), state.id());

			Updator updator = new Updator();
			int updated = updator.update(STATES ,  fields , fieldTypes , fieldValues);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateState in MySQLStateFactory class");
			throw ex;
		}
	}

	public int deleteState(int id) throws Exception {
		try{
			List<String> fields = Arrays.asList(ID);
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Deletor deletor = new Deletor();
			int deleted = deletor.delete(STATES , fields , fieldTypes , fieldValues);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in deleteState in MySQLStateFactory class");
			throw ex;
		}
	}














	//getting states from resultset 


	private State getStateFromResultSet(ResultSet rs) throws Exception{
		try{
			int id = rs.getInt(ID);
			String name = rs.getString(NAME);

			State state = new State(id, name);

			return state;
		}
		catch(Exception ex){
			System.out.println("Error in getStateFromResultSet in MySQLStateFactory class");
			throw ex;
		}
	}


	public List<State> getStatesFromResultSet(ResultSet rs) throws Exception {
		try{
			List<State> states = new ArrayList<State>();

			while(rs.next()){
				states.add(getStateFromResultSet(rs));
			} 

			return states;
		}
		catch(Exception ex){
			System.out.println("Exception in getStatesFromResultSet method in MySQLStateFactory class");
			throw ex;
		}
	}
	
}