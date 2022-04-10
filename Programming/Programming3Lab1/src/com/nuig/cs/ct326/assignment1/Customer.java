package com.nuig.cs.ct326.assignment1;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.joda.money.Money;

/**
 * This class represents customers who are registered for a loyalty program. Customers can earn loyalty points 
 * based on the amount they spend. 
 * @author Nicole Colgan 19345826
 *
 */
public abstract class Customer {
	
	private String firstName;				//the first name of the customer
	private String lastName;				//the surname of the customer
	private int pointsEarned;				//the current points earned by the customer
	private LocalDate registerDate;
	//each customer has a unique incremental id
	private static int count; //all objects of this class have access to this variable
	private int id;
	
	
	/**
	 * Constructor for instantiating Customer objects
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public Customer(String firstName, String lastName,LocalDate regDate) throws InvalidRegisterDateException {
		//check if registration date is in the future
		if(regDate.isAfter(LocalDate.now())) {
			throw new InvalidRegisterDateException("\nName: "+firstName+" "+lastName+"\nRegistration date entered: "
					+ regDate+"\nAttempted to enter registration date in the future");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		registerDate = regDate;
		pointsEarned = 0;
		//assign new id
		id=count++;
	}
	
	public String getName() {
		return firstName+" "+lastName;
	}
	
	public int getId() {
		return id;
	}
	

	//accessor method for register date
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	
	/**
	 * A method for calculating the points due to the customer based on their spending and then adding this to
	 * their currently earned points.
	 * @param an int representing the amount of points to add for the customer before any tier bonus has been applied.
	 */
	public abstract void calculatePointsAndAdd(int pointsToAdd);
	
	/**
	 * A method for making a customer purchase and adding an appropriate set of points.
	 * @param purchaseAmount the amount spent by the customer for a purchase.
	 * @throws  NegativePurchaseAmountException
	 */
	public void makePurchase (Money purchaseAmount) throws NegativePurchaseAmountException{
		/*
		 * if amount is less than 0, exception should have a message with customer name
		 * , purchase amount, description of problem
		 */
		if(purchaseAmount.getAmountMajorInt()<0) {
			throw new NegativePurchaseAmountException("Name: "+firstName+" "+lastName+" \nPurchase ammount: "+ 
		purchaseAmount+"\nAttempted to make purchase with a negative ammount");
		}
		
		if((purchaseAmount.getAmountMajorInt())>=100) //convert money to an int to compare
			calculatePointsAndAdd(purchaseAmount.getAmountMajorInt()/100);
	}

	/**
	 * Get the current number of points earned by a customer.
	 * @return the current number of points earned by a customer.
	 */
	public int getPoints() {
		return pointsEarned;
	}
	
	/**
	 * Add points to the customers existing loyalty points
	 * @param pointsToAdd the points to add for the customer
	 */
	public void addPoints(int pointsToAdd) {
		pointsEarned += pointsToAdd;
	}
	
	

}
