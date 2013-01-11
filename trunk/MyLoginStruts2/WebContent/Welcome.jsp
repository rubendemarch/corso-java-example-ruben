<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Welcome</title>
</head>
 
<body>
    <h2>Howdy, <s:property value="userName" />...!</h2>
<s:set name="userName" value="userName"/>
<s:if test="#userName!=null">
    <s:a action="list.action">
    <s:text name="label.list"/>
    </s:a>
</s:if>
</body>
</html>