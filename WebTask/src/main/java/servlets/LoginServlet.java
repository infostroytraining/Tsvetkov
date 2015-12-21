package main.java.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.impl.UserDAO;
import main.java.entity.User;
import main.java.service.UserService;
import main.java.service.exception.ServiceException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		String username = request.getParameter("login");
		String password = request.getParameter("pass");
		int userId;
		User user;
		try {
			userId = userService.getUserIdByUsername(username);
			user = userService.getUserById(userId);
			System.out.println(user.getLogin());
			System.out.println(username);
			if (password != null && user.getPassword() != null && user.getPassword().equals(password)) {
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} else {
				errors.add("Invalid username or password");
				request.setAttribute("ErrorMsg", errors);
				errors = null;
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
