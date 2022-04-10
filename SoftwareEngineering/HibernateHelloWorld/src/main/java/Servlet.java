

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.hibernate.Student;
import net.codejava.hibernate.StudentManager;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentManager manager = new StudentManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation");
		response.getWriter().println("Chosen operation: "+operation);
		if(operation.equals("CREATE")) {
			//DISPLAY A form which allows you to create a student
			RequestDispatcher rd = request.getRequestDispatcher("Create.jsp");
			rd.forward(request, response);
		}
		else if(operation.equals("READONESTUDENT")) {
			//DISPLAY A form which allows you to create a student
			RequestDispatcher rd = request.getRequestDispatcher("ReadStudentById.jsp");
			rd.forward(request, response);
			
		}
		else if(operation.equals("READALL")) {
			//DISPLAY A form which allows you to create a student
			ArrayList<Student> students=new ArrayList<>();;
			
			try {
				manager.setUp();
				students = manager.getAllStudents();
				manager.exit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Student s: students) {
				System.out.println(s.getFirst_name()+" "+s.getLast_name());
			}
			
			request.setAttribute("Students", students);
			RequestDispatcher rd = request.getRequestDispatcher("Read.jsp");
			rd.forward(request, response);
			
		}
		else if(operation.equals("UPDATE")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("Update.jsp");
			rd.forward(request, response);
		}
		else if(operation.equals("DELETE")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("Delete.jsp");
			rd.forward(request, response);
		}
		else if(operation.equals("CHANGEEMAIL")) {
			
				try {
					manager.changeEmail();
					response.sendRedirect("SucessfulOperation.jsp");
				} catch (Exception e) {
					
					response.sendRedirect("InvalidEntry.jsp");
					e.printStackTrace();
				}
				
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
