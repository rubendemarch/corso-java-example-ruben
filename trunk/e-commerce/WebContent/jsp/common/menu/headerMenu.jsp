<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../props.jsp" %>
<table>
	<tr>
		<th><button onclick="dispatchPage('menu','./ManageLanguages','<%=Common.ACTION%>','')">
			<%=rb.getString("menu.language")%>
		</button></th>
		<th><button onclick="dispatchPage('menu','./ManageBrands','<%=Common.ACTION%>','<%=Common.LIST%>')">
	 		<%=rb.getString("menu.brand")%>
		</button></th>
	</tr>
</table>

<jsp:include page="../form/form.jsp">
<jsp:param value="menu" name="FORM_ID"/>
</jsp:include>
