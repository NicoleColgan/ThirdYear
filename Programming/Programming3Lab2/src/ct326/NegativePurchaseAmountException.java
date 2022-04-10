package ct326;

/**
 * Purchases can only be made for positive amounts of money. This class represents an exception that 
 * should be thrown when a purchase is made for a negative amount. 
 * @author Nicole Colgan 19345826
 *
 */
public class NegativePurchaseAmountException extends Exception {
	/**
	 * Constructor for initialising a negative purchase amount exception.
	 * @param message A string representing the message to be associated with this exception.
	 */
	public NegativePurchaseAmountException(String message) {
		super(message);
	}

}
