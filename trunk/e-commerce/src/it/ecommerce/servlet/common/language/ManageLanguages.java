package it.ecommerce.servlet.common.language;

import it.ecommerce.servlet.RootServlet;
import it.ecommerce.util.constants.Common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * Servlet implementation class ManageLanguages
 */
public class ManageLanguages extends RootServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageLanguages() {
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

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(Common.ACTION);
		if(!StringUtils.isEmpty(action)){
			//1 se hanno agginuto una lingua la va a salvare nel db
			//2 aggiorna lo stato di visibilità delle lingue gestite
		}
		SqlSession sql = sqlSessionFactory.openSession();
		//3 carico la lista delle lingue gestite
		List<HashMap<String, Object>> list =(List<HashMap<String, Object>>)(List<?>)sql.selectList("language.list");
		//4 carico la lista delle lingue da gestire
		for (String locale : Locale.getISOLanguages()) {
			//TODO
		}
		
	}
	
}
