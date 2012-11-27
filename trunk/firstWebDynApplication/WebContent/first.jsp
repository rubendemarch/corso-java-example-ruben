<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>
		<%=new SimpleDateFormat(
					"EEEEEEEEE d MMMMMMMMMMMM YYYY H:m.s SSS",
					Locale.ITALIAN
					).format(Calendar.getInstance(Locale.ITALY).getTime())%>
	</p>
	<p>
		<%=new SimpleDateFormat(
					"EEEEEEEEE d MMMMMMMMMMMM YYYY H:m.s SSS",
					Locale.ENGLISH
					).format(Calendar.getInstance(Locale.JAPAN).getTime())%>
	</p>
	<p>
		<%=new SimpleDateFormat(
					"EEEEEEEEE d MMMMMMMMMMMM YYYY H:m.s SSS",
					Locale.CANADA_FRENCH
					).format(Calendar.getInstance(Locale.GERMAN).getTime())%>
	</p>
	<p>
		<%=new SimpleDateFormat(
					"EEEEEEEEE d MMMMMMMMMMMM YYYY H:m.s SSS",
					Locale.KOREA
					).format(Calendar.getInstance(Locale.TRADITIONAL_CHINESE).getTime())%>
	</p>
</body>
</html>