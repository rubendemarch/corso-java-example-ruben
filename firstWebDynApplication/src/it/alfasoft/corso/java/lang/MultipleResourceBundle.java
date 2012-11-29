/**
 * 
 */
package it.alfasoft.corso.java.lang;

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
	

	/**
	 * @param locale
	 * @param bundles
	 */
	public MultipleResourceBundle(Locale locale, List<String> bundles) {
		super();
		//this.locale = locale;
		this.bundles = new ArrayList<ResourceBundle>();
		for (String bundle : bundles) {
			this.bundles.add(ResourceBundle.getBundle(bundle, locale));
		}
//		initKeys();
	}

//	private void initKeys() {
//		List<String>keys=new ArrayList<String>();
//		for (ResourceBundle bundle : bundles) {
//			while(bundle.getKeys().hasMoreElements())
//				keys.add(bundle.getKeys().nextElement());
//			}
//		
//		this.keys=Collections.enumeration(keys);
//	}
	
	/* (non-Javadoc)
	 * @see java.util.ResourceBundle#getKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
//			return keys;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject(String key) {
		Object val = null;
		for (ResourceBundle bundle : bundles) {
				try {
					val=bundle.getObject(key);//=>deve esserci una sola chiave key
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(val!=null){break;}
		}
		return val;
	}

}
