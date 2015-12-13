package main.java.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import com.sun.prism.Image;

import main.java.DAO.impl.UserDAO;
import main.java.entity.User;
import main.java.service.UserService;
import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger();
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
		int random = (int) (Math.random() * 1000);
		session.setAttribute("randomNum", random);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Pattern pattern = (Pattern) request.getServletContext().getAttribute("NameValid");
		List<String> list = new ArrayList<>();
		User user = new User();
		if (pattern.matcher(request.getParameter("fname")).matches() == false) {
			list.add("Invalid firstname");
		} else {
			user.setFirstName(request.getParameter("fname"));
		}
		if (pattern.matcher(request.getParameter("lname")).matches() == false) {
			list.add("Invalid lastname");
		} else {
			user.setLastName(request.getParameter("lname"));
		}
		user.setEmail(request.getParameter("email"));
		user.setLogin(request.getParameter("login"));
		if (request.getParameter("pass").equals(request.getParameter("cpass"))) {
			user.setPassword(request.getParameter("pass"));
		} else {
			list.add("invalid password");
		}
		if (list.isEmpty() == false) {
			request.setAttribute("ErrorMsg", list);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			UserService userService = new UserService((UserDAO) request.getServletContext().getAttribute("USER_DAO"));
			user = userService.add(user);
			request.getSession().setAttribute("id", user.getId());
			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
		}

	}

}
