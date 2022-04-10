import java.awt.List;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * a class that represents celebrity objects. 
 * This class is serializable but it handles its awards in a custom way
 * @author nicole colgan 19345826
 *
 */
public class Celebrity implements Serializable{
	private int id;
	private String profession;
	private LocalDate dateOfBirth;
	private transient ArrayList<Award> awards; //awards are not serializable
	
	public Celebrity(int id, String profession, LocalDate dateOfBirth) {
		this.setId(id);
		this.setProfession(profession);
		this.setDateOfBirth(dateOfBirth);
		awards=new ArrayList<Award>();
	}
	
	//default Constructor for reconstructing celebritys
	public Celebrity() {
		awards=new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public ArrayList<Award> getAwards() {
		return awards;
	}
	/**
	 * Method used for formatting awards in the toString
	 * @return string representation of awards
	 */
	public String printAwards() {
		String returnString=" Awards- ";
		if(awards != null) {
			for(int i=0; i< awards.size(); i++) {
				returnString+=awards.get(i).getAwardName()+" ("+awards.get(i).getAwardOrganisation()+
						" "+awards.get(i).getDateOfAward().toString()+") ";
			}
		}
		return returnString;
	}
	/**
	 * Method for adding a new award to the celebrity's awards list
	 * @param award
	 */
	public void setAwards(Award award) {
		awards.add(award);
	}
	
	@Override
	public String toString() {
		return id+" "+profession+" "+dateOfBirth + " "+ printAwards();
	}
	/**
	 * Serialize the celeb aswel as manually writing awards because awards is not serializable.
	 * List of awards is written in text format to a CSV file called awards.csv using a file writer which is initialised in the test class
	 * Each award is written in a separate line in CSV file and each line contains attributes of 
	 * awards separated by a coma aswel as the id of the celebrity they belong to
	 * @param out - the output stream used to write the object
	 * @param fileWriter - used to write the awards to the CSV file
	 * @throws IOException
	 */
	public void writeObject(ObjectOutputStream out, FileWriter fileWriter) throws IOException{
		try {
			//write out attributes so we know in which order to get them back when de-serializing
			out.writeInt(id);	//write int
			out.writeUTF(profession);	//write string
			out.writeUTF(dateOfBirth.toString());
			out.flush();	//flushes the stream by writing any remaining info
			//System.out.println("celebrity "+this.id+"sucessfully y serialised");
			
			//use fileWriter to append awards to the csv file
			//if we didn't pass in a file writer, the awards would be overwritten every time a new celebrity was serialized
			for(int i=0; i<getAwards().size(); i++) {
				fileWriter.append(String.valueOf(getId()));
				fileWriter.append(",");	//seperate values with the "," delimiter
				fileWriter.append(getAwards().get(i).getAwardName());
				fileWriter.append(",");
				fileWriter.append(getAwards().get(i).getAwardOrganisation());
				fileWriter.append(",");
				//Change date format to match what a csv file expects
				DateTimeFormatter format= DateTimeFormatter.ofPattern("d/MM/uuuu");
				String date = getAwards().get(i).getDateOfAward().format(format);
				//System.out.println(date);
				fileWriter.append(date);
				fileWriter.append("\n");	//end the line with a "\n" end of line character
				//System.out.println("Celebrity award "+getAwards().get(i)+" successfully serialized");
			}
			
			}catch(IOException e) {	//catch exception
				e.printStackTrace();
		}
	} //end WriteObject
	
	/**
	 * De-Serialize the celebrity object
	 * Carry out reading of object attributes followed by reading of awards because awards are not serializable
	 * Method has to be static so we can use the customer class to call it in the test class rather than a customer object (as the customer object does not exist before de-serialization)
	 * @param in - stream from which to read
	 * @throws IOException
	 */
	public static Celebrity readObject(ObjectInputStream in)throws IOException{
		Celebrity c=new Celebrity();	
		try {
			//we know the order of the celebrity attributes because I wrote them in a particular order
			c.setId(in.readInt());
			c.setProfession(in.readUTF());
			c.setDateOfBirth(LocalDate.parse(in.readUTF()));
			//c.setAwards(new Award(",",",",LocalDate.of(2017,1,13)));
			//System.out.println(c);
			
			//add the awards back from  csv file
			BufferedReader in2 = new BufferedReader(new FileReader("awards.csv"));
			String line=null;
			line=in2.readLine();	//skip the header information
			Scanner lineReader=null;
			
			while((line=in2.readLine()) != null) {	//while there is more lines, read them
				StringReader sr = new StringReader(line);
				lineReader = new Scanner(sr);
				lineReader.useDelimiter(",");
				int id=lineReader.nextInt();
				//System.out.println("id: "+id);
				
				if(id==c.getId()) {	//if the id, matches this celebrity's id, add this award to their awards list.
					String name=lineReader.next();
					String org = lineReader.next();
					String date = lineReader.next();
					//change date format back
					DateTimeFormatter format=DateTimeFormatter.ofPattern("d/MM/yyyy");	//to string writes date as DD/MM/YYYY but LocalDate parses from a string in the format DD-MM-YYYY
					//System.out.println(id+" "+name+" "+org+" "+LocalDate.parse(date));
					c.setAwards(new Award(name,org,LocalDate.parse(date,format)));
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return c;	//return this customer
	} //end readObject
}
