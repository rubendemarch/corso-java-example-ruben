<%@page import="it.alfasoft.corso.java.util.constants.Request"%>
<%@page import="it.alfasoft.corso.java.util.constants.Session"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	ResourceBundle rb = (ResourceBundle)request.getAttribute(Request.ResourceBundle);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=rb.getString("title") %></title>
</head>
<body>
<jsp:include page="commons/include/comboLanguage.jsp"></jsp:include>
<br>
<table>
<tr>
<th><%=rb.getString("id_disp") %></th>
<th>Nome ID</th>
<th><%=rb.getString("ora") %></th>
</tr>
<%
SimpleDateFormat sdf = new SimpleDateFormat(//sdf � una VARIABILE DI PAGINA
		"EEEEEEEEE d MMMMMMMMMMMM YYYY H:m.s SSS");
for(String id: TimeZone.getAvailableIDs()){
	sdf.setTimeZone(TimeZone.getTimeZone(id));//sovrascrive LOCALE.ITALY
	%>
<tr>
<td><%=id%></td>
<td><%=TimeZone.getTimeZone(id).getDisplayName() %></td>
<td><%=sdf.format(Calendar.getInstance().getTime()) %></td>
</tr>
<%
}
%>


</table>
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
					).format(Calendar.getInstance(Locale.FRENCH).getTime())%>
	</p>
	<p>
		<%=new SimpleDateFormat(
					"EEEEEEEEE d MMMMMMMMMMMM YYYY H:m.s SSS",
					Locale.KOREA
					).format(Calendar.getInstance(Locale.TRADITIONAL_CHINESE).getTime())%>
	</p>
</body>
</html>