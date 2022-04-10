package net.codejava.hibernate;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import net.sf.ehcache.hibernate.HibernateUtil;

public class StudentManager {

	protected SessionFactory sessionFactory;
	
	//load session factory
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	//write a method to close the session factory
	public void exit() {
		sessionFactory.close();
	}
	
	//create method
	public void create(String fname, String lname) throws Exception {
		setUp();
		Student student = new Student();
		
		//when creating a student, we need to create the email ourself because its in the format
		//firstName.LastName@nuigalway.ie
		//there should be no duplicate email unless the primary key is the same
		student.setFirst_name(fname);
		student.setLast_name(lname);
		//need to have a check that the email doesnt already exist and if it does, the number should
		//be incrimented to fname.lname1@nuigalway.ie
		
		//iterate through all items in db and check if email exists
		boolean alreadyInDb=false;
		int numTimesInDb=0;
		for(Student s: getAllStudents()) {
			if((s.getFirst_name().equals(student.getFirst_name()) && (s.getLast_name().equals(student.getLast_name())))) {
				
				//if it exists, check id is the same
				//if id is the same do nothing
				if(s.getId() == student.getId()) {	//nothing is changed
					System.out.println("Student already in database");
				}
				System.out.println("changing email to one with  a digit");
				//else student is a different student with the same name
				//incriment the number of times a student with the same name is in the db so we know what
				//number to concatenate with the email
				numTimesInDb++;
				alreadyInDb=true;
			}
		}
		
		if(!alreadyInDb) {
			//otherwise they dont have the same name as anyone so just set email normally
			student.setEmail(fname+"."+lname+"@nuigalway.ie");
		}else {
			//same name in the database potentially multiple times
			student.setEmail(fname+"."+lname+numTimesInDb+"@nuigalway.ie");
		}
		
		//if not, concatenate a number on the end of the email and incriment this number
		//get a session from the session factory and call the methods on the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(student);
		
		session.getTransaction().commit();
		session.close();
		exit();
	}
	
	//read method
	public Student read(int id) throws Exception {

		setUp();
		Session session = sessionFactory.openSession();
		//you dont need a transaction for the read operation
		
		Student student = session.get(Student.class, id);
		session.close();
		exit();
		
		return student;	//might be null
	}
	
	//method to get all students
	@SuppressWarnings("unchecked")
	public ArrayList<Student> getAllStudents() throws Exception{
		
		ArrayList<Student> students = new ArrayList<>();
		
		Session session = sessionFactory.openSession();
			
		session.beginTransaction();
		students = (ArrayList<Student>) session.createSQLQuery("SELECT * FROM student").addEntity(Student.class).list();
			
		session.getTransaction().commit();
		session.close();
		
		return students;
	}
	//update method
	public void update(int id, String firstName, String lastName) throws Exception {
		
		setUp();
		Student student = new Student();
		student.setId(id);
		student.setFirst_name(firstName);
		student.setLast_name(lastName);
		
		//iterate through all items in db and check if email exists
				boolean alreadyInDb=false;
				int numTimesInDb=0;
				for(Student s: getAllStudents()) {
					if((s.getFirst_name().equals(student.getFirst_name()) && (s.getLast_name().equals(student.getLast_name())))) {
						
						//if it exists, check id is the same
						//if id is the same do nothing
						if(s.getId() == student.getId()) {	//nothing is changed
							System.out.println("Student already in database");
						}
						System.out.println("changing email to one with  a digit");
						//else student is a different student with the same name
						//incriment the number of times a student with the same name is in the db so we know what
						//number to concatenate with the email
						numTimesInDb++;
						alreadyInDb=true;
					}
				}
				
				if(!alreadyInDb) {
					//otherwise they dont have the same name as anyone so just set email normally
					student.setEmail(firstName+"."+lastName+"@nuigalway.ie");
				}else {
					//same name in the database potentially multiple times
					student.setEmail(firstName+"."+lastName+numTimesInDb+"@nuigalway.ie");
				}
				
		//get a session from the session factory and call the methods on the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(student);
			
		session.getTransaction().commit();
		session.close();
		exit();
	}
	
	//delete method
	public void delete(int id) throws Exception {
			
		setUp();
		Student student = new Student();
		student.setId(id);
		
		//get a session from the session factory and call the methods on the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(student);
				
		session.getTransaction().commit();
		session.close();
		exit();
	}
	//iterate through database and change college email
	public void changeEmail() throws Exception {
		
		setUp();
		//we cant simple set new email to be firstName.lastName@gmit.ie because there might be some 
		//number concatenated onto the email
		ArrayList<Student> students = new ArrayList<>();
		try {
			students = getAllStudents();
			for(Student s: students) {
				String beforeEnding="";	//ending before the "@nuigalway.ie"
				String email = s.getEmail();
				boolean endFound=false;
				int i=0;
				while(!endFound) {	//name before the ending
					if(!(email.charAt(i) == '@')) {
						beforeEnding+=email.charAt(i);	//add it to a string
						i++;
					}else {
						endFound=true;
					}
				}
				
				//update student email
				Student student = new Student();
				student.setId(s.getId());
				student.setFirst_name(s.getFirst_name());
				student.setLast_name(s.getLast_name());
				student.setEmail(beforeEnding+"@gmit.ie");	//new email
				
				//get a session from the session factory and call the methods on the session
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(student);
					
				session.getTransaction().commit();
				session.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		exit();
	}
	/**
	 * testing purposes
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// check connaction 
		StudentManager manager = new StudentManager();
		//manager.setUp();
		//manager.read();
		//manager.create();
		//manager.update();
		manager.delete(22);
		//manager.exit();

	}

}
