import java.time.LocalDate;
/**
 * class for award objects (Awards are not serializable)
 * @author nicole colgan 19345826
 *
 */
public class Award {
	private String awardName, awardOrganisation;
	private LocalDate dateOfAward;
	
	/**
	 * create a new award with a name, organisation and date
	 * @param awardName - name of award
	 * @param awardOrganisation - name of organisation of award
	 * @param dateOfAward - date award was given
	 */
	public Award(String awardName,String awardOrganisation,LocalDate dateOfAward) {
		this.setAwardName(awardName);
		this.setAwardOrganisation(awardOrganisation);
		this.setDateOfAward(dateOfAward);
	}
	/**
	 * get award name of award
	 * @return awardName
	 */
	public String getAwardName() {
		return awardName;
	}
	/**
	 * set award name
	 * @param awardName
	 */
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardOrganisation() {
		return awardOrganisation;
	}
	/**
	 * set organization name
	 * @param aawardOrganisation
	 */
	public void setAwardOrganisation(String awardOrganisation) {
		this.awardOrganisation = awardOrganisation;
	}

	public LocalDate getDateOfAward() {
		return dateOfAward;
	}
	/**
	 * set date of award.
	 * @param dateOfAward
	 */
	public void setDateOfAward(LocalDate dateOfAward) {
		this.dateOfAward = dateOfAward;
	}
}
