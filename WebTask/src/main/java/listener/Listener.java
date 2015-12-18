package main.java.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import main.java.DAO.impl.UserDAO;
import main.java.db.TransactionManager;
import main.java.listener.factory.ServiceFactory;
import main.java.service.CreateImage;
import main.java.service.UserService;
import main.java.validator.Validation;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
public class Listener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public Listener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext sc = arg0.getServletContext();

		Validation validation = new Validation();
		sc.setAttribute("validation", validation);
		UserService userService = ServiceFactory.getUserService();
		sc.setAttribute("userService", userService);
		CreateImage createImage = new CreateImage();
		sc.setAttribute("createImage", createImage);
	}

}
