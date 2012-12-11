/**
 * 
 */
function manageRadio(idAbilitare,idDisabilitare){
	document.getElementById(idAbilitare).disabled=false;
	document.getElementById(idDisabilitare).value="";
	document.getElementById(idDisabilitare).disabled=true;
}