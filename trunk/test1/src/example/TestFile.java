/**
 * 
 */
package example;

import java.io.File;
import java.io.IOException;

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
	}

}
