/**
 * 
 */
package util.map;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import bussinessObject.interfaces.ColumnDescriptorInterface;
import bussinessObject.interfaces.DescriptorsInterface;

/**
 * @author ALFA403
 *
 */
public class Convert {

	/**
	 * 
	 */

	public static HashMap<String, Object> formatMap(	String line,
														boolean isCsvFormat,
														DescriptorsInterface di){
		HashMap<String,Object>map=new HashMap<String,Object>();
		
		String l=line;
		String appo=null;
		for(ColumnDescriptorInterface cdi:di.getDescriptors()){
			if(isCsvFormat){
				appo=l.substring(0, l.indexOf(cdi.getSeparetor()));
				l=l.substring(l.indexOf(cdi.getSeparetor())+1);
				if(StringUtils.isEmpty(cdi.getPattern())){//se la colonna non ha il pattern
					map.put(cdi.getColumnName(), appo);
				}else{//se la colonna ha il pattern (e quindi sarà una data)
					try {
						map.put(
								cdi.getColumnName(),
								new Timestamp(
										new SimpleDateFormat(cdi.getPattern())
												.parse(appo).getTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}else{//non è csv format
				
				appo=l.substring(0, cdi.getFileSize());
				l=l.substring((cdi.getFileSize()));
//				if(cdi.getPadChar()==' '){
//					map.put(cdi.getColumnName(), appo.trim());}
//				else {
				if(cdi.isLeftAlign()){
						while (appo.charAt((appo.length())-1)==cdi.getPadChar()){
							appo=appo.substring(0, (appo.length())-1);
						}
				} else{
						while (appo.charAt(0)==cdi.getPadChar()){
							appo=appo.substring(1);
						}
				}
				//i metodi sopra si possono sostituire con StringUtils.StripEnd e StripStart
				
				if(StringUtils.isEmpty(cdi.getPattern())){//se la colonna non ha il pattern
						map.put(cdi.getColumnName(), appo);
				}else{//se la colonna ha il pattern (e quindi sarà una data)
					try {
						map.put(
								cdi.getColumnName(),
								new Timestamp(
										new SimpleDateFormat(cdi.getPattern())
												.parse(appo).getTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

			}
		}

		return map;
	}

}
