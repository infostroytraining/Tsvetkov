package main.java.servlets;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.runtime.ProtectedFunctionMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import main.java.entity.User;
import main.java.service.CreateImage;
import main.java.service.UserService;
import main.java.service.exception.ServiceException;
import main.java.validator.Validation;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CreateImage image = (CreateImage) request.getServletContext().getAttribute("createImage");
		HttpSession session = request.getSession();
		int random = (int) (Math.random() * 1000);
		session.setAttribute("randomNum", random);
		log.info("captcha number " + random);
		image.crateImage(Integer.toString(random), session.getId());
		request.setAttribute("captcha", session.getId().concat(".jpg"));
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		Validation validation = (Validation) request.getServletContext().getAttribute("validation");
		request.removeAttribute("ErrorMsg");;
		List<String> errorList = new ArrayList<>();
		request.setAttribute("ErrorMsg", errorList);
		if (request.getParameter("captcha").equals(request.getSession().getAttribute("randomNum").toString()) == false) {
			CreateImage image = (CreateImage) request.getServletContext().getAttribute("createImage");
			HttpSession session = request.getSession();
			int random = (int) (Math.random() * 1000);
			session.setAttribute("randomNum", random);
			log.info("captcha number " + random);
			image.crateImage(Integer.toString(random), session.getId());
			request.setAttribute("captcha", session.getId().concat(".jpg"));
			errorList.add("invalid captcha");
			request.setAttribute("ErrorMsg", errorList);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			errorList.clear();
			UserService userService = (UserService) request.getServletContext().getAttribute("userService");
			user.setFirstName(request.getParameter("fname"));
			user.setLastName(request.getParameter("lname"));
			user.setEmail(request.getParameter("email"));
			user.setLogin(request.getParameter("login"));
			if (request.getParameter("pass").equals(request.getParameter("cpass"))) {
				user.setPassword(request.getParameter("pass"));
			} else {
				errorList.add("Invalid confirm password");
			}
			try {
				if (userService.getUserIdByUsername(request.getParameter("login")) != 0) {
					errorList.add("username already exist");
				}
			} catch (ServiceException e1) {
				log.error("Error validation username");
				e1.printStackTrace();
			}
			errorList.addAll(validation.checkUser(user));
			if (!errorList.isEmpty()) {
				request.setAttribute("ErrorMsg", errorList);
				CreateImage image = (CreateImage) request.getServletContext().getAttribute("createImage");
				HttpSession session = request.getSession();
				int random = (int) (Math.random() * 1000);
				session.setAttribute("randomNum", random);
				log.info("captcha number " + random);
				image.crateImage(Integer.toString(random), session.getId());
				request.setAttribute("captcha", session.getId().concat(".jpg"));
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				try {
					user = userService.add(user);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("id", user.getId());
				request.getRequestDispatcher("/welcome.jsp").forward(request, response);
			}

		}

	}


}
