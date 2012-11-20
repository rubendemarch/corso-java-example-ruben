/**
 * 
 */
package example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import util.StringFormat;
import util.dbo.Convert;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
import dbo.connection.Connessione;
import exception.config.Config;
import file.RootFile;

/**
 * @author ALFA403
 *
 */
public class TestInsertUdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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
		rf.creaFile("Leggi1.txt");
		RootFile rf2 = new RootFile();
		rf2.creaFile("Leggi2.txt");
		RootFile rf3 = new RootFile();
		rf3.creaFile("Leggi3.txt");
//Se lo vogliamo fare a mano:
		Descriptors di = new Descriptors();
		di.addColumnDescriptor(new ColumnDescriptor("USER_ID",						36, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("nome",								35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("cognome",						35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("data_nascita",					0, 		50,true,' ', ";","yyyyMMdd"));//YYYY dà l'anno della settimana, non del giorno
		di.addColumnDescriptor(new ColumnDescriptor("sesso",								1, 		1,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("cf",									16, 	16,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",				35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""));

		
		StringBuilder leggi = leggi(di);
		
		for (HashMap<String, Object> map: dbo.dynamicExecuteQuery(di, leggi.toString(), null)) {
			rf.println(StringFormat.formatMap(map, true, di));
		}
		rf.closePrintStream();
		
		StringBuilder insert = inserisci(di);

		Calendar data= Calendar.getInstance();
		data.set(1989, 05, 15);
		ArrayList<Object> params = new ArrayList<>();
		params.add("R3DM");
		params.add("Ruben");params.add("De March");
		params.add(Convert.convert(data));
		params.add("M");params.add("DMRRBN89A15L219I");
		params.add("Italia");params.add("I");params.add("Torino");params.add("L219");
		
		int k = dbo.dynamicExecuteUpdate(insert.toString(), params);
		System.out.println("scritte "+k+" nuove righe");
		
		for (HashMap<String, Object> map: dbo.dynamicExecuteQuery(di, leggi.toString(), null)) {
			rf2.println(StringFormat.formatMap(map, true, di));
		}
		rf2.closePrintStream();
		
		
		
		
		for (HashMap<String, Object> map: dbo.dynamicExecuteQuery(di, leggi.toString(), null)) {
			rf3.println(StringFormat.formatMap(map, true, di));
		}
		rf3.closePrintStream();
		
	}
	c.closeConnection();//chiude la connessione proprio quando abbiamo finito di usarla
}

	private static StringBuilder inserisci(Descriptors di) {
		StringBuilder sql=new StringBuilder("insert into ");
		sql.append("alunni (");
		for(ColumnDescriptorInterface cdi : di.getDescriptors()){
			sql.append(cdi.getColumnName()).append(",");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(") values (");
		for(ColumnDescriptorInterface cdi : di.getDescriptors()){
			sql.append("?,");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		return sql;
	}

	/**
	 * @param di
	 * @return
	 */
	private static StringBuilder leggi(Descriptors di) {
		StringBuilder sql=new StringBuilder("select ");
		for(ColumnDescriptorInterface cdi : di.getDescriptors()){
			sql.append(cdi.getColumnName()).append(",");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(" from").append(" alunni")
			.append("")//whereCond
			.append("")//orderByCond
			.append("");//groupByCond
		return sql;
	}




}
