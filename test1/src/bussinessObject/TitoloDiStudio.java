/**
 * 
 */
package bussinessObject;

import util.MyLogger;

/**
 * @author Dr
 *
 */
public class TitoloDiStudio {
	private MyLogger logger;
	private String tipo;

	/**
	 * 
	 */
	public TitoloDiStudio(String tipo) {
		super();
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		this.setTipo(tipo);
		logger.end(metodo);
	}


	/**
	 * 
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}