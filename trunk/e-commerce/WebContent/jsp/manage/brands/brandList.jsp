
<%@page import="it.ecommerce.util.constants.Common"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@include file="../../common/props.jsp"%>
<%List<HashMap<String,Object>>brandList=(List<HashMap<String,Object>>)request.getAttribute("brandList");%>
<table>
<tr><th colspan="4">LISTA DELLE MARCHE</th></tr>
<%for(HashMap<String,Object>brand:brandList){%>
<tr>
<td>
<%=(String)brand.get("NAME")%>&nbsp;
</td>
<td>
<a href="<%=(String)brand.get("URL")%>"> <%=rb.getString("manage.brand.url")%></a> &nbsp;
</td>
<td>
<a href="<%=(String)brand.get("LOGO_URL")%>"> <%=rb.getString("manage.brand.logoUrl")%></a> &nbsp;
</td>
<td>
<button onclick="loadAll('./ManageBrands','<%=Common.DETAIL%>','<%=(String)brand.get("ID_BRAND")%>')">
	<%=rb.getString("manage.brand.page.detail") %>
</button>
</td>
<td>
<button onclick="loadAll('./ManageBrands','<%=Common.MODIFY%>','<%=(String)brand.get("ID_BRAND")%>')">
	<%=rb.getString("manage.brand.page.modify") %>
</button>
</td>
</tr>
<%--  SE LO VUOI FARE IN GET E NON IN POST:
<a href="./ManageBrands?<%=Common.ACTION%>=<%=Common.DETAIL%>&id=<%=(String)brand.get("ID_BRAND")%>">
	<%=rb.getString("manage.brand.page.detail")%>
</a>&nbsp; --%>
<%}%>
</table>