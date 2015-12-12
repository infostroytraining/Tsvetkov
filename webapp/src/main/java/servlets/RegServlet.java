package main.java.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.entity.User;
import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServlet() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int random = (int) (Math.random()*1000);
		session.setAttribute("randomNum", random);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setFirstName(request.getParameter("fname"));
		user.setLasteName(request.getParameter("lname"));
		user.setEmail(request.getParameter("email"));
		user.setLogin(request.getParameter("login"));
		if (request.getParameter("pass").equals(request.getParameter("cpass"))) {
			user.setPassword(request.getParameter("password"));
		} else {
			request.setAttribute("ErrorMsg", "invalid password");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

}
