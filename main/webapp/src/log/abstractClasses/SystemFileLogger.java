package com.sharebooks.logger.abstractClasses;


import java.io.*;
import com.sharebooks.logger.interfaces.Logger;
import com.sharebooks.exception.entities.*;
import com.sharebooks.fileHandling.entities.FileValidator;



public abstract class SystemFileLogger implements Logger {

	private String filePath;
	private boolean debuggingOn = false;
	private FileWriter fileWriter;
	private BufferedWriter bW;


	protected SystemFileLogger(){

	}


	public void init(String filePath , String debuggingOn) throws IncorrectPathException , Exception {
		try{
			if(!FileValidator.validateFile(filePath)){
				throw new IncorrectPathException(filePath);
			}
			this.filePath = filePath;
			if(debuggingOn.equals("true")){
				this.debuggingOn = true;
			}
			
			prepareWriter();
		}
		catch(IncorrectPathException ex){
			throw ex;
		}
		catch(Exception ex){
			throw ex;
		}
	}


	private void prepareWriter() throws IOException {
		try{
			File file = new File(filePath);
			fileWriter = new FileWriter(file);
			bW = new BufferedWriter(fileWriter);
		}
		catch(IOException ex){

		}
	}



	public synchronized void log(String message) {
		try{
			try{
				bW.write(message);
				bW.write("\n\n");
			}
			catch(Exception ex){
				throw new LogException(filePath);
			}
		}
		catch(LogException ex){
			System.out.println(ex);
		}
	}


	public synchronized void error(String errMessage){
		try{
			try{
				bW.write("ERROR : ");
				bW.write(errMessage);
				bW.write("\n\n");
			}
			catch(Exception ex){
				throw new LogException(filePath);
			}
		}
		catch(LogException ex){
			System.out.println(ex);
		}
	}


	public synchronized void debug(String message) {
		try{
			try{
				if(!debuggingOn){
					return;
				}

				bW.write("DEBUGGING MODE : ");
				bW.write(message);
				bW.write("\n\n");
			}
			catch(Exception ex){
				throw new DebugException(filePath);
			}
		}
		catch(DebugException ex){
			System.out.println(ex);
		}
	} 

}