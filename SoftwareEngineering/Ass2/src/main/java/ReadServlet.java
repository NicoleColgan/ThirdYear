

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql_connection.Employee;

/**
 * Servlet implementation class Read
 */
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 104831973239L;
	static String url= "jdbc:mysql://localhost:3306/test";
	static String user= "root";
	static String password="Msql.2099@&";
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,user,password);
			System.out.println("Connection is succesfully made "+url);
			
			//create string query
			String query="SELECT * FROM employees";
			
			ArrayList<Employee> emps = new ArrayList<>();
			
			Statement statement = connect.createStatement();
			//call the execute method passing the query
			ResultSet resultSet=statement.executeQuery(query);
			//System.out.println("Query sucessfully executed");
			while(resultSet.next()) {	//iterate through results to get employee fields
				int id = Integer.parseInt(resultSet.getString("id"));
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String desig = resultSet.getString("desig");
				float salary = Float.parseFloat(resultSet.getString("salary"));
				int number = Integer.parseInt(resultSet.getString("number"));
				Employee e = new Employee(id,name,email,desig,salary,number);
				emps.add(e);
			}
			request.setAttribute("employees", emps);	//send arrayList to jsp
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
