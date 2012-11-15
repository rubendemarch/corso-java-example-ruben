/**
 * 
 */
package util.dbo;

import java.sql.Date;
import java.util.Calendar;

import bussinessObject.Sesso;

/**
 * @author ALFA403
 *
 */
public class Convert {
//il metodo convert si può chiamare sempre uguale perché tanto dipende dal tipo in input
	public static Calendar convert(Date d){
		Calendar c =Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	public static  Date convert(Calendar c){
		return new Date(c.getTimeInMillis());
	}
	public static  Sesso convert(String s){
		return Sesso.valueOf(s);
	}
	public static  String convert(Sesso s){
		return s.name();
	}
//	public static Object convert(Alunno a, ResultSet rs){//object è sopraclasse di tutto quello che ritorna 'sto metodo
//		if(a.getClazz()==String.class){
//			try {
//				return rs.getString(a.getColumnName());
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(a.getClazz()==Calendar.class){
//			try {
//				return convert(rs.getDate(a.getColumnName()));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

}
