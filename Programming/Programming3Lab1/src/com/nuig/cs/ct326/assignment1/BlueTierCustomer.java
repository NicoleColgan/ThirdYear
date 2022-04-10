package com.nuig.cs.ct326.assignment1;

import java.time.LocalDate;

/**
 * This class represents a customer who is on the Blue tier of the loyalty program.
 * @author Nicole Colgan 19345826
 *
 */
public final class BlueTierCustomer extends Customer {
	
	/**
	 * A constructor for creating Blue tier customers.
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public BlueTierCustomer(String firstName, String lastName,LocalDate regDate) throws InvalidRegisterDateException{
		super(firstName, lastName, regDate);
	}

	@Override
	public void calculatePointsAndAdd(int pointsToAdd) {
		super.addPoints(pointsToAdd);
	}
	

}
