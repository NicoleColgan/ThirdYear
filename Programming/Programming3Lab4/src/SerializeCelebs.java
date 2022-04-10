import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a list of celebritys and awards then serializes celebritys and writes awards to CSV file using Celebrity write award method
 * @author nicole
 *
 */
public class SerializeCelebs {

	private static ObjectOutputStream out=null; //writes to an output stream
	private static Scanner input;
	private static ArrayList<Celebrity> celebs;
	
	public static void main(String[] args) throws IOException {
		
		//scan in a file name from the user
		System.out.println("Enter a file name to store your customer objects");
		input=new Scanner(System.in);
		String fileName=input.next();
		
		//create awards
		Award a1 = new Award("best actor","disney",LocalDate.of(2017,1,13));
		Award a2 = new Award("best director","pixar",LocalDate.of(2019,4,13));
		Award a3 = new Award("best stuntable","disney",LocalDate.of(2020,2,26));
		Award a4 = new Award("best singer","grammy",LocalDate.of(2020,1,20));
		Award a5 = new Award("best female actor","disney",LocalDate.of(2019,11,23));
		Award a6 = new Award("best child actor","pixar",LocalDate.of(2017,5,17));
		
		//create celebrities and add awards to their awards collection
		//add celebrities to an arrayL list for ease of writing (makes program more scalable)
		celebs= new ArrayList<Celebrity>();
		Celebrity c1 = new Celebrity(1,"singer",LocalDate.of(1999,11, 21));
		c1.setAwards(a4);
		c1.setAwards(a2);
		celebs.add(c1);
		Celebrity c2 = new Celebrity(2,"actor",LocalDate.of(1992,10, 21));
		c2.setAwards(a1);
		c2.setAwards(a5);
		celebs.add(c2);
		Celebrity c3 = new Celebrity(3,"child actor",LocalDate.of(2001, 9, 21));
		c3.setAwards(a6);
		celebs.add(c3);
		Celebrity c4 = new Celebrity(4,"stuntable",LocalDate.of(1997,1, 1));
		c4.setAwards(a3);
		celebs.add(c4);
		Celebrity c5 = new Celebrity(5,"singer",LocalDate.of(1990,11, 11));
		c5.setAwards(a4);
		celebs.add(c5);
		
		/*for(Celebrity c: celebs) {
			System.out.println(c);
		}*/
		
		
		//write awards to csv
		FileWriter fileWriter = null;
		String fileHeader = "id, award name, organisation name, date";
		try {
			fileWriter = new FileWriter("awards.csv");
			
			//write the csv file header
			fileWriter.append(fileHeader.toString());
			
			//add new line seperator after the header 
			fileWriter.append("\n");
			
			//try to serialize the customer arrayList and write awards to csv file
			out=new ObjectOutputStream(new FileOutputStream(fileName+".ser",true));	//true makes the outputstream append rather than overwrite
			
			for(Celebrity c: celebs) {
				c.writeObject(out, fileWriter);
			} 
			System.out.println("All celebrities serialised and awards written to CSV file");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileWriter.flush();	//flush the stream
				fileWriter.close();	//close the fileWriter (throws IOException)
			}catch(IOException e) {
				e.printStackTrace();
			}
		} //end finally
	}//end main
	}//end class

