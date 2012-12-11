package it.ecommerce.servlet.common.brands;

import it.ecommerce.servlet.RootServlet;
import it.ecommerce.util.KeyGenerator;
import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.log.MyLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 * Servlet implementation class ManageBrand
 */

public class ManageBrand extends RootServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;
	/**
	 * @see RootServlet#RootServlet()
	 */
	public ManageBrand() {
		super();
		log = new MyLogger(this.getClass());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="doGet";
		log.start(metodo);
		process(request, response);
		log.end(metodo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="doPost";
		log.start(metodo);
		process(request, response);
		log.end(metodo);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="process";
		log.start(metodo);
		loadLanguage(request);
		String action = request.getParameter(Common.ACTION);//va a prendere il value del form hidden
		if("inserisci".equals(action)){
			ResourceBundle rb = (ResourceBundle) request.getAttribute(Request.ResourceBundle);
			request
				.setAttribute(
							"msg",
							(insertNewBrand(request))?
									rb.getString("salvataggio.ok"):
									rb.getString("salvataggio.ko")
							);
		}
		request
		.getRequestDispatcher("jsp/manage/brands/insertBrand.jsp")
			.forward(request, response);
	
		log.end(metodo);
	}
	
	private boolean insertNewBrand(HttpServletRequest request){
		final String metodo="insertNewBrand";
		log.start(metodo);
		
		SqlSession sql = sqlSessionFactory.openSession(TransactionIsolationLevel.READ_COMMITTED);
		int rowsAffected =0;
		try {
			HashMap<String, Object> brand = new HashMap<String, Object>();
			brand.put("ID_BRAND",
					KeyGenerator.keyGen(sql, "ID_BRAND", "brands", "B"));
			brand.put("IS_VISIBLE", true);
			brand.put("URL", null);//da inserire successivamente tramite update
			brand.put("LOGO_URL", null);//da inserire successivamente tramite update
			brand.put("NAME", request.getParameter("name"));
			brand.put("IS_DELETED", false);
			rowsAffected = sql.insert("brand.add", brand);
			
			sql.commit();
		} catch (Exception e) {
			log.error(metodo, "Sessione: "+request.getSession().getId(), e);
			sql.rollback();
		} finally{
			sql.close();
			log.end(metodo);
		}
		return rowsAffected>0;
		
	}
}
