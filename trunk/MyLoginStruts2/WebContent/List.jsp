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
<table border="1">
	<s:iterator value="table" >
		<tr>
			
			<td><s:property value="idUser"/></td>
			<td><s:property value="userName"/></td>
			<td><s:property value="name"/></td>
			<td><s:property value="surname"/></td>
			<td><s:property value="password"/></td>
			<td><s:property value="wrongTriesCount"/></td>
			<td><s:property value="email"/></td>
			<td><s:property value="phone"/></td>
			<td><s:property value="mobilePhone"/></td>
			<td><s:property value="birthDay"/></td>
			<td><s:property value="registerDay"/></td>
			<td><s:property value="lastLogin"/></td>
			<td><s:property value="idRole"/></td>
			
		</tr>
	</s:iterator>
</table>

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

</body>
</html>