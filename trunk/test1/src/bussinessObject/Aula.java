/**
 * 
 */
package bussinessObject;

import java.util.ArrayList;
import java.util.List;

import util.MyLogger;

/**
 * @author ALFA403
 *
 */
public class Aula {
	private MyLogger logger;
	
	private List<Persona>alunnoList;
	private List<Docente>docenteList;
	
	/**
	 * 
	 */
	public Aula() {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.info(metodo, "Aula");
		alunnoList = new ArrayList<Persona>();
		docenteList = new ArrayList<Docente>();
		logger.end(metodo);
	}

	public void addNewAlunno(Persona alunno){
		final String metodo="addNewAlunno";
		logger.start(metodo);
		alunnoList.add(alunno);

		logger.end(metodo);
	}

	public void addNewDocente(Docente docente){
		final String metodo="addNewAlunno";
		logger.start(metodo);
		docenteList.add(docente);

		logger.end(metodo);
	}
}
