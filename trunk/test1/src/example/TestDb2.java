/**
 * 
 */
package example;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.StringFormat;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
import dbo.connection.Connessione;
import file.RootFile;

/**
 * @author ALFA403
 *
 */
public class TestDb2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connessione c=null;
		try {
			c = new Connessione(new MyProperties("DbConf.xml"));
		} catch (ReflectiveOperationException | SQLException e) {
			e.printStackTrace();
		}
		
	if (c!=null) {
		RootDbo dbo = new RootDbo(c);
		
		Descriptors di = new Descriptors();
		di.addColumnDescriptor(new ColumnDescriptor("USER_ID",					36, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("nome",						35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("cognome",					35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("data_nascita",				0, 		50,true,' ', ";","yyyyMMdd"));//YYYY dà l'anno della settimana, non del giorno
		di.addColumnDescriptor(new ColumnDescriptor("sesso",					1, 		1,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("cf",						16, 	16,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",			35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",		4,		50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
		di.addColumnDescriptor(new ColumnDescriptor("DATA_ISCRIZIONE",			35, 	50,true,' ', ";","yyyyMMdd"));		
		
		
		StringBuilder sql=new StringBuilder("select ");
		for(ColumnDescriptorInterface cdi : di.getDescriptors()){
			sql.append(cdi.getColumnName()).append(",");
		}
		sql=sql.deleteCharAt(sql.length()-1);
		sql.append(" from").append(" alunni")
			.append("")//whereCond
			.append("")//orderByCond
			.append("");
		
		RootFile f1 = new RootFile();
		f1.creaFile("1.txt");
		
		RootFile f2 = new RootFile();
		f2.creaFile("2.txt");
		
		RootFile f3 = new RootFile();
		f3.creaFile("3.txt");
//leggo1
		List<HashMap<String, Object>> alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);

//insert
		StringBuilder sqlInsert=new StringBuilder("insert into alunni (");
		StringBuilder sqlInsertParam=new StringBuilder();
		for(ColumnDescriptorInterface cdi : di.getDescriptors()){
			sqlInsert.append(cdi.getColumnName()).append(",");
			sqlInsertParam.append("?,");
		}
		sqlInsert=sqlInsert.deleteCharAt(sqlInsert.length()-1);
		sqlInsert.append(") values(").append(sqlInsertParam.deleteCharAt(sqlInsertParam.length()-1))
			.append(")");

		ArrayList<Object>params=new ArrayList<>();
		HashMap<String, Object> alunno;
		ColumnDescriptorInterface cdi;
		for (int i=0;i<alunni.size();i++) {
			alunno=alunni.get(i);
			f1.println(StringFormat.formatMap(alunno, true, di));
			params.clear();//toglie i valori dell'alunno precedente per metterci quelli dell'alunno corrente
			for(int ci=0;ci<di.getDescriptors().size();ci++){
				cdi=di.getDescriptors().get(ci);
				params.add("USER_ID".equals(cdi.getColumnName())?
						i+"_"+alunno.get(cdi.getColumnName())
						:alunno.get(cdi.getColumnName()));
			}
			System.out.println(dbo.dynamicExecuteUpdate(sqlInsert.toString(), params));
		}

//leggo2
		 alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);

//update (di solito si fa a mano)
		StringBuilder sqlUpdate=new StringBuilder("update alunni set ");
		for(int ui=3;ui<6;ui++){
			cdi=di.getDescriptors().get(ui);
			sqlUpdate.append(cdi.getColumnName()).append("=?,");
		}
		sqlUpdate=sqlUpdate.deleteCharAt(sqlUpdate.length()-1);
		sqlUpdate.append(" where ").append(di.getDescriptors().get(0).getColumnName())//0 corrisponde a user_id, cioè la Primary key
				.append("=?");
		
		for (int i=0;i<alunni.size();i++) {
			alunno=alunni.get(i);
			f2.println(StringFormat.formatMap(alunno, true, di));
			params.clear();//cancella i valori dell'alunno precedente per metterci quelli dell'alunno corrente
			for(int ui=3;ui<6;ui++){
				cdi=di.getDescriptors().get(ui);
				params.add(
						"data_nascita".equals(cdi.getColumnName())?
						Timestamp.valueOf("2012-12-21 15:15:15")
						:alunno.get(cdi.getColumnName()));
				
			}
			params.add(alunno.get(di.getDescriptors().get(0).getColumnName()));
			
			System.out.println(dbo.dynamicExecuteUpdate(sqlUpdate.toString(), params));
		}
	 
//leggo3
		alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);
		
		for (int i=0;i<alunni.size();i++) {
			alunno=alunni.get(i);
			f3.println(StringFormat.formatMap(alunno, true, di));
		}
	}
	c.closeConnection();

	}

}
