package com.sharebooks.util;

import java.util.*;
import java.time.LocalDate;



public class StringParser {


	public static int getAgeFromBirthday(String DOB) throws Exception{
		try{
			return 25;

			// int date = getDateFromDOB(DOB);
			// int month = getMonthFromDOB(DOB);
			// int year = getYearFromDOB(DOB);

			// LocalDate birthdate = new LocalDate(1970, 1, 20);
			// LocalDate now = new LocalDate();
			// Years age = Years.yearsBetween(birthdate, now);

			
		}
		catch(Exception ex){
			System.out.println("Error in getAgeFromBirthday in StringParser");
			throw ex;
		}
	}



	

	public static int getDateFromDOB(String birthDate) throws Exception{
		try{
			char[] chars = birthDate.toCharArray();
			char c = ' ';

			int size = chars.length;
			int[] dateArray = new int[2];
			int dateArrayIndex = 0;
			int slashIndex = -1;


			int i=0;

			while(i< size){
				c = chars[i];
				if(slashIndex > 0)
					break;
				if(slashIndex == -1 && c != '/'){
					dateArray[dateArrayIndex] = (int)c - 48;
					dateArrayIndex++;
				}
				if(c == '/'){
					slashIndex++;
				}
				i++;
			}

			int date = getNumberFromArray(dateArray);

			return date;
		}
		catch(Exception ex){
			System.out.println("Error in getDateFromDOB in StringParser");
			throw ex;
		}
	}


	public static int getMonthFromDOB(String birthDate) throws Exception{
		try{
			char[] chars = birthDate.toCharArray();
			char c = ' ';

			int size = chars.length;
			int[] monthArray = new int[2];
			int monthArrayIndex = 0;
			int slashIndex = -1;


			int i=0;

			while(i< size){
				c = chars[i];
				if(slashIndex > 0)
					break;
				if(slashIndex == 0 && c != '/'){
					monthArray[monthArrayIndex] = (int)c - 48;
					monthArrayIndex++;
				}
				if(c == '/'){
					slashIndex++;
				}
				i++;
			}

			int month = getNumberFromArray(monthArray);

			return month;
		}
		catch(Exception ex){
			System.out.println("Error in getMonthFromDOB in StringParser");
			throw ex;
		}
		
	}


	public static int getYearFromDOB(String birthDate) throws Exception{
		try{
			char[] chars = birthDate.toCharArray();
			char c = ' ';

			int size = chars.length;
			int[] yearArray = new int[2];
			int yearArrayIndex = 0;
			int slashIndex = -1;


			int i=0;

			while(i< size){
				c = chars[i];
				if(slashIndex > 0)
					break;
				if(slashIndex == 1 && c != '/'){
					yearArray[yearArrayIndex] = (int)c - 48;
					yearArrayIndex++;
				}
				if(c == '/'){
					slashIndex++;
				}
				i++;
			}

			int year = getNumberFromArray(yearArray);

			return year;
		}
		catch(Exception ex){
			System.out.println("Error in getYearFromDOB in StringParser");
			throw ex;
		}
	}




	public static int getNumberFromArray(int[] numArray) throws Exception{
		try{
			int length = numArray.length;
			int num = 0;
			int currentInt = 0;

			int i = 0;

			while(i < length){
				currentInt = numArray[i];
				num += Math.pow(currentInt , length - 1 - i);
				i++;
			}

			return num;
		}
		catch(Exception ex){
			System.out.println("Error in getNumberFromArray in StringParser");
			throw ex;
		}
	}

}