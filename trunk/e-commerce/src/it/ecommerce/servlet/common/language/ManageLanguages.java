package it.ecommerce.servlet.common.language;

import it.ecommerce.servlet.RootServlet;
import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.log.MyLogger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
	private MyLogger log;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageLanguages() {
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
		initProcess(request);
		if(!StringUtils.isEmpty(request.getParameter(Common.CUSTOM_ACTION))){
			updateLanguages(request, response);
		}
		SqlSession sql = sqlSessionFactory.openSession();
		//3 carico la lista delle lingue gestite
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> managedLanguages =(List<HashMap<String, Object>>)(List<?>)sql.selectList("Language.list");
		sql.close();//non devo più fare query e quindi chiudo
		request.setAttribute(Request.MANAGED_LANGUAGES, managedLanguages);
		request.getSession().setAttribute(Request.MANAGED_LANGUAGES, managedLanguages);//si salva una copia della sessione
		//4 carico la lista delle lingue da gestire
		
		List<Locale>toManage=new ArrayList<Locale>();
		boolean find;
		for (String locale : Locale.getISOLanguages()) {
			find=false;
			for (HashMap<String, Object> managedLanguage : managedLanguages) {
				if(locale.equals((String)managedLanguage.get("ID_LANGUAGE"))){
					find=true;
					break;
				}
			}
				if(!find){
					toManage.add(new Locale(locale));
				}
		}
		request.setAttribute(Request.TO_MANAGE_LANGUAGES, toManage);
		dispatch(request, response);
		log.end(metodo);
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
			toManage.put("ID_LANGUAGE", id_language);
			toManage.put("IS_VISIBLE", false);//abbiamo scelto che di default la visibilità è false
			sql.insert("Language.add", toManage);
			}
			//2 aggiorna lo stato di visibilità delle lingue gestite
		/*	List<String>parameters =Collections.list(request.getParameterNames());
			HashMap<String, Object> toManage=new HashMap<String, Object>();*/
			List<HashMap<String, Object>> managedLanguages =
					(List<HashMap<String, Object>>)request.getSession()
						.getAttribute(Request.MANAGED_LANGUAGES);
			for (HashMap<String, Object> managedLanguage : managedLanguages) {
				if(request.getParameter((String)managedLanguage.get("ID_LANGUAGE")) != null){//cioé nella pagina il valore è checckato e quindi viene mandato come parametro
					if(((BigDecimal)managedLanguage.get("IS_VISIBLE")).intValue()==0){//devo fare update solo se la lingua è invisibile
						managedLanguage.put("IS_VISIBLE", true);
						sql.update("Language.update",managedLanguage);
					}
				}else{//cioé nella pagina il valore non è checckato e quindi NON viene mandato come parametro
					if(((BigDecimal)managedLanguage.get("IS_VISIBLE")).intValue()==1){//devo fare update solo se la lingua è visibile
						managedLanguage.put("IS_VISIBLE", false);
						sql.update("Language.update",managedLanguage);
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
