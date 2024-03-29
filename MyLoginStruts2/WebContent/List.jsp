<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<s:head/>
<link rel="stylesheet" href="css/css.css" type="text/css"/>
</head>

<body>
<h2><s:text name="label.list.title"/></h2><%-- 
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
</table> --%>

<display:table requestURI="list" export="true" name="table" cellpadding="0" cellspacing="0" pagesize="10" sort="list" >
	<tr>
		<td><display:column property="idUser"			title="idUser"			sortable="true" /></td>
		<td><display:column property="userName"			title="userName"		sortable="true" 	/></td>
		<td><display:column property="name"				title="name"			sortable="true" 	/></td>
		<td><display:column property="surname"			title="surname"			sortable="true" /></td>
		<td><display:column property="password"			title="password"		sortable="true" 	/></td>
		<td><display:column property="wrongTriesCount"	title="wrongTriesCount"	sortable="true" /></td>
		<td><display:column property="email"			title="email"			sortable="true" autolink="true"/></td>
		<td><display:column property="phone"			title="phone"			sortable="true" /></td>
		<td><display:column property="mobilePhone"		title="mobilePhone"		sortable="true" /></td>
		<td><display:column property="birthDay"			title="birthDay"		sortable="true" 	/></td>
		<td><display:column property="registerDay"		title="registerDay"		sortable="true" /></td>
		<td><display:column property="lastLogin"		title="lastLogin"		sortable="true" /></td>
		<td><display:column property="idRole"			title="idRole"			sortable="true" /></td>
	</tr>
</display:table>

</body>
</html>