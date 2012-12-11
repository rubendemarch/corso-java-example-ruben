<%@page import="java.util.Locale"%>
<%@page import="it.ecommerce.util.constants.Request"%>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle rb = (ResourceBundle) request.getAttribute(Request.ResourceBundle);
	Locale inLocale= (Locale)request.getAttribute(Request.LOCALE);
%>