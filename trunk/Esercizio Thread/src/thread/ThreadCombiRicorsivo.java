package thread;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.DateTime;

import utils.Date_Utils;
import businessObject.ContenitorePasswords;
import businessObject.Range;
import businessObject.RangeList;



public class ThreadCombiRicorsivo implements  Runnable{

	private Thread runner;
	private RangeList rangeList;
	private Calendar calendario;
	private DateTime start;
	private DateTime end;
	private int numDischi;
	private ContenitorePasswords chiave; 
	
	public ThreadCombiRicorsivo(String threadName,RangeList rangeList,int numDischi,
			                    ContenitorePasswords chiave)
	{
		this.numDischi = numDischi;//numero di dischi di caratteri maiuscolo,minuscolo,numerico
		this.chiave = chiave;
		runner = new Thread(this, threadName); 
		calendario = Calendar.getInstance();
		start = DateTime.now();//DATA quando parte il processo
		System.out.println(Date_Utils.formatDate(calendario) + runner.getName() + " [Avviato]");
		this.rangeList = rangeList;
		runner.start();
	}
	
	
	public void run() 
	{
		System.out.println(Thread.currentThread() + " [Elaborazione iniziata]");
		
		if (getCombinazione(new ArrayList<String>(), numDischi,
			rangeList, chiave) != null)//lista con un solo elemento
		{
			
		}
		else
		{
			//non ha trovato un sega
		}
		
		System.out.println(Thread.currentThread() + " [Thread Terminato]");
	}
	
	public List<String> getCombinazione(List<String> combinazioniList, int numDischi,
			RangeList rangeList, ContenitorePasswords chiave){
		
		//creo lista, da quella passata come parametro
		List<String> myCombiList = new ArrayList<String> (combinazioniList);
		
		//se è vuota vado a generare
		if (combinazioniList.isEmpty())
		{
			for (Range r : rangeList.getRangeList()) 
			{
				for (int i = r.getStart(); i <= r.getEnd(); i++) {
					myCombiList.add(""+(char) i);
					
					//oggetto ContenitorePasswords(chiave) passato come paramentro
					if (chiave.isCorrectPass(""+(char) i))
					{
						System.out.println(Thread.currentThread() +
								           " ha trovato la chiave: [" +
								           (""+(char) i) + "]");
						List<String> ret = new ArrayList<String>();
						ret.add(""+(char) i);
						return ret;
					}
				}
			}
		}
		else
		{

			for (Range r : rangeList.getRangeList()) 
			{
				for (int i = r.getStart(); i <= r.getEnd(); i++)
				{
					for (String c : combinazioniList) 
					{
						myCombiList.add(c += ((char) i));
						
						//oggetto ContenitorePasswords(chiave) passato come paramentro
						if (chiave.isCorrectPass(c))
						{
							System.out.println(Thread.currentThread() +
									           " ha trovato la chiave: [" +
									           c + "]");
							List<String> ret = new ArrayList<String>();
							ret.add(c);
							return ret;
						}
					}
				}
			}
		}
		
		if (numDischi > 1)
		{
			/*dando -1 al numero di dischi passati, viene richiamata la funzione 
			  su se stessa(ricorsione)*/
			System.out.println(Thread.currentThread() + " Aggiunge un disco di valori");
			return getCombinazione(myCombiList, numDischi - 1, rangeList, chiave);
		}
		else
		{
			System.out.println(Thread.currentThread() + " Terminato senza successo");
			return null;//non ha trovato un cacchio
		}
			
		
	}

	
	
	
	
}
