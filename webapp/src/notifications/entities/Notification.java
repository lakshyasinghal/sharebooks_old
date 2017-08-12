package com.sharenotifications.notifications.entities;


import com.sharebooks.commonEntity.entities.Entity;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


//immutable class
public final class Notification extends Entity implements Comparable<Notification> {

	private static final long serialVersionUID = 1L;

	private int id;
	private int targetUserId;
	private int bookRequestId;
	private NotificationType notificationType;
	private Date creationTime = new Date();


	private static final String[] fields;
	private static final String[] fieldTypes;

	static {
		fields = new String[]{"id" , "targetUserId" , "bookRequestId" , "notificationType" , "creationTime"};
		fieldTypes = new String[]{"int" , "int" , "int" , "string" , "datetime"};
	}

	public Notification(){
		//do nothing 
	}


	public Notification(){
		//this();
		
	}



	public Notification(int id , int targetUserId , int bookRequestId , ){
		//this();
		//this.id = id;
		this.userId = userId;
		this.name = name;
		this.authorName = authorName;
		this.category = category;
		this.subcategory = subcategory;
		this.pages = pages;
		this.image = image;
		this.available = available;
		this.buy = buy;
		this.rent = rent;
		this.buyAmount = buyAmount;
		this.rentAmount = rentAmount;
	}



	//compareTo method to be used for sorting
	public int compareTo(Notification notification){
		return name.compareTo(notification.getName());
	}


	//overriding hashCode method
	public int hashCode(){
		return name.hashCode();
	}


	//overriding equals method
	public boolean equals(Object o){
		if(o == null){
			return false;
		}

		if(!(o instanceof Notification)){
			return false;
		}

		if(o == this){
			return true;
		}
		
		Notification notification = (Notification)o;
		return name.equals(notification.getName());
	}







	//getter methods

	public int getId(){
		return id;
	}

	public int getUserId(){
		return userId;
	}

	public String getName(){
		return name;
	}

	public String getAuthorName(){
		return authorName;
	}

	public String getCategory(){
		return category;
	}

	public String getSubcategory(){
		return subcategory;
	}

	public int getPages(){
		return pages;
	}

	public String getImage(){
		return image;
	}

	public int getAvailable(){
		return available;
	}

	public int getBuy(){
		return buy;
	}

	public int getRent(){
		return rent;
	}

	public double getBuyAmount(){
		return buyAmount;
	}

	public double getRentAmount(){
		return rentAmount;
	}




	//setter methods

	public void setId(int id){
		this.id = id;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public void setSubCategory(String subcategory){
		this.subcategory = subcategory;
	}

	public void setPages(int pages){
		this.pages = pages;
	}

	public void setImage(String image){
		this.image = image;
	}

	public void setAvailable(int available){
		this.available = available;
	}

	public void setBuy(int buy){
		this.buy = buy;
	}

	public void setRent(int rent){
		this.rent = rent;
	}

	public void setBuyAmount(double buyAmount){
		this.buyAmount = buyAmount;
	}

	public void setRentAmount(double rentAmount){
		this.rentAmount = rentAmount;
	}




	//static methods

	public String[] getFields(){
		return fields;
	}


	public String[] getFieldTypes(){
		return fieldTypes;
	}


	public Object[] getFieldValues(){

		Object[] fieldValues = {this.id , this.userId , this.name , this.authorName , this.category , this.subcategory , this.pages ,
								 this.image , this.available , this.buy , this.rent , this.buyAmount , this.rentAmount};

		return fieldValues;
	}



	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Name : " + this.name);
		stringBuilder.append(" ");
		stringBuilder.append("Author name : " + this.authorName);
		stringBuilder.append(" ");
		stringBuilder.append("Category : " + this.category);
		stringBuilder.append(" ");
		stringBuilder.append("Subcategory : " + this.subcategory);
		stringBuilder.append(" ");
		stringBuilder.append("Pages : " + this.pages);

		return stringBuilder.toString();
	}




	//method for getting a notification object from the result set
	public static Notification getNotificationFromResultSet(ResultSet rs) throws Exception {
		try{
			int id = rs.getInt("id");
			int userId = rs.getInt("userId");
			String name = rs.getString("name");
			String authorName = rs.getString("authorName");
			String category = rs.getString("category");
			String subcategory = rs.getString("subcategory");
			int pages = rs.getInt("pages");
			String image = rs.getString("image");
			int available = rs.getInt("available");
			int buy = rs.getInt("buy");
			int rent = rs.getInt("rent");
			double buyAmount = rs.getDouble("buyAmount");
			double rentAmount = rs.getDouble("rentAmount");

			Notification notification = new Notification(id , userId , name , authorName , category , subcategory , pages , image , available , buy , rent , buyAmount , rentAmount);

			return notification;
		}
		catch(Exception ex){
			System.out.println("Exception in getNotificationFromResultSet method in Notifications class");
			throw ex;
		}
	}


	//getting notification object from httpServletRequest object
	public static Notification getNotificationObjectFromRequest(HttpServletRequest req) throws Exception{
		try{
			String id = req.getParameter("id");
			String userId = req.getParameter("userId");
			String name = req.getParameter("name");
			String authorName = req.getParameter("authorName");
			String category = req.getParameter("category");
			String subcategory = req.getParameter("subcategory");
			String pages = req.getParameter("pages");
			String image = req.getParameter("image");
			System.out.println("image ------- " + image);
			String available = req.getParameter("available");
			String buy = req.getParameter("buy");
			String rent = req.getParameter("rent");
			String buyAmount = req.getParameter("buyAmount");
			
			String rentAmount = req.getParameter("rentAmount");
			

			//creating new notification object from values retrieved
			//id value will be passed as -1
			Notification notification = new Notification(Integer.parseInt(userId) , name , authorName , category , subcategory , Integer.parseInt(pages) , image
						, Integer.parseInt(available) , Integer.parseInt(buy) , Integer.parseInt(rent) , Double.parseDouble(buyAmount) ,
						Double.parseDouble(rentAmount));
			System.out.println("buyAmount ------- " + notification.getBuyAmount());
			System.out.println("rentAmount ------- " + notification.getRentAmount());

			return notification;
		}
		catch(Exception ex){
			System.out.println("Error in getNotificationObjectFromRequest in NotificationsHandler");
			throw ex;
		}
	}


	//getting string representation of notification for fields name , authorname , category , subcategory
	public String getNotificationString(){
		try{
			StringBuilder notificationString = new StringBuilder();

			notificationString.append(this.name);
			notificationString.append(this.authorName);
			notificationString.append(this.category);
			notificationString.append(this.subcategory);

			return notificationString.toString();
		}
		catch(Exception ex){
			System.out.println("Error in getNotificationString in Notifications");
			throw ex;
		}
	}


} 