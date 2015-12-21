package test.java.servlets;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Matchers.*;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.filter.RegexFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.servlets.CaptchaServlet;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaServletTest {

	private CaptchaServlet servlet = new CaptchaServlet();

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
	@Before
	public void init() throws IOException {
		when(request.getServletContext()).thenReturn(context);
		when(request.getServletContext().getMimeType(anyString())).thenReturn("image/jpeg");
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getId()).thenReturn("123");
		when(response.getOutputStream()).thenReturn(outputStream);
	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		System.out.println(request.getSession().getId());
		
		servlet.doGet(request, response);

	}
}
