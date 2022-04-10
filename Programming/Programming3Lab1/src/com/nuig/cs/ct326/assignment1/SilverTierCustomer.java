package com.nuig.cs.ct326.assignment1;

import java.time.LocalDate;

/**
 * This class represents a customer who is on the Silver tier of the loyalty program.
 * @author Nicole Colgan 19345826
 *
 */
public class SilverTierCustomer extends Customer {
	
	/**
	 * A constructor for creating Silver tier customers.
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public SilverTierCustomer(String firstName, String lastName,LocalDate registerDate) throws InvalidRegisterDateException{
		super(firstName, lastName, registerDate);
	}

	@Override
	public void calculatePointsAndAdd(int pointsToAdd) {
		super.addPoints(pointsToAdd + (int)(pointsToAdd*(15/100.0f)));
	}
	

}
