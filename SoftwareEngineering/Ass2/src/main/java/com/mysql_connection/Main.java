package com.mysql_connection;

import java.sql.*;

public class Main {
	static String url= "jdbc:mysql://localhost:3306/test";
	static String user= "root";
	static String password="Msql.2099@&";
	public static void main(String[] args) {
		//connect to the database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,user,password);
			System.out.println("Connection is succesfully made "+url);
			
			//inserting into the table
			//create string query
			String query="INSERT into employees(id,name,email,desig,salary,number) values(4,'Kacper','kacper@gmail,com','employee',50.00,1)";
			//create an insert statment object
			Statement statement = connect.createStatement();
			//call the execute method passing the query
			statement.execute(query);
			System.out.println("Query sucessfully executed");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
