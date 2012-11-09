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
public class Alunno extends Persona {
	private MyLogger logger;
	private Calendar dataIscrizione;
	private TitoloDiStudio titoloDiStudio;
	/**
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
	 * @param dataIscrizione
	 * @param titoloDiStudio
	 */
	public Alunno(String nome, String cognome, Calendar dataNascita,
			Sesso sesso, String cf, String statoNascita,
			String codStatoNascita, String comuneNascita,
			String codComuneNascita, String email, Calendar dataIscrizione,
			TitoloDiStudio titoloDiStudio) {
		super(nome, cognome, dataNascita, sesso, cf, statoNascita,
				codStatoNascita, comuneNascita, codComuneNascita, email);
		this.dataIscrizione = dataIscrizione;
		this.titoloDiStudio = titoloDiStudio;
	}

	/**
	 * 
	 * @param userID
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
	 * @param dataIscrizione
	 * @param titoloDiStudio
	 */
	public Alunno(String userID, String nome, String cognome, Calendar dataNascita,
			Sesso sesso, String cf, String statoNascita,
			String codStatoNascita, String comuneNascita,
			String codComuneNascita, String email, Calendar dataIscrizione,
			TitoloDiStudio titoloDiStudio) {
		this(nome, cognome, dataNascita, sesso, cf, statoNascita,
				codStatoNascita, comuneNascita, codComuneNascita, email, dataIscrizione, titoloDiStudio);
		this.userID=userID;
	}
	
/**
 
	 * 
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param dataIscrizione
	 * @param titoloDiStudio
	 */
	/*public Alunno(String nome,
					String cognome,
					Calendar dataNascita,
					Sesso sesso,
					String comuneNascita,
					Calendar dataIscrizione,
					TitoloDiStudio titoloDiStudio
					) {
		super(nome,cognome,comuneNascita, dataNascita,sesso, comuneNascita, comuneNascita, comuneNascita, comuneNascita, comuneNascita, comuneNascita);
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.info(metodo, "Alunno");
		this.dataIscrizione=dataIscrizione;
		this.titoloDiStudio=titoloDiStudio;
		logger.end(metodo);
	}*/


	/**
	 * @return the dataIscrizione
	 */
	public Calendar getDataIscrizione() {
		return dataIscrizione;
	}

	/**
	 * @return the titoloDiStudio
	 */
	public TitoloDiStudio getTitoloDiStudio() {
		return titoloDiStudio;
	}
	


}
