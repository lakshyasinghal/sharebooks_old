package com.sharebooks.transactions.entities;


import com.sharebooks.common.interfaces.JsonObjectBuilder;


//immutable class
public final class Transaction implements JsonObjectBuilder {

	private int id;
	private int transactionType;
	private int active;
	private int userId;
	private int bookId;
	private Date startDate;
	private Date endDate;
	private double amount;


	public Transaction(){
		//no arg constructor
	}


	public Transaction(int id , int transactionType , int active , int userId , int bookId , Date startDate , Date endDate , double amount){
		this.id = id;
		this.transactionType = transactionType;
		this.active = active;
		this.userId = userId;
		this.bookId = bookId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
	}




	public int getId(){
		return id;
	}

	public int getTransactionType(){
		return transactionType;
	}

	public int getActive(){
		return active;
	}

	public int getUserId(){
		return userId;
	}

	public int getBookId(){
		return bookId;
	}

	public Date getStartDate(){
		return startDate;
	}

	public Date getEndDate(){
		return endDate;
	}

	public double getAmount(){
		return amount;
	}

	





	public String getJsonObject(Transaction transaction){
		StringBuilder jsonObj = new StringBuilder();

		try{
			jsonObj.append("{");
			jsonObj.append("id" + ":" + transaction.getId());
			jsonObj.append(",");
			jsonObj.append("transactionType" + ":" + transaction.getTransactionType());
			jsonObj.append(",");
			jsonObj.append("active" + ":" + transaction.getActive());
			jsonObj.append(",");
			jsonObj.append("userId" + ":" + transaction.getUserId());
			jsonObj.append(",");
			jsonObj.append("bookId" + ":" + transaction.getBookId());
			jsonObj.append(",");
			jsonObj.append("startDate" + ":" + "'" + transaction.getStartDate().toString() + "'");
			jsonObj.append(",");
			jsonObj.append("endDate" + ":" + "'" + transaction.getEndDate().toString() + "'");
			jsonObj.append(",");
			jsonObj.append("amount" + ":" + transaction.getEndAmount() + "'");
			jsonObj.append("}");

			return jsonObj.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getJsonObject in Transaction");
			throw ex;
		}
	}
} 