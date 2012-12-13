function dispatchPage(formId,formActionId,actionId,actionValue){
	document.getElementById(formId+"_"+actionId).value=actionValue;
	document.getElementById(formId).action=formActionId;
	document.getElementById(formId).submit();
}