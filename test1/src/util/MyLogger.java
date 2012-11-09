/**
 * 
 */
package util;


import org.apache.log4j.Logger;

/**
 * @author Dr
 *
 */
public class MyLogger {
	private Logger logger;
	
	private final static String START="START";
	private final static String END="END";
	/**
	 * 
	 */
	public MyLogger(@SuppressWarnings("rawtypes") Class clazz) {
		logger=Logger.getLogger(clazz);
	}

	public void fatal(String metodo, String message){
		logger.fatal(StringFormat.formatMessage(metodo, message));
	}
	public void fatal(String metodo, String message, Exception e){
		logger.fatal(StringFormat.formatMessage(metodo, message),e);
	}

	public void error(String metodo, String message){
		logger.error(StringFormat.formatMessage(metodo, message));
	}
	public void error(String metodo, String message, Exception e){
		logger.error(StringFormat.formatMessage(metodo, message),e);
	}

	public void warn(String metodo, String message){
		logger.warn(StringFormat.formatMessage(metodo, message));
	}
	public void warn(String metodo, String message, Exception e){
		logger.warn(StringFormat.formatMessage(metodo, message),e);
	}

	public void info(String metodo, String message){
		logger.info(StringFormat.formatMessage(metodo, message));
	}
	public void info(String metodo, String message, Exception e){
		logger.info(StringFormat.formatMessage(metodo, message),e);
	}

	public void debug(String metodo, String message){
		logger.debug(StringFormat.formatMessage(metodo, message));
	}
	public void debug(String metodo, String message, Exception e){
		logger.debug(StringFormat.formatMessage(metodo, message),e);
	}

	public void trace(String metodo, String message){
		logger.trace(StringFormat.formatMessage(metodo, message));
	}
	public void trace(String metodo, String message, Exception e){
		logger.trace(StringFormat.formatMessage(metodo, message),e);
	}

	public void start(String metodo){
		logger.trace(StringFormat.formatMessage(metodo,START));
	}
	public void end(String metodo){
		logger.trace(StringFormat.formatMessage(metodo,END));
	}
}
