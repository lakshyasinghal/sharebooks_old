package com.sharebooks.fileHandling.entities.propertiesHandling;



import java.io.*;
import java.util.*;
import static com.sharebooks.staticClasses.ExceptionMessages.*;



public class PropertyFileReader {
	

	public List<String> readFile(String filePath) throws Exception{
		FileReader fr = null;
		BufferedReader br = null;

		try{
			List<String> lines = new ArrayList<String>();
			String line = null;

			fr = new FileReader(new File(filePath));
			br = new BufferedReader(fr);

			while((line = br.readLine()) != null){
				//System.out.println(line);
				lines.add(line);
			}

			return lines;
		}
		catch(IOException ex){
			throw ex;
		}
		catch(Exception ex){
			System.out.println("Error in readFile in FileManager");
			throw ex;
		}
		finally{
			try{
				if(br != null){
					br.close();
				}
				if(fr != null){
					fr.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}	
}