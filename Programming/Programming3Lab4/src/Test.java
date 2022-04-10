import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * test program with list of five celebs
 * Each celeb has awards 
 * The program writes a list of Celebritys using object Serialization to a file that can be used
 * to load up the objects again
 * The name of the file should be red from the user using a Scanner on Sytem.in
 * @author nicole
 *
 */
public class Test {
	
	private static ObjectOutputStream out=null; //writes to an output stream
	private static Scanner input;
	private static ArrayList<Celebrity> celebs;
	private static ObjectInputStream in= null;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
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
		
		
		//write celebritys to a file that can be loaded up again
		WriteObjectsToInputFile(fileName);
		
		System.out.println("File Serialised.\nNow you can try to deserialize the serialised file\nEnter a file name"
				+ "to de-Serialize: ");
		input=new Scanner(System.in);
		fileName=input.next();
		ReadObjectsFromInputFile(fileName);
		
		
	}
	/**
	 * Method for deserializing celebs
	 */
	private static void ReadObjectsFromInputFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		try {
			in=new ObjectInputStream(new FileInputStream(fileName+".ser"));
			
			//while theres more info, read it
			while(true) {
				Celebrity c = (Celebrity) in.readObject();
				//ArrayList<Celebrity> celebs2 =new ArrayList<>();
				//celebs2=(ArrayList<Celebrity>)in.readObject();
				
				System.out.println(c);
				
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void WriteObjectsToInputFile(String fileName) throws IOException{
		try {
			out=new ObjectOutputStream(new FileOutputStream(fileName+".ser",true));
			
			for(int i = 0; i< celebs.size(); i++) {
				System.out.println(celebs.get(i));
				out.writeObject(celebs.get(i));
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
