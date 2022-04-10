import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Test class
 * @author nicole Colgan 19345826
 *
 */
public class LoadUpUsers {
	public static void main(String[] args) {
		//load up users from a csv file and create user objects
		java.util.List<UserAccount> users = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("users.csv"));
			String line = null;
			Scanner lineReader = null;
			
			while((line=in.readLine()) != null) {	//while more info
				StringReader sr = new StringReader(line);
				lineReader = new Scanner(sr);
				lineReader.useDelimiter(",");
				long id = lineReader.nextInt();
				String name = lineReader.next();
				String email = lineReader.next();
				UserAccount u = new UserAccount(id,name,email);
				users.add(u);
			}
			//check all users were sucessfully loaded from the csv file
			System.out.println("======================================================");
			System.out.println("checking users were correctly added to ArrayList\n");
			System.out.println(users);
			System.out.println("======================================================");
			
			//check if the equality method works
			System.out.println("======================================================");
			System.out.println("Does "+users.get(0)+" = "+users.get(1)+": "+users.get(0).equals(users.get(1)));
			System.out.println("Does "+users.get(6)+" = "+users.get(1)+": "+users.get(6).equals(users.get(1)));
			System.out.println("======================================================");
			//test hash code
			System.out.println("\n======================================================");
			System.out.println("Testing hash code method for userID value");
			for(UserAccount u: users) {
				System.out.println(u+" hashedID ="+u.hashCode());
			}
			
			//use collections.sort() to sort the list of users based on the natural ordering and print out results
			//natural ordering for users is based on their name i.e. ascending order based on name
			Collections.sort(users);
			System.out.println("======================================================");
			System.out.println("\nUsers after being sorted based on the natural ordering of users\n");
			System.out.println(users);
			System.out.println("======================================================");
			
			//sort list of users based on name in descending alphabetical order using a Comparator defined
			//as an anonymous inner class
			Collections.sort(users, new Comparator<UserAccount>() {
				public int compare(UserAccount p1, UserAccount p2) {
					return p2.getName().compareTo(p1.getName());
				}
			});
			System.out.println("======================================================");
			System.out.println("\nUsers sorted in descending order based on name\n");
			System.out.println(users);
			System.out.println("======================================================");
			
			//sort the users based on ID in ascending order using a Comparator defined as an 
			//anonymous inner function
			//we could not simple compare a long to a long. 
			//comapre to can only be used for wrapper classes like Integer and Long, so we have to use
			//Long.valueof() in order to compare the values
			Collections.sort(users, (UserAccount u1, UserAccount u2)->
		    Long.valueOf(u1.getUserID()).compareTo(Long.valueOf(u2.getUserID())));
			System.out.println("======================================================");
			System.out.println("\nUsers sorted in ascending order based on id\n");
			System.out.println(users);
			System.out.println("======================================================");
			
			//use binary search to search for a userAccount in the list that has been pre- sorted
			//using natural ordering
			UserAccount u = new UserAccount(19838484, "Amy Louise", "Amy@gmail.com");
			UserAccount u2 = new UserAccount(1111111111, "Nicole Colgan", "nicole@gmail.com");
			//pre sorted using the natural order
			Collections.sort(users);
			System.out.println("Users in natural order:\n"+users);
			//binary search searches a list using a binary search algorithm for an element
			//The list must be sorted in ascending order according to its natural ordering
			//@returns index of the search tree if the object is contained in the list
			//otherwise it will return (-(insertion point)-1) which indicates the point in which it should
			//be inserted
			
			//not required to insert element into the list in the correct position if its not found
			int pos = Collections.binarySearch(users,u);	//should not be in the list
			if(pos>=0)
				System.out.println("Object "+u+" found in list at index: "+pos);
			else
				System.out.println("Object "+u+" not found in list, insertion point: "+pos);
			
			int pos2 = Collections.binarySearch(users,u2);	//should be in the list 
			if(pos2>=0)
				System.out.println("Object "+u2+"  found in list at index: "+pos2);
			else
				System.out.println("Object "+u2+" not found in list, insertion point: "+pos2);
			
			/*
			 * Add the list of UserAccounts to a Map where the instance of UserAccount is used as the key and a 
			 * list of workSpace objects is the value
			 * 
			 * Add at least one workspace for the UserAccount at position 0 in the original list
			 * add two collaborators (user 4 and 7) to this workspace.
			 * Retrieve the Workspaces for the user from the map using an appropriate key and print out the 
			 * results
			 */
			Workspace w = new Workspace("IT office","Software engineering products are worked on here",users.get(2));
			//index 3 = 4th user, index 6 = 7th user
			w.addCollaborator(users.get(3));	//add collaborators
			w.addCollaborator(users.get(6));
			
			Workspace w3 = new Workspace("Computer suite","Programming done here",users.get(4));
			w3.addCollaborator(users.get(5));
			w3.addCollaborator(users.get(7));
			java.util.List<Workspace> workspaces = new ArrayList<>();
			workspaces.add(w3);
			workspaces.add(w);
			
			//add user account to map 
			//key = user
			//value = list of workspace objects
			Map<UserAccount, java.util.List<Workspace>> map = new HashMap<>();
			map.put(users.get(0), workspaces);
			for(int i=1; i<users.size(); i++) {
				map.put(users.get(i), null);	//other users have no work spaces
			}
			
			//retrieve workspaces for users - user at position 0 in the arrayList should have workspace w & w3
			//hash
			//Although we can add entries to the map in some order, hashmaps make no guarantee that the order
			//of the entries will remain constant over time i.e. even though we add the elements in a particular
			//order, it doesn't mean that when we read the map that the elements will be in the same order
			List<Workspace> w1 = map.get(users.get(0));
			System.out.println("==================================================");
			System.out.println("\nWorkspace for "+users.get(0)+w1);
			System.out.println("==================================================");
			System.out.println("\nFull hash map for users and workspaces\n");
			System.out.println(map);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
