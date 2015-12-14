package main.java.servlets;

import java.io.File;
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
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.octo.captcha.engine.image.utils.SimpleImageCaptchaToJPEG;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import com.sun.prism.Image;

import main.java.DAO.impl.UserDAO;
import main.java.entity.User;
import main.java.service.UserService;
import main.java.validator.Validation;
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

		User user = new User();
		user.setFirstName(request.getParameter("fname"));
		user.setLastName(request.getParameter("lname"));
		user.setEmail(request.getParameter("email"));
		user.setLogin(request.getParameter("login"));
		if (request.getParameter("pass").equals(request.getParameter("cpass"))) {
			user.setPassword(request.getParameter("pass"));
		}
		Validation validation = (Validation) request.getServletContext().getAttribute("validation");
		List<String> errorList = validation.checkUser(user);
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("USER_DAO");
		if (userDAO.getUserIdByUsername(request.getParameter("login")) != 0) {
			errorList.add("username already exist");
		}
		if (errorList.isEmpty() == false) {
			request.setAttribute("ErrorMsg", errorList);
			errorList = null;
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			UserService userService = (UserService) request.getServletContext().getAttribute("userService");
			user = userService.add(user);
			request.getSession().setAttribute("id", user.getId());
			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
		}

	}

}
