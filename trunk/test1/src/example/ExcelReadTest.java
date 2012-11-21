/**
 * 
 */
package example;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import util.MyLogger;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
import dbo.connection.Connessione;
import exception.config.Config;
/**
 * @author ALFA403
 *
 */
public class ExcelReadTest {

	private String inputFile;
	private MyLogger logger;
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		logger=new MyLogger(this.getClass());
		final String metodo="read";
		logger.start(metodo);
		

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

			Descriptors di = new Descriptors();
			di.addColumnDescriptor(new ColumnDescriptor("USER_ID",					36, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("nome",						35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cognome",					35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("data_nascita",				0, 		50,true,' ', ";","yyyyMMdd"));//YYYY dà l'anno della settimana, non del giorno
			di.addColumnDescriptor(new ColumnDescriptor("sesso",					1, 		1,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("cf",						16, 	16,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("STATO_NASCITA",			35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_STATO_NASCITA",		4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COMUNE_NASCITA",			35, 	50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("COD_COMUNE_NASCITA",		4,		50,true,' ', ";",""));
			di.addColumnDescriptor(new ColumnDescriptor("DATA_ISCRIZIONE",			35, 	50,true,' ', ";","yyyyMMdd"));
			
			ArrayList<HashMap<String, Object>> alunni= new ArrayList<>() ;
									
			try {
				w = Workbook.getWorkbook(inputWorkbook);
				
				// Get the first sheet
				Sheet sheet = w.getSheet(0);
				
				for (int i = 1; i < sheet.getRows(); i++) {
					HashMap<String, Object> alunno = new HashMap<>();
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j, i);
						Object value = new Object();
						if ( cell.getType() == CellType.LABEL) {
							value = cell.getContents();
						}
						if ( cell.getType() == CellType.DATE) {
							DateCell dateCell=(DateCell)cell;
							value = new Timestamp(dateCell.getDate().getTime()) ;
						}
						alunno.put(sheet.getCell(j, 0).getContents(), value);
					}
					alunni.add(alunno);
					
//					for(ColumnDescriptorInterface cdi:di.getDescriptors()){
//						if(StringUtils.isEmpty(cdi.getPattern())){//se la colonna non ha il pattern
//						}else{
//							try {
//								alunno.put(
//										cdi.getColumnName(),
//										new Timestamp(
//												new SimpleDateFormat(cdi.getPattern())
//														.parse((String) alunno.get(cdi.getColumnName())).getTime()));
//							} catch (ParseException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
				}	
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
					params.clear();//toglie i valori dell'alunno precedente per metterci quelli dell'alunno corrente
					for(int ci=0;ci<di.getDescriptors().size();ci++){
						cdi=di.getDescriptors().get(ci);
						params.add(alunno.get(cdi.getColumnName()));
					}
					System.out.println(i +"  "+dbo.dynamicExecuteUpdate(sqlInsert.toString(), params));
				}
			} catch (BiffException e) {
				e.printStackTrace();
			}
			alunni.clear();
			c.closeConnection();
			
		}
		logger.end(metodo);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		ExcelReadTest test = new ExcelReadTest();
		test.setInputFile("prova.xls");
		test.read();
	}


}