package ct326;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * This class represents customers who are registered for a loyalty program. Customers can earn loyalty points 
 * based on the amount they spend. 
 * @author Nicole Colgan 19345826
 *
 */
public class Customer implements PointsCalculator{ //must provide implementation of calculate points
	
	private String firstName;				//the first name of the customer
	private String lastName;				//the surname of the customer
	private LocalDate registerDate;			//the date the customer registered for the loyalty program
	private int customerID;					//unique ID of the customer
	private int pointsEarned;				//the current points earned by the customer
	private Tier tier;						//each customer has a tier

	
	private static int nextCustomerID = 0;	//a static int to hold the ID for the next customer to register
	
	/**
	 * Constructor for instantiating Customer objects
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 * @param registerDate the LocalDate on which the customer registered for the loyalty program. Cannot be in the future.
	 * @throws InvalidRegisterDateException Thrown if the registration date is in the future.
	 */
	public Customer(String firstName, String lastName, LocalDate registerDate, Tier tier) throws InvalidRegisterDateException {
		this.firstName = firstName;
		this.lastName = lastName;
		pointsEarned = 0;
		this.tier=tier;
		if(registerDate.isAfter(LocalDate.now())) throw new InvalidRegisterDateException("The value " + registerDate + " was entered for " + firstName + 
				" " + lastName + ". The join date cannot be in the future.");
		this.registerDate = registerDate;
		customerID = nextCustomerID;
		nextCustomerID++;
	}
	public String getName() {
		return this.firstName+" "+this.lastName;
	}
	public Tier getTier() {
		return tier;
	}
	
	/**
	 * A method for making a customer purchase and adding an appropriate set of points.
	 * @param purchaseAmount the amount spent by the customer for a purchase.
	 * @param calc a PointsCalculator to determine how bonus points are to be applied to the customer.
	 * @throws NegativePurchaseAmountException if the value of the purchase is less than zero.
	 */
	public void makePurchase (Money purchaseAmount, PointsCalculator calc) throws NegativePurchaseAmountException {
		CurrencyUnit eur = CurrencyUnit.of("EUR");
		if(purchaseAmount.isLessThan(Money.of(eur, 0.0d))) throw new NegativePurchaseAmountException("The customer " + firstName + " " + lastName 
				+ " attempted to make a purchase for " + purchaseAmount + ". Cannot make a purchase for a negative amount.");
		if(purchaseAmount.getAmount().doubleValue() >=100) {
			
			//add an appropriate number of points to the customers points by using points calculator implementation
			int pointsToAdd =purchaseAmount.getAmountMajorInt()/100;
			System.out.println("Points added: "+pointsToAdd);
			
			int pointsAfterTierBonus=calc.calculatePoints(pointsToAdd);  //calls whatever implementation of points calculator we have passed in
			System.out.println("Points added after bonus is applied: "+pointsAfterTierBonus);
			
			//add points to previously earned points
			pointsEarned+=pointsAfterTierBonus;
			
		}
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
	
	/**
	 * Get the date that the customer was registered for the loyalty program on.
	 * @return an LocalDate indicating the date that the customer registered on.
	 */
	public LocalDate getRegisterDate() {
		return registerDate;
	}

	@Override
	public int calculatePoints(int somePoints) {
		System.out.println("reached customer class calculatePoints implementation (class implementing PointsCalculator)");
		return somePoints+(int)(somePoints*(this.tier.bonus()));  //adds some bonus depending on customer tier
	}
	
	@Override
	public String toString() {
		//using string formatter to represent customers in a more user friendly way
		//this.getTier() automatically calls the Tier enums toString()
		return String.format("%s: %s %s (%d %s %d)%nLoyalty points balance: %d", this.getTier(),this.firstName,this.lastName,
				this.registerDate.getDayOfMonth(),this.registerDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US), this.registerDate.getYear(), this.pointsEarned);
	}

}
