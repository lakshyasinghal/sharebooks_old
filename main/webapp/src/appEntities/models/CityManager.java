package com.sharebooks.appEntities.models;

//import com.sharebooks.database.models.*;
import com.sharebooks.appEntities.entities.City;
import com.sharebooks.appEntities.entities.Entity;
import com.sharebooks.factory.interfaces.Factory;
import com.sharebooks.exception.entities.*;
import java.util.*;
import java.sql.*;
import static com.sharebooks.staticClasses.Tables.*;
import javax.servlet.http.*;


//singleton class
public class CityManager extends EntityManager {
	
	private GenericCityFactory cityFactory;

	private CityManager(){
		//nothing
	}



	public void init(GenericBookRequestFactory bookRequestFactory) throws Exception{
		try{
			if(this.bookRequestFactory == null){
				this.bookRequestFactory = bookRequestFactory;
			}
			else{
				throw new IllegalInitializationException();
			}
		}
		catch(IllegalInitializationException ex){
			System.out.println("init method in BookRequestManager");
			System.out.println(ex);
		}
		catch(Exception ex){
			System.out.println("Error in init method in BookRequestManager");
			throw ex;
		}
	}



	public static CityManager cityManager() throws Exception{
		try{
			return cityManager;
		}
		catch(Exception ex){
			System.out.println("Exception in cityManager method in CityManager class");
			throw ex;
		}
	}


	public void init() throws Exception{
		try{
			cities = fetchAllCities();
			System.out.println("Cities : \n" + cities.toString() + "\n\n");
		}
		catch(Exception ex){
			System.out.println("Exception in init method in CityManager class");
			throw ex;
		}
	}


	//this method will fetch all the books in the database
	public List<City> fetchAllCities() throws Exception{
		try{
			List<Entity> entities = fetchAllEntities(CITIES);
			
			List<City> cities = convertEntitiesToCities(entities);
			
			return cities;
		}
		catch(Exception ex){
			System.out.println("Exception in fetchAllCities method in CityManager class");
			throw ex;
		}
	}


	private List<City> convertEntitiesToCities(List<Entity> entities) throws Exception{
		try{
			List<City> cities = new ArrayList<City>();
			City city = null;
			int size = entities.size();

			for(int i=0 ; i<size ; i++){
				city = (City)entities.get(i);
				cities.add(city);
			}

			return cities;
		}
		catch(Exception ex){
			System.out.println("Exception in convertEntitiesToCities method in CityManager class");
			throw ex;
		}
	}

}