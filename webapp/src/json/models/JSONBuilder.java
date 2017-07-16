package com.sharebooks.json.models;


import com.sharebooks.json.interfaces.JSONBuilderInterface;
import com.sharebooks.commonEntity.entities.Entity;
import java.util.*;
//import static com.sharebooks.staticClasses.JSONResultTypes.*;



public class JSONBuilder implements JSONBuilderInterface {



	public JSONBuilder(){
		//do nothing
	}



	//this method will return string of type {"success" : true , "statusCode" : 5 , "results" : [{"name" : "lakshya"} , {"name" : "himanshu"}]}
	public String getJSON(boolean success , int statusCode , List<Entity> entities) throws Exception {
		try{
			StringBuilder json = new StringBuilder();

			json.append("{");

			json.append("\"" + "success" + "\"" + ":" + success);
			json.append(" , ");

			// json.append("\"" + "message" + "\"" + ":" + "\"" + message + "\"");
			// json.append(" , ");

			json.append("\"" + "statusCode" + "\"" + ":" + statusCode);
			json.append(" , ");

			json.append("\"" + "results" + "\"" + ":" + this.getJSONObjectsArray(entities));

			json.append("}");

			return json.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getJSON in JSONBuilder");
			throw ex;
		}
	}


	



	/*this method will return the string containing all the objects in an array format
		resultant string - [{"property1" : "" , "property2" : ""} , {"property1" : "" , "property2" : ""}] */
	public String getJSONObjectsArray(List<Entity> entities) throws Exception {
		try{
			StringBuilder jsonObjectsArray = new StringBuilder();
			int length = 0;
			if(entities != null){
				length = entities.size();
			}

			Entity entity = null;

			jsonObjectsArray.append("[");

			for(int i=0 ; i<length ; i++){
				entity = entities.get(i); 
				String jsonObject = getJsonObject(entity);
				jsonObjectsArray.append(jsonObject);
				if(i != length-1){
					jsonObjectsArray.append(",");
				}
			}

			jsonObjectsArray.append("]");

			return jsonObjectsArray.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getJSONObjectsArray in JSONBuilder");
			throw ex;
		}
	}



	public String getJsonObject(Entity e) throws Exception {

		try{
			StringBuilder jsonObj = new StringBuilder();

			String[] fields = e.getFields();
			String[] fieldTypes = e.getFieldTypes();
			Object[] fieldValues = e.getFieldValues();

			//System.out.println("Inside getJsonObject\n object --- " + e);

			//System.out.println();

			int length = fields.length;
			String fieldType = null;

			jsonObj.append("{");

			for(int i=0 ; i<length ; i++){
				fieldType = fieldTypes[i];

				//System.out.println("Field Type ---- " + fieldType);

				switch(fieldType){
					case "string" :
						jsonObj.append("\"" + fields[i] + "\"" + ":" + "\"" + (String)fieldValues[i] + "\"");
						//System.out.println(fields[i] + " , " + (String)fieldValues[i]);
						break;
					case "int" :
						jsonObj.append("\"" + fields[i] + "\"" + ":" + (Integer)fieldValues[i]);
						//System.out.println(fields[i] + " , " + (Integer)fieldValues[i]);
						break;
					case "double" :
						jsonObj.append("\"" + fields[i] + "\"" + ":" + (Double)fieldValues[i]);
						//System.out.println(fields[i] + " , " + (Double)fieldValues[i]);
						break;
					case "datetime" :
						jsonObj.append("\"" + fields[i] + "\"" + ":" + "\"" + ((java.util.Date)fieldValues[i]).toString() + "\"");
						//System.out.println(fields[i] + " , " + (java.util.Date)fieldValues[i]);
						break;
					default :
						break;
				}

				if(i != length-1){
					jsonObj.append(",");
				}
			}

			jsonObj.append("}");

			return jsonObj.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getJsonObject in JSONBuilder");
			throw ex;
		}
	}

	
}