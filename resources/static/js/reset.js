/**
 * 数量をリセットする
 */

 function resets(){	
 	var elements = document.getElementsByClassName("target");
 	var customerIdElement = document.getElementById("txt_custId");
 	var customerNameElement = document.getElementById("lbl_custNm");
 	for(var i = 0 ; i < elements.length ; i++){
 		elements[i].selected = true;
 	}
 	customerIdElement.value = "";
 	customerNameElement.innerHTML = "様";
 }