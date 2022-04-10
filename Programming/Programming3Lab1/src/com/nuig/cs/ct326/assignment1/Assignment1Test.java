package com.nuig.cs.ct326.assignment1;

import org.joda.money.Money;
import java.time.*;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * A test class for the customer loyalty system.
 * @author Nicole Colgan 19345826
 *
 */
public class Assignment1Test {

	public static void main(String[] args) {
		Customer c1,c2,c3,c4;
		
		/*
		 * Use arraylist for customers instead of array because some customers may not be instantiated if they enter
		 * an invalid registration date. In that case, the array size would be smaller than stated.
		 */
		ArrayList<Customer> customers= new ArrayList<Customer>();
		
		//customer 1
		LocalDate date = LocalDate.of(2021, 1, 21);
		Money money = Money.parse("EUR -160.00");
		try {
			/*
			 * customer will only be created if registration date is correct, otherwise InvalidRegisterDateException
			 * is thrown and caught
			 * 
			 * if customer tries to purchase something with a negative ammount, purchase will fail and
			 * NegativePurchaseAmountException is thrown and caught
			 */
			c1 = new SilverTierCustomer("Peter", "O'Reilly",date);
			
			customers.add(c1); //add cutomer to array list if details are correct
			
			c1.makePurchase(money); //this negative amount should generate a new exception
		}
		catch(InvalidRegisterDateException ex) { //exceptions are caught and displayed
			ex.printStackTrace();
		}
		catch(NegativePurchaseAmountException ex) {
			ex.printStackTrace();
		}
		
		//customer 2
		date = LocalDate.of(2022, 2, 26); //this date should generate a new exception
		try {
			c2 = new BlueTierCustomer("Orla", "Finn",date);
			money = Money.parse("EUR 1160.00");
			customers.add(c2);
			
			c2.makePurchase(money); //should not generate exception
		}
		catch(InvalidRegisterDateException ex) { 
			ex.printStackTrace();
		}
		catch(NegativePurchaseAmountException ex) {
			ex.printStackTrace();
		}

		//customer 3
		date = LocalDate.of(2021, 3, 21);
		try {
			c3 = new GoldTierCustomer("Vanessa", "Weathers",date);
			customers.add(c3);
			
			money = Money.parse("EUR 160.00");
			c3.makePurchase(money); //should not generate exception
		}
		catch(InvalidRegisterDateException ex) { 
			ex.printStackTrace();
		}
		catch(NegativePurchaseAmountException ex) {
			ex.printStackTrace();
		}

		//customer 4
		date = LocalDate.now();
		try {
			c4 = new PlatinumTierCustomer("Philip", "Grant",date);
			customers.add(c4);
			
			money = Money.parse("EUR 5000.00");
			c4.makePurchase(money); //should not generate exception
		}
		catch(InvalidRegisterDateException ex) { 
			ex.printStackTrace();
		}
		catch(NegativePurchaseAmountException ex) {
			ex.printStackTrace();
		}
		
		
		//check if current date is an anniversary of register date i.e. if month and day are the same but not year
		//test by printing our customer name 
		for(Customer c: customers) {
			//System.out.println(c.getName()+" id: "+c.getId());  //<- test static id 
			if((c.getRegisterDate().getMonthValue()==LocalDate.now().getMonthValue())
					&& (c.getRegisterDate().getDayOfMonth()==LocalDate.now().getDayOfMonth())) {
				c.addPoints(100);
				System.out.println("Customer "+c.getName()+" has a registration aniversary today"); //philip is printed out
			}
		}
	}
}
