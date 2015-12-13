package main.java.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import main.java.DAO.impl.UserDAO;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
public class Listener implements ServletContextListener {

    private static final String USER_DAO = "USER_DAO";
    private static final String USER_NAME_PATTERN="^[a-zA-Z]{3,15}$";
	/**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         UserDAO userDAO = new UserDAO();
         ServletContext sc = arg0.getServletContext();
         sc.setAttribute(USER_DAO, userDAO);
         Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
         sc.setAttribute("NameValid", pattern);
    }
	
}
