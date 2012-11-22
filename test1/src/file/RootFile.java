/**
 * 
 */
package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import util.MyLogger;

/**
 * @author ALFA403
 *
 * contiene tutte le utility delle classi che creeranno file txt, pdf, xls,....
 */
public class RootFile {
private MyLogger logger;
private File f=null;
private PrintStream printStream=null;
	/**
	 * 
	 */
	public RootFile() {
		logger=new MyLogger(this.getClass());
	}

	public boolean creaFile(String path){
		final String metodo="creaFile";
		f=new File(path);
		if(f.exists()){
			return true;
		}
		
		try {
			f.createNewFile();
			return true;
		} catch (IOException e) {
			logger.error(metodo, "createNewFile", e);
		}
		return false;
	}
	
	public void println(String line){
		final String metodo="println";
		logger.start(metodo);
		initPrintStream();
		printStream.println(line);
		logger.end(metodo);
	}

	private void initPrintStream() {
		final String metodo="initPrintStream";
		logger.start(metodo);
		if(f!=null&&printStream==null){
			try {
				printStream = new PrintStream(f);
			} catch (FileNotFoundException e) {
			logger.error(metodo, "initPrintStream", e);
			}
		}
		logger.end(metodo);
		
	}
	
	public void closePrintStream(){
		printStream.close();
	}

	/**
	 * @return the f
	 */
	public File getF() {
		return f;
	}
}
