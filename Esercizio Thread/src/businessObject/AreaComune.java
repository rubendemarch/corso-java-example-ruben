package businessObject;

public class AreaComune {

	private boolean trovato = false;
	
	/*private boolean testNumberPw= true;
	private boolean searchNumeri= true;
	private boolean searchMaiuscolo= true;
	private boolean searchMinuscolo= true;
	private boolean searchAlfabetico= true;
	private boolean searchAlfabeticoNum= true;
	private boolean searchSimboli= true;*/
	

	/**
	 * @return the trovato
	 */
	public synchronized boolean isTrovato() {
		return trovato;
	}

	/**
	 * @param trovato the trovato to set
	 */
	public synchronized void setTrovato(boolean trovato) {
		this.trovato = trovato;
	}

	
	
	
	
	
	
	
	
}
