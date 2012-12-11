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
</head>
<%
	String msg = (String) request.getAttribute("msg");
%>
<body
	<%-- <%if(StringUtils.isEmpty(msg)){ %> --%>
onload="msg('<%=msg%>')"<%-- <%} %> --%>>
	<!-- QUESTA FUNZIONE CE L'HA GIA' IL JAVASCRIPT -->

	<form action="./ManageBrands" method="post">
		<input type="hidden" name="<%=Common.ACTION%>" value="inserisci">
		<label><%=rb.getString("manage.brand.page.labelName")%></label> <input
			type="text" value="" name="name" maxlength="100" size="50"> <input
			type="submit"
			value="<%=rb.getString("common.save")%><%=rb.getString("manage.brand")%>">
	</form>

</body>
</html>