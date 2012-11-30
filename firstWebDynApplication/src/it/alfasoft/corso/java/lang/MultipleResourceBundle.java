/**
 * 
 */
package it.alfasoft.corso.java.lang;

import it.alfasoft.corso.java.util.log.MyLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author ALFA403
 *
 */
public class MultipleResourceBundle extends ResourceBundle {
	//private List<String> bundles;
	//private Locale locale;
	private List<ResourceBundle> bundles;
	private Enumeration<String>keys;
	private MyLogger log;

	/**
	 * @param locale
	 * @param bundles
	 */
	public MultipleResourceBundle(Locale locale, List<String> bundles) {
		super();
		log = new MyLogger(this.getClass());
		final String metodo="costruttore";
		log.start(metodo);
		//this.locale = locale;
		this.bundles = new ArrayList<ResourceBundle>();
		for (String bundle : bundles) {
			this.bundles.add(ResourceBundle.getBundle(bundle, locale));
		}
		initKeys();
		log.end(metodo);
	}

	private void initKeys() {
		final String metodo="initKeys";
		log.start(metodo);
		List<String>keys=new ArrayList<String>();
		List<String>bundleKeys;
		for (ResourceBundle bundle : bundles) {
			bundleKeys=Collections.list(bundle.getKeys());
			for(String bundleKey:bundleKeys)
				keys.add(bundleKey);
			}
		log.end(metodo);
//metodo vecchio che dava casini:
//		List<String>keys=new ArrayList<String>();
//		for (ResourceBundle bundle : bundles) {
//			while(bundle.getKeys().hasMoreElements())
//				keys.add(bundle.getKeys().nextElement());
//			}
//		
//		this.keys=Collections.enumeration(keys);
	}
	
	/* (non-Javadoc)
	 * @see java.util.ResourceBundle#getKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
//		final String metodo="getKeys";
//		log.start(metodo);
//		initKeys();
//
//		log.end(metodo);
		return keys;
	}
	
	/* (non-Javadoc)
	 * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject(String key) {
		final String metodo="handleGetObject";
		log.start(metodo);
		Object val = null;
		for (ResourceBundle bundle : bundles) {
				try {
					val=bundle.getObject(key);//=>deve esserci una sola chiave key
				} catch (Exception e) {
					log.trace(metodo, "la chiave "+ key + " non è nel file "+bundle.toString());
//					log.trace(metodo,  key + " not found");
				}
				if(val!=null){break;}
		}
		log.end(metodo);
		return val;
	}

}
