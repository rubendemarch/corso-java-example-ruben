/**
 * 
 */
package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;
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
			for (enums.Alunno a : enums.Alunno.values()){
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
						StringUtils.leftPad(
								format(alunno.get(a.getColumnName()),a),
								a.getFileSize(),
								a.getPadChar())
						);
						
						
			}
		}
		return ret.toString();
	}

	public static String format(Object val, Alunno a){
		if(val!=null){
			
//			if(a.getClazz()==String.class){//non mi piace il getClazz
			if(val.getClass()==String.class){//infatti capisce direttamente la classe di val
				return (String)val;//casta l'OBJECT come String
			}
			if(val.getClass()==Calendar.class){
			
				return new SimpleDateFormat(a.getPattern()).format(((Calendar)val).getTime());//c'è ClassCastException da date a calendar
			}
		
			if(val.getClass()==Timestamp.class){//Timestamp è il formato in cui ritornano le date (si può vedere debuggando)
			
				return new SimpleDateFormat(a.getPattern()).format(((Timestamp)val).getTime());
			}
			return val.getClass().getName();//dice qual è la classe che non sono riuscito a gestire
		}
		
		return"";//rappresentazione stringa vuota
	}
	
	
	
	public static String formatMap(HashMap<String, Object>map, boolean isCsvFormat, DescriptorsInterface di){
		StringBuilder ret= new StringBuilder();
		if(isCsvFormat){
			for(ColumnDescriptorInterface cdi : di.getDescriptors()){
				ret.append(format(map.get(cdi.getColumnName()),cdi)).append(cdi.getSeparetor());
			}
		}else {//formato righe fisse
			for(ColumnDescriptorInterface cdi : di.getDescriptors()){
				ret.append(
						
						(cdi.isLeftAlign())?
						StringUtils.rightPad(
								format(map.get(cdi.getColumnName()),cdi),
								cdi.getFileSize(),
								cdi.getPadChar()):
						StringUtils.leftPad(
								format(map.get(cdi.getColumnName()),cdi),
								cdi.getFileSize(),
								cdi.getPadChar())
						);
						
						
			}
		}
		return ret.toString();
	}

	
	
	public static String format(Object val, ColumnDescriptorInterface cdi){
		if(val!=null){
			
			if(val.getClass()==String.class){
				return (String)val;
			}
			if(val.getClass()==Calendar.class){
			
				return new SimpleDateFormat(cdi.getPattern()).format(((Calendar)val).getTime());
			}
		
			if(val.getClass()==Timestamp.class){
			
				return new SimpleDateFormat(cdi.getPattern()).format(((Timestamp)val).getTime());
			}
			return val.getClass().getName();
		}
		
		return"";
	}
	

}
