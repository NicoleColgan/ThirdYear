import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to de-serialize Celebrity objects from a file and get back their rewards
 * @author nicole
 *
 */
public class DeSerilizeCelebs {
	
	private static ObjectInputStream in= null;
	private static ArrayList<Celebrity> celebs=new ArrayList<>();
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		//scan in a file name from the user
		System.out.println("Enter a file name to retrieve your customer objects from ");
		Scanner input=new Scanner(System.in);
		String fileName=input.next();
		
		try {
			in=new ObjectInputStream(new FileInputStream(fileName+".ser"));
			
			while(in.available()>0) { //while there is more to be read
				
				Celebrity c = Celebrity.readObject(in);	//use celebrity static method to reconstruct celebrity object
				System.out.println(c);
				celebs.add(c);
			}//end while
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}//end if
		}//end finally
	}//end main
}//end class
