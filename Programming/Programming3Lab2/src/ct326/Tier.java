package ct326;
/**
 * 
 * Enum for customer tiers
 * @author Nicole Colgan 19345863
 *
 */
public enum Tier {
	PLATINUMTIER(50/100.0f, "Platinum tier"),		//each value has a name and a bonus assosiated with it
	GOLDTIER (30/100.0f,"Gold tier"),
	SILVERTIER (15/100.0f,"Silver tier"),
	BLUETIER (0f,"blue tier");
	
	
	private final float bonus;
	private final String printString;
	
	Tier(float bonus,String print){
		this.bonus=bonus;
		printString=print;
	}
	
	public float bonus() {
		return bonus;
	}
	@Override
	public String toString() {
		return printString;
	}
	
	
}
