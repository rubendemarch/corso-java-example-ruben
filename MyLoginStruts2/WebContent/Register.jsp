<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<s:head/>
</head>
 
<body>

<h2><s:text name="label.register.title"/></h2>
<s:actionerror />
<s:form action="register.action" method="post">
<!-- la action è quella che vedremo nell'url -->
    <s:textfield name="userName" key="label.reg.username" size="20" />
    <s:password name="password" key="label.reg.password" size="20" />
    <s:password name="passwordConfirm" key="label.reg.passwordConfirm" size="20" />
    <s:submit method="execute" key="label.register" align="center"/>
</s:form>
</body>
</html>