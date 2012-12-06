package it.ecommerce.servlet;

import it.ecommerce.util.log.MyLogger;
import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.constants.Session;

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
				ResourceBundle.getBundle(Common.RESOURCE, locale));
		
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

}
