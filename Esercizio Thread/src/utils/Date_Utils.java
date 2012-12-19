package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Period;

import org.joda.time.format.PeriodFormat;


public class Date_Utils {

	public final static String DATE_FORMAT = "dd/MM/yyyy HH:mm.ss.SSS ";
	//public final static String DATE_IMPIEGATO = "yyyy/MM/dd HH:mm.ss";
	
	public static String formatDate(Calendar c)
	{
		return new SimpleDateFormat(DATE_FORMAT).format(c.getTime());
	}
	
	public static void difference(DateTime start, DateTime end)
	{
		Period periodo = new Period(start, end);
		
		System.out.println("Tempo impiegato: " + (PeriodFormat.getDefault().print(periodo)));
		
	}
	

}
