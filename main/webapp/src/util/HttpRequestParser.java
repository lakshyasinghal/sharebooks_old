package com.sharebooks.util;


import javax.servlet.http.*;



public class HttpRequestParser {

	

	public static int getInt(HttpServletRequest req , String param) throws Exception {
		try{
			String val = req.getParameter(param);
			if(val == null){
				return -1;
			}

			return Integer.parseInt(val);
		}
		catch(Exception ex){
			System.out.println("Error in getInt in ParamsParser");
			throw ex;
		}
	}


	public static String getString(HttpServletRequest req , String param) throws Exception {
		try{
			String val = req.getParameter(param);
			
			return val;
		}
		catch(Exception ex){
			System.out.println("Error in getInt in getString");
			throw ex;
		}
	}


	public static float getFloat(HttpServletRequest req , String param) throws Exception {
		try{
			String val = req.getParameter(param);
			if(val == null){
				return -1.00;
			}

			return Float.parseFloat(val);
		}
		catch(Exception ex){
			System.out.println("Error in getInt in getFloat");
			throw ex;
		}
	}


	public static double getDouble(HttpServletRequest req , String param) throws Exception {
		try{
			String val = req.getParameter(param);
			if(val == null){
				return -1.00;
			}

			return Double.parseDouble(val);
		}
		catch(Exception ex){
			System.out.println("Error in getInt in getDouble");
			throw ex;
		}
	}
}