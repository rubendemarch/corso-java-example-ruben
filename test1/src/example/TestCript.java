/**
 * 
 */
package example;

import util.cript.MyCript;

/**
 * @author ALFA403
 *
 */
public class TestCript {

	/**
	 * @param args
	 * 
	 * stampa la codifica hash md5 della parola scelta
	 */
	public static void main(String[] args) {
		MyCript cript = new MyCript();
		String str = "pippo";
		byte[]in=str.getBytes();
		byte[]out=cript.md5Encrypt(in);
		StringBuilder ret = new StringBuilder();
		for(byte b:out){
			ret.append(String.format("%02x", b));
		}
		System.out.println(ret);
	}

}
