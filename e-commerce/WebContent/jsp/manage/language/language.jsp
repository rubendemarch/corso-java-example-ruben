<%@page import="java.math.BigDecimal"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="it.ecommerce.util.constants.Request"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- <jsp:include page="../../common/props.jsp"></jsp:include> --%>
<!-- IL COMANDO PRECEDENTE IMPORTA SOLO RUNTIME LE VARIABILI, NON DURANTE LA COSTRUZIONE, E DA' ERRORE -->
<%@include file="../../common/props.jsp" %>
<!-- IL COMANDO PRECEDENTE INCOLLA SEMPRE IL PEZZO INCLUSO -->

<%
	List<HashMap<String, Object>>managedLanguages = (List<HashMap<String, Object>>)request.getAttribute(Request.MANAGED_LANGUAGES);
	List<Locale>toManage = (List<Locale>)request.getAttribute(Request.TO_MANAGE_LANGUAGES);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= rb.getString("manage.language.page.title")%></title>
<style> 
 @import url("css/rdm.css");
 </style>
</head>
<body>
<jsp:include page="../../common/menu/headerMenu.jsp"></jsp:include>
<form name="language" action="./ManageLanguages" method="post">
<input type="hidden" name="<%=Common.ACTION%>" value="save">
<table>
<tr><th colspan="2"><%= rb.getString("manage.language.page.managedLanguages")%></th></tr>
<%
String lang;
boolean isVisible;
for(HashMap<String,Object> managedLanguage:managedLanguages){ 
lang=(String)managedLanguage.get("ID_LANGUAGE");
isVisible=((BigDecimal)managedLanguage.get("IS_VISIBLE")).intValue()>0;
/*In questo modo dà false se l'int è 0 e true se l'int è 1*/%>
<tr><td>
<%=new Locale(lang).getDisplayLanguage(inLocale) %>
</td><td>
<input type="checkbox" name="<%=lang%>"<%if(isVisible){ %> checked <%} %>>
</td></tr>
<%} %>
</table>
<select name="toManage">
	<option value="0000"><%= rb.getString("common.sel")%></option>
<% for(Locale locale:toManage){ %>
	<option value="<%= locale.getLanguage()%>"><%= locale.getDisplayLanguage(inLocale)%></option>
<%} %>
</select>

<input type="submit" value="<%= rb.getString("common.save")%>">

</form>
</body>
</html>