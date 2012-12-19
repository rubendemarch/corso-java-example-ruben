/*package test;

import thread.ThreadCombiRicorsivo;
import businessObject.ContenitorePasswords;
import businessObject.Range;
import businessObject.RangeList;

public class StartCombinatoreRicorsivo {


	public static void main(String[] args) {
		
		
		ContenitorePasswords chiave = new ContenitorePasswords();
		
		System.out.println("*********************************************");
		
		RangeList numerico = new RangeList(new Range (48,57));
		
		ThreadCombiRicorsivo ricorsivoNumerico = new ThreadCombiRicorsivo("ricorsivoNumerico",
				             numerico,5 ,chiave);
				      
		RangeList alfabeticoMaiuscolo = new RangeList(new Range (65, 90));
		
		ThreadCombiRicorsivo ricorsivoMaiuscolo = new ThreadCombiRicorsivo("ricorsivoAlfabeticoMaiuscolo",
				             alfabeticoMaiuscolo,5 ,chiave);
		
		RangeList alfabeticoMinuscolo = new RangeList(new Range(97, 122));
		
		ThreadCombiRicorsivo ricorsivoMinuscolo = new ThreadCombiRicorsivo("ricorsivoAlfabeticoMinuscolo",
				             alfabeticoMinuscolo,5 ,chiave);
		
		RangeList alfabetoCompleto = new RangeList(new Range (65, 90));
		alfabetoCompleto.addRange(new Range (97, 122));
		
		ThreadCombiRicorsivo ricorsivoCompleto = new ThreadCombiRicorsivo("ricorsivoAlfabetoCompleto", 
							 alfabetoCompleto,5, chiave);
		
		RangeList alfaNumerico = new RangeList (new Range(48, 57));
		alfaNumerico.addRange(new Range(65, 90));
		alfaNumerico.addRange(new Range (97, 122));
		
		ThreadCombiRicorsivo ricorsivoAlfaNumerico = new ThreadCombiRicorsivo("ricorsivoAlfANumerico",
				             alfaNumerico, 5, chiave);
		
		System.out.println("*********************************************");
	}

}*/
