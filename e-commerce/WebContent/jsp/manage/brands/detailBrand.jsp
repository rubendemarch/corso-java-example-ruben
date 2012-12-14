<%@page import="java.util.HashMap"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
HashMap<String,Object>brand=
	(HashMap<String,Object>)request.getAttribute("brand");
%>
<label><%=rb.getString("manage.brand.page.labelName")%></label>
&nbsp;<label><%=(String)brand.get("NAME")%></label>
<br>
<a href="<%=(String)brand.get("URL")%>"><%=rb.getString("manage.brand.page.labelUrl")%></a>
<br>
<a href="<%=(String)brand.get("LOGO_URL")%>"><%=rb.getString("manage.brand.page.labelLogoUrl")%></a>