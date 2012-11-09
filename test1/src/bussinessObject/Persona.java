/**
 * 
 */
package bussinessObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import util.MyLogger;

/**
 * @author Dr
 * 
 */
public class Persona {
	private MyLogger logger; //questo logger lo usa solo persona, e non alunno, perché private
//in questo modo vediamo la classe specifica che l'ha loggato
	protected String userID;
	private String nome;
	private String cognome;
	private Calendar dataNascita;
	private Sesso sesso;
	private String cf;
	private String statoNascita;
	private String codStatoNascita;
	private String comuneNascita;
	private String codComuneNascita;
	private String email;
	private String recTel;


	/**
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
	 */
	public Persona(String nome, String cognome, Calendar dataNascita,
			Sesso sesso, String cf, String statoNascita,
			String codStatoNascita, String comuneNascita,
			String codComuneNascita, String email) {
		super();
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.info(metodo, "Persona");
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.cf = cf;
		this.statoNascita = statoNascita;
		this.codStatoNascita = codStatoNascita;
		this.comuneNascita = comuneNascita;
		this.codComuneNascita = codComuneNascita;
		this.email = email;
		logger.end(metodo);
	}

	public Persona(String userID, String nome, String cognome,
			Calendar dataNascita, Sesso sesso, String cf, String statoNascita,
			String codStatoNascita, String comuneNascita,
			String codComuneNascita, String email) {
		this(nome, cognome,
				 dataNascita,  sesso,  cf,  statoNascita,
				 codStatoNascita,  comuneNascita,
				 codComuneNascita, email);
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		this.userID = userID;
		logger.end(metodo);
	}



	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return the dataNascita
	 */
	public Calendar getDataNascita() {
		return dataNascita;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	/**
	 * @return the sesso
	 */
	public Sesso getSesso() {
		return sesso;
	}

	/**
	 * @return the recTel
	 */
	public String getRecTel() {
		return recTel;
	}

	/**
	 * @param recTel the recTel to set
	 */
	public void setRecTel(String recTel) {
		this.recTel = recTel;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the cf
	 */
	public String getCf() {
		return cf;
	}

	/**
	 * @return the statoNascita
	 */
	public String getStatoNascita() {
		return statoNascita;
	}

	/**
	 * @return the codStatoNascita
	 */
	public String getCodStatoNascita() {
		return codStatoNascita;
	}

	/**
	 * @return the codComuneNascita
	 */
	public String getCodComuneNascita() {
		return codComuneNascita;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the dataNascita
	 */
	public int getEta() {
		return Calendar.getInstance().get(Calendar.YEAR)-
				dataNascita.get(Calendar.YEAR);
	}
	

	public String creaID() {
		/**
		 * @author RDM
		 * @return se la classe ha lo user id returna l'id, altrimenti lo genera e lo restituisce
		 */
		logger=new MyLogger(this.getClass());
		final String metodo="creaID";
		logger.start(metodo);
		if(userID==null){
					userID=new StringBuilder(cf)
			.append(
					new SimpleDateFormat("YYYYMMddHHmmssSSS")
					.format(
							Calendar.getInstance().getTime()))
			.append(new Random().nextInt(999)).toString();

	}
		logger.info(metodo , userID);
		logger.end(metodo);
		return userID;
		
	}
	
	public String toString(){
		return userID;
	}
/*	public void finalize(){
		
	}*/
}

