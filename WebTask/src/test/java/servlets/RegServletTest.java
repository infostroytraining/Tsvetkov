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
import main.java.service.CreateImage;
import main.java.service.UserService;
import main.java.service.exception.ServiceException;
import main.java.servlets.LoginServlet;
import main.java.servlets.RegServlet;
import main.java.validator.Validation;

@RunWith(MockitoJUnitRunner.class)
public class RegServletTest {
	private RegServlet servlet = new RegServlet();
	private UserDAO userDAO = new UserDAO();
	private TransactionManager transactionManager = new TransactionManager();
	private UserService userService = new UserService(userDAO, transactionManager);
	private CreateImage createImage = new CreateImage();
	private Validation validation = new Validation();
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
		when(request.getServletContext().getAttribute("createImage")).thenReturn(createImage);
		when(request.getServletContext().getAttribute("validation")).thenReturn(validation);

	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		servlet.doGet(request, response);
		verify(request).getRequestDispatcher(anyString());

	}

	@Test
	public void testDoPost() throws ServletException, IOException {
		when(request.getParameter("fname")).thenReturn("Vasya");
		when(request.getParameter("lname")).thenReturn("Olololevich");
		when(request.getParameter("captcha")).thenReturn("123");
		when(request.getSession().getAttribute("randomNum")).thenReturn(123);
		when(request.getParameter("pass")).thenReturn("passwordaaa");
		when(request.getParameter("cpass")).thenReturn("passwordaaa");
		when(request.getParameter("login")).thenReturn("loginaaa");
		when(request.getParameter("email")).thenReturn("some@mail.ru");
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(anyString());
	}

	@Test
	public void testDoPostWithInvalidCaptcha() throws ServletException, IOException {
		when(request.getParameter("captcha")).thenReturn("123");
		when(request.getSession().getAttribute("randomNum")).thenReturn(233);
		servlet.doPost(request, response);
		verify(request).getRequestDispatcher(anyString());
	}
}
