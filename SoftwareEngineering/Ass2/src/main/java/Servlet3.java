

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql_connection.Employee;

/**
 * Servlet implementation class Servlet3
 */
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 102881973239L;
	static String url= "jdbc:mysql://localhost:3306/test";
	static String user= "root";
	static String password="Msql.2099@&";
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String name= request.getParameter("name");
			int id= Integer.parseInt(request.getParameter("id"));
			int num = Integer.parseInt(request.getParameter("number"));
			String email= request.getParameter("email");
			float salary = Float.parseFloat(request.getParameter("salary"));
			String desig= request.getParameter("desig");
			
			Employee e = new Employee(id,name,email,desig,salary,num);
			
			response.getWriter().println("Employee: "+e);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,user,password);
			System.out.println("Connection is succesfully made "+url);
			
			//inserting into the table
			//create string query
			String query="SELECT * FROM employees";
			//create an insert statment object
			Statement statement = connect.createStatement();
			//call the execute method passing the query
			statement.execute(query);
			System.out.println("Query sucessfully executed");
			RequestDispatcher rd = request.getRequestDispatcher("Read.jsp");
			rd.forward(request, response);
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
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
