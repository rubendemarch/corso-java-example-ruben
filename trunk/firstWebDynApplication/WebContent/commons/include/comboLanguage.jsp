<%@page import="it.alfasoft.corso.java.util.constants.Request"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%
List<Locale> managedLanguages =
	(List<Locale>)request.getAttribute(Request.managedLanguages);
Locale locale= request.getAttribute(Request.LOCALE);
%>
<form name="chooseLanguage" action="./ManageLanguage">
<!-- "./" fa la chiamata direttamente alla stessa appl.web -->
<select name="language" onchange="submit()">
<%
for(Locale l:managedLanguages){
%>
<option 
value="<%=l %>" 
class="lang_<%=l %>" 
<%if(l.equals(locale)){	%>selected="selected"<%}%>
>
<%=l.getDisplayLanguage() %>
</option>
<%
}
%>
</select>
</form>