/*questo è vecchio
function dispatchPage(formId,formActionId,actionId,actionValue){
	document.getElementById(formId+"_"+actionId).value=actionValue;
	document.getElementById(formId).action=formActionId;
	document.getElementById(formId).submit();
}*/

function loadAction(commonFormActionValue){
	loadActionAndSubAction(commonFormActionValue,'');
}

function loadActionAndSubAction(commonFormActionValue,commonActionValue){
	loadAll(commonFormActionValue,commonActionValue,'');
}

function loadAll(commonFormActionValue,commonActionValue,commonIdValue){
	document.getElementById('commonAction').value=commonFormActionValue;
	document.getElementById('commonId').value=commonIdValue;
	document.getElementById('commonForm').action=commonFormActionValue;
	document.getElementById('commonForm').submit();
}