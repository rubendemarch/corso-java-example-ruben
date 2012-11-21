/**
 * 
 */
package example;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.StringUtils;

import util.MyLogger;
import bussinessObject.ColumnDescriptor;
import bussinessObject.Descriptors;
import bussinessObject.interfaces.ColumnDescriptorInterface;
import configuration.MyProperties;
import dbo.RootDbo;
import dbo.connection.Connessione;
import exception.config.Config;


public class ExcelWriteTest {

	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private String inputFile;
	private MyLogger logger;
	
	public ExcelWriteTest() {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		logger.end(metodo);
	}

	public void setOutputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void write() throws IOException, WriteException {
//		RootFile file = new RootFile();
//		file.creaFile(inputFile);
		File file = new File(inputFile);
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("it", "IT"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Alunni", 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createLabel(excelSheet);
//		createContent(excelSheet);

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

			StringBuilder sql=new StringBuilder("select ");
			for(ColumnDescriptorInterface cdi : di.getDescriptors()){
				sql.append(cdi.getColumnName()).append(",");
			}
			sql=sql.deleteCharAt(sql.length()-1);
			sql.append(" from").append(" alunni");

			List<HashMap<String, Object>> alunni = dbo.dynamicExecuteQuery(di, sql.toString(), null);
			HashMap<String, Object> alunno;
			ColumnDescriptorInterface cdi;
			//scrivo i nomi delle colonne
			for(int i=0;i<di.getDescriptors().size();i++){
				cdi=di.getDescriptors().get(i);
				addCaption(excelSheet, i, 0, cdi.getColumnName());
			}
			//scrivo i valori dentro le colonne
			for (int j=0;j<alunni.size();j++) {
				alunno=alunni.get(j);
				for (int i=0;i<di.getDescriptors().size();i++){
					cdi=di.getDescriptors().get(i);
					if(StringUtils.isEmpty(cdi.getPattern())){//se la colonna non ha il pattern
						addLabel(excelSheet, i, j+1, alunno.get(cdi.getColumnName()).toString());
					}else{//se la colonna ha il pattern (e quindi sarà una data)
//						Date date = (Date) alunno.get(cdi.getColumnName());//cast da sql.date a util.date//ma è influenzato dalla timezone
						Timestamp timestamp = (Timestamp) alunno.get(cdi.getColumnName());
						Date date=new Date(timestamp.getTime());
						addDate(excelSheet, i, j+1, date);
					}
				}
			}
		}
		//aggiusto larghezza colonne
		for(int j = 0; j < excelSheet.getColumns(); j++) {
		CellView cv = excelSheet.getColumnView(j);
		cv.setAutosize(true);
		excelSheet.setColumnView(j, cv);
		}
		workbook.write();
		workbook.close();
		c.closeConnection();
	}


	
	private void createLabel(WritableSheet sheet)
			throws WriteException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);

		// Create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setAutosize(false);

//		// Write a few headers
//		addCaption(sheet, 0, 0, "Header 1");
//		addCaption(sheet, 1, 0, "This is another header");


	}

//	private void createContent(WritableSheet sheet) throws WriteException,
//	RowsExceededException {
//		// Write a few number
//		for (int i = 1; i < 10; i++) {
//			// First column
//			addNumber(sheet, 0, i, i + 10);
//			// Second column
//			addNumber(sheet, 1, i, i * i);
//		}
//		// Lets calculate the sum of it
//		StringBuffer buf = new StringBuffer();
//		buf.append("SUM(A2:A10)");
//		Formula f = new Formula(0, 10, buf.toString());
//		sheet.addCell(f);
//		buf = new StringBuffer();
//		buf.append("SUM(B2:B10)");
//		f = new Formula(1, 10, buf.toString());
//		sheet.addCell(f);
//
//		// Now a bit of text
//		for (int i = 12; i < 20; i++) {
//			// First column
//			addLabel(sheet, 0, i, "Boring text " + i);
//			// Second column
//			addLabel(sheet, 1, i, "Another text");
//		}
//	}

	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}
	
	private void addDate(WritableSheet sheet, int column, int row,
			Date date) throws WriteException, RowsExceededException {
		DateTime dateTime = new DateTime(column, row, date );
		sheet.addCell(dateTime);
	}

	public static void main(String[] args) throws WriteException, IOException {
		ExcelWriteTest test = new ExcelWriteTest();
		test.setOutputFile("prova.xls");
		test.write();

	}
} 