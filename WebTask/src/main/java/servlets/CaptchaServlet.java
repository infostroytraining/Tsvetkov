package main.java.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class CaptchaServlet
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String captchaName = "/".concat(request.getSession().getId().concat(".jpg"));
		InputStream is = new FileInputStream(new File("D://captcha//".concat(captchaName)));
	//	String str = getServletContext().getMimeType(captchaName);
		byte[] mass = new byte[is.available()];
		is.read(mass);
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		os.write(mass);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
