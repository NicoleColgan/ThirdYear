

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
 * Servlet implementation class Servlet4
 */
public class Servlet4 extends HttpServlet {
	private static final long serialVersionUID = 102831073239L;
	static String url= "jdbc:mysql://localhost:3306/test";
	static String user= "root";
	static String password="Msql.2099@&";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id= Integer.parseInt(request.getParameter("id"));
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,user,password);
			System.out.println("Connection is succesfully made "+url);
			
			//create string query
			String query="DELETE FROM employees WHERE id="+id;
			
			Statement statement = connect.createStatement();
			//call the execute method passing the query
			statement.execute(query);
			System.out.println("Query sucessfully executed");
			//Sucessful operation
			RequestDispatcher rd = request.getRequestDispatcher("SucessfulOperation.jsp");
			rd.forward(request, response);
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {	//unsucessful operation
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("InvalidEntry.jsp");
			rd.forward(request, response);
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
