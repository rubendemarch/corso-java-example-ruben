/**
 * 
 */
package example;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author ALFA403
 *
 */
public class TestFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f=new File("C:\\primoFile.txt");
		if (f.exists()) {
			System.out.println("il file è già stato creato");
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// ora provo a scriverci
		PrintStream printStream = null;
		try {
			printStream=new PrintStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(printStream!=null){
			for (int i = 0; i < 1500; i++) {
				printStream.println("riga: "+i);
			}
//			printStream.flush();//scrive sul file quello che ha in memoria, si può fargli scrivere su file ogni 10 righe, altrimenti lo fa quando vuole lui
			printStream.close();
		}
		

		
	}

}
