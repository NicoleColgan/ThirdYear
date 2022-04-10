

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getEmployees
 */
public class getEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//create list of employees
		List<Employee> employees = new ArrayList<Employee>();
		Employee e1 = new Employee(1,"Zach");
		Employee e2 = new Employee(2,"Jake");
		Employee e3 = new Employee(3,"Sarah");
		Employee e4 = new Employee(4,"Elener");
		Employee e5 = new Employee(5,"Nick");
		Employee e6 = new Employee(6,"Florence");
		Employee e7 = new Employee(7,"Sam");
		
		//create departments
		Department d1 = new Department(1,"IT");
		Department d2 = new Department(2,"Support");
		Department d3 = new Department(3,"HR");
		
		//add departments to employees
		e1.setDepartment(d1);
		e2.setDepartment(d2);
		e3.setDepartment(d2);
		e4.setDepartment(d1);
		e5.setDepartment(d3);
		e6.setDepartment(d3);
		e7.setDepartment(d1);
		
		//add employees to arraylist
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		employees.add(e5);
		employees.add(e6);
		employees.add(e7);
		
		request.setAttribute("employees", employees);
		
		//send to jsp to be displayed
		RequestDispatcher rd = request.getRequestDispatcher("listEmployees.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
