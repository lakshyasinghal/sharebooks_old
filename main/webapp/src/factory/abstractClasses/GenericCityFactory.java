package com.sharebooks.factory.abstractClasses;


import com.sharebooks.factory.interfaces.*;
import com.sharebooks.staticClasses.ExceptionMessages;
import com.sharebooks.exception.entities.*;
import com.sharebooks.appEntities.entities.*;
import static com.sharebooks.appEntities.enums.CityFields.*;
import static com.sharebooks.staticClasses.Tables.*;
import static com.sharebooks.util.HttpRequestParser.*;



public abstract class GenericCityFactory implements Factory {


	public GenericCityFactory(){
		//nothing
	}


	public abstract List<City> getAllCities() throws Exception;

	public abstract City getCityById(int id) throws Exception;

	public abstract int addCity(City city) throws Exception;

	public abstract int updateCity(City city) throws Exception;

	public abstract int deleteCity(int id) throws Exception;



	//get city from http return request

	public City getCityFromHttpRequest(HttpServletRequest req) throws Exception{
		try{
			return null;	
		}
		catch(Exception ex){
			System.out.println("Error in getCityFromHttpRequest in HttpRequestFactory");
			throw ex;
		}
	}

	
	
}