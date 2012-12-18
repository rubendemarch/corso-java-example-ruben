<%@page import="it.ecommerce.servlet.common.language.ManageLanguages"%>
<%@page import="it.ecommerce.servlet.common.brands.ManageBrand"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- NOI VOGLIAMO HTML 5<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!DOCTYPE html>
<!-- IL COMANDO PRECEDENTE IMPORTA SOLO RUNTIME LE VARIABILI, NON DURANTE LA COSTRUZIONE, E DA' ERRORE -->
<%-- <jsp:include page="../../common/props.jsp"></jsp:include> --%>
<%@include file="props.jsp"%>
<!-- IL COMANDO PRECEDENTE INCOLLA SEMPRE IL PEZZO INCLUSO -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute(Common.PAGE_TITLE)%></title>
<script type="text/javascript" src="js/common/message.js"></script>
<script type="text/javascript" src="js/common/radio.js"></script>
<script type="text/javascript" src="js/common/button.js"></script>
<script type="text/javascript" src="js/common/form.js"></script>
<style> 
 @import url("css/rdm.css");
 </style>
</head>
<%
	String msg = (String) request.getAttribute("msg");
	String servletName = (String) request.getAttribute(Common.SERVLET_NAME);
%>
<body
	<%-- <%if(StringUtils.isEmpty(msg)){ %> --%>
onload="msg('<%=msg%>')"<%-- <%} %> --%>>
	<!-- QUESTA FUNZIONE CE L'HA GIA' IL JAVASCRIPT -->

<jsp:include page="form/form.jsp"></jsp:include>

<jsp:include page="menu/headerMenu.jsp"></jsp:include>
<br>
<%
if(ManageBrand.class.getName().equals(servletName)){
%>
<jsp:include page="../manage/brands/brand.jsp"></jsp:include>
<%
} else if(ManageLanguages.class.getName().equals(servletName)){
%>
<jsp:include page="../manage/language/language.jsp"/>
<% } %>
</body>
</html>