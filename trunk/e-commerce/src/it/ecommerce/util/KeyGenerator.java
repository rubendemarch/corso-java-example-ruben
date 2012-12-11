/**
 * 
 */
package it.ecommerce.util;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author ALFA403
 *
 */
public class KeyGenerator {
	private static final int keyMaxLength=20;
	private static final char padChar='0';

	public static String keyGen(SqlSession sql,
			String colName,
			String tableName,
			String prefix){//prefix è la prima lettera per gli ID di quella tabella
		HashMap<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("colName", colName);
		parameterMap.put("tableName", tableName);
		String lastInsertedKey = sql.selectOne("Key.lastInsertedKey",parameterMap);
		//questo metodo verrà chiamato solo la prima volta quando l'id è null
		if(StringUtils.isEmpty(lastInsertedKey)){
			return StringUtils.rightPad(prefix, keyMaxLength, padChar);
		}
		long l = Long.parseLong(lastInsertedKey.substring(prefix.length()));
		l++;
		return prefix+StringUtils.leftPad(l+"",keyMaxLength-prefix.length(), padChar);
	}
}
