package it.alfasoft.corso.java.servlet;

import it.alfasoft.corso.java.lang.MultipleResourceBundle;
import it.alfasoft.corso.java.util.constants.Request;
import it.alfasoft.corso.java.util.constants.Session;
import it.alfasoft.corso.java.util.log.MyLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class RootServlet
 */
@WebServlet(description = "RootServlet", urlPatterns = { "/RootServlet" })
public class RootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RootServlet() {
		super();
		log = new MyLogger(this.getClass());
		final String metodo = "costruttore";
		log.start(metodo);

		// .....

		log.end(metodo);
	}

//	 /**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	 public void init(ServletConfig config) throws ServletException {
//	 final String metodo="init";
//	 log.start(metodo);
//	 super.init(config);
//	 log.end(metodo);
//	 }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO CONNESSIONE
	}

	protected Locale getLocale(HttpServletRequest request) {
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
			/*hasMoreElements() e nextElement() ciclano all'infinito
			 * while(request.getLocales().hasMoreElements()){
				l=request.getLocales().nextElement();
				if(language.contains(
						l.getDisplayLanguage())){
					request.getSession().setAttribute(Session.LANG, l);
				}
			}*/

		}
		//TODO verifica se deve caricare una lingua salvata nel profilo
		log.end(metodo);
		return (Locale) request.getSession().getAttribute(Session.LANG);
	}

	protected void loadLanguage(HttpServletRequest request,
			List<String> resources// sono i nomi di tutti i file di properties che ci servono
			) {
		Locale locale = getLocale(request);
		request.setAttribute(Request.ResourceBundle,
				new MultipleResourceBundle(locale, resources));
		
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

	// Metodi che non sovrascriviamo rispetto a quelli della madre
	// /**
	// * @see Servlet#getServletConfig()
	// */
	// public ServletConfig getServletConfig() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * @see Servlet#getServletInfo()
	// */
	// public String getServletInfo() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /**
	// * @see HttpServlet#service(HttpServletRequest request,
	// HttpServletResponse
	// * response)
	// */
	// protected void service(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// }
	//
	// /**
	// * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	// * response)
	// */
	// protected void doGet(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }
	//
	// /**
	// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	// * response)
	// */
	// protected void doPost(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }
	//
	// /**
	// * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	// */
	// protected void doPut(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }
	//
	// /**
	// * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	// */
	// protected void doDelete(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }
	//
	// /**
	// * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	// */
	// protected void doHead(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }
	//
	// /**
	// * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	// */
	// protected void doOptions(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }
	//
	// /**
	// * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	// */
	// protected void doTrace(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// }

}
