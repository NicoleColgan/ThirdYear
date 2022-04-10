/**
 * users have an ID, name and email address
 * Can compare user objects based on the defined comparTo method
 * @author nicole Colgan 19345826
 *
 */
public class UserAccount implements Comparable<UserAccount>{
	private final long userID;
	private final String name, emailAddress;
	
	public UserAccount(long userID, String name, String emailAddress) {
		this.userID=userID;
		this.name=name;
		this.emailAddress=emailAddress;
	}

	public long getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	@Override
	public String toString() {
		return "ID: "+userID+" name: "+name+" email: "+emailAddress+"\n";
	}
	/**
	 * Comparable interface defines the natural order for this class
	 * Natural ordering is based on name
	 */
	@Override
	public int compareTo(UserAccount u) {
		//compare based on name 
		return name.compareTo(u.getName());
	}
	/**
	 * equality is true is the users ID is the same
	 */
	@Override
	public boolean equals(Object o) {
		//if this object was passed into its own method, its equal
		if(this == o)
			return true;
		//null check
		if(o == null)
			return false;
		//type check
		//getClass() returns the runtime class of the object
		if(getClass() != o.getClass())
			return false;	//must be the same type in order to cast
		
		//type cast
		UserAccount u = (UserAccount)o;
		//equality is based on user id
		return userID == u.getUserID();
	}
	/**
	 * hasCode() method for UserAccount provides a hash of the UserID
	 * This returns a value for an object of this class as an integer
	 * 
	 * Instances with the same hash are not necessarily equal but equal instances will
	 * always have the same hash code because we use the same algorithm for hashing
	 * 
	 * The hash code can greatly reduce the number of possible matches when searching for an
	 * object using something like a hashmap
	 * 
	 */
	public int hashCode() {
		/*
		 * If two objects are equal according to their equals method, then the hashCode
		 * method on each object should produce the same integer value
		 * A common algorithm is to start with some arbitrary number and repeatedly multiply it
		 * with some number (usually a small prime) before adding the fields to the hash
		 * 
		 * This might result in overflows but thats not really problematic because they cause
		 * no exception in java
		 */
		int prime = 32;
		int result = 1;
		result = prime + result* ((userID ==0L)? 0 : (Long.hashCode(userID)));
		return result;
	}
	
}
