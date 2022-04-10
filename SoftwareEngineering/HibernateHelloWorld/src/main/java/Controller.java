

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.hibernate.Student;
import net.codejava.hibernate.StudentManager;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentManager manager = new StudentManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("create") != null) {
			try {
				//only create user if a valid name was entered
				if((request.getParameter("firstName") != "") && (request.getParameter("lastName") != "")) {
					manager.create(request.getParameter("firstName"),request.getParameter("lastName"));
					//then redirect to index
					response.sendRedirect("SucessfulOperation.jsp");
				}
				else
					response.sendRedirect("InvalidEntry.jsp");
			}catch(Exception e) {
				response.sendRedirect("InvalidEntry.jsp");
				e.printStackTrace();
			}
			//we want to create a student
		}
		else if(request.getParameter("readById") != null) {
			try {
				Student student = manager.read(Integer.parseInt(request.getParameter("id")));
				
				if(student != null) {	//if user exists, send them to jsp
					request.setAttribute("Student", student);
					RequestDispatcher rd = request.getRequestDispatcher("DisplayOneStudent.jsp");
					rd.forward(request, response);
				}
				else {	//else display unsucessful message
					response.sendRedirect("InvalidEntry.jsp");
				}
				
			}catch(Exception e) {
				response.sendRedirect("InvalidEntry.jsp");
				e.printStackTrace();
			}
		}
		else if(request.getParameter("update") != null) {
			try {
				manager.update(Integer.parseInt(request.getParameter("id")),request.getParameter("firstName"),request.getParameter("lastName"));
				//then redirect to index
				response.sendRedirect("SucessfulOperation.jsp");
			}catch(Exception e) {
				response.sendRedirect("InvalidEntry.jsp");
				e.printStackTrace();
			}
		}
		else if(request.getParameter("delete") != null) {
			try {
				manager.delete(Integer.parseInt(request.getParameter("id")));
				//then redirect to index
				response.sendRedirect("SucessfulOperation.jsp");
			}catch(Exception e) {
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
