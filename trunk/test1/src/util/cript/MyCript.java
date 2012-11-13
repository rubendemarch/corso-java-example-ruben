/**
 * 
 */
package util.cript;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.MyLogger;

/**
 * @author ALFA403
 *
 */
public class MyCript {
	private MyLogger logger;
	private MessageDigest md5=null;
	/**
	 * 
	 */
	public MyCript() {
		logger=new MyLogger(this.getClass());
		final String metodo="costruttore";
		logger.start(metodo);
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error(metodo, "MD5", e);
		}
		logger.end(metodo);
		
	}


	public byte[]md5Encrypt(byte[]in) {//si può far ritornare un array di byte: byte[] o una stringa
//		logger=new MyLogger(this.getClass());
//		final String metodo="md5crypt";
//		logger.start(metodo);
		return (md5.digest(in)); //lo scrivo su più righe per mettere il logger, ma basterebbe questa riga
//		byte[]in=input.getBytes();
//		byte[]out=md5.digest(in);
//		logger.end(metodo);//così vedo quanto tempo ci mette md5 nel log
//		StringBuilder ret = new StringBuilder();
//		for(byte b:out){
//			ret.append(b);
//		}
//		return ret.toString();
//		//		md5.reset(); questo lavoro lo fa già il digest
////		md5.update(password.getBytes()); si può passare direttamente al digest
	}
	
	private byte[]md5Decrypt(byte[]in){
		return (md5.digest(in));
	}
}