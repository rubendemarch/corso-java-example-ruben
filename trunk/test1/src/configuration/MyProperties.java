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
import util.cript.MyCript;
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

	public String getProperty(String key) throws Config{
		final String metodo="getProperty";
		logger.start(metodo);
		// inizializzazione
		init();
		//fine inizializzazione
		String value=properties.getProperty(key);//ritorna null se il file non è nè xml nè properties,
		if(value==null){// o se non trova la chiave(perché il file è criptato ;) )
			value=properties.getProperty(MyCript.encrypt(key));
			value= MyCript.decrypt(value);
		}
		logger.end(metodo);
		return value;
	}
	
	private void init() throws ConfigFileNotFound, ConfigFileNotValid{
		final String metodo="init()getProperty";
		if(properties==null && inputStream==null){//se il properties non è valorizzato devo creare il canale, altrimenti il canale non serve
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
	
	public void setProperty(String key, String value , Boolean writeEncripted){
		if(properties==null){
			properties= new Properties();
		}
		if(writeEncripted) {
			properties.setProperty(MyCript.encrypt(key), MyCript.encrypt(value));
		}
		else {//cioè è vero (!isCript), non li voglio criptati
			properties.setProperty(key, value);
		}
		/*metodo spiccio per scrivere con meno righe(=>probabilità minore di mettere bachi)
		 * properties.setProperty(writeEncripted?MyCript.encrypt(key):key,
		 * 							writeEncripted?MyCript.encrypt(value):value)
		 */
	}
}
