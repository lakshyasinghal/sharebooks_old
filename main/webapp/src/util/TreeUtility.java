package com.sharebooks.util;

import java.util.*;



public class TreeUtility {


	public static List<Object> getArrayListFromTreeSet(TreeSet treeSet) throws Exception{
		try{
			List<Object> list = new ArrayList<Object>();
			Object obj = null;

			Iterator<Object> itr = treeSet.iterator(); 
			while (itr.hasNext()){
				list.add(itr.next()); 
			}

			return list;
		}
		catch(Exception ex){
			System.out.println("Error in getListFromTreeSet in TreeUtility");
			throw ex;
		}
	}
}