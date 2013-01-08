<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- import della tag library associata alla lettera s,
 in tal caso delle librerie di struts -->
 
<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<s:head/>
</head>
 
<body>

<h2><s:text name="label.title"/></h2>
<s:actionerror /><!-- verrà rimpiazzato da un messaggio d'errore nel caso -->
<s:form action="login.action" method="post">
<!-- la action è quella che vedremo nell'url -->
    <s:textfield name="userName" key="label.username" size="20" />
    <s:password name="password" key="label.password" size="20" />
    <s:submit key="label.login" align="center" /> <!-- non serve scrivere dentro  method="execute" --> 
</s:form>
</body>
</html>