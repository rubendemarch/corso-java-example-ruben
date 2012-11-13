/**
 * 
 */
package example;

import configuration.MyProperties;
import exception.config.Config;

/**
 * @author ALFA403
 *
 */
public class PropertiesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MyProperties mP=new MyProperties("C:/prop/DbConf.xml");
		MyProperties mP=new MyProperties("DbConf.xml");//Lo crea nella cartella workspace/test1
//		MyProperties mP=new MyProperties("../../DbConf.xml");
		
		mP.setProperty("url", "jdbc:oracle:thin:@localhost:1521");
		mP.setProperty("dbName", ":xe");
		mP.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		mP.setProperty("userName", "APPLICAZIONIJAVA");
		mP.setProperty("password", "JAVA");
		try {
			mP.writeConfigFile();
		} catch (Config e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
