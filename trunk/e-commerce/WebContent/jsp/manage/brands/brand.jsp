<!-- Questa jsp sostituisce e unifica insertBrand, detailBrand e modifyBrand -->

<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
HashMap<String,Object>brand=(HashMap<String,Object>)request.getAttribute("brand");
boolean isVisible=false;
boolean isDeleted=false;
if(!brand.isEmpty()){
isVisible =((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0;
isDeleted =((BigDecimal)brand.get("IS_DELETED")).intValue()>0;
}
String action = (String) request.getAttribute(Common.ACTION);

String disable=(Common.DETAIL.equals(action))?" disabled='disabled' ":"";
%>

<form action="./ManageBrands" method="post" name="brand" enctype="multipart/form-data">
	<input type="hidden" name="<%=Common.COMMON_ACTION%>" value="<%=action%>">
	<input type="hidden" name="<%=Common.CUSTOM_ACTION%>" value="<%=Common.SAVE%>">
	<input type="hidden" name="ext" value="" id="ext"><!-- L'unico modo per trasportare l'estensione del file -->
	<input type="hidden" name="idColumnValue" value="<%=(String)brand.get("ID_BRAND")%>">
	<input type="hidden" name="oldLogoUrl" value="<%=(String)brand.get("LOGO_URL")%>" >
	<input type="hidden" name="commonId" value="<%=(String)brand.get("ID_BRAND")%>">

<table><tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.labelName")%></label>
	<input	type="text" name="name" value="<%=brand.get("NAME")!=null?(String)brand.get("NAME"):""%>" maxlength="100" size="50" onkeypress="validateText(event)" onblur="cleanText(this)" <%=disable%>>
	</td></tr>
	
	<tr><td colspan="2">
<%if (!Common.DETAIL.equals(action)){%>
	<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
	<input	type="url" name="url" value="<%=brand.get("URL")!=null?(String)brand.get("URL"):""%>" maxlength="150" size="50" onkeypress="validateUrl(event)" onblur="cleanUrl(this)">
<%} else{%>
	<a href="<%=(String)brand.get("URL")%>"> <%=rb.getString("manage.brand.page.labelUrl")%></a>
<%}%>
	</td></tr>
	
	<tr>
<%if (!Common.DETAIL.equals(action)){%>	
	<td>
	<input type="radio" name="radioLogoUrl" checked="checked" onchange='manageRadio("logoUrl","logoImg")' value="url">
	<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
	<input	type="url" value="<%=brand.get("LOGO_URL")!=null?(String)brand.get("LOGO_URL"):""%>" name="logoUrl" id="logoUrl" maxlength="150" size="50" onkeypress="validateUrl(event)" onblur="cleanUrl(this)">
	</td><td>
	<input type="radio" name="radioLogoUrl" onchange='manageRadio("logoImg","logoUrl")' value="image">
	<input	type="file" name="logoImg" id="logoImg" disabled="disabled" accept="image/*">
	</td>
<%} else{%>
	<td colspan="2">
	<a href="<%=(String)brand.get("LOGO_URL")%>"> <%=rb.getString("manage.brand.page.labelLogoUrl")%></a>
	</td>
<%}%>
	</tr>
	
<%if (!Common.ADD.equals(action)){%>
	<tr><td>
	<%=rb.getString("manage.brand.page.IS_VISIBLE")%>&nbsp;
	</td><td>
	<input	type="radio" value="1" name="isVisible" <%if(isVisible){ %> checked <%} %> <%=disable%>>
	<%=rb.getString("common.yes")%>
	<input	type="radio" value="0" name="isVisible" <%if(!isVisible){ %> checked <%} %> <%=disable%>>
	<%=rb.getString("common.no")%>&nbsp;
	</td></tr>
	<tr><td >
	<%=rb.getString("manage.brand.page.IS_DELETED")%>&nbsp;
	</td><td>
	<input	type="radio" value="1" name="isDeleted"	<%if(isDeleted){ %> checked <%} %> 	<%=disable%>>
	<%=rb.getString("common.yes")%>
	<input	type="radio" value="0" name="isDeleted" <%if(!isDeleted){ %> checked <%} %> <%=disable%>>
	<%=rb.getString("common.no")%>&nbsp;
	</td></tr>
<%} else{%>
	<tr><td>
	<input type="hidden" name="isVisible" value="1" >
	</td><td>
	<input type="hidden" name="isDeleted" value="0" >
	</td></tr>
<%} %>
	
<%-- MY VERSION <tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.IS_VISIBLE")%></label>
	<input	type="checkbox"
		<%if(((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0){ %> checked <%} %>
		name="isVisible">
	</td></tr>
	<tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.IS_DELETED")%></label>
	<input	type="checkbox"
		<%if(((BigDecimal)brand.get("IS_DELETED")).intValue()>0){ %> checked <%} %>
		name="isDeleted">
	</td></tr> --%>
</table>
<%if (!Common.DETAIL.equals(action)){%>
	<input id="buttonSubmit" type="button" value="<%=rb.getString("common.save")%> <%=rb.getString("manage.brand")%>"
	onmouseup="copyValue('logoImg','ext')"
	onclick="validateBrand('<%=rb.getString("file.size.limit.exceeded") %>',<%=(String)request.getAttribute(Common.maxImageSize) %>,'buttonSubmit')">

<%} else{%>
	<button onclick="loadAll('./ManageBrands','<%=Common.MODIFY%>','<%=(String)brand.get("ID_BRAND")%>')">
		<%=rb.getString("manage.brand.page.modify") %>
	</button>
<%} %>
</form>
