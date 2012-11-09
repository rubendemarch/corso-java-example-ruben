/**
 * 
 */
package bussinessObject;

import java.util.Calendar;

import util.MyLogger;


/**
 * @author Dr
 *
 */
public class Docente extends Persona {
	private MyLogger logger;
	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param cf
	 * @param statoNascita
	 * @param codStatoNascita
	 * @param comuneNascita
	 * @param codComuneNascita
	 * @param email
	 */
	 
	public Docente(String nome, String cognome, Calendar dataNascita,
			Sesso sesso, String cf, String statoNascita,
			String codStatoNascita, String comuneNascita,
			String codComuneNascita, String email) {
		super(nome, cognome, dataNascita,
				 sesso,  cf,  statoNascita,
				codStatoNascita, comuneNascita,
				 codComuneNascita,  email );
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.info(metodo, "Docente");

		logger.end(metodo);
	}
	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param cf
	 * @param statoNascita
	 * @param codStatoNascita
	 * @param comuneNascita
	 * @param codComuneNascita
	 * @param email
	 */
	 
	public Docente(String userID, String nome, String cognome, Calendar dataNascita,
			Sesso sesso, String cf, String statoNascita,
			String codStatoNascita, String comuneNascita,
			String codComuneNascita, String email) {
		super(userID, nome, cognome, dataNascita,
				 sesso,  cf,  statoNascita,
				codStatoNascita, comuneNascita,
				 codComuneNascita,  email );
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.info(metodo, "Docente");
		
		logger.end(metodo);
	}
}
