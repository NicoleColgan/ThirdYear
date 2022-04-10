import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Scanner;
/**
 * Class for reading and writing to and from files
 * @author nicole Colgan 19345826
 *
 */
public class IOStreams {
	//structure of each data point [land type],[region],[value of land sold]
	//read contents of the line using character stream and write content to another file (1 for each region)
	//output structure: land type value
	public Region region;
	public static void main(String[] args) {
		
		BufferedReader in = null;
		Scanner lineReader = null;
		
		try {
			in = new BufferedReader(new FileReader("ass3.csv")); //use bufferedReader to read lines 
			
			String line=null, landType, regionName;
			double valueOfLand;
			
			while((line = in.readLine()) !=null) { //read line returns a string of the line
				//takes in an input stream and changes it to character stream
				StringReader sr = new StringReader(line);
				
				// "," is the deliminator seperating the values. 
				//scanner to read tokens seperated by ",". set delimiter with useDelimiter()
				lineReader = new Scanner(sr);
				lineReader.useDelimiter(",");
				
				//need to skip the first line because they're just headings
				if(line.startsWith("A") || line.startsWith("P")) {
					landType=lineReader.next();
					regionName=lineReader.next();
					valueOfLand = lineReader.nextDouble();
					System.out.println("Land type: "+landType+"\tRegion: "+regionName+"\tvalue of land: "+valueOfLand);
				
				/*
				 * Printwriter to write to individual files (open and close it for each new line because the data 
				 * isnt ordered in the csv files)
				 */
				PrintWriter out; //prints formatted objects to output
				try {
					//printwriter and file writer are similar but printwriter allows for formatting the output
					//we have the region name now we need to pass in the corrosponding region enum
					
					String fileToWriteTo = getFileName(regionName);
					
					//true makes the printwriter append to the existing file rather than overwriting it
					out= new PrintWriter(new FileWriter(fileToWriteTo,true));
					out.printf("%s %f%n", landType,valueOfLand);
					
					//close the printWriter stream
					out.close();
					
				}catch(IOException e) { //catch exceptions
					e.printStackTrace();
				}
				
				}
				
			}
		}catch(FileNotFoundException e) { //file reader can throw file not found exception
			e.printStackTrace();
		}catch(IOException e) { //readLine can throw I/O exception
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * Returns a file name based on some string enum name
	 * @param enum print name
	 * @return text file name to be written to
	 */
	private static String getFileName(String region) {
		
		//use a switch statement to determine which file to write it to
		switch(Region.getRegion(region)) {
		
		case STATE:
			return "State.txt";
			
		case BORDER:
			return "Border.txt";
		
		case MIDLAND:
			return "Midland.txt";
			
		case WEST:
			return "West.txt";
			
		case DUBLIN:
			return "Dublin.txt";
					
		case MIDEAST:
			return "Mideast.txt";
			
		case MIDWEST:
			return "Midwest.txt";
			
		case SOUTHEAST:
			return "Southeast.txt";
			
		case SOUTHWEST:
			return "Southwest.txt";
		
		}
		return null;
	}

}
