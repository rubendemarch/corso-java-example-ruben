package it.ecommerce.servlet.common.language;

import it.ecommerce.servlet.RootServlet;
import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

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
		loadLanguage(request);
		String action = request.getParameter(Common.ACTION);
		if(!StringUtils.isEmpty(action)){
			updateLanguages(request, response);
		}
		SqlSession sql = sqlSessionFactory.openSession();
		//3 carico la lista delle lingue gestite
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> managedLanguages =(List<HashMap<String, Object>>)(List<?>)sql.selectList("language.list");
		sql.close();//non devo pi� fare query e quindi chiudo
		request.setAttribute(Request.MANAGED_LANGUAGES, managedLanguages);
		request.getSession().setAttribute(Request.MANAGED_LANGUAGES, managedLanguages);//si salva una copia della sessione
		//4 carico la lista delle lingue da gestire
		
		List<Locale>toManage=new ArrayList<Locale>();
		boolean find;
		for (String locale : Locale.getISOLanguages()) {
			find=false;
			for (HashMap<String, Object> managedLanguage : managedLanguages) {
				if(locale.equals((String)managedLanguage.get("id_language"))){
					find=true;
					break;
				}
			}
				if(!find){
					toManage.add(new Locale(locale));
				}
		}
		request.setAttribute(Request.TO_MANAGE_LANGUAGES, toManage);
		
		request.getRequestDispatcher("jsp/manage/language/language.jsp").forward(request, response);
	}
	
	private synchronized void updateLanguages(HttpServletRequest request,
			HttpServletResponse response){
		SqlSession sql = sqlSessionFactory
				.openSession(TransactionIsolationLevel.READ_COMMITTED);
		String id_language = request.getParameter("toManage");
		try {
			//1 se hanno aggiunto una lingua la va a salvare nel db
			if(!"0000".equals(id_language)){
			HashMap<String, Object> toManage=new HashMap<String, Object>();
			toManage.put("id_language", id_language);
			toManage.put("is_visible", false);//abbiamo scelto che di default la visibilit� � false
			sql.insert("language.add", toManage);
			}
			//2 aggiorna lo stato di visibilit� delle lingue gestite
			List<String>parameters =Collections.list(request.getParameterNames());
			HashMap<String, Object> toManage=new HashMap<String, Object>();
			List<HashMap<String, Object>> managedLanguages =
					(List<HashMap<String, Object>>)request.getSession()
						.getAttribute(Request.MANAGED_LANGUAGES);
			for (HashMap<String, Object> managedLanguage : managedLanguages) {
				if(request.getParameter((String)managedLanguage.get("id_language")) != null){//cio� nella pagina il valore � checckato e quindi viene mandato come parametro
					if(((BigDecimal)managedLanguage.get("is_visible")).intValue()==0){//devo fare update solo se la lingua � invisibile
						managedLanguage.put("is_visible", true);
						sql.update("language.update",managedLanguage);
					}
				}else{//cio� nella pagina il valore non � checckato e quindi NON viene mandato come parametro
					if(((BigDecimal)managedLanguage.get("is_visible")).intValue()==1){//devo fare update solo se la lingua � visibile
						managedLanguage.put("is_visible", false);
						sql.update("language.update",managedLanguage);
					}
				}
			}
			sql.commit();
		} catch (Exception e) {
			sql.rollback();
		}
		sql.close();
	}
	
}
