/**
 * 
 */
package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import util.MyLogger;
import exception.config.Config;
import exception.config.ConfigFileCreateEx;
import exception.config.ConfigFileNotFound;
import exception.config.ConfigFileNotValid;

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

	public String getPropertyValue(String key) throws Config{
		final String metodo="getPropertyValue";
		logger.start(metodo);
		if(inputStream==null){
			try {
				inputStream=new FileInputStream(pathFile);
			} catch (FileNotFoundException e) {//non c'è il file xml con username e password, quindi non possiamo fare un cavolo
				logger.fatal(metodo, "tentativo connessione al file di properties", e);
				throw new ConfigFileNotFound("file di configurazione non trovato");//solleva una config exception
			}
		}
		if(properties==null){
			properties=new Properties();
			if(pathFile.toUpperCase().endsWith(".XML")){
				try {
					properties.loadFromXML(inputStream);
				} catch (InvalidPropertiesFormatException e) {
					logger.fatal(metodo, "file corrotto", e);
					throw new ConfigFileNotValid("file corrotto");
				} catch (IOException e) {
					logger.fatal(metodo, "impossibile leggere file di properties.xml", e);
					throw new ConfigFileNotValid("impossibile leggere file di properties.xml");
				}
			}else if (pathFile.toUpperCase().endsWith(".PROPERTIES")) {
				try {
					properties.load(inputStream);
				} catch (IOException e) {
					logger.fatal(metodo, "impossibile leggere file di properties.properties", e);
					throw new ConfigFileNotValid("impossibile leggere file di properties.properties");
				}
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.warn(metodo, "fallito tentativo chiusura configInputStream", e);
			}
		}
		logger.end(metodo);
		return properties.getProperty(key);//ritorna null se il file non è nè xml nè properties
	}
	
	/**
	 * Scrive quello che gli compete nel file apposito
	 */
	public void writeConfigFile() throws Config{
		final String metodo="writeConfigFile";
		if(outputStream==null){
			try {
				outputStream=new FileOutputStream(pathFile);
			} catch (FileNotFoundException e) {
				logger.fatal(metodo, "impossibile esportare file di properties", e);
				throw new ConfigFileCreateEx("impossibile esportare file di properties");
			}
		}
		File f=new File(pathFile);
		
		if(pathFile.toUpperCase().endsWith(".XML")){
			try {
				properties.storeToXML(outputStream, f.getName());
			} catch (IOException e) {
				logger.fatal(metodo, "fallita costruzione file di properties.xml", e);
				throw new ConfigFileCreateEx("fallita costruzione file di properties.xml");
			}
		}else if(pathFile.toUpperCase().endsWith(".PROPERTIES")){
				try {
					properties.store(outputStream, f.getName());
				} catch (IOException e) {
					logger.fatal(metodo, "fallita costruzione file di properties.properties", e);
					throw new ConfigFileCreateEx("fallita costruzione file di properties.properties");
			}
		}
	}
}
