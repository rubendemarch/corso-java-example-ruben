
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@include file="../../common/props.jsp"%>

<%
List<HashMap<String,Object>>brandList=
(List<HashMap<String,Object>>)
	request.getAttribute("brandList");
%>
<%
for(HashMap<String,Object>brand:brandList){
%>
<a href="<%=(String)brand.get("URL")%>"> <%=rb.getString("manage.brand.url")%></a> &nbsp;
<a href="<%=(String)brand.get("LOGO_URL")%>"> <%=rb.getString("manage.brand.logoUrl")%></a> &nbsp;
<%=(String)brand.get("NAME")%>&nbsp;
<br>
<%
}
%>
