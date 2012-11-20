/**
 * 
 */
package example;

import java.sql.SQLException;
import java.util.HashMap;

import util.StringFormat;

import configuration.MyProperties;

import bussinessObject.Alunno;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import dbo.RootDbo;
import dbo.connection.Connessione;
import dbo.impl.DboAlunni;
import exception.config.Config;
import file.RootFile;

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
		test2();
		System.out.println("aspetta");
		int cnt=0;
		while (cnt<7) {
			cnt++;
			try {
			Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
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
				e.printStackTrace();
			}catch (Config e) {
				e.printStackTrace();
			}
		if (c!=null) {
			DboAlunni dboAlunni = new DboAlunni(c);
			
			RootFile rf = new RootFile();
			rf.creaFile("test1.txt");
			RootFile rf2 = new RootFile();
			rf2.creaFile("test2.txt");
			
			for (HashMap<String, Object> alunno: dboAlunni.dynamicReadAlunni()) {
				rf.println(StringFormat.formatAlunno(alunno, true));
				rf2.println(StringFormat.formatAlunno(alunno, false));
			}
			rf.closePrintStream();
			rf2.closePrintStream();
			

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
	
	/**
	 * 
	 */
	  private static void test2() {
		Connessione c=null;
			try {
				c = new Connessione(new MyProperties("DbConf.xml"));
			} catch (ReflectiveOperationException | SQLException e) {
				e.printStackTrace();
			} catch (Config e) {
				e.printStackTrace();
			}
		if (c!=null) {
			RootDbo dbo = new RootDbo(c);
			
			RootFile rf = new RootFile();
			rf.creaFile("test1_1.txt");
			RootFile rf2 = new RootFile();
			rf2.creaFile("test2_1.txt");
// Se lo vogliamo fare a mano:
			Descriptors di = new Descriptors();
			di.addColumnDescriptor(new ColumnDescriptor("USER_ID",						36, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("nome",								35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cognome",						35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("data_nascita",					0, 		50,true,' ', ";","yyyyMMdd"));//YYYY dà l'anno della settimana, non del giorno
			di.addColumnDescriptor(new ColumnDescriptor("sesso",								1, 		1,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cf",									16, 	16,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",				35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
			
			StringBuilder sql=new StringBuilder("select ");
			for(ColumnDescriptorInterface cdi : di.getDescriptors()){
				sql.append(cdi.getColumnName()).append(",");
			}
			sql=sql.deleteCharAt(sql.length()-1);
			sql.append(" from").append(" alunni")
				.append("")//whereCond
				.append("")//orderByCond
				.append("");//groupByCond
			
			for (HashMap<String, Object> map: dbo.dynamicExecuteQuery(di, sql.toString(), null)) {
				rf.println(StringFormat.formatMap(map, true, di));
				rf2.println(StringFormat.formatMap(map, false, di));
			}
			rf.closePrintStream();
			rf2.closePrintStream();
			
		}
		c.closeConnection();//chiude la connessione proprio quando abbiamo finito di usarla
	}
	
}
