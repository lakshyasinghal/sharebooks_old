package com.sharecities.factory.mySQL;


import com.sharecities.factory.abstractClasses.GenericCityFactory;
import com.sharecities.staticClasses.ExceptionMessages;
import com.sharecities.exception.entities.*;
import com.sharecities.appEntities.entities.*;
import static com.sharecities.appEntities.enums.CityFields.*;
import static com.sharecities.staticClasses.Tables.*;



public class MySQLCityFactory extends GenericCityFactory {


	public CityFactory(){
		//nothing
	}


	public List<City> getAllCities() throws Exception {
		try{
			List<City> cities = new Arraylist<City>();
			//fieldTypes , fieldValues & fields will be null so that we can get all the entitys
			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(CITIES , null , null , null);

			cities = getCitiesFromResultSet(rs);

			return cities;
		}
		catch(Exception ex){
			System.out.println("Exception in getAllCities method in MySQLCityFactory class");
			throw ex;
		}
	}


	public City getCityById(int id) throws NoResultsFoundException , Exception {
		try{
			List<City> cities = new Arraylist<City>();

			List<String> fields = Arrays.asList("id");
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Fetcher fetcher = new Fetcher();
			ResultSet rs = fetcher.fetch(CITIES , fields , fieldTypes , fieldValues);

			cities = getCitiesFromResultSet(rs);

			if(cities.size() == 0){
				throw new NoResultsFoundException("City Id : " + id);
			}

			return cities.get(0);
		}
		catch(NoResultsFoundException ex){
			System.out.println("Exception in getCityById method in MySQLCityFactory class");
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Exception in getCityById method in MySQLCityFactory class");
			throw ex;
		}
	}



	public int addCity(City city) throws Exception {
		try{
			List<String> fields = Arrays.asList(ID , NAME , STATE_ID);
			List<String> fieldTypes = Arrays.asList("int" , "string" , "int");
			List<Object> fieldValues = Arrays.asList(city.id() , city.name() , city.stateId());

			Insertor insertor = new Insertor();
			int added = insertor.insert(CITIES , fields , fieldTypes , fieldValues);

			return added;
		}
		catch(Exception ex){
			System.out.println("Exception in addCity method in MySQLCityFactory class");
			throw ex;
		}
	}



	public int updateCity(City city) throws Exception {
		try{
			List<String> fields = Arrays.asList(NAME , STATE_ID);
			List<String> fieldTypes = Arrays.asList("string" , "int", "int");
			List<Object> fieldValues = Arrays.asList(city.name(), city.stateId(), city.id());

			Updator updator = new Updator();
			int updated = updator.update(CITIES ,  fields , fieldTypes , fieldValues);

			return updated;
		}
		catch(Exception ex){
			System.out.println("Exception in updateCity in MySQLCityFactory class");
			throw ex;
		}
	}

	public int deleteCity(int id) throws Exception {
		try{
			List<String> fields = Arrays.asList(ID);
			List<String> fieldTypes = Arrays.asList("int");
			List<Object> fieldValues = Arrays.asList(id);

			Deletor deletor = new Deletor();
			int deleted = deletor.delete(CITIES , fields , fieldTypes , fieldValues);

			return deleted;
		}
		catch(Exception ex){
			System.out.println("Exception in deleteCity in MySQLCityFactory class");
			throw ex;
		}
	}














	//getting cities from resultset 


	private City getCityFromResultSet(ResultSet rs) throws Exception{
		try{
			int id = rs.getInt(ID);
			String name = rs.getString(NAME);
			int stateId = rs.getInt(STATE_ID);

			City city = new City(id, name, stateId);

			return city;
		}
		catch(Exception ex){
			System.out.println("Error in getCityFromResultSet in MySQLCityFactory class");
			throw ex;
		}
	}


	public List<City> getCitiesFromResultSet(ResultSet rs) throws Exception {
		try{
			List<City> cities = new ArrayList<City>();

			while(rs.next()){
				cities.add(getCityFromResultSet(rs));
			} 

			return cities;
		}
		catch(Exception ex){
			System.out.println("Exception in getCitiesFromResultSet method in MySQLCityFactory class");
			throw ex;
		}
	}
	
}