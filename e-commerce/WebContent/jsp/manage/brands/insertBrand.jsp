<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@page import="it.ecommerce.util.constants.Request"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- IL COMANDO PRECEDENTE IMPORTA SOLO RUNTIME LE VARIABILI, NON DURANTE LA COSTRUZIONE, E DA' ERRORE -->
<%-- <jsp:include page="../../common/props.jsp"></jsp:include> --%>
<%@include file="../../common/props.jsp"%>
<!-- IL COMANDO PRECEDENTE INCOLLA SEMPRE IL PEZZO INCLUSO -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=rb.getString("manage.brand.page.title")%></title>
<script type="text/javascript" src="js/common/message.js"></script>
<script type="text/javascript" src="js/common/radio.js"></script>
<style> 
 @import url("css/rdm.css");
 </style>
</head>
<%
	String msg = (String) request.getAttribute("msg");
%>
<body
	<%-- <%if(StringUtils.isEmpty(msg)){ %> --%>
onload="msg('<%=msg%>')"<%-- <%} %> --%>>
	<!-- QUESTA FUNZIONE CE L'HA GIA' IL JAVASCRIPT -->

<jsp:include page="../../common/menu/headerMenu.jsp"></jsp:include>

	<form action="./ManageBrands" method="post" enctype="multipart/form-data">
		<input type="hidden" name="<%=Common.ACTION%>" value="inserisci">
	<table><tr><td colspan="2">
		<label><%=rb.getString("manage.brand.page.labelName")%></label>
		<input	type="text" value="" name="name" maxlength="100" size="50">
		</td></tr><tr><td colspan="2">
		<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
		<input	type="text" value="" name="url" maxlength="150" size="50">
		</td></tr><tr><td>
		<input type="radio" name="radioLogoUrl" checked="checked" onchange='manageRadio("urlLogo","imgLogo")' value="url">
		<label><%=rb.getString("manage.brand.page.labelUrlLogo")%></label>
		<input	type="text" value="" name="urlLogo" id="urlLogo" maxlength="150" size="50">
		</td><td>
		<input type="radio" name="radioLogoUrl" onchange='manageRadio("imgLogo","urlLogo")' value="image">
		<input	type="file" name="imgLogo" id="imgLogo" disabled="disabled" accept="image/*">
		</td></tr>
	</table>
		<input	type="submit" value="<%=rb.getString("common.save")%> <%=rb.getString("manage.brand")%>">
		
	</form>

</body>
</html>