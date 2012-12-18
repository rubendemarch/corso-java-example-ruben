<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>
<%
HashMap<String,Object>brand=(HashMap<String,Object>)request.getAttribute("brand");
boolean isVisible =((BigDecimal)brand.get("IS_VISIBLE")).intValue()>0;
boolean isDeleted =((BigDecimal)brand.get("IS_DELETED")).intValue()>0;
%>
<form action="./ManageBrands" method="post" name="brand" enctype="multipart/form-data">
	<input type="hidden" name="<%=Common.COMMON_ACTION%>" value="<%=Common.MODIFY%>">
	<input type="hidden" name="<%=Common.CUSTOM_ACTION%>" value="<%=Common.SAVE%>">
	<input type="hidden" name="ext" value="" id="ext"><!-- L'unico modo per trasportare l'estensione del file -->
	<input type="hidden" name="idColumnValue" value="<%=(String)brand.get("ID_BRAND")%>">
	<input type="hidden" value="<%=(String)brand.get("LOGO_URL")%>" name="oldLogoUrl">
	<input type="hidden" value="<%=(String)brand.get("ID_BRAND")%>" name="commonId">

<table><tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.labelName")%></label>
	<input	type="text" value="<%=(String)brand.get("NAME")%>" name="name" maxlength="100" size="50" onkeypress="validateText(event)" onblur="cleanText(this)">
	</td></tr><tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
	<input	type="url" value="<%=(String)brand.get("URL")%>" name="url" maxlength="150" size="50" onkeypress="validateUrl(event)" onblur="cleanUrl(this)">
	</td></tr><tr><td>
	<input type="radio" name="radioLogoUrl" checked="checked" onchange='manageRadio("logoUrl","logoImg")' value="url">
	<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
	<input	type="url" value="<%=(String)brand.get("LOGO_URL")%>" name="logoUrl" id="logoUrl" maxlength="150" size="50" onkeypress="validateUrl(event)" onblur="cleanUrl(this)">
	</td><td>
	<input type="radio" name="radioLogoUrl" onchange='manageRadio("logoImg","logoUrl")' value="image">
	<input	type="file" name="logoImg" id="logoImg" disabled="disabled" accept="image/*">
	</td></tr>
	
	<tr><td>
	<%=rb.getString("manage.brand.page.IS_VISIBLE")%>&nbsp;
	</td><td>
	<input	type="radio" value="1" name="isVisible" <%if(isVisible){ %> checked <%} %>>
	<%=rb.getString("common.yes")%>
	<input	type="radio" value="0" name="isVisible" <%if(!isVisible){ %> checked <%} %>>
	<%=rb.getString("common.no")%>&nbsp;
	</td></tr>
	<tr><td >
	<%=rb.getString("manage.brand.page.IS_DELETED")%>&nbsp;
	</td><td>
	<input	type="radio" value="1" name="isDeleted"<%if(isDeleted){ %> checked <%} %>>
	<%=rb.getString("common.yes")%>
	<input	type="radio" value="0" name="isDeleted" <%if(!isDeleted){ %> checked <%} %>>
	<%=rb.getString("common.no")%>&nbsp;
	</td></tr>
	
	
<!-- MY VERSION <tr><td colspan="2">
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
	</td></tr> -->
</table>
	<input	type="submit" 
		value="<%=rb.getString("common.update")%> <%=rb.getString("manage.brand")%>"
		onmouseup="copyValue('logoImg','ext')">
		
</form>
