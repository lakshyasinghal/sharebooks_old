package com.sharebooks.util;

import java.util.*;


public class StringHandler{


	public static boolean isSubSequence(String mainString , String subString){
		try{
			boolean isSubSequence = false;

			if(subString == null){
				return true;
			}

			char[] mainCharArray = mainString.toCharArray();
			char[] subCharArray = subString.toCharArray();

			int len1 = mainCharArray.length;
			int len2 = subCharArray.length;

			if(len2 == 0){
				return true;
			}

			int ptr1 = 0;
			int ptr2 = 0;

			while(ptr1 < len1){
				if(subCharArray[ptr2] == mainCharArray[ptr1]){
					if(++ptr2 == len2){
						isSubSequence = true;
						break;
					}
				}
				
				ptr1++;
			}

			return isSubSequence;
		}
		catch(Exception ex){
			System.out.println("\nException in isSubSequence in StringHandler\n");
			throw ex;
		}
	}




}