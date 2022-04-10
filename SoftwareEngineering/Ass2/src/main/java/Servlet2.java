
import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql_connection.Employee;

/**
 * Servlet implementation class Servlet2
 */
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	static String url= "jdbc:mysql://localhost:3306/test";
	static String user= "root";
	static String password="Msql.2099@&";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//get employee details from request
			String name= request.getParameter("name");
			int id= Integer.parseInt(request.getParameter("id"));
			int num = Integer.parseInt(request.getParameter("number"));
			String email= request.getParameter("email");
			float salary = Float.parseFloat(request.getParameter("salary"));
			String desig= request.getParameter("desig");
			
			Employee e = new Employee(id,name,email,desig,salary,num);	//create employee
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection connect = DriverManager.getConnection(url,user,password);
			System.out.println("Connection is succesfully made "+url);
			
			//inserting into the table
			//create string query
			String query="INSERT into employees(id,name,email,desig,salary,number) values("+id+",'"+name+"','"+email+"','"+desig+"',"+salary+","+num+")";
			//create an insert statment object
			Statement statement = connect.createStatement();
			//call the execute method passing the query
			statement.execute(query);
			response.getWriter().println("Employee: "+e+ " added to the data base");
			System.out.println("Query sucessfully executed");
			RequestDispatcher rd = request.getRequestDispatcher("SucessfulOperation.jsp");
			rd.forward(request, response);
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			response.getWriter().println("invalid entry for employee");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("InvalidEntry.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
