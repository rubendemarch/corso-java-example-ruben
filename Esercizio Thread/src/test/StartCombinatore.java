package test;

import net.lingala.zip4j.exception.ZipException;
import businessObject.AreaComune;
import businessObject.ContenitorePasswords;
import businessObject.Range;
import businessObject.RangeList;
import thread.ThreadCombinatore;




public class StartCombinatore {

	
	public static void main(String[] args) {
		
		String path = "C:/TESTth/Provaci.zip";
		
		
		ContenitorePasswords c = new ContenitorePasswords( "main");
			
		
		if (c!= null && c.isEncrypted())
		{
			AreaComune ac = new AreaComune();
			
			System.out.println("*****************************************");
			
			//String chiave = "11111";
			
			
			
			/////////////////////////////////////////////////////////////////////////
			
			RangeList numerico = new RangeList(new Range (0, 0), true, null );
			numerico.addRange(new Range(48,57));
			
			ThreadCombinatore tredNumerico = new ThreadCombinatore("threadNumerico",
										     numerico, new ContenitorePasswords("threadNumerico"), ac);
			
			/////////////////////////////////////////////////////////////////////////
			
			RangeList alfabeticoMaiuscolo = new RangeList(new Range (0, 0), true, null);
			alfabeticoMaiuscolo.addRange(new Range(65, 90));
			
			ThreadCombinatore tredAlfaBeticoMaiusc = new ThreadCombinatore("threadAlfabeticoMaiusc",
											         alfabeticoMaiuscolo, new ContenitorePasswords( "threadAlfabeticoMaiusc"),
											         ac);
			
			//////////////////////////////////////////////////////////////////////////
			
			RangeList alfabeticoMinuscolo = new RangeList(new Range(0, 0), true, null);
			alfabeticoMinuscolo.addRange(new Range (97, 122));
			
			ThreadCombinatore tredAlfabeticoMinusc = new ThreadCombinatore("threadAlfabeticoMinusc",
													 alfabeticoMinuscolo, new ContenitorePasswords( "threadAlfabeticoMinusc"),
													 ac);
			
			///////////////////////////////////////////////////////////////////////////
			
			RangeList alfabetoCompleto = new RangeList(new Range (0, 0), false, ("([a-z]+)|([A-Z]+)"));
			alfabetoCompleto.addRange(new Range (65, 90));
			alfabetoCompleto.addRange(new Range (97, 122));
			
			ThreadCombinatore tredAlfabetoCompleto = new ThreadCombinatore("threadAlfabetoCompleto", 
												     alfabetoCompleto, new ContenitorePasswords( "threadAlfabetoCompleto"),
												     ac);
			
			////////////////////////////////////////////////////////////////////////////
			
			RangeList alfaNumerico = new RangeList (new Range(0, 0),false, ("([a-z]+)|([A-Z]+)|([0-9])"));
			alfaNumerico.addRange(new Range (48, 57));
			alfaNumerico.addRange(new Range (65, 90));
			alfaNumerico.addRange(new Range (97, 122));
			
			ThreadCombinatore tredAlfaNum = new ThreadCombinatore("threadAlfaNumerico",
											alfaNumerico, new ContenitorePasswords( "threadAlfaNumerico"),
											ac);
			
			/////////////////////////////////////////////////////////////////////////////
			
			RangeList simbolico = new RangeList(new Range (0, 0), true, "(([a-z]+)|([A-Z]+)|([0-9]+)|([^\\w]+))");
			simbolico.addRange(new Range (33, 47));
			simbolico.addRange(new Range (58, 64));
			simbolico.addRange(new Range(91, 96));
			simbolico.addRange(new Range(123, 126));
			simbolico.addRange(new Range(128, 239));

			
			ThreadCombinatore tredSimbolico = new ThreadCombinatore("threaSimbolico",
					                          simbolico, new ContenitorePasswords( "threaSimbolico"),
					                          ac);
			
			///////////////////////////////////////////////////////////////////////////////
			
			RangeList all = new RangeList(new Range(0, 0),true, "(([a-z]+)|([A-Z]+)|([0-9]+)|([\\w]+))");
			all.addRange(new Range (32, 126));
			
			ThreadCombinatore tredAll = new ThreadCombinatore("thredAll", all, new ContenitorePasswords( "thredAll"),
																ac);
			
			///////////////////////////////////////////////////////////////////////////////
			
			System.out.println("*****************************************");
			
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//System.out.println(Thread.currentThread());
			
		
			
		}else{
			System.out.println("Non è criptato");
		}
	}

}
