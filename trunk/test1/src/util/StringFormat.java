/**
 * 
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import enums.Alunno;


/**
 * @author Dr
 *
 */
public class StringFormat {
	/**
	 * @param metodo
	 * @param message
	 * @return il messaggio nel formato per il log
	 */
	public static String formatMessage(String metodo, String message){
		return new StringBuilder("[").append(metodo).append("][").append(message).append("]").toString();
	}
	
	public static String formatAlunno(HashMap<String, Object>alunno, boolean isCsvFormat){
		StringBuilder ret= new StringBuilder();
		if(isCsvFormat){
			for (enums.Alunno a:enums.Alunno.values()){
				ret.append(format(alunno.get(a.getColumnName()),a)).append(a.getSeparetor());
			}
		}else {//formato righe fisse
			for (enums.Alunno a:enums.Alunno.values()){
				ret.append(
						
						(a.isLeftAlign())?
						StringUtils.rightPad(
								format(alunno.get(a.getColumnName()),a),
								a.getFileSize(),
								a.getPadChar()):
						StringUtils.rightPad(
								format(alunno.get(a.getColumnName()),a),
								a.getFileSize(),
								a.getPadChar())
						);
						
						
			}
		}
		return ret.toString();
	}

	public static String format(Object val, Alunno a){
		if(a.getClazz()==String.class){
			return (String)val;
		}
		if(a.getClazz()==Calendar.class){
			return new SimpleDateFormat("YYYYMMDD").format(((Calendar)val).getTime());
		}
		return"";
	}
}
