package it.alfasoft.corso.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
//@WebServlet(description = "home", urlPatterns = { "/Home" })
/*urlPatterns pu� anche non essere l'url-pattern nel web-xml,
verranno letti entrambi allo stesso modo
MEGLIO CENTRALIZZARE SOLO NEL web.xml
*/
public class Home extends RootServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		List<String>resources=new ArrayList<String>();
		resources.add("test");
		resources.add("testa");
		loadLanguage(request, resources);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
