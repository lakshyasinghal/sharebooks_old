package com.sharebooks.login.http;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.sharebooks.util.ObjectPair;




public class HttpRequestMaker implements RequestMaker {
	private final URL url;
	private final List<ObjectPair> parameters;
	private final String requestType;
	private OutputStreamWriter writer;
	private BufferedReader reader;
	private URLConnection conn;


	public HttpRequestMaker(String urlString , List<ObjectPair> parameters , String requestType){
		this.url = new URL(urlString);
		this.parameters = parameters;
		this.requestType = requestType;
	}


	public Response sendRequest() throws Exception {
		try{
			//URL url = new URL("http://www.java2s.com");
		    conn = url.openConnection();
		    conn.setDoOutput(true);
		    writer = new OutputStreamWriter(conn.getOutputStream());

		    if(requestType.equals("POST")){
			    //getting parameter string from objectPairs in the form of  value1=1&value2=2
			    String paramString = buildParamsString(parameters);

			    writer.write(paramString);
			}
		    writer.flush();

		    
		}
		catch(Exception ex){
			System.out.println("Error in sendRequest in HttpRequestMaker ");
			throw ex;
		}
		finally{
			writer.close();
		}
	}



	public String getResponse(){
		try{
			String line;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    while ((line = reader.readLine()) != null) {
		      System.out.println(line);
		    }

		}
		catch(Exception ex){
			System.out.println("Error in getResponse in HttpRequestMaker ");
			throw ex;
		}
		finally{
			reader.close();
		}
	}



	public String buildParamsString(List<ObjectPair> params) throws Exception {
		try{

		}
		catch(Exception ex){
			System.out.println("Error in buildParamsString in HttpRequestMaker ");
			throw ex;
		}
	}


	public void functionName(){
		URL url = new URL("http://www.java2s.com");
	    URLConnection conn = url.openConnection();
	    conn.setDoOutput(true);
	    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

	    writer.write("value=1&anotherValue=1");
	    writer.flush();
	    String line;
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    while ((line = reader.readLine()) != null) {
	      System.out.println(line);
	    }
	    writer.close();
	    reader.close();
	}

}