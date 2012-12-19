package thread;

import java.util.Calendar;

import org.joda.time.DateTime;

import utils.Date_Utils;

import businessObject.AreaComune;
import businessObject.ContenitorePasswords;
import businessObject.Range;
import businessObject.RangeList;

public class ThreadCombinatore implements  Runnable {

	private Thread runner;
	private RangeList rangeList;
	private Calendar calendario;
	private DateTime start;
	private DateTime end;
	AreaComune comune;
	private ContenitorePasswords contPasswords;
	
	public ThreadCombinatore(String threadName,RangeList rangeList,
							ContenitorePasswords contPasswords, AreaComune aC) {
		comune = aC;
		this.contPasswords = contPasswords;
		runner = new Thread(this, threadName); 
		calendario = Calendar.getInstance();
		start = DateTime.now();//DATA quando parte il processo
		System.out.println(Date_Utils.formatDate(calendario) + runner.getName() + " [Avviato]");
		this.rangeList = rangeList;
		runner.start(); 
	}

	
	
	// metodo implementato con l'interfaccia Runnable
	public void run() {
		String a,b,c,d,e;// caratteri
		

		for (Range re : rangeList.getRangeList()) {
			for (int ie = re.getStart(); ie <= re.getEnd(); ie++) {
				//trasformo il char in una stringa e inserisco il carettere vuoto
				e = (ie == 0) ? "" : "" + (char) ie;
				for (Range rd : rangeList.getRangeList()) {
					for (int id = rd.getStart(); id <= rd.getEnd(); id++) {
						d = (id == 0) ? "" : "" + (char) id;
						for (Range rc : rangeList.getRangeList()) {
							for (int ic = rc.getStart(); ic <= rc.getEnd(); ic++) {
								c = (ic == 0) ? "" : "" + (char) ic;
								if (comune.isTrovato()) {
									System.out.println(Thread.currentThread() + "[TERMINATO] [chiave trovata]");
									// termino l'esecuzione se nell'area comune qualcuno ha
									// trovato
									return;
								}
								for (Range rb : rangeList.getRangeList()) {
									for (int ib = rb.getStart(); ib <= rb.getEnd(); ib++) {
										// trsformiamo il carettere in strimghe
										b = (ib == 0) ? "" : "" + (char) ib;
										for (Range ra : rangeList.getRangeList()) {
											for (int ia = ra.getStart(); ia <= ra.getEnd(); ia++) {
												a = (ia == 0) ? "" : "" + (char) ia;
												
												if (rangeList.isRicercaEasy() || !(e + d + c + b + a).matches(rangeList.getRegEX())) {

													// key è la chiave che si vuole cercare
													if (contPasswords.isCorrectPass(e + d + c + b + a)) {
														System.out.println(Thread.currentThread() + "[PASSWORD TROVATA]" + "[chiave]:[" + e + d + c + b + a + "]");
														end = DateTime.now();
														Date_Utils.difference(start,end);
														comune.setTrovato(true);
														return;

													}
												}

											}

										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(Thread.currentThread() + "[TERMINATO] [password not found]");
	}

	
	
	
}


