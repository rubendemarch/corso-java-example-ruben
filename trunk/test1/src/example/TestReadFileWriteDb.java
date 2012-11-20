package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.map.Convert;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
import dbo.connection.Connessione;
import exception.config.Config;

public class TestReadFileWriteDb {

	public TestReadFileWriteDb() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connessione c = null;
		try {
			c = new Connessione(new MyProperties("DbConf.xml"));
		} catch (ReflectiveOperationException | SQLException e) {
			e.printStackTrace();
		}catch (Config e) {
			e.printStackTrace();
		}
		if (c!=null) {

			RootDbo dbo = new RootDbo(c);

			Descriptors di = new Descriptors();

			di.addColumnDescriptor(new ColumnDescriptor("USER_ID",							36, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("nome",								35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cognome",							35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("data_nascita",					0, 		50,true,' ', ";","yyyyMMdd"));
			di.addColumnDescriptor(new ColumnDescriptor("sesso",								1, 		1,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cf",									16, 	16,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",				35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",	4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("DATA_ISCRIZIONE",			0, 	50,true,' ', ";","yyyyMMdd"));

			StringBuilder sqlInsert = new StringBuilder("INSERT INTO ALUNNI (");
			StringBuilder sqlInsertParam = new StringBuilder();
			for (ColumnDescriptorInterface cdi: di.getDescriptors()) {
				sqlInsert.append(cdi.getColumnName()).append(",");
				sqlInsertParam.append("?,");
			}
			sqlInsert=sqlInsert.deleteCharAt(sqlInsert.length()-1);
			sqlInsert.append(")VALUES(")
						.append(sqlInsertParam.deleteCharAt(sqlInsertParam.length()-1))
						.append(")");
			
			ArrayList<Object>params=new ArrayList<>();
			
			HashMap<String, Object> alunno;
			
			ColumnDescriptorInterface cdi;
			
			BufferedReader bufferedReader=null;
			try {
				bufferedReader= new BufferedReader(new FileReader(new File("3.txt")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String line;
			try {
				while( (line=bufferedReader.readLine())!=null ){
				//for (int i =0;i<alunni.size();i++) {
					
					alunno=Convert.formatMap(line, true, di);
					params.clear();
					
					for (int ci =0;ci<di.getDescriptors().size();ci++ ) {
						cdi=di.getDescriptors().get(ci);
						params.add(alunno.get(cdi.getColumnName()));
					}
					
					System.out.println(dbo.dynamicExecuteUpdate(sqlInsert.toString(), params));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	}

}