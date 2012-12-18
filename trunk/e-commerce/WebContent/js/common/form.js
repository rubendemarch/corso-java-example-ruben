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
	document.getElementById('commonAction').value=commonActionValue;
	document.getElementById('commonId').value=commonIdValue;
	document.getElementById('commonForm').action=commonFormActionValue;
	document.getElementById('commonForm').submit();
}

function validateText(evt){
	var regex = /[0-9]|[a-z]|[A-Z]|\ /;
	validate(evt,regex);
}

function validateNumber(evt){
	var regex = /[0-9]/;
	validate(evt,regex);
}

function validateUrl(evt){
	var regex = /[0-9]|[a-z]|[A-Z]|\:|\/|\.|\?|\&|\=|\#|\-|\_|\%/;
	validate(evt,regex);
}

function validate(evt,regex){
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault){
			theEvent.preventDefault();
		}
	}
}

function cleanText(field){
	var regex = /[^a-zA-Z0-9\ ]/g;
	field.value=clean(field.value,regex);
}
function cleanUrl(field){
	var regex = /[^a-zA-Z0-9\ \:\.\?\=\&\°\_\-\/]/g;
	field.value=clean(field.value,regex);
}
function cleanNumber(field){
	var regex = /[^0-9]/g;
	field.value=clean(field.value,regex);
}
function clean(string,regex){
	return string.replace(regex,'');
}