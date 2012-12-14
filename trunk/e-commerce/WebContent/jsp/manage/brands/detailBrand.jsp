<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
HashMap<String,Object>brand=(HashMap<String,Object>)request.getAttribute("brand");
%>
<table>
<tr><td>
<label><%=rb.getString("manage.brand.page.labelName")%></label>&nbsp;
</td><th>
<label><%=(String)brand.get("NAME")%></label>
</th></tr><tr><td colspan="2">
<a href="<%=(String)brand.get("URL")%>"><%=rb.getString("manage.brand.page.labelUrl")%></a>
</td></tr><tr><td colspan="2">
<a href="<%=(String)brand.get("LOGO_URL")%>"><%=rb.getString("manage.brand.page.labelLogoUrl")%></a>
</td></tr><tr><td>
<%=rb.getString("manage.brand.page.IS_VISIBLE")%>
</td><td>
<%=((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0?
		rb.getString("common.yes"):
			rb.getString("common.no")%>
</td></tr><tr><td>
<%=rb.getString("manage.brand.page.IS_DELETED")%>
</td><td>
<%=((BigDecimal)brand.get("IS_DELETED")).intValue()>0?
		rb.getString("common.yes"):
			rb.getString("common.no")%>
</td></tr>
</table>