<%@page import="it.ecommerce.util.constants.Common"%>
<%@include file="../../common/props.jsp"%>

<form action="./ManageBrands" method="post" name="brand" enctype="multipart/form-data">
	<input type="hidden" name="<%=Common.COMMON_ACTION%>" value="<%=Common.ADD%>">
	<input type="hidden" name="<%=Common.CUSTOM_ACTION%>" value="<%=Common.SAVE%>">
	<input type="hidden" name="ext" value="" id="ext"> <!-- L'unico modo per trasportare l'estensione del file -->
	<input type="hidden" name="isVisible" value="1" >
	<input type="hidden" name="isDeleted" value="0" >
<table><tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.labelName")%></label>
	<input	type="text" value="" name="name" maxlength="100" size="50" onkeypress="validateText(event)" onblur="cleanText(this)">
	</td></tr><tr><td colspan="2">
	<label><%=rb.getString("manage.brand.page.labelUrl")%></label>
	<input	type="url" value="" name="url" maxlength="150" size="50" onkeypress="validateUrl(event)" onblur="cleanUrl(this)">
	</td></tr><tr><td>
	<input type="radio" name="radioLogoUrl" checked="checked" onchange='manageRadio("logoUrl","logoImg")' value="url">
	<label><%=rb.getString("manage.brand.page.labelLogoUrl")%></label>
	<input	type="url" value="" name="logoUrl" id="logoUrl" maxlength="150" size="50" onkeypress="validateUrl(event)" onblur="cleanUrl(this)">
	</td><td>
	<input type="radio" name="radioLogoUrl" onchange='manageRadio("logoImg","logoUrl")' value="image">
	<input	type="file" name="logoImg" id="logoImg" disabled="disabled" accept="image/*">
	</td></tr>
</table>
	<input id="buttonSubmit" type="button" value="<%=rb.getString("common.save")%> <%=rb.getString("manage.brand")%>"
		onmouseup="copyValue('logoImg','ext')"
		onclick="validateBrand('<%=rb.getString("file.size.limit.exceeded") %>',<%=(String)request.getAttribute(Common.maxImageSize) %>,'buttonSubmit')">
</form>
