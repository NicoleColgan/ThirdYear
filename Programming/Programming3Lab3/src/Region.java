import com.sun.net.httpserver.Authenticator.Result;

/**
 * Enum for different regions
 * Each enum has a name in a String format
 * @author nicole colgan 19345826
 *
 */
public enum Region {
	STATE("State"),
	BORDER("Border"),
	MIDLAND("Midland"),
	WEST("West"),
	DUBLIN("Dublin"),
	MIDEAST("Mid-East"),
	MIDWEST("Mid-West"),
	SOUTHEAST("South-East"),
	SOUTHWEST("South-West");
	
	private final String name;
	
	Region(String name) {
		this.name=name;
	}
	
	/**
	 * Allows for the aquisition of an enum based on its name
	 * @param enum print name
	 * @return the enum with this name
	 */
	public static Region getRegion(String name) {
		Region result=null;
		for(Region r: Region.values()) {
			if(r.toString().equalsIgnoreCase(name))
				result=r;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
