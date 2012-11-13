/**
 * 
 */
package example;

import util.MyLogger;
import util.cript.MyCript;

/**
 * @author ALFA403
 *
 */
public class TestCript {
	private MyLogger logger;
	/**
	 * @param args
	 * 
	 * stampa la codifica hash md5 della parola scelta
	 */
	
	public TestCript() {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		
		MyCript cript = new MyCript();
		String str = "pipuigtnbtrouibtiobvtripmwer8gpo";
		byte[]in=str.getBytes();
		logger.info("md5Encrypt di "+str, "start");//vedo quanto impiega a md5encriptare
		byte[]out=cript.md5Encrypt(in);
		logger.info("md5Encrypt di "+str, "end");//quando finisce
		StringBuilder ret = new StringBuilder();
		for(byte b:out){
			ret.append(String.format("%02x", b));
		}
		System.out.println(ret);
		logger.end(metodo);
	}

	public static void main(String[] args){
		
	//	new TestCript();//il test in md5
		

		String password = "mypassword";
		String passwordEnc = MyCript.encrypt(password);
		String passwordDec = MyCript.decrypt(passwordEnc);
		
		System.out.println("Plain Text : " + password);
		System.out.println("Encrypted Text : " + passwordEnc);
		System.out.println("Decrypted Text : " + passwordDec);
		
	}


}
