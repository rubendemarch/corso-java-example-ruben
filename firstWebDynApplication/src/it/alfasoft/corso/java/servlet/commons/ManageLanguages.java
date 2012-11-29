package it.alfasoft.corso.java.servlet.commons;

import it.alfasoft.corso.java.servlet.RootServlet;
import it.alfasoft.corso.java.util.constants.Request;
import it.alfasoft.corso.java.util.constants.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageLanguages
 */
@WebServlet("/ManageLanguages")
public class ManageLanguages extends RootServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see RootServlet#RootServlet()
     */
    public ManageLanguages() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.getSession().setAttribute(Session.LANG, new Locale(request.getParameter("language")));
		//ricarica la nuova lingua
		List<String>resources=new ArrayList<String>();
		resources.add("test");
		resources.add("testa");
		loadLanguage(request, resources);
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
