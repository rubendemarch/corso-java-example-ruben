<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
<html>
<head>
<title><s:text name="label.pageTitle"/></title>
<s:head/>
<sd:head/>
<script type="text/javascript">
window.onload = function() {document.getElementsByName("dojo.birthDate")[0].setAttribute("readOnly","true");};
</script>
</head>
 
<body>

<h2><s:text name="label.register.title"/></h2>
<s:actionerror />
<s:form action="register.action" method="post">
	<s:textfield name="name" key="label.name" size="20" required="true"/>
 	<s:textfield name="surname" key="label.surname" size="20"  required="true"/>
	<s:textfield name="email" key="label.eMail" size="20" required="true"/>
	<s:textfield name="phone" key="label.phone" size="20" />
	<s:textfield name="mobilePhone" key="label.mobilePhone" size="20" />
	<sd:datetimepicker name="birthDate" key="label.birthDate" weekStartsOn="1" toggleType="wipe" toggleDuration="800" displayFormat="%{getText('date.pattern')}" />
	<!-- se toggleDuration>=1000 non funge più l'effetto speciale -->
    <s:textfield name="userName" key="label.reg.username" size="20" required="true"/>
    <s:password name="password" key="label.reg.password" size="20" required="true"/>
    <s:password name="passwordConfirm" key="label.reg.passwordConfirm" size="20" required="true"/>
    <s:submit method="execute" key="label.register" align="center"/>
</s:form>
</body>
</html>