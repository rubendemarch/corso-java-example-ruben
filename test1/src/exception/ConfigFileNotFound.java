/**
 * 
 */
package exception;

/**
 * @author ALFA403
 * 
 * Abbiamo creato un'eccezione!! La lasciamo come creata di default, è solo x dare un nome
 */
public class ConfigFileNotFound extends Exception {

	/**
	 * 
	 */
	public ConfigFileNotFound() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ConfigFileNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ConfigFileNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConfigFileNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ConfigFileNotFound(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
