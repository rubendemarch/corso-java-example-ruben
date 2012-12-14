
<%@page import="it.ecommerce.util.constants.Common"%>
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
<%=(String)brand.get("NAME")%>&nbsp;
<a href="<%=(String)brand.get("URL")%>"> <%=rb.getString("manage.brand.url")%></a> &nbsp;
<a href="<%=(String)brand.get("LOGO_URL")%>"> <%=rb.getString("manage.brand.logoUrl")%></a> &nbsp;
<button onclick="loadAll('./ManageBrands','<%=Common.DETAIL%>','<%=(String)brand.get("ID_BRAND")%>')">
		<%=rb.getString("manage.brand.page.detail") %>
		</button>
<a href="./ManageBrands?<%=Common.ACTION%>=<%=Common.DETAIL%>&id=<%=(String)brand.get("ID_BRAND")%>">
	<%=rb.getString("manage.brand.page.detail")%>
</a>&nbsp;
<br>
<%
}
%>
