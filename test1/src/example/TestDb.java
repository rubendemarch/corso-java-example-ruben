/**
 * 
 */
package example;

import java.sql.SQLException;

import configuration.MyProperties;

import bussinessObject.Alunno;
import dbo.connection.Connessione;
import dbo.impl.DboAlunni;

/**
 * @author ALFA403
 *
 */
public class TestDb {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("GOOOOOOOOOOOOOOOOOOO");
		test1();
		System.out.println("aspetta");
		int cnt=0;
		while (cnt<7) {
			cnt++;
			try {
			Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("end");	
	}

	/**
	 * 
	 */
	private static void test1() {
		Connessione c=null;
			try {
				c = new Connessione(new MyProperties("DbConf.xml"));
			} catch (ReflectiveOperationException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (c!=null) {
			DboAlunni dboAlunni = new DboAlunni(c);

//tutti metodi equivalenti, sceglieremo chi funziona meglio			
			
//			List<Alunno>alunnoList=dboAlunni.readAlunni();
//			for (int i = 0; i < alunnoList.size(); i++) {
//				System.out.println(alunnoList.get(i));
//			}
//			
//			for (Alunno alunno : alunnoList) {
//				System.out.println(alunno);
//			}
		
			for (Alunno alunno : dboAlunni.readAlunni()) {
				System.out.println(alunno);
			}
		}
		c.closeConnection();//chiude la connessione proprio quando abbiamo finito di usarla
	}
}
