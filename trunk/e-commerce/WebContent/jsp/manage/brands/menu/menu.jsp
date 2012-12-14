<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../../common/props.jsp" %>
<table>
	<tr>
		<th>
		<button onclick="loadActionAndSubAction('./ManageBrands','<%=Common.ADD%>')">
		<%=rb.getString("subMenu.brand.insert") %>
		</button></th>
		<th><button onclick="loadActionAndSubAction('./ManageBrands','<%=Common.LIST%>')">
		<%=rb.getString("subMenu.brand.list") %>
		</button></th>
	</tr>
</table>

<!-- <jsp:include page="../../../common/form/form.jsp">
<jsp:param value="subMenu" name="FORM_ID"/>
</jsp:include>  E' CENTRALIZZATO-->
