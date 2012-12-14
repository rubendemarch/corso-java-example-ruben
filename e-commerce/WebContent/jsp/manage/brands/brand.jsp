<%@page import="it.ecommerce.util.constants.Common"%>
<%
	String action = (String) request.getAttribute(Common.ACTION);
%>
<jsp:include page="menu/menu.jsp"></jsp:include>
<%
	if (Common.ADD.equals(action)) {
%>
<jsp:include page="insertBrand.jsp"></jsp:include>
<%
	} else if (Common.LIST.equals(action)) {
%>
<jsp:include page="brandList.jsp" />
<%
	} else if (Common.DETAIL.equals(action)) {
%>
<jsp:include page="detailBrand.jsp" />
<%
	}
%>
