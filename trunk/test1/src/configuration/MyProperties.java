/**
 * 
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import util.MyLogger;

/**
 * @author ALFA403
 *
 */
public class MyProperties {

	private MyLogger logger;
	private Properties properties=null;
	private FileInputStream inputStream=null; // fa come lo streaming, non lo prende tutto in un colpo
	private FileOutputStream outputStream=null;
	private String pathFile;//deve esserci, per aprire la connessione al file xml
	/**
	 * 
	 */
	public MyProperties(String pathFile) {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		this.pathFile=pathFile;
		
		logger.end(metodo);
	}

	public String get(String key) throws Exception{
		final String metodo="get";
		if(inputStream==null){
			try {
				inputStream=new FileInputStream(pathFile);
			} catch (FileNotFoundException e) {//non c'è il file xml con username e password, quindi non possiamo fare un cavolo
				logger.fatal(metodo, "tentativo connessione al file di properties", e);
				throw new Exception("file di configurazione non trovato");//solleva una config exception
			}
		}
		if(properties==null){
			properties=new Properties();
			if(pathFile.toUpperCase().endsWith(".XML")){
				properties.loadFromXML(inputStream);
			}else if (pathFile.toUpperCase().endsWith(".PROPERTIES")) {
				properties.load(inputStream);
			}
		}
		return properties.getProperty(key);
	}
}
