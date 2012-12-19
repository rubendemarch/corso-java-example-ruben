function validateBrand(msg,maxFileSize,buttonId){
	if(document.brand.radioLogoUrl[1].checked &&
		document.brand.logoImg.value!='' &&
			!checkFileSize('logoImg', maxFileSize)){
		alert(msg);
		return false;
	}
	document.getElementById(buttonId).type="submit";
}