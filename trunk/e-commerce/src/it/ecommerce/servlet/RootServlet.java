package it.ecommerce.servlet;

import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.constants.Session;
import it.ecommerce.util.log.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class RootServlet
 */
public class RootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;
	protected SqlSessionFactory sqlSessionFactory = null;
	protected String urlSite;
	protected String realPath;
	protected String contextPath;
	protected String maxImageSize;
	
	protected String commonAction;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RootServlet() {
		super();
		log = new MyLogger(this.getClass());
		final String metodo = "costruttore";
		log.start(metodo);

		String resource = "mybatis/config/mybatis-config.xml";
		/*La configurazione di mybatis va bene qui perché
		 *  tanto lo uso solo in questo metodo (almeno per il momento)*/
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
		}
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			log.fatal(metodo, "Fallita SqlSessionFactoryBuilder", e);
		}

		log.end(metodo);
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		final String metodo="init";
		log.start(metodo);
		super.init(config);
		urlSite=(String) getServletContext().getInitParameter("urlSite");
		maxImageSize=(String) getServletContext().getInitParameter("maxImageSize");
		log.trace(metodo, urlSite);
		realPath=getServletContext().getRealPath("/");
		log.trace(metodo , realPath);
		contextPath=getServletContext().getContextPath();
		log.trace(metodo, contextPath);
		log.end(metodo);
	}

	
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO 
	}

	private Locale getLocale(HttpServletRequest request) {
		final String metodo="getLocale";
		log.start(metodo);
		if(request.getSession().getAttribute(Session.LANG)==null){

			String language= (String) getServletContext().getInitParameter("managedLanguages");
			log.info(metodo, language);
			
			List<Locale>locs=Collections.list(request.getLocales());
			//setta la lingua di default
			/*NB Come lingua di default viene data la lingua 
			 * nella quale è installato il server
			 */
			request.getSession().setAttribute(Session.LANG, request.getLocale());
			//controlla le lingue richieste e se c'è la sovrascrive
			for(Locale loc:locs){
				if(language.contains(loc.toString())){
					request.getSession().setAttribute(Session.LANG, loc);
					break;
				}
			}
		}
		//TODO verifica se deve caricare una lingua salvata nel profilo
		log.end(metodo);
		return (Locale) request.getSession().getAttribute(Session.LANG);
	}

	private void loadLanguage(HttpServletRequest request) {
		Locale locale = getLocale(request);
		String servletName = this.getClass().getName();
		request.setAttribute(Common.SERVLET_NAME,servletName);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(Common.RESOURCE, locale);
		request.setAttribute(Common.PAGE_TITLE, resourceBundle.getString(servletName));//carica il titolo in lingua ad ogni pagina che carica
		request.setAttribute(Request.ResourceBundle,resourceBundle);
		request.setAttribute(Request.LOCALE, locale);
		String language= (String) getServletContext().getInitParameter("managedLanguages");
		List<Locale>managedLanguages=new ArrayList<Locale>();
		StringTokenizer tok=
				new StringTokenizer(language, " ");
		while(tok.hasMoreTokens()){
			managedLanguages.add(new Locale(tok.nextToken()));
		}
		request.setAttribute(Request.managedLanguages, managedLanguages);

	}

	protected void initProcess(HttpServletRequest request){
		loadLanguage(request);
		commonAction = request.getParameter(Common.COMMON_ACTION);//va a prendere il value del form hidden
		request.setAttribute(Common.ACTION,commonAction);
		request.setAttribute(Common.maxImageSize,maxImageSize);
	}
	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request
			.getRequestDispatcher("jsp/common/root.jsp")//ci pensa questa jsp a caricare chi serve
				.forward(request, response);
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="process";
		log.start(metodo);
		initProcess(request);
		dispatch(request, response);
		log.end(metodo);
	}
}
