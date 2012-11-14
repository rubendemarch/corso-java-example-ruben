/**
 * 
 */
package util.file;

import java.io.File;
import java.io.IOException;

import util.MyLogger;

/**
 * @author ALFA403
 *
 * contiene tutte le utility delle classi che creeranno file txt, pdf, xls,....
 */
public class RootFile {
private MyLogger logger;
private File f;
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
}
