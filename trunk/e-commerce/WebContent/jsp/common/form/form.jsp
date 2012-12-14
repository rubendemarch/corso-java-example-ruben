<%-- <!-- <%@page import="it.ecommerce.util.constants.Common"%> NON SERVE + --> --%>
<%--  TANTO E'  UN FORM PER TUTTI <%
String formId =request.getParameter(Common.FORM_ID);
%> --%>
<%--  LA VECCHIA VERSIONE
<form name="<%=formId %>"
	action=""
	method="post"
	id="<%=formId%>">
<input type="hidden"
	name="<%=Common.ACTION%>"
	value=""
	id="<%=formId %>_<%=Common.ACTION%>">
</form>
--%>
<form name="commonForm" action="" method="post" id="commonForm">
<input type="hidden" name="commonAction" value="" id="commonAction">
<input type="hidden" name="commonId" value="" id="commonId">
</form>