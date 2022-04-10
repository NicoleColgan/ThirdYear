package ct326;

import java.time.LocalDate;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
/**
 * A test class for the customer loyalty system for Assignment 2.
 * @author Nicole Colgan 19345826
 *
 */
public class Assignment2Test {
	
	
	/**
	 * Utility method for calling the makePurchase method for a customer and passing an appropriate
	 * implementation of the PointsCalculator interface.
	 * @param customer	the customer making the purchase
	 * @param amount	the amount of the purchase
	 * @throws NegativePurchaseAmountException	Negative purchase values are not allowed.
	 */
	public static void makePurchase(Customer customer, Money amount) throws NegativePurchaseAmountException {
		//switch statement for different tiers
		System.out.println("Entered the make purchase method of the test class");
		switch(customer.getTier()) {
		case PLATINUMTIER:
			//uses customers implementation of points calculator method
			System.out.println("Platinum tier case reached for customer "+customer.getName());
			customer.makePurchase(amount, customer); 
			break;
			
		case GOLDTIER:
			//anonymous function implementation
			System.out.println("Gold tier case reached for customer " +customer.getName());
			PointsCalculator calc = new PointsCalculator() {

				@Override
				public int calculatePoints(int pointsToAdd) {
					System.out.println("Gold tier implementation of pointsCalculator reached (anonymous function)");
					return pointsToAdd+(int)(pointsToAdd*(customer.getTier().bonus()));		//adds some bonus depending on customer tier
				}
				
			};
			customer.makePurchase(amount, calc);
			break;
			
		case SILVERTIER:
			//lamda function implementation
			System.out.println("silver tier case reached for customer "+customer.getName());
			PointsCalculator calc2 = (pointsToAdd) -> {
				System.out.println("Silver tier implementation of pointsCalculator reached (lamda function)");
				return pointsToAdd+(int)(pointsToAdd*(customer.getTier().bonus()));		//adds some bonus depending on customer tier
			};
			customer.makePurchase(amount, calc2);
			break;
			
		default:	//blue tier
			//uses customers implementation of points calculator method
			System.out.println("Blue tier case reached  for customer "+customer.getName());
			customer.makePurchase(amount, customer);
			break;
		}
	}

	public static void main(String[] args) {
		CurrencyUnit eur = CurrencyUnit.of("EUR");
		Customer c1,c2,c3,c4;
		int nextIndex = 0;
		
		
		
		Customer [] customers = new Customer[4];									//declare and initialise array for customers
		try {
			 Money money = Money.parse("EUR 2000.00");
			 
			c1 = new Customer("Peter", "O'Reilly", LocalDate.of(2004, 01, 03),Tier.GOLDTIER);		//create a new Silver customer and
			customers[nextIndex] = c1;
			//add to the array
			nextIndex++;
			
			
			Assignment2Test.makePurchase(c1, money);			//call to utility makePurchase method above
			System.out.println("Gold tier customer made a purchase\n");
			
		} catch (InvalidRegisterDateException e1) {
			System.out.println(e1.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}

		try {
			c2 = new Customer("Orla", "Finn", LocalDate.of(2021, 05, 21),Tier.BLUETIER);			//create a new Blue customer and
			customers[nextIndex] = c2;												//add to the array
			nextIndex++;
			Assignment2Test.makePurchase(c2, Money.of(eur, 2000d));					//call to utility makePurchase method above
			System.out.println("Blue tier customer made a purchase\n");
			
		} catch (InvalidRegisterDateException e) {
			System.out.println(e.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}

		try {
			c3 = new Customer("Vanessa", "Weathers", LocalDate.of(2014, 01, 01),Tier.SILVERTIER);	//create a new Gold customer and
			customers[nextIndex] = c3;												//add to the array
			nextIndex++;
			Assignment2Test.makePurchase(c3, Money.of(eur, 2000d));					//call to utility makePurchase method above
			System.out.println("Silver tier customer made a purchase\n");
			
		} catch (InvalidRegisterDateException e) {
			System.out.println(e.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}
		
		try {
			c4 = new Customer("Philip", "Grant", LocalDate.of(1999, 9, 16),Tier.PLATINUMTIER);		//create a new Platinium customer and
			customers[nextIndex] = c4;												//add to the array
			nextIndex++;
			Assignment2Test.makePurchase(c4, Money.of(eur, 2000d));					//call to utility makePurchase method above
			System.out.println("Platinum tier customer made a purchase\n");
			
		} catch (InvalidRegisterDateException e1) {
			System.out.println(e1.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < customers.length; i++) {									//loop through the customer array and check for 
																					//anniversaries before printing them out.
			if(customers[i] != null) {
				if(customers[i].getRegisterDate().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
				   customers[i].getRegisterDate().getMonthValue() == LocalDate.now().getMonthValue()) {
					customers[i].addPoints(100);					
				}
				System.out.println(customers[i]);
			}
	}
	}
}
