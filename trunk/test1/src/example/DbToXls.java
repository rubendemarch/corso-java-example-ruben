/**
 * 
 */
package example;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
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
public class DbToXls {

	/**
	 * 
	 */
	public DbToXls() {
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

			List<HashMap<String,Object>>alunni=dbo.dynamicReadAlunni(di);

			RootFile rf = new RootFile();
			rf.creaFile("testXl.xls");
			
			WritableWorkbook workbook=null;
			try {
				workbook = Workbook.createWorkbook(rf.getF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			workbook.createSheet("alunni", 0);

			WritableSheet s = workbook.getSheet(0);

			for(int c=0;c<di.getDescriptors().size();c++){
				try {
					s.addCell(
						new Label(c,
										0,
										di.getDescriptors().get(c).getColumnName()));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for(int r=0;r<alunni.size();r++){
				for(int c=0;c<di.getDescriptors().size();c++){
					if(alunni.get(r)//mappa
								.get(
									di.getDescriptors().get(c).getColumnName()//key della mappa[di.getDescriptors().get(c)]
									)//oggetto associato alla key nella mappa
						!=null &&
						Timestamp.class==
							alunni.get(r)//mappa
								.get(
									di.getDescriptors().get(c).getColumnName()//key della mappa[di.getDescriptors().get(c)]
									)//oggetto associato alla key nella mappa
								.getClass()){
						try {
							s.addCell(
								new DateTime(
									c,
									r+1,
									(Timestamp)alunni.get(r)//mappa
										.get(
											di.getDescriptors().get(c).getColumnName()//key della mappa[di.getDescriptors().get(c)]
											)//oggetto associato alla key nella mappa)
								)
							);
						} catch (RowsExceededException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{//String
						try {
							s.addCell(
								new Label(
									c,
									r+1,
									(String)alunni.get(r)//mappa
										.get(
											di.getDescriptors().get(c).getColumnName()//key della mappa[di.getDescriptors().get(c)]
											)//oggetto associato alla key nella mappa)
								)
							);
						} catch (RowsExceededException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			try {
				workbook.write();
				workbook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
