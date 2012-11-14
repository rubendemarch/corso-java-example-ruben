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
		MyProperties mP=new MyProperties("DbConf.properties");//Lo crea nella cartella workspace/test1
//		MyProperties mP=new MyProperties("../../DbConf.xml");
		
		//Boolean cript=Boolean.TRUE;
		mP.setProperty("url", "jdbc:oracle:thin:@localhost:1521", false);
		mP.setProperty("dbName", ":xe", false);
		mP.setProperty("driver", "oracle.jdbc.driver.OracleDriver", false);
		mP.setProperty("userName", "APPLICAZIONIJAVA", true);
		mP.setProperty("password", "JAVA", true);
		try {
			mP.writeConfigFile();
		} catch (Config e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
