/**
 * 
 */
package example;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import bussinessObject.interfaces.DescriptorsInterface;
import configuration.MyProperties;
import dbo.connection.Connessione;
import dbo.impl.DboAlunni;
import exception.config.Config;
import file.RootFile;

/**
 * @author Dr
 *
 */
public class XlsToDb {

	/**
	 * 
	 */
	public XlsToDb() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connessione connessione = null;
		try {
			connessione = new Connessione(new MyProperties("DbConf.xml"));
		} catch (ReflectiveOperationException | SQLException e) {
			e.printStackTrace();
		}catch (Config e) {
			e.printStackTrace();
		}
		if (connessione!=null) {

			DboAlunni dbo = new DboAlunni(connessione);

			DescriptorsInterface di = dbo.readConfig("readAlunni");

			List<HashMap<String,Object>>alunni=new ArrayList<HashMap<String,Object>>();

			RootFile rf = new RootFile();
			rf.creaFile("testXl.xls");

			Workbook workbook=null;

			try {
				workbook = Workbook.getWorkbook(rf.getF());
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Sheet sheet;
			for(int s=0;s<workbook.getNumberOfSheets();s++){

				sheet = workbook.getSheet(s);

				if("alunni".equals(sheet.getName())){
					/* se facessi sheet.getName().equals("alunni")
					 * potrei avere una roba del tipo null.equals
					 *  (che è nullpointerexception)
					 */
					for(int r=1;r<sheet.getRows();r++){
						alunni.add(new HashMap<String,Object>());
						for(int c=0;c<sheet.getColumns();c++){
							if(CellType.DATE==sheet.getCell(c, r).getType()){
								alunni.get(r-1)//è la mappa che sto popolando
									.put(
										di.getDescriptors().get(c).getColumnName(),//la chiave assegnata nella mappa
										new Timestamp(
											((DateCell)
													sheet.getCell(c,r)//cella
											).getDate()//castando a date cell posso chiedere la data
												.getTime()//alla data chiedo il long che rappresenta la data in millisec
										)//il long lo uso per istanziare il nuovo timestamp da scrivere su db
									);
							}else{//String
								alunni.get(r-1)//è la mappa che sto popolando
								.put(
									di.getDescriptors().get(c).getColumnName(),//la chiave assegnata nella mappa
									sheet.getCell(c,r)//cella
										.getContents()//leggo il contenuto
								);
							}
						}
					}
				}

			}

			for (HashMap<String, Object> alunno : alunni) {
				//se ha provato a fare l'insert ma nn ci è riuscito fa l'update
				if(dbo.dynamicInsertAlunno(di, alunno)==0){
					dbo.dynamicUpdateAlunno(di, alunno);
				}
			}

			connessione.closeConnection();
		}
	}
}
