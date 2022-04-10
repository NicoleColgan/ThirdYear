

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		response.getWriter().println("Chosen operation: "+operation);
		if(operation.equals("CREATE")) {
			//DISPLAY A form which allows you to create a student
			RequestDispatcher rd = request.getRequestDispatcher("Create.jsp");
			rd.forward(request, response);
		}
		else if(operation.equals("READ")) {
			//DISPLAY A form which allows you to create a student
			RequestDispatcher rd = request.getRequestDispatcher("ReadServlet");
			rd.forward(request, response);
			
		}
		else if(operation.equals("DELETE")) {
			//DISPLAY A form which allows you to create a student
			RequestDispatcher rd = request.getRequestDispatcher("Delete.jsp");
			rd.forward(request, response);
		}
		else if(operation.equals("UPDATE")) {
			//DISPLAY A form which allows you to create a student
			RequestDispatcher rd = request.getRequestDispatcher("Update.jsp");
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
