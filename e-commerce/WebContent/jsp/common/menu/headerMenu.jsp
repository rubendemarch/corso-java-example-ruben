<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../props.jsp" %>
<table>
	<tr>
		<th><button onclick="loadAction('./ManageLanguages')">
			<%=rb.getString("menu.language")%>
		</button></th>
		<th><button onclick="loadActionAndSubAction('./ManageBrands','<%=Common.LIST%>')">
	 		<%=rb.getString("menu.brand")%>
		</button></th>
	</tr>
</table>

<!-- <jsp:include page="../form/form.jsp">
<jsp:param value="menu" name="FORM_ID"/>
</jsp:include>  E' CENTRALIZZATO-->
