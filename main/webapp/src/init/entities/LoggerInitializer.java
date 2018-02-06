package com.sharebooks.init.entities;


import java.util.*;
import com.sharebooks.logging.entities.*;
import com.sharebooks.logging.interfaces.Logger;
import static com.sharebooks.staticClasses.Properties.*;
import com.sharebooks.exception.entities.*;


class LoggerInitializer {

	private Map<String , String> propertyMapper;



	public LoggerInitializer(Map<String , String> propertyMapper){
		this.propertyMapper = propertyMapper;
	}



	public void initLoggers() throws Exception {
		try{
			initBooksLogger();
			initFLowLogger();
			initDBLogger();
			initCacheLogger();
			initMailLogger();
			initThreadLogger();
			initTransactionLogger();
			initUserLogger();
		}
		catch(Exception ex){
			throw ex;
		}
	}


	private void initBooksLogger() throws Exception {
		try{
			Logger booksLogger = BooksLogger.booksLogger();
			String booksLoggerLogPath = propertyMapper.get(BOOKS_LOGGER_LOG_PATH);
			String booksLoggerLoggingOn = propertyMapper.get(BOOKS_LOGGER_LOGGING_ON);

			System.out.println("booksLoggerLogPath : " + booksLoggerLogPath);
			System.out.println("booksLoggerLoggingOn : " + booksLoggerLoggingOn);

			booksLogger.init(booksLoggerLogPath , booksLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initBooksLogger");
			throw ex;
		}
	}

	private void initFLowLogger() throws Exception {
		try{
			Logger flowLogger = FlowLogger.flowLogger();
			String flowLoggerLogPath = propertyMapper.get(FLOW_LOGGER_LOG_PATH);
			String flowLoggerLoggingOn = propertyMapper.get(FLOW_LOGGER_LOGGING_ON);

			flowLogger.init(flowLoggerLogPath , flowLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initFLowLogger");
			throw ex;
		}
	}

	private void initDBLogger() throws Exception {
		try{
			Logger dbLogger = DBLogger.dbLogger();
			String dbLoggerLogPath = propertyMapper.get(DB_LOGGER_LOG_PATH);
			String dbLoggerLoggingOn = propertyMapper.get(DB_LOGGER_LOGGING_ON);

			dbLogger.init(dbLoggerLogPath , dbLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initDBLogger");
			throw ex;
		}
	}

	private void initCacheLogger() throws Exception {
		try{
			Logger cacheLogger = CacheLogger.cacheLogger();
			String cacheLoggerLogPath = propertyMapper.get(CACHE_LOGGER_LOG_PATH);
			String cacheLoggerLoggingOn = propertyMapper.get(CACHE_LOGGER_LOGGING_ON);

			cacheLogger.init(cacheLoggerLogPath , cacheLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initCacheLogger");
			throw ex;
		}
	}

	private void initMailLogger() throws Exception {
		try{
			Logger mailLogger = MailLogger.mailLogger();
			String mailLoggerLogPath = propertyMapper.get(MAIL_LOGGER_LOG_PATH);
			String mailLoggerLoggingOn = propertyMapper.get(MAIL_LOGGER_LOGGING_ON);

			mailLogger.init(mailLoggerLogPath , mailLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initMailLogger");
			throw ex;
		}
	}

	private void initThreadLogger() throws Exception {
		try{
			Logger threadLogger = ThreadLogger.threadLogger();
			String threadLoggerLogPath = propertyMapper.get(THREAD_LOGGER_LOG_PATH);
			String threadLoggerLoggingOn = propertyMapper.get(THREAD_LOGGER_LOGGING_ON);

			threadLogger.init(threadLoggerLogPath , threadLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initThreadLogger");
			throw ex;
		}
	}

	private void initTransactionLogger() throws Exception {
		try{
			Logger transactionLogger = TransactionLogger.transactionLogger();
			String transactionLoggerLogPath = propertyMapper.get(TRANSACTION_LOGGER_LOG_PATH);
			String transactionLoggerLoggingOn = propertyMapper.get(TRANSACTION_LOGGER_LOGGING_ON);

			transactionLogger.init(transactionLoggerLogPath , transactionLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initTransactionLogger");
			throw ex;
		}
	}

	private void initUserLogger() throws Exception {
		try{
			Logger userLogger = UserLogger.userLogger();
			String userLoggerLogPath = propertyMapper.get(USER_LOGGER_LOG_PATH);
			String userLoggerLoggingOn = propertyMapper.get(USER_LOGGER_LOGGING_ON);

			userLogger.init(userLoggerLogPath , userLoggerLoggingOn);
		}
		catch(Exception ex){
			System.out.println("Error in initUserLogger");
			throw ex;
		}
	}


}