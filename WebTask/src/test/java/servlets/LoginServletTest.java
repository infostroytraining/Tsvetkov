package test.java.servlets;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Matchers.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.logging.log4j.core.filter.RegexFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.DAO.impl.UserDAO;
import main.java.db.TransactionManager;
import main.java.entity.User;
import main.java.service.UserService;
import main.java.service.exception.ServiceException;
import main.java.servlets.LoginServlet;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

	private LoginServlet servlet = new LoginServlet();
	private UserDAO userDAO = new UserDAO();
	private TransactionManager transactionManager = new TransactionManager();
	private UserService userService = new UserService(userDAO, transactionManager);
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private ServletContext context;
	@Mock
	private HttpSession session;
	@Mock
	private ServletOutputStream outputStream;
	@Mock
	private RequestDispatcher dispatcher;

	@Before
	public void init() throws IOException {
		when(request.getServletContext()).thenReturn(context);
		when(request.getServletContext().getMimeType(anyString())).thenReturn("image/jpeg");
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getId()).thenReturn("123");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		when(response.getOutputStream()).thenReturn(outputStream);
		when(request.getServletContext().getAttribute("userService")).thenReturn(userService);

	}

	@Test
	public void testDoPosWithCorrectLoginAndPass() throws ServletException, IOException {
		mockGetLoginPassword("wwww", "wwww");
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(anyString());
		
	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		servlet.doGet(request, response);
		request.getRequestDispatcher(anyString());

	}

	@Test
	public void testDoPost() throws ServletException, IOException {

		servlet.doPost(request, response);
		verify(request).getRequestDispatcher("login.jsp");
	}

	private void mockGetLoginPassword(String username, String password) {
		when(request.getParameter("login")).thenReturn(username);
		when(request.getParameter("pass")).thenReturn(password);
	}

}
