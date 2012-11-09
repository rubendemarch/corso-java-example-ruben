/**
 * 
 */
package example;

import java.util.Calendar;

import bussinessObject.Persona;
import bussinessObject.Sesso;

/**
 * @author Dr
 *
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i=0;
	while (i<12) {
	i++;
		
		Persona p = new Persona( "nome", "cognome", Calendar.getInstance(),
				Sesso.F, "cf", "statoNascita",
				"codStatoNascita", "comuneNascita",
				"codComuneNascita", "email");
		p.creaID();
	}
/*		Alunno a = new Alunno("nome",
				"cognome",
				Calendar.getInstance(),
				Sesso.MASCHIO,
				"comune", Calendar.getInstance(),
				new TitoloDiStudio("titoloDiStudio"));
		Docente d = new Docente("nome",
				"cognome",
				Calendar.getInstance(),
				Sesso.FEMMINA, "comune");
		*/
	}

}
