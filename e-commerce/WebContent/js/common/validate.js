function validateBrand(msg,maxFileSize,formId){
	if(document.brand.radioLogoUrl[1].checked &&
		document.brand.logoImg.value!='' &&
			!checkFileSize('logoImg', maxFileSize)){
		alert(msg);
		return false;
	}
	document.getElementById(formId).submit();
}