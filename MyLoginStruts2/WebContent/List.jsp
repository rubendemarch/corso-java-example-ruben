<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<s:head/>
</head>
 
<body>

<h2><s:text name="label.list.title"/></h2>
<table>
	<s:iterator value="table" var="row">
		<tr>
			<s:iterator value="row" id="cell">
				<td><s:property value=""/></td>
			</s:iterator>
		</tr>
	</s:iterator>
</table>
<table>
	<display:table pagesize="10" name="table">
		<tr>
			<td><display:column property="idUser"/></td>
			<td><display:column property="userName"/></td>
			<td><display:column property="name"/></td>
			<td><display:column property="surname"/></td>
			<td><display:column property="password"/></td>
			<td><display:column property="wrongTriesCount"/></td>
			<td><display:column property="email"/></td>
			<td><display:column property="phone"/></td>
			<td><display:column property="mobilePhone"/></td>
			<td><display:column property="birthDay"/></td>
			<td><display:column property="registerDay"/></td>
			<td><display:column property="lastLogin"/></td>
			<td><display:column property="idRole"/></td>
		</tr>
	</display:table>
</table>
</body>
</html>